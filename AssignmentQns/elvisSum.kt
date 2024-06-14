fun sumNonNull(a: Int?, b: Int?): Int {
    var a1 = a ?: -1 
    var b1 = b ?: -1
    if(a1 == -1 || b1 == -1) return -1 else return a1 + b1
}


fun main() {
    val num1: Int? = 10
    val num2: Int? = 20
    val num3: Int? = null
    val num4: Int? = 30
    
    println("Sum of $num1 and $num2: ${sumNonNull(num1, num2)}") // Output: Sum of 10 and 20: 30
    println("Sum of $num1 and $num3: ${sumNonNull(num1, num3)}") // Output: Sum of 10 and null: -1
    println("Sum of $num3 and $num4: ${sumNonNull(num3, num4)}") // Output: Sum of null and 30: -1
}
