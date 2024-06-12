fun main() {
   //Q3
    var num1 = 36
    var num2 = 6
    var rem1=num1
    var rem2=num2
    while(rem2!=0){
        var mod = rem1%rem2
        rem1 = rem2
        rem2=mod
    }
    println("Answer is $rem1")
}