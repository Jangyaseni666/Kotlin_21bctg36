fun createAdder(number: Int): (Int) -> Int {
    return { i: Int -> i + number }
}

fun main() {
    val addFive = createAdder(5)
    
    println(addFive(3))
    println(addFive(10)) 
}
