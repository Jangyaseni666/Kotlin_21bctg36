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
### varargs keyword:
By using varargs keyword we can send any number of arguments to the function.
```
fun printVars(varargs numbers:Int){
  for(number in numbers){
    println(number)
  }
}
```

## Extension Functions:

These functions are used to add more functionality to existing classes without inheriting them.

```
class Rectangle(val length: Double, val breadth: Double){
  fun area():Double{
    return length*breadth
  }
}
fun main(){
  fun Rectangle.perimeter():Double{
    return 2*(length+breadth)
  }
  val rect = Rectangle(4,5)
  println("Perimeter of circle is ${rect.perimeter()})
}
```

## Higher Order Functions:

A function that can accept another function as argument is called a Higher Order Function. It usually takes a lambda function as argument.

```
var lambda = {a: Int , b: Int -> a + b }

fun higherfunc( lower: (Int, Int) -> Int) {          
    var result = lower(2,4)                  
    println("The sum of two numbers is: $result")
}

fun main() {
   higherfunc(lambda)
}

```

# Classes

A class is a blueprint for creating objects, providing initial values for state, and implementations of behavior using member variables and functions respectively.

An object is an instance of a class and has its own state and behavior.

```
class Person{
  val name
  fun showName(){
    println(name)
  }
}
fun main(){
  var p = Person()
  p.name="XYZ"
  p.showName()
}
```

## Generic Classes:

In Kotlin, generic classes allow us to create classes, interfaces, and functions that can operate with different types of data while maintaining type safety. This means we can write a class or function that can handle multiple data types and makes sure of type checking.

For example we want to create a type called Box that contains an integer:

```
class Box<T>(var content: T) // Generic class with a placeholder type

fun main() {
    val intBox: Box<Int> = Box(1)
    println("Content of intBox: ${intBox.content}")
    intBox.content = 42
    println("Updated content of intBox: ${intBox.content}")
}

```


