class User(val name: String, val age: Int) {
    companion object {
        fun createUser(name: String): User {
            return User(name, 0) 
        }
        fun createUserwithOptionalParameters(name:String = "John Doe", age:Int = 30):User{
            return User(name,age)
        }
    }
}
​
fun main() {
    val user1 = User.createUser("Alice") //Default method
    println("User 1: ${user1.name}, ${user1.age} years old")
    
    val user2 = User.createUserwithOptionalParameters(name = "Bob")
    println("User 2: ${user2.name}, ${user2.age} years old")
    
    val user3 = User.createUserwithOptionalParameters(age=25)
    println("User 3: ${user3.name}, ${user3.age} years old")
}
​