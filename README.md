# Kotlin Collections

It is used to manage similar items in a collection. We'll mainly focus on 2 different type of collections:
- Immutable Collection
- Mutable Collection

Different types of collection would include: lists, sets and maps.

## Lists:

- It is an ordered collection of elements
- It allows duplicates
- To make lists mutable use mutableListOf

Example:
```
val readOnlyList = listOf(1, 2, 3)
val mutableList = mutableListOf(1, 2, 3)
```
## Sets:

- It is an unordered collection of elements
- It doesn't allow duplicates
- To make sets mutable use mutableSetOf

Example:
```
val readOnlySet = setOf(1, 2, 3)
val mutableSet = mutableSetOf(1, 2, 3)
```
## Maps:

- It is a collection of key-value pairs
- Keys are unique but values can be duplicates
- To make maps mutable use mutableMapOf

Example:
```
val readOnlyMap = mapOf("one" to 1, "two" to 2)
val mutableMap= mutableMapOf("one" to 1, "two" to 2)
```

## Transformations:

Transformations in Kotlin help us manipulate and process data.

### 1. map

It transforms each element in the existing collection and returns a new collection.

```
fun mapExample() {
    val numbers = listOf(1, 2, 3, 4)
    val squaredNumbers = numbers.map { it * it }
    println(squaredNumbers) 
}
```

### 2. filter

It filters the collection based on a condition, returning only the elements that match the condition.

```
fun filterExample() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers)
}
```


# Null Safety

It is a concept in Kotlin that helps us handle null values and prevent errors that might happen due to null values.

To denote a variable as nullabe we use ? operator

```
val a:Int? = null
```

To call a function that might return null value, we use safe calls, i.e, we use '?.' operator to make the call.

```
val a:String? = null
println(a?.length)
```

When we are 100% sure a function will definitely return non null value, we use non-null assertion operator to make a function call. We use '!!.' operatore for this.

```
val a:String?="hello"
println(a!!.length)
```

### Elvis Operator (?:)

 It is a concise way to handle nullable variables and provide default values in case of null.

 ```
 val name: String? = "Kotlin"
val displayName = name ?: "Unknown"
println(displayName)
 ```


## Let Keyword:

<b>let</b> is a scope function that allows us to execute a block of code within the context of an object. That is, the block executes only when a certain condition is met. It is used for null checks, transforming and processing data.

```
val name: String? = "Kotlin"
name?.let {
    println("Name length: ${it.length}")
}
```

Here, the code only runs when name is not null.