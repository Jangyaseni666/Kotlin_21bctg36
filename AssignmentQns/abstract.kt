abstract class Shape {
    abstract fun area(): Double
}

class Rectangle(val length: Double, val breadth: Double) : Shape() {
    override fun area(): Double {
        return length * breadth
    }

    fun perimeter(): Double {
        return 2 * (length + breadth)
    }
}

class Circle(val radius: Double) : Shape() {
    override fun area(): Double {
        return Math.PI * radius * radius
    }

    fun perimeter(): Double {
        return 2 * Math.PI * radius
    }
}

fun main() {
    var rect = Rectangle(3.0,5.0)
    var cir = Circle(2.0)
    println("The area of rectangle is: ${rect.area()} \n The perimeter is: ${rect.perimeter()}")
    println("The area of circle is: ${cir.area()} \n The perimeter is: ${cir.perimeter()}")
    
}
