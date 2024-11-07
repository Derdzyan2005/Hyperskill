package chucknorris

fun decodeUnary(encodedMessage: String): String {
    val blocks = encodedMessage.split(" ")

    var binaryString = StringBuilder()
    var i = 0
    while (i < blocks.size - 1) {
        val prefix = blocks[i]
        val sequence = blocks[i + 1]

        val bit = if (prefix == "0") '1' else '0'

        repeat(sequence.length) {
            binaryString.append(bit)
        }

        i += 2
    }

    return binaryString.toString()
}

fun binaryToText(binaryString: String): String {

    val result = StringBuilder()
    var i = 0
    while (i < binaryString.length) {
        val binaryChar = binaryString.substring(i, minOf(i + 7, binaryString.length))

        val char = binaryChar.toInt(2).toChar()
        result.append(char)
        i += 7
    }
    return result.toString()
}

fun main() {
    print("Input encoded string: ")
    val encodedString = readln()

    val binaryString = decodeUnary(encodedString)

    val decodedMessage = binaryToText(binaryString)

    println("The result:")
    println(decodedMessage)
}
