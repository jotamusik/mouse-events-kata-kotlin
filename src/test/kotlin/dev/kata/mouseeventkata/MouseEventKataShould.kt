package dev.kata.mouseeventkata

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class MouseEventKataShould {

  private fun createMouseWithListener(mouseEventListener: MouseEventListener): Mouse {
    return Mouse().also { it.subscribe(mouseEventListener) }
  }

  @Test
  fun `send SingleClick event when the user press and then release the left button`() {
    val mouseListener = mock<MouseEventListener>()
    val mouse = createMouseWithListener(mouseListener)

    mouse.pressLeftButton(0)
    mouse.releaseLeftButton(0)

    verify(mouseListener).handleMouseEvent(MouseEventType.SingleClick)
  }
}
