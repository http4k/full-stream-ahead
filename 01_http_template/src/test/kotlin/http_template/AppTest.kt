package http_template

import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun `can get user list`() {
        val response: Response = app(Request(GET, ""))
        assertThat(response, hasStatus(OK))
    }
}



