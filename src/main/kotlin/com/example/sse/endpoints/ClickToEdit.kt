package com.example.sse.endpoints

import com.example.domain.User
import com.example.sse.endpoints.Foo.datastar
import org.http4k.core.Method.GET
import org.http4k.core.Method.PATCH
import org.http4k.core.Method.PUT
import org.http4k.core.Request
import org.http4k.format.Moshi
import org.http4k.format.Moshi.auto
import org.http4k.format.Moshi.json
import org.http4k.lens.Query
import org.http4k.lens.urlEncoded
import org.http4k.routing.RoutingSseHandler
import org.http4k.routing.sse
import org.http4k.routing.sse.bind
import org.http4k.template.DatastarSse
import org.http4k.template.DatastarSseResponse.MergeFragments
import org.http4k.template.TemplateRenderer
import org.http4k.template.ViewModel

val initial = User(1, "Joe", "Bloggs", "joe@bloggs.com")

fun ClickToEdit(renderer: TemplateRenderer): RoutingSseHandler {
    var contact = initial

    return "/examples/click_to_edit/contact/{id}" bind sse(
        "/" bind sse(
            GET to DatastarSse(renderer) {
                MergeFragments(DisplayUser(contact))
            },
            PUT to DatastarSse(renderer) {
                contact = it.datastar<User>()
                MergeFragments(DisplayUser(it.datastar<User>()))
            }
        ),
        "edit" bind sse(GET to DatastarSse(renderer) {
            MergeFragments(EditUser(contact))
        }),
        "reset" bind sse(PATCH to DatastarSse(renderer) {
            contact = initial
            MergeFragments(DisplayUser(contact))
        })
    )
}

data class EditUser(val user: User) : ViewModel {
    val userString = Moshi.asFormatString(user)
}

data class DisplayUser(val user: User) : ViewModel

object Foo {
    inline fun <reified T : Any> Request.datastar() = when (method) {
        GET -> Query.urlEncoded().auto<T>().required("datastar")(this)
        else -> json<T>()
    }
}
