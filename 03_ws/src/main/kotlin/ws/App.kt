package ws

import model.users
import org.http4k.core.Body
import org.http4k.format.Moshi
import org.http4k.format.Moshi.asFormatString
import org.http4k.format.Moshi.json
import org.http4k.routing.ResourceLoader.Companion.Classpath
import org.http4k.routing.poly
import org.http4k.routing.static
import org.http4k.routing.websocket.bind
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse


val app = poly(
    "/ws" bind { req ->
        WsResponse { ws ->
            ws.onMessage {
                if (it.bodyString() == "getUsers") ws.send(WsMessage().json(users))
            }                                                                                       ; req
        }
    },
    static()
)

fun main() {
    app.asServer(Helidon(8000)).start()
}


private fun <T : Any> WsMessage.json(t: T): WsMessage = body(Body(asFormatString(t)))

fun WsMessage() = WsMessage("")

