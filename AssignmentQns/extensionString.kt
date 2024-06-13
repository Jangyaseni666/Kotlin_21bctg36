fun String.isPalindrome(): Boolean {
	var l=0
    var r=this.length-1
    var flag=true
    while(l<=r){
        if(this[l]!=this[r]){
            flag=false
            break
        }
        l++
        r--
    }
    return flag
}

fun main() {
    val str = "racecar"
    println("$str is a palindrome: ${str.isPalindrome()}")
}
