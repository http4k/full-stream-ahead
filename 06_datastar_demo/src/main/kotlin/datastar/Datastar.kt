package datastar

import datastar.sse.SseApp
import datastar.web.WebApp
import dev.forkhandles.time.executors.SimpleScheduler
import org.http4k.core.PolyHandler
import org.http4k.routing.poly
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates

fun DatastarApp(scheduler: SimpleScheduler = loomScheduler()): PolyHandler {
    val renderer = HandlebarsTemplates().CachingClasspath()
    val users = Users()

    return poly(
        SseApp(users, renderer, scheduler),
        WebApp(users, renderer)
    )
}

fun main() {
    DatastarApp().asServer(Helidon(8999)).start()
}
