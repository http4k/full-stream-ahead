package com.example.web

import com.example.web.endpoints.index
import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.then
import org.http4k.filter.ServerFilters.CatchAll
import org.http4k.lens.BiDiBodyLens
import org.http4k.routing.ResourceLoader
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.template.TemplateRenderer
import org.http4k.template.ViewModel
import org.http4k.template.viewModel

fun WebApp(renderer: TemplateRenderer): RoutingHttpHandler {
    val viewLens = Body.viewModel(renderer, TEXT_HTML).toLens()
    return CatchAll().then(
        routes(
            static(ResourceLoader.Classpath("public")),
            index(viewLens)
        )
    )
}
