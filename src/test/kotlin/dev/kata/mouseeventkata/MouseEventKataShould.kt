package dev.kata.mouseeventkata

import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

internal class MouseEventKataShould {

  @Test
  fun `send SingleClick event when the user press and then release the left button`() {
    val mouseListener = mock<MouseEventListener>()
    val mouse = Mouse.createWithListener(mouseListener)

    runBlocking {
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      delay(600L)
    }
    verify(mouseListener, times(1)).handleMouseEvent(MouseEventType.SingleClick)
  }

  @Test
  fun `send DoubleCLick event when the user press and then release the left button twice`() {
    val mouseListener = mock<MouseEventListener>()
    val mouse = Mouse.createWithListener(mouseListener)

    runBlocking {
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      delay(600L)
    }

    verify(mouseListener, times(1)).handleMouseEvent(MouseEventType.DoubleClick)
  }

  @Test
  fun `send TripleCLick event when the user press and then release the left button three times`() {
    val mouseListener = mock<MouseEventListener>()
    val mouse = Mouse.createWithListener(mouseListener)

    runBlocking {
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      mouse.pressLeftButton(0)
      mouse.releaseLeftButton(0)
      delay(600L)
    }

    verify(mouseListener, times(1)).handleMouseEvent(MouseEventType.TripleClick)
  }
}
