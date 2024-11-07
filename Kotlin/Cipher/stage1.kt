package chucknorris

fun main() {
    print("Input string: ")
    val inputString = readln()

    val result = inputString.toCharArray().joinToString(" ")
    println(result)
}
