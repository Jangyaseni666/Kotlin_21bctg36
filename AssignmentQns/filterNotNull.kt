fun filterNonNullIntegers(list: List<Int?>): List<Int> {
    return list.filterNotNull()
}

fun main() {
    val list: List<Int?> = listOf(1, null, 2, null, 3, null, 4)
    val nonNullIntegers = filterNonNullIntegers(list)
    
    println("Non-null integers: $nonNullIntegers")
}
