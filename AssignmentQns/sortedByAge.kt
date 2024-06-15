fun sortByAge(persons: List<Pair<String, Int>>): List<Pair<String, Int>> {
    return persons.sortedBy { pair -> pair.second }
}

fun main() {
    val people = listOf(
        "Alice" to 30,
        "Bob" to 25,
        "Charlie" to 35,
        "Daisy" to 28
    )

    val sortedPeople = sortByAge(people)
    println(sortedPeople)
}
