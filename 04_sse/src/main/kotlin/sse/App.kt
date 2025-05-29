package sse

import model.users
import org.http4k.format.Moshi.json
import org.http4k.routing.poly
import org.http4k.routing.sse.bind
import org.http4k.routing.static
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.sse.SseMessage
import org.http4k.sse.SseResponse

val app = poly(
    "/users" bind {
        SseResponse { sse ->
            sse.send(SseMessage.Event("event").json(users))
            sse.close()
        }
    },
    static()
)

fun main() {
    app.asServer(Helidon(8000)).start()
}


