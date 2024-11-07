package chucknorris

fun main() {
    print("Input string: ")
    val inputString = readln()

    println("The result:")
    for (char in inputString) {
        val binaryString = Integer.toBinaryString(char.code).padStart(7, '0')
        println("$char = $binaryString")
    }
}
