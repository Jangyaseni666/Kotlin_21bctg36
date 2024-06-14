class Address(val city: String?)

class Person(val address: Address?)

fun getCityName(person: Person): String {
    return person.address?.city ?: "Unknown City"
}

fun main() {
    val person1 = Person(Address("New York"))
    val person2 = Person(Address(null))

    println("Person 1 City: ${getCityName(person1)}")
    println("Person 2 City: ${getCityName(person2)}")
}
