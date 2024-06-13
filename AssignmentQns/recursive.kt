tailrec fun factorial(n: Int, acc:Long=1): Long {
    if(n<=1) return acc
    else return factorial(n-1, acc*n)
}

fun main() {
    val num = 5
    
    println("Factorial of $num is: ${factorial(num)}")
}