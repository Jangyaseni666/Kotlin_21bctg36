# Functions
Functions in Kotlin are defined using the fun keyword. The return type is denoted after the parenthesis with a colon. Also, we can't use var or val keywords in the function signature.
```
fun double(num: Int):Int{
  return 2*num
}
```
We can also use default parameters in the function.
```
fun double(num:Int=0):Int{
  return 2*num
}
```

### Single Expression Functions:
These are functions whose body contain only a single expression.
```
fun sum(a:Int, b:Int):Int = a+b
```
