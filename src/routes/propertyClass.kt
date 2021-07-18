package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.propertyClassRoute() {
    get("/propertyClass") {
        val respondText = StringBuilder()

        val user1 = User1()
        user1.name = "yosuke"
        respondText.append("<div>user1.name:${user1.name}</div>")

        val user2 = User2(1)
        // user2.id = 2 // valで宣言しているため、setterが生成されずコンパイルエラー
        user2.name = "yosuke2"
        respondText.append("<div>user2.id:${user2.id}</div>")
        respondText.append("<div>user2.name:${user2.name}</div>")

        val user3 = User3()
        user3.name = "yosuke3"
        respondText.append("<div>user3.name:${user3.name}</div>")

        val user4 = User4()
        user4.name = ""
        respondText.append("<div>user4.isValidName:${user4.isValidName}</div>")
        user4.name = "yosuke4"
        respondText.append("<div>user4.isValidName:${user4.isValidName}</div>")

        val user5 = User5()
        user5.name = ""
        respondText.append("<div>user5.isValidName:${user5.name}</div>")
        user5.name = "yosuke5"
        respondText.append("<div>user5.isValidName:${user5.name}</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

private class User1 {
    var name: String = ""
}

private class User2(val id: Int){
    var name: String = ""
}

private class User3 {
    lateinit var name: String
}

private class User4 {
    lateinit var name: String
    val isValidName: Boolean
        get() = name != ""
}

private class User5 {
    var name: String = ""
        set(value) {
            field = if(value == ""){
                "Kotlin"
            }else{
                value
            }
        }
}
