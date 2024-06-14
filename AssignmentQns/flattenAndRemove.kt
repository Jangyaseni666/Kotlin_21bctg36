fun flattenAndRemoveDuplicates(numberLists: List<List<Int>>): List<Int> {
    var res =  numberLists.flatten()
    res = res.distinct()
    return res
}

fun main() {
    val numberLists = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(2,4,7)
    )
    
    val result = flattenAndRemoveDuplicates(numberLists)
    println(result)
}
