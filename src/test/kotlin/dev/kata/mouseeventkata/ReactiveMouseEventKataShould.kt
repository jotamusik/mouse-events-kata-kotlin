package dev.kata.mouseeventkata

import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

internal class ReactiveMouseEventKataShould {

  private val delay = 300L
  private val waitUntil = delay + 50L

  private val mockMouseSubscriber = mock<Consumer<MouseEventType>>()
  private val sut = ReactiveMouse.createWithSubscribers(subscribers = arrayOf(mockMouseSubscriber), delay = delay)

  @Test
  fun `send SingleClick event when the user press and then release the left button`() {
    runBlocking {
      sut.pressLeftButton()
      sut.releaseLeftButton()
      delay(waitUntil)
    }

    verify(mockMouseSubscriber, times(1)).accept(MouseEventType.SingleClick)

    sut.dispose()
  }

  @Test
  fun `send DoubleCLick event when the user press and then release the left button twice`() {
    runBlocking {
      sut.pressLeftButton()
      sut.releaseLeftButton()
      sut.pressLeftButton()
      sut.releaseLeftButton()
      delay(waitUntil)
    }

    verify(mockMouseSubscriber, times(1)).accept(MouseEventType.DoubleClick)

    sut.dispose()
  }

  @Test
  fun `send TripleCLick event when the user press and then release the left button three times`() {
    runBlocking {
      sut.pressLeftButton()
      sut.releaseLeftButton()
      sut.pressLeftButton()
      sut.releaseLeftButton()
      sut.pressLeftButton()
      sut.releaseLeftButton()
      delay(waitUntil)
    }

    verify(mockMouseSubscriber, times(1)).accept(MouseEventType.TripleClick)

    sut.dispose()
  }
}
