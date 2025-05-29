package sse

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.startsWith
import org.http4k.core.ContentType
import org.http4k.core.ContentType.Companion.TEXT_EVENT_STREAM
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.lens.accept
import org.http4k.sse.SseClient
import org.http4k.sse.SseMessage
import org.http4k.testing.testSseClient
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun `can get users`() {
        app.testSseClient(Request(GET, "/users").accept(TEXT_EVENT_STREAM))
            .use { ws: SseClient ->
                val message = ws.received().first()
                assertThat(message as SseMessage.Event, equalTo(message))
                assertThat(message.data, startsWith("["))
            }
    }
}



