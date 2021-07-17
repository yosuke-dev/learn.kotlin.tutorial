package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.dataClassRoute() {
    get("/dataClass") {
        val respondText = StringBuilder()

        val user = DataClass(1, "yosuke")
        user.name = "Kotlin"
        respondText.append("<div>user.name:${user.name}</div>")

        val same = DataClass(1, "Kotlin")
        val other = DataClass(2, "yosuke")

        respondText.append("<div>same:${user == same}</div>")
        respondText.append("<div>other:${user == other}</div>")

        respondText.append("<div>user.hashCode:${user.hashCode()}</div>")
        respondText.append("<div>same.hashCode:${same.hashCode()}</div>")
        respondText.append("<div>other.hashCode:${other.hashCode()}</div>")

        val set = hashSetOf(user)

        respondText.append("<div>same.contains:${set.contains(same)}</div>")
        respondText.append("<div>other.hashCode:${set.contains(other)}</div>")

        respondText.append("<div>user.toString:$user</div>")

        respondText.append("<div>user.component1:${user.component1()}</div>")
        respondText.append("<div>user.component2:${user.component2()}</div>")

        val updated = user.copy(name = "updateName")

        respondText.append("<div>updated.component2:$updated</div>")

        respondText.append("<div>user.isValidName:${user.isValidName}</div>")

        val default = DataClass(1)

        respondText.append("<div>default.name:${default.name}</div>")

        val default2 = DataClass2(id = 1, age = 30)

        respondText.append("<div>default2:$default2</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

data class DataClass(val id: Int, var name: String = "") {
    val isValidName: Boolean
        get() = name != ""
}

data class DataClass2(val id: Int, var name: String = "", val age: Int) {
    val isValidName: Boolean
        get() = name != ""
}
