package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.lambdaRoute() {
    get("/lambda") {
        val respondText = StringBuilder()

        val calc: (Int, Int) -> Int = { num1: Int, num2: Int -> num1 + num2 }

        respondText.append("<div>calc(2, 13):${calc(2, 13)}</div>")

        val squared: (Int) -> Int = { it * it }

        respondText.append("<div>squared(3):${squared(3)}</div>")

        val calcResult1 = calcResult(10, 20) { num1, num2 -> num1 + num2 }
        val calcResult2 = calcResult(10, 20) { num1, num2 -> num1 * num2 }

        respondText.append("<div>calcResult1(10+20):$calcResult1</div>")
        respondText.append("<div>calcResult2(10*20):$calcResult2</div>")

        val typealiasResult1 = typealiasResult(10, 20) { num1, num2 -> num1 + num2 }
        val typealiasResult2 = typealiasResult(10, 20) { num1, num2 -> num1 * num2 }

        respondText.append("<div>typealiasResult1(10+20):$typealiasResult1</div>")
        respondText.append("<div>typealiasResult2(10*20):$typealiasResult2</div>")

        respondText.append("<div>Int.square(2):${2.square()}</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

fun calcResult(num1: Int, num2: Int, calc: (Int, Int) -> Int): Int {
    return calc(num1, num2)
}

typealias TypeCalc = (Int, Int) -> Int

fun typealiasResult(num1: Int, num2: Int, calc: TypeCalc): Int {
    return calc(num1, num2)
}

fun Int.square(): Int = this * this
