package com.example

import com.example.sse.Sse
import com.example.web.WebApp
import org.http4k.server.Helidon
import org.http4k.server.PolyHandler
import org.http4k.server.asServer
import org.http4k.template.HandlebarsTemplates

fun Datastar(): PolyHandler {
    val renderer = HandlebarsTemplates().CachingClasspath()

    return PolyHandler(
        http = WebApp(renderer),
        sse = Sse(renderer)
    )
}

fun main() {
    Datastar().asServer(Helidon(8999)).start()
}
