package tictactoe

import kotlin.math.abs

fun main() {
    val input = readln()

    printGrid(input)

    println(analyzeGameState(input))
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

fun analyzeGameState(gameState: String): String {

    val xCount = gameState.count { it == 'X' }
    val oCount = gameState.count { it == 'O' }

    if (abs(xCount - oCount) >= 2) {
        return "Impossible"
    }

    val xWins = hasWon(gameState, 'X')
    val oWins = hasWon(gameState, 'O')

    return when {
        xWins && oWins -> "Impossible"
        xWins -> "X wins"
        oWins -> "O wins"
        '_' in gameState -> "Game not finished"
        else -> "Draw"
    }
}

fun hasWon(gameState: String, player: Char): Boolean {


    for (i in 0..2) {
        if (gameState[i * 3] == player &&
            gameState[i * 3 + 1] == player &&
            gameState[i * 3 + 2] == player) {
            return true
        }
    }

    for (i in 0..2) {
        if (gameState[i] == player &&
            gameState[i + 3] == player &&
            gameState[i + 6] == player) {
            return true
        }
    }


    if (gameState[0] == player &&
        gameState[4] == player &&
        gameState[8] == player) {
        return true
    }

    if (gameState[2] == player &&
        gameState[4] == player &&
        gameState[6] == player) {
        return true
    }

    return false
}
