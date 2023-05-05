package dev.kata.mouseeventkata

import java.util.function.Consumer


class Mouse {
  private val listeners: MutableList<MouseEventListener> = ArrayList<MouseEventListener>()
  private val timeWindowInMillisecondsForDoubleClick: Long = 500
  fun pressLeftButton(currentTimeInMilliseconds: Long) {
    /*... implement this method ...*/
  }

  fun releaseLeftButton(currentTimeInMilliseconds: Long) {
    /*... implement this method ...*/
  }

  fun move(from: MousePointerCoordinates?, to: MousePointerCoordinates?, currentTimeInMilliseconds: Long) {
    /*... implement this method ...*/
  }

  fun subscribe(listener: MouseEventListener) {
    listeners.add(listener)
  }

  private fun notifySubscribers(eventType: MouseEventType) {
    listeners.forEach(Consumer<MouseEventListener> { listener: MouseEventListener ->
      listener.handleMouseEvent(
        eventType
      )
    })
  }
}
