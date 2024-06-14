data class Person(val name: String, val age: Int)

fun main() {
    val persons = listOf(
        Person("Alice", 25),
        Person("Bob", 30),
        Person("Charlie", 20),
        Person("David", 25)
    )

    val sortedPersons = persons.sortedWith(compareBy<Person> { it.age }.thenBy { it.name })
    
    sortedPersons.forEach { println("${it.name}, ${it.age}") }
}
