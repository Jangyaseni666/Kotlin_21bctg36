fun main() {
   //Q4
    var str = "aabbbccddde"
    var temp="" // to check if we have visited this letter before
    println("Character Frequency")
    for(i in str){
        var c=0
        for(j in str){
            if(i==j) c++
        }
        if(!(i in temp)) println("$i\t$c")
        temp+=i
    }
}