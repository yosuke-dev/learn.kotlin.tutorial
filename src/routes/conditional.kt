package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.conditionalRoute() {
    get("/conditional") {
        val ifExample = ifExample(100)
        val whenExample = whenExample(99)

        call.respondText("ifExample:$ifExample, whenExample:$whenExample", contentType = ContentType.Text.Plain)
    }
}

fun ifExample( num: Int): String {
    return if (num < 100) {
        "Less than 100"
    } else if (num == 100) {
        "Equal to 100"
    } else {
        "Greater than 100"
    }
}

fun whenExample( num: Int): String {
    return when {
        num < 100 -> {
            "Less than 100"
        }
        num == 100 -> {
            "Equal to 100"
        }
        else -> {
            "Greater than 100"
        }
    }
}