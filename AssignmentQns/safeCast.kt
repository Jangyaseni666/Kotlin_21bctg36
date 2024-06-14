fun filterStrings(list: List<Any>): List<String> {
    val result = mutableListOf<String>()
    
    for (element in list) {
        val str = element as? String
        if (str != null) {
            result.add(str)
        }
    }
    
    return result
}

fun main() {
    val list: List<Any> = listOf("apple", 1, "banana", 2, "cherry")
    val stringsOnly = filterStrings(list)
    
    println("Strings only: $stringsOnly") 
}
