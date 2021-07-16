package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.loopRoute() {
    get("/loop") {
        val whileExample = whileExample()
        val forExample = forExample()
        val forUntilStepExample = forUntilStepExample()
        val forEachExample = forEachExample()

        call.respondText("<div>whileExample:$whileExample</div><div>forExample:$forExample</div><div>forUntilStepExample:$forUntilStepExample</div><div>forEachExample:$forEachExample</div>", contentType = ContentType.Text.Html)
    }
}

fun whileExample(): String {
    var i = 1
    val result = StringBuilder()

    while(i < 10){
        result.append("i is $i")
        i++
    }

    return result.toString()
}

fun forExample(): String {
    val result = StringBuilder()

    for(i in 1..10){
        result.append("i is $i")
    }

    return result.toString()
}

fun forUntilStepExample(): String {
    val result = StringBuilder()

    for(i in 1 until 10 step 2){
        result.append("i is $i")
    }

    return result.toString()
}

fun forEachExample(): String {
    val result = StringBuilder()
    val list = listOf(1,2,5,6,10)

    for(i in list){
        result.append("i is $i")
    }

    return result.toString()
}