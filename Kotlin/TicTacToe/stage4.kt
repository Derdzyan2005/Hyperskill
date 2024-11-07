package tictactoe

fun main() {
    val initialState = readln()
    val gameBoard = initialState.toCharArray()

    printGrid(gameBoard)

    while (true) {
        print("Enter the coordinates: ")
        try {
            val (row, col) = readln().split(" ").map { it.toInt() }

            when {
                row !in 1..3 || col !in 1..3 -> {
                    println("Coordinates should be from 1 to 3!")
                    continue
                }

                !isCellEmpty(gameBoard, row, col) -> {
                    println("This cell is occupied! Choose another one!")
                    continue
                }

                else -> {

                    makeMove(gameBoard, row, col)
                    printGrid(gameBoard)
                    break
                }
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        } catch (e: Exception) {
            println("You should enter numbers!")
        }
    }
}

fun printGrid(gameState: CharArray) {
    println("---------")
    for (i in 0..2) {
        val rowStart = i * 3
        println("| ${gameState[rowStart]} ${gameState[rowStart + 1]} ${gameState[rowStart + 2]} |")
    }
    println("---------")
}

fun isCellEmpty(gameBoard: CharArray, row: Int, col: Int): Boolean {
    val index = (row - 1) * 3 + (col - 1)
    return gameBoard[index] == '_'
}

fun makeMove(gameBoard: CharArray, row: Int, col: Int) {
    val index = (row - 1) * 3 + (col - 1)
    gameBoard[index] = 'X'
}
