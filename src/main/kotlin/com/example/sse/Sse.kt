package com.example.sse

import com.example.sse.endpoints.BadApples
import com.example.sse.endpoints.ClickToEdit
import org.http4k.routing.sse
import org.http4k.template.TemplateRenderer
import org.http4k.template.ViewModel

fun Sse(renderer: TemplateRenderer) = sse(
        ClickToEdit({ it: ViewModel ->
            renderer(it).replace("\n", "")
        }),
        BadApples()
    )
