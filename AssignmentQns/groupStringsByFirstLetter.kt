fun groupStrings(strings: List<String>): Map<Char, List<String>> {
    return strings.groupBy { it.first() }
}

fun main() {
    val strings = listOf("apple", "apricot", "banana", "blueberry", "cherry")
    val result = grouStrings(strings)

    result.forEach { (key, value) ->
        println("Key: $key, Values: $value, Count: ${value.size}")
    }
}
