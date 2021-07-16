package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.classRoute() {
    get("/class") {
        val dog = Dog("Yosuke")

        call.respondText("<div>showName:${dog.showName()}</div><div>cries:${dog.cries()}</div>", contentType = ContentType.Text.Html)
    }
}

sealed class Animal(private val name: String){
    fun showName(): String {
        return "name is $name"
    }

    open fun cries(): String{
        return ""
    }
}

class Dog(name: String) : Animal(name){
    override fun cries(): String {
        return "bowwow"
    }
}
