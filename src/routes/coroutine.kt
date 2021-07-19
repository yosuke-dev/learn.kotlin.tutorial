package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.*
import java.lang.StringBuilder

fun Route.coroutineRoute() {
    get("/coroutine") {
        val respondText = StringBuilder()

        runBlocking {
            launch { respondText.append(displayName()) }
            respondText.append("<div>My name is </div>")
        }

        runBlocking {
            val result = async {
                delay(2000L)
                var sum = 0
                for (i in 1..10) {
                    sum += i
                }
                sum
            }
            respondText.append("<div>計算中...</div>")
            respondText.append("<div>sum=${result.await()}</div>")
        }

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

private suspend fun displayName(): String {
    delay(1000L)
    return "<div>Yosuke.</div>"
}
