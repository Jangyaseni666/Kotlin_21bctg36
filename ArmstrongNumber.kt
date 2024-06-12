fun main() {
   //Q2
    var num=153
    var temp = num
    var sumOfDigits=0
    while(temp!=0){
        var digit=temp%10
        sumOfDigits+= digit*digit*digit
        temp = temp/10
    }
    if(num==sumOfDigits) println("Armstrong Number")
    else println("Not Armstrong number")
}