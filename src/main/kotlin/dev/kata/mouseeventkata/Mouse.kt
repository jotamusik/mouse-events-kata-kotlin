package dev.kata.mouseeventkata

import java.util.Timer
import java.util.TimerTask
import java.util.function.Consumer
import kotlin.concurrent.schedule


class Mouse {
  private val listeners: MutableList<MouseEventListener> = mutableListOf()
  private val timeWindowInMillisecondsForDoubleClick: Long = 500
  private val timer = Timer("DebounceMouse", true)
  private var debounceTask: TimerTask? = null

  private val mouseSignalChain: MutableList<Pair<String, Long>> = mutableListOf()

  fun pressLeftButton(currentTimeInMilliseconds: Long) {
    debounceTask?.cancel()
    mouseSignalChain.add(Pair("LeftButtonDown", currentTimeInMilliseconds))
  }

  fun releaseLeftButton(currentTimeInMilliseconds: Long) {
    mouseSignalChain.add(Pair("LeftButtonUp", currentTimeInMilliseconds))
    debounceTask = timer.schedule(timeWindowInMillisecondsForDoubleClick) {
      evaluateSignalChain()
    }
  }

  private fun evaluateSignalChain() {
    val event = when (mouseSignalChain.filter { it.first == "LeftButtonUp" }.size) {
      1 -> MouseEventType.SingleClick
      else -> MouseEventType.DoubleClick
    }
    notifySubscribers(event)
  }

  fun move(from: Position, to: Position, currentTimeInMilliseconds: Long) {
    /*... implement this method ...*/
  }

  fun subscribe(listener: MouseEventListener) {
    listeners.add(listener)
  }

  private fun notifySubscribers(eventType: MouseEventType) {
    // To be changed to RxKotlin
    listeners.forEach(Consumer { it.handleMouseEvent(eventType) })
  }

  companion object {
    fun createWithListener(mouseEventListener: MouseEventListener): Mouse {
      return Mouse().also { it.subscribe(mouseEventListener) }
    }
  }
}
