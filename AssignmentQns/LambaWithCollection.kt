fun processStrings(strings: List<String>, startingLetter: Char): List<String> {
    return strings
        .filter { it.startsWith(startingLetter, ignoreCase = true) }
        .map { it.uppercase() }
        .sorted()
}

fun main() {
    val words = listOf("apple", "Banana", "orange","Grape")
    val filteredUppercaseSorted = processStrings(words, 'g')
    println("Filtered, uppercase, sorted: $filteredUppercaseSorted")
}
