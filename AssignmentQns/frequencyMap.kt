fun calculateFrequency(numbers: List<Int>): Map<Int, Int> {
    return numbers.groupingBy{it}.eachCount()
}

fun main() {
    val numbers = listOf(1,1,1,1, 2, 2,2, 3, 3, 4)
    val frequencyMap = calculateFrequency(numbers)

    frequencyMap.forEach { (number, frequency) ->
        println("$number: $frequency")
    }
}
