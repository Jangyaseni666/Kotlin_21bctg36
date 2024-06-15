fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val squaredNumbers = numbers.map { it * it }
    val oddNumbers = squaredNumbers.filter { it % 2 != 0 }
    val sumOfOddNumbers = oddNumbers.reduce { sum, number -> sum + number }

    println("Sum of odd squared numbers: $sumOfOddNumbers")
}
