fun main() {
   // Pattern of Q1
    for(i in 1..5){
        for(k in 5-i downTo 1){
            print(' ')
        }
        for(j in 1..i){
            print('*')
        }
        for(l in 1..i-1){
            print('*')
		}
        println()
    }
    for(i in 5 downTo 1){
        for(k in 1..5-i){
            print(' ')
        }
        for(j in i downTo 1){
            print('*')
        }
        for(l in 1..i-1){
            print('*')
        }
        println()
    }
}