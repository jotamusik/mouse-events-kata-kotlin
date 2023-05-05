package dev.kata.mouseeventkata

import java.util.function.Consumer


class Mouse {
  private val listeners: MutableList<MouseEventListener> = mutableListOf()
  private val timeWindowInMillisecondsForDoubleClick: Long = 500

  private val mouseSignalChain: MutableList<Pair<String, Long>> = mutableListOf()

  fun pressLeftButton(currentTimeInMilliseconds: Long) {
    mouseSignalChain.add(Pair("LeftButtonDown", currentTimeInMilliseconds))
  }

  fun releaseLeftButton(currentTimeInMilliseconds: Long) {
    mouseSignalChain.add(Pair("LeftButtonUp", currentTimeInMilliseconds))
    evaluateSignalChain()
  }

  private fun evaluateSignalChain() {
    notifySubscribers(MouseEventType.SingleClick)
  }

  fun move(from: Position, to: Position, currentTimeInMilliseconds: Long) {
    /*... implement this method ...*/
  }

  fun subscribe(listener: MouseEventListener) {
    listeners.add(listener)
  }

  private fun notifySubscribers(eventType: MouseEventType) {
    // To be change to RxKotlin
    listeners.forEach(Consumer<MouseEventListener> { listener: MouseEventListener ->
      listener.handleMouseEvent(
        eventType
      )
    })
  }
}
