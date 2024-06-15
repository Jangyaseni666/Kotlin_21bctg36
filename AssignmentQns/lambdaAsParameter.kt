fun transformStrings(strings: List<String>, transformer: (String) -> String): List<String> {
    return strings.map { transformer(it) }
}

fun main() {
    val names = listOf("alice", "bob", "charlie", "david")
    val transformer = {s:String -> s.uppercase()}
    val uppercasedNames = transformStrings(names, transformer)
    println("Uppercased names: $uppercasedNames")
}
