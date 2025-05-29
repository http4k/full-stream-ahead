package http_template

import model.User
import model.users
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel

typealias HttpHandler = (Request) -> Response

data class Index(val users: List<User>) : ViewModel

val templates = HandlebarsTemplates().CachingClasspath()

val app: HttpHandler = { _: Request ->
    Response(OK).body(templates(Index(users)))
}

fun main() {
    app.asServer(Helidon(8000)).start()
}



