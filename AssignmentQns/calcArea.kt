fun calculateArea(length: Double, breadth: Double): Double {
    return length * breadth
}
fun calculateArea(radius: Double): Double {
    return Math.PI * radius * radius
}

fun main() {
    val length = 5.0
    val breadth = 3.0
    val rectangleArea = calculateArea(length, breadth)
    println("The area of the rectangle is: $rectangleArea")

    val radius = 4.0
    val circleArea = calculateArea(radius)
    println("The area of the circle is: $circleArea")
}
