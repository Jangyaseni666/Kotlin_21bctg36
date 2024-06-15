 fun calculateFactorial(number: Int): Int {
        return factorial(number)
    }
 
 val factorial: (Int) -> Int = { number ->
        var result: Int = 1
        for (i in 2..number) {
            result *= i
        }
        result
    }

fun main() {
    val number = 5
    val factorialOfNumber = calculateFactorial(number)
    println("Factorial of $number is: $factorialOfNumber")
}
