fun divideNumbers(numerator: Int, denominator: Int): String {
    try {
        val result = numerator / denominator
        return "Result: $result"
    } catch (e: ArithmeticException) {
        return "Error: Division by zero is not allowed."
    }
}

fun main() {
    val num1 = 10
    val num2 = 0

    val result = divideNumbers(num1, num2)
    println(result)

    val result2 = divideNumbers(num1, 2)
    println(result2)
}
