package dev.kata.mouseeventkata

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


private enum class MouseInternalEvents {
  LeftButtonDown,
  LeftButtonUp,
  Move
}

class ReactiveMouse private constructor() {
  private val eventSubject = PublishSubject.create<MouseInternalEvents>()
  private val subscribers = mutableListOf<Consumer<MouseEventType>>()
  private val subscription = CompositeDisposable()

  fun pressLeftButton() {
    eventSubject.onNext(MouseInternalEvents.LeftButtonDown)
  }

  fun releaseLeftButton() {
    eventSubject.onNext(MouseInternalEvents.LeftButtonUp)
  }

  fun move(from: Position, to: Position) {
    /*... implement this method ...*/
  }

  fun subscribe(subscriber: Consumer<MouseEventType>) {
    subscribers.add(subscriber)
  }

  fun dispose() {
    subscription.dispose()
  }

  private fun subscribeAllWithDelay(delay: Long = 500) {
    subscription.add(
      eventSubject.buffer(eventSubject.debounce(delay, TimeUnit.MILLISECONDS))
        .map(::evaluateInternalEvents)
        .subscribe(::notifySubscribers)
    )
  }

  private fun evaluateInternalEvents(events: List<MouseInternalEvents>): MouseEventType {
    return events
      .count { it == MouseInternalEvents.LeftButtonUp }
      .let {
        when (it) {
          1 -> MouseEventType.SingleClick
          2 -> MouseEventType.DoubleClick
          else -> MouseEventType.TripleClick
        }
      }
  }

  private fun notifySubscribers(eventType: MouseEventType) {
    subscribers.forEach { it.accept(eventType) }
  }

  companion object {
    fun createWithSubscribers(vararg subscribers: Consumer<MouseEventType>, delay: Long = 500): ReactiveMouse {
      return ReactiveMouse()
        .also { instance -> subscribers.forEach(instance::subscribe) }
        .also { it.subscribeAllWithDelay(delay) }
    }
  }
}
