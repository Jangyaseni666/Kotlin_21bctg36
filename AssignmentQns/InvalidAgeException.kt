class InvalidAgeException(message:String):Exception(message)

fun checkAge(age: Int) {
    if (age < 18) {
        throw InvalidAgeException("Age must be >=18. Given age: $age")
    } else {
        println("Age is valid: $age")
    }
}

fun main() {
    try {
        checkAge(16)
    } catch (e: InvalidAgeException) {
        println(e.message)
    }
}
