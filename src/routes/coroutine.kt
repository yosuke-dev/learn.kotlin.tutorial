package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.StringBuilder

fun Route.coroutineRoute() {
    get("/coroutine") {
        val respondText = StringBuilder()

        GlobalScope.launch {
            delay(1000L)
            respondText.append("<div>Yosuke.</div>")
        }

        respondText.append("<div>My name is </div>")
        Thread.sleep(2000L)

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

