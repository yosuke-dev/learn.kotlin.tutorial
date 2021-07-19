package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.StringBuilder

fun Route.coroutineRoute() {
    get("/coroutine") {
        val respondText = StringBuilder()

        runBlocking {
            launch {
                delay(1000L)
                respondText.append("<div>Yosuke.</div>")
            }
            respondText.append("<div>My name is </div>")
        }

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

