package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.scopeRoute() {
    get("/scope") {
        val respondText = StringBuilder()

        val oddNumbersWith = with(mutableListOf<Int>()) {
            for (i in 1..10){
                if (i % 2 == 1) add(i)
            }
            this.joinToString(separator = " ")
        }
        respondText.append("<div>oddNumbersWith:$oddNumbersWith</div>")

        val oddNumbersRun = mutableListOf<Int>().run {
            for (i in 1..10){
                if (i % 2 == 1) add(i)
            }
            joinToString(separator = " ")
        }
        respondText.append("<div>oddNumbersRun:$oddNumbersRun</div>")

        val user = User(1, "yosuke")

        respondText.append("<div>getUserString(null, 'new_yosuke'):${getUserString(null, "new_yosuke")}</div>")
        respondText.append("<div>getUserString(user, 'new_yosuke'):${getUserString(user, "new_yosuke")}</div>")

        respondText.append("<div>createUser(null):${createUser(null)}</div>")
        respondText.append("<div>createUser('yosuke'):${createUser("yosuke")}</div>")

        val updateUserApply = updateUserApply(1, "update_yosuke")

        respondText.append("<div>updateUserApply:$updateUserApply</div>")

        val updateUserAlso = updateUserAlso(1, "update_yosuke")

        respondText.append("<div>updateUserAlso:$updateUserAlso</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

data class User(val id: Int, var name: String)

fun getUserString(user: User?, newName: String): String? {
    return user?.run {
        name = newName
        toString()
    }
}

fun createUser(name: String?): User? {
    return name?.let {n -> User(1, n)}
}

fun getUser(id: Int): User {
    // 疑似ユーザー取得
    return User(id, "getUser")
}

fun updateUserApply(id: Int, newName: String): User {
    return getUser(id).apply {
        name = newName
    }
}

fun updateUserAlso(id: Int, newName: String): User {
    return getUser(id).also { u ->
        u.name = newName
    }
}
