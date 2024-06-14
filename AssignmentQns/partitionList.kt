fun partitionNumbers(numbers: List<Int>): List<List<Int>> {
    val (evens, odds) = numbers.partition { it % 2 == 0 }
    return listOf(evens,odds)
}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val evenNumbers = partitionNumbers(numbers)[0]
    val oddNumbers = partitionNumbers(numbers)[1]
    
    println("Even numbers: $evenNumbers")
    println("Odd numbers: $oddNumbers")
}
