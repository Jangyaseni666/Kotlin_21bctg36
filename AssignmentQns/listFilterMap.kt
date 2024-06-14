fun processNumbers(numbers: List<Int>): List<Int> {
    var newNumbers = numbers.filter{it%2==0}
    newNumbers = newNumbers.map{it*2}
    return newNumbers
}

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val result = processNumbers(numbers)
    println(result)
}
