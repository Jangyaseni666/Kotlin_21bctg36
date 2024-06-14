fun alternateLists(list1: List<Int>, list2: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    val maxLength = if(list1.size>list2.size) list1.size else list2.size
    
    for (i in 0 until maxLength) {
        if (i < list1.size) {
            result.add(list1[i])
        }
        if (i < list2.size) {
            result.add(list2[i])
        }
    }
    
    return result
}

fun main() {
    val list1 = listOf(1, 3, 5, 7, 9)
    val list2 = listOf(2, 4, 6, 8)
    
    val alternatedList = alternateLists(list1, list2)
    println("Alternated list: $alternatedList")
}
