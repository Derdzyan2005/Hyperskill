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

fun decodeUnary(encodedMessage: String): String {
    val blocks = encodedMessage.split(" ")
    if (blocks.any { it != "0" && it != "00" && !it.all { char -> char == '0' } }) return "not valid"
    if (blocks.size % 2 != 0) return "not valid"

    var binaryString = StringBuilder()
    var i = 0
    while (i < blocks.size - 1) {
        val prefix = blocks[i]
        val sequence = blocks[i + 1]
        val bit = if (prefix == "0") '1' else '0'

        if (prefix != "0" && prefix != "00") return "not valid"
        repeat(sequence.length) {
            binaryString.append(bit)
        }

        i += 2
    }

    return if (binaryString.length % 7 == 0) binaryString.toString() else "not valid"
}

fun binaryToText(binaryString: String): String {
    val result = StringBuilder()
    var i = 0
    while (i < binaryString.length) {
        val binaryChar = binaryString.substring(i, i + 7)
        val char = binaryChar.toInt(2).toChar()
        result.append(char)
        i += 7
    }
    return result.toString()
}

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        val operation = readln().trim()

        when (operation) {
            "encode" -> {
                println("Input string:")
                val inputString = readln()
                val binaryString = inputString.flatMap { char -> toBinaryString(char).toList() }.joinToString("")
                val encodedMessage = encodeUnary(binaryString)
                println("Encoded string:")
                println(encodedMessage)
            }
            "decode" -> {
                println("Input encoded string:")
                val encodedString = readln().trim()
                val binaryString = decodeUnary(encodedString)
                if (binaryString == "not valid") {
                    println("Encoded string is not valid.")
                } else {
                    val decodedMessage = binaryToText(binaryString)
                    println("Decoded string:")
                    println(decodedMessage)
                }
            }
            "exit" -> {
                println("Bye!")
                return
            }
            else -> {
                println("There is no '$operation' operation")
            }
        }
    }
}
