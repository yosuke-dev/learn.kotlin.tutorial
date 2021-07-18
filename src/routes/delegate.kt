package lean.kotlin.tutorial.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.IllegalStateException
import java.lang.StringBuilder
import kotlin.reflect.KProperty

fun Route.delegateRoute() {
    get("/delegate") {
        val respondText = StringBuilder()

        val executor = AddCalculationExecutor(CommonCalculationExecutor())

        respondText.append("<div>startMessage:${executor.getStartMessage()}</div>")
        respondText.append("<div>calc:${executor.calc(8,11)}</div>")

        val person = Person()
        person.name = "yosuke"
        person.address = "tokyo"
        println(person. name)
        println(person. address)

        val delegatePerson = DelegatePerson()
        delegatePerson.name = "delegate_yosuke"
        delegatePerson.address = "delegate_tokyo"
        println(delegatePerson. name)
        println(delegatePerson. address)

        call.respondText(respondText.toString(), contentType = ContentType.Text.Html)
    }
}

private interface CalculationExecutor {
    val message: String
    fun calc(num1: Int, num2: Int): Int
    fun getStartMessage(): String
}

private class CommonCalculationExecutor(override val message: String = "calc") : CalculationExecutor {
    override fun calc(num1: Int, num2: Int): Int {
        throw IllegalStateException("Not yet implemented")
    }

    override fun getStartMessage(): String {
        return "start $message"
    }
}

private class AddCalculationExecutor(private val calculationExecutor: CalculationExecutor) : CalculationExecutor by calculationExecutor {
    override fun calc(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}

private class Person {
    var name: String = ""
        get() {
            println("nameを取得します")
            return field
        }
        set(value) {
            println("nameを更新します")
            field = value
        }
    var address: String = ""
        get() {
            println("addressを取得します")
            return field
        }
        set(value) {
            println("addressを更新します")
            field = value
        }
}

private class DelegateWithMessage<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        println("${property.name}を取得します")
        return value!!
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        println("${property.name}を更新します")
        this.value = value
    }
}

private class DelegatePerson {
    var name: String by DelegateWithMessage()
    var address: String by DelegateWithMessage()
}
