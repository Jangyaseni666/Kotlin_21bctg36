fun doubleNonNullValues(list: List<Int?>): List<Int> {
    return list.mapNotNull { if (it != null) it * 2 else null  }
}

fun main() {
    val list: List<Int?> = listOf(1, null, 2, null, 3, null, 4)
    val doubledValues = doubleNonNullValues(list)
    
    println("Doubled non-null values: $doubledValues") 
}