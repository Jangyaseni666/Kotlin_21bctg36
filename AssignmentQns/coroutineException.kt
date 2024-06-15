import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        try {
            var x = 10
            var y=0
            println(x/y)
        } catch (e: Exception) {
            println("Caught exception: ${e.message}")
        } finally {
            println("Task finished")
        }
    }
    println("Task started")
}
