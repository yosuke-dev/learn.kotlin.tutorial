package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.StringBuilder

fun Route.collectionLibraryRoute() {
    get("/collectionLibrary") {
        val respondText = StringBuilder()

        val list = listOf(1, 2, 3)

        list.forEach { num -> respondText.append("<div>list.num:$num</div>") }
        list.forEach { respondText.append("<div>list.it:$it</div>") }

        val idList = list.map { it * 10 }

        idList.forEach { respondText.append("<div>idList.it:$it</div>") }

        val userList = listOf(TeamUser(1, 100, "yosuke"), TeamUser(2, 200, "Kotlin"), TeamUser(3, 100, "Java"))
        val idUserList = userList.map { it.id }

        idUserList.forEach { respondText.append("<div>idUserList.it:$it</div>") }

        val filteredList = userList.filter { it.teamId == 100 }
        filteredList.forEach { respondText.append("<div>filteredList.it:$it</div>") }

        respondText.append("<div>userList.first:${userList.first()}</div>")
        respondText.append("<div>userList.last:${userList.last()}</div>")

        respondText.append("<div>userList.firstOrNull {teamId 200}:${userList.firstOrNull { it.teamId == 200 }}</div>")
        respondText.append("<div>userList.lastOrNull {teamId 200}:${userList.lastOrNull { it.teamId == 200 }}</div>")

        respondText.append("<div>userList.firstOrNull {teamId 1000}:${userList.firstOrNull { it.teamId == 1000 }}</div>")
        respondText.append("<div>userList.lastOrNull {teamId 1000}:${userList.lastOrNull { it.teamId == 1000 }}</div>")

        val list2 = listOf(1, 1, 2, 3, 4, 4, 5)
        list2.distinct().forEach { respondText.append("<div>list2.distinct.it:$it</div>") }

        val associateByMap = userList.associateBy { it.id }
        respondText.append("<div>associateByMap:${associateByMap}</div>")

        val associateWithMap = userList.associateWith { it.name.length }
        respondText.append("<div>associateWithMap:${associateWithMap}</div>")

        val groupByMap = userList.groupBy { it.teamId }
        groupByMap.forEach { respondText.append("<div>groupByMap.it:$it</div>") }

        val count = userList.count { it.teamId == 100 }
        respondText.append("<div>count:$count</div>")

        val list3 = listOf("Yosuke", "Kotlin", "Java", "Groovy", "Scala")
        val chunkedList = list3.chunked(2)
        respondText.append("<div>chunkedList:$chunkedList</div>")

        val reduceResult = list2.distinct().reduce { sum, value -> sum + value }
        respondText.append("<div>reduceResult:$reduceResult</div>")

        val reduceStringResult = list3.reduce { line, str -> line + str }
        respondText.append("<div>reduceStringResult:$reduceStringResult</div>")

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

data class TeamUser(val id: Int, val teamId: Int, val name: String)
