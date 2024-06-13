fun operation(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}
fun main() {
    val add: (Int, Int) -> Int = { x, y -> x + y }

    val multiply: (Int, Int) -> Int = { x, y -> x * y }

    val sub: (Int, Int) -> Int = {x,y -> x-y}

    val sum = operation(5, 3, add)
    println("The sum is: $sum")

    val product = operation(5, 3, multiply)
    println("The product is: $product")

    val difference = operation(5, 3, sub)
    println("The difference is: $difference")

}
