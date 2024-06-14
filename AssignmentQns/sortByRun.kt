fun sortAndPrintList(numbers: List<Int>?) {
    val sortedList = numbers?.run {
        sorted()
    }
    println("Sorted List: $sortedList")
}

fun main() {
    val list1: List<Int>? = listOf(3, 1, 4, 1, 5, 9)
    val list2: List<Int>? = null

    sortAndPrintList(list1)
    sortAndPrintList(list2)
}
