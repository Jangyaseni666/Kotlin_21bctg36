import kotlinx.coroutines.*

suspend fun longRunningTask() {
    delay(2500L)
    println("Hello")
}

fun main() = runBlocking {
    try {
        withTimeout(2000L) {
            longRunningTask()
        }
    } catch (e: TimeoutCancellationException) {
        println("Task cancelled due to timeout.")
    }
    println("Program finished.")
}
