package chucknorris

fun toBinaryString(char: Char): String {
    return Integer.toBinaryString(char.code).padStart(7, '0')
}

fun encodeUnary(binaryString: String): String {
    val encodedList = mutableListOf<String>()
    var i = 0

    while (i < binaryString.length) {
        val currentBit = binaryString[i]

        val prefix = if (currentBit == '1') "0" else "00"
        val count = binaryString.drop(i).takeWhile { it == currentBit }.count()

        encodedList.add(prefix)
        encodedList.add("0".repeat(count))

        i += count
    }

    return encodedList.joinToString(" ")
}

fun main() {
    print("Input string: ")
    val inputString = readln()

    val binaryString = inputString.flatMap { char -> toBinaryString(char).toList() }.joinToString("")

    val encodedMessage = encodeUnary(binaryString)

    println("The result:")
    println(encodedMessage)
}
