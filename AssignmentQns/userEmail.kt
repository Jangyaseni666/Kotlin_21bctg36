data class User(val name: String?, val email: String?)

fun printUserDetails(user: User) {
    if (user.name == null || user.email == null) {
        println("Incomplete User")
    } else {
        println("User Details: Name - ${user.name}, Email - ${user.email}")
    }
}

fun main() {
    val user1 = User("Alice", "alice@example.com")
    val user2 = User(null, "bob@example.com")
    val user3 = User("Charlie", null)

    printUserDetails(user1)
    printUserDetails(user2)
    printUserDetails(user3)
}
