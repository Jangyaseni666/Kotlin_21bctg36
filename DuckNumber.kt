fun main() {
   //Q5
    var num=101
    var temp=num
    var flag=0
    while(temp!=0){
        var digit = temp%10
        if(digit == 0){
            println("Duck number")
            flag=1
            break
        }
        temp=temp/10        
    }
    if(flag==0) println("Not a duck number")
}