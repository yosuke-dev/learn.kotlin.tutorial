package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.overloadRoute() {
    get("/overload") {
        val respondText = StringBuilder()

        val num = Num(10) + Num(5)

        respondText.append("<div>num:$num</div>")

        respondText.append("<div>greaterThan:${Num(5) > Num(1)}</div>")
        respondText.append("<div>lessThan:${Num(5) < Num(1)}</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

data class Num(val value: Int) {
    operator fun plus(num: Num): Num {
        return Num(value + num.value)
    }
    operator fun compareTo(num: Num): Int {
        return value.compareTo((num.value))
    }
}