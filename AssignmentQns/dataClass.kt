data class Person(val name: String, val age: Int, val address: String)
fun main() {
    val person1 = Person(name = "Jangyaseni Sahoo", age = 23, address = "Bhubaneswar, Odisha")
    
    val person2 = person1.copy(age = 21, address = "Bangalore, Karnataka")
    
    println("Original Person: $person1")
    println("New Person: $person2")
}
