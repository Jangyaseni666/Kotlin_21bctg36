# Lambda Functions in Kotlin:
Lambda functions in Kotlin are anonymous functions that can be treated as values. They are defined using the following syntax:

```
val lambdaName: (parameters) -> returnType = { arguments -> lambdaBody }
```

It is commonly used with higher-order functions like map, filter, reduce, etc., to perform operations on collections. It improves code readability.

# Concurrency in Kotlin:
Concurrency refers to the ability of a program to execute multiple tasks simultaneously, allowing for efficient utilization of resources and enhanced responsiveness.

## Coroutines in Kotlin:
Coroutines are a feature in Kotlin that allow you to write asynchronous code sequentially. They are defined using suspend functions and managed by Kotlin's CoroutineScope.

We import kotlinx.coroutines.* to use coroutines in Kotlin.

### runBlocking:
---
runBlocking is a coroutine builder that creates a new coroutine and blocks the current thread where it is called until the coroutine completes. It is typically used in main functions to bridge synchronous and asynchronous code.

```
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("Hello")
        delay(1000L)
        println("World")
    }
}
```

- launch keyword: It is is used to start non-blocking coroutines that run concurrently.
- delay is used to set a time delay. 1000L=1s

### Dispatchers
---
They specify the execution context of coroutines, helping manage thread pools and ensuring efficient resource utilization.

Types:
- Dispatchers.Default: Suitable for CPU-bound tasks
- Dispatchers.IO: Optimized for I/O-bound tasks
- Dispatchers.Main: Available on Android and is used for UI updates from coroutines.
- Dispatchers.Unconfined: Starts the coroutine in the current thread, but after suspension, it resumes execution in the thread that is used by the parent coroutine.
