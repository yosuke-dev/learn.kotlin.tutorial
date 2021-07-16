package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.interfaceRoute() {
    get("/interface") {
        val interfaceExample = GreeterImpl()

        call.respondText("<div>interfaceExample:${interfaceExample.hello()}</div>", contentType = ContentType.Text.Html)
    }
}

interface Greeter{
    fun hello(): String
}

class GreeterImpl : Greeter{
    override fun hello(): String {
        return "Hello"
    }
}
