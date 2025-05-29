package ws

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.startsWith
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.testing.testWsClient
import org.http4k.websocket.WsClient
import org.http4k.websocket.WsMessage
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun `can get users`() {
        app.testWsClient(Request(GET, "/ws")).use { ws: WsClient ->
            ws.send(WsMessage("getUsers"))
            assertThat(ws.received().first().bodyString(), startsWith("["))
        }
    }
}
