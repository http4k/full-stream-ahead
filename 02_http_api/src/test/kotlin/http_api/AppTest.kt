package http_api

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.startsWith
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun `can get index`() {
        val response: Response = app(Request(GET, "/"))
        assertThat(response, hasStatus(OK).and(hasBody(startsWith("<!DOCTYPE html>"))))
    }

    @Test
    fun `can get users`() {
        val response: Response = app(Request(GET, "/users"))
        assertThat(response, hasStatus(OK).and(hasBody(startsWith("["))))
    }
}
