interface Drawable {
    fun draw()
    fun resize(newSize: Double)
}

class Square : Drawable {
    override fun draw() {
        println("Drawing a square")
    }
    
    override fun resize(newSize: Double) {
        println("Resizing square to size: $newSize")
    }
}

class Triangle : Drawable {
    override fun draw() {
        println("Drawing a triangle")
    }
    
    override fun resize(newSize: Double) {
        println("Resizing triangle to size: $newSize")
    }
}

fun main() {
    val square = Square()
    val triangle = Triangle()
    
    square.draw()
    triangle.draw()
    
    square.resize(10.0)
    triangle.resize(7.5)
}
