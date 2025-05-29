package datastar

import model.User
import model.users
import org.http4k.core.ContentType.Companion.TEXT_EVENT_STREAM
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.datastar.Fragment
import org.http4k.format.Moshi.json
import org.http4k.lens.datastarFragments
import org.http4k.routing.accepting
import org.http4k.routing.bind
import org.http4k.routing.bindSse
import org.http4k.routing.poly
import org.http4k.routing.static
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.sse.SseResponse
import org.http4k.sse.sendMergeFragments
import org.http4k.template.DatastarFragmentRenderer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.TemplateRenderer
import org.http4k.template.ViewModel
import kotlin.concurrent.thread


data class UserList(val users: List<User>) : ViewModel

val renderer = DatastarFragmentRenderer(HandlebarsTemplates().CachingClasspath())

val app = poly(
    "/users" accepting TEXT_EVENT_STREAM bindSse {
        SseResponse { sse ->
            thread {
                while (true) {
                    sse.sendMergeFragments(renderer(UserList(users)))
                    Thread.sleep(2500)
                }
            }
        }
    },
    "/data" bind { Response(OK).json(users) },
    static()
)

fun main() {
    app.asServer(Helidon(8000)).start()
}
