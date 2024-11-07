package tictactoe

fun main() {
    val input = readln()

    printGrid(input)
}

fun printGrid(gameState: String) {
    println("---------")

    for (i in 0..2) {
        val rowStart = i * 3
        val row = gameState.substring(rowStart, rowStart + 3)
        println("| ${row[0]} ${row[1]} ${row[2]} |")
    }

    println("---------")
}
