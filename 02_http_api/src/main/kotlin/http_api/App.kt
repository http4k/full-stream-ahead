package http_api

import model.users
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Moshi.asFormatString
import org.http4k.format.Moshi.json
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Helidon
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/users" bind GET to { _: Request -> Response(OK).json(users) },
    static()
)

fun main() {
    app.asServer(Helidon(8000)).start()
}



