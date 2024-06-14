fun intersectionOperation(list1: List<Int>, list2: List<Int>): List<Int> {
    val set1 = list1.toSet()
    val set2 = list2.toSet()
    return (set1 intersect set2).sorted()
}

fun unionOperation(list1: List<Int>, list2: List<Int>): List<Int> {
    val set1 = list1.toSet()
    val set2 = list2.toSet()
    return (set1 union set2).sorted()
}

fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(3, 4, 5, 6, 7)

    val intersectionResult = intersectionOperation(list1, list2)
    println("Intersection: $intersectionResult") 

    val unionResult = unionOperation(list1, list2)
    println("Union: $unionResult") 
}
