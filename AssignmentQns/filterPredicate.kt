fun filterStringsByLength(strings: List<String>, length: Int): List<String> {
    return strings.filter { it.length == length }
}

fun main() {
    val strings = listOf("apple", "banana", "cherry", "date", "fig")
    val filteredStrings = filterStringsByLength(strings, 6)
    
    println("Filtered strings by length 6: $filteredStrings")
}
