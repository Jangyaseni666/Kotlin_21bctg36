class UniqueList<T> {
    private val elements: MutableList<T> = mutableListOf()
    fun add(element: T): Boolean {
        if (!elements.contains(element)) {
            elements.add(element)
            return true
        } else {
            return false
        }
    }

    fun remove(element: T): Boolean {
        return elements.remove(element)
    }

    fun contains(element: T): Boolean {
        return elements.contains(element)
    }

    fun get(index: Int): T {
        return elements[index]
    }
    
  	fun printList(){
        println(elements)
    }
}

fun main() {
    val uniqueList = UniqueList<String>()

    uniqueList.add("Kotlin") 
    uniqueList.add("Java") 
    uniqueList.add("Kotlin")
    
 	uniqueList.printList()

    uniqueList.remove("Java")
    
    uniqueList.printList()

    println(uniqueList.contains("Kotlin")) 
    println(uniqueList.contains("Java")) 

    uniqueList.add("Groovy")
    uniqueList.printList()
    println(uniqueList.get(1))
}

