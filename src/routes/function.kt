package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.functionRoute() {
    get("/function") {
        val countString = "length"
        val countLength = countLength(countString)
        displayMessage("exec displayMessage")

        call.respondText("countString.length:$countLength", contentType = ContentType.Text.Plain)
    }
}

fun countLength(str: String): Int{
    return str.length
}

fun displayMessage(message: String){
    println(message)
}