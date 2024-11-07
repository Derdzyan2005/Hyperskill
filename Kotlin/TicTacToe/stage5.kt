package tictactoe

fun main() {
    val gameBoard = CharArray(9) { ' ' }
    var currentPlayer = 'X'
    var gameStatus = "Game not finished"

    printGrid(gameBoard)

    while (gameStatus == "Game not finished") {
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
                        makeMove(gameBoard, row, col, currentPlayer)
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

        gameStatus = analyzeGameState(gameBoard)

        if (gameStatus == "Game not finished") {
            currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        } else {
            println(gameStatus)
        }
    }
}

fun printGrid(gameBoard: CharArray) {
    println("---------")
    for (i in 0..2) {
        val rowStart = i * 3
        println("| ${gameBoard[rowStart]} ${gameBoard[rowStart + 1]} ${gameBoard[rowStart + 2]} |")
    }
    println("---------")
}

fun isCellEmpty(gameBoard: CharArray, row: Int, col: Int): Boolean {
    val index = (row - 1) * 3 + (col - 1)
    return gameBoard[index] == ' '
}

fun makeMove(gameBoard: CharArray, row: Int, col: Int, player: Char) {
    val index = (row - 1) * 3 + (col - 1)
    gameBoard[index] = player
}

fun hasWon(gameBoard: CharArray, player: Char): Boolean {
    for (i in 0..2) {
        if (gameBoard[i * 3] == player &&
            gameBoard[i * 3 + 1] == player &&
            gameBoard[i * 3 + 2] == player) {
            return true
        }
    }

    for (i in 0..2) {
        if (gameBoard[i] == player &&
            gameBoard[i + 3] == player &&
            gameBoard[i + 6] == player) {
            return true
        }
    }

    if (gameBoard[0] == player &&
        gameBoard[4] == player &&
        gameBoard[8] == player) {
        return true
    }

    if (gameBoard[2] == player &&
        gameBoard[4] == player &&
        gameBoard[6] == player) {
        return true
    }

    return false
}

fun analyzeGameState(gameBoard: CharArray): String {
    if (hasWon(gameBoard, 'X')) return "X wins"
    if (hasWon(gameBoard, 'O')) return "O wins"

    if (!gameBoard.contains(' ')) return "Draw"

    return "Game not finished"
}
