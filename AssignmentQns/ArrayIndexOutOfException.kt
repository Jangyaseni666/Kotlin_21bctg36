fun accessElement(list: List<Int>, index: Int){
    try {
        val element = list[index]
        println("Element at index $index: $element")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Error: Index $index is out of bounds.")
    } finally {
        println("Operation ended.")
    }
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
   	accessElement(list, 2)
    accessElement(list, 10)
}
