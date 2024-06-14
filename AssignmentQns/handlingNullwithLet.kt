fun printUppercaseOrMessage(str: String?) {
    str?.let {
        println(it.toUpperCase())
    } ?: run {
        println("String is null")
    }
}

fun main() {
    val str1: String? = "hello"
    val str2: String? = null
    
    printUppercaseOrMessage(str1)
    printUppercaseOrMessage(str2)
}
