fun operation(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}
fun main() {
    val add: (Int, Int) -> Int = { x, y -> x + y } // lambda
    
    var sum = operation(3,5,add)
    println("The sum is ${sum}")
}
