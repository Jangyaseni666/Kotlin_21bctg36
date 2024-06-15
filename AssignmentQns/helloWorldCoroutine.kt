import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("Hello")
        delay(1000L)
        println("World")
    }
}
