package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.nullsafetyRoute() {
    get("/nullsafety") {
        //val str1:String = null // Null非許容 コンパイルエラーになることを確認

        val str2:String? = null // Null許容
        //val str2 = "Yosuke" // valへの変更なので コンパイルエラーになることを確認

        var str3: String? = null
        str3 = "Yosuke"

        call.respondText("str2:$str2 str3:$str3", contentType = ContentType.Text.Plain)
    }
}