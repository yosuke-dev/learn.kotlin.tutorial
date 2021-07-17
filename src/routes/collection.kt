package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.collectionRoute() {
    get("/collection") {
        val listExample = listExample()
        val mapExample = mapExample()
        val mapContainKeyExample = mapContainKeyExample()
        val setContainExample = setContainExample()

        call.respondText("<div>listExample:$listExample</div><div>mapExample:$mapExample</div><div>mapContainKeyExample:$mapContainKeyExample</div><div>setContainExample:$setContainExample</div>", contentType = ContentType.Text.Html)
    }
}

fun listExample(): String {
    val intList = mutableListOf(1, 2, 3)
    intList.add(4)
    return intList.joinToString()
}

fun mapExample(): String {
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    return map.toString()
}

fun mapContainKeyExample(): String {
    val map = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    map[4] = "four"
    return "containsKey{4}:${map.containsKey(4)}, containsKey{5}:${map.containsKey(5)}"
}

fun setContainExample(): String {
    val set = mutableSetOf("One", "Two", "Three")
    set.add("four")
    return set.joinToString()
}
