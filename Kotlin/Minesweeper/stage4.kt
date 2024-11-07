import kotlin.random.Random

data class Field(
    val visible: Array<Array<Char>>,
    val mines: Array<Array<Boolean>>,
    val marks: Array<Array<Boolean>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Field
        return visible.contentDeepEquals(other.visible) &&
                mines.contentDeepEquals(other.mines) &&
                marks.contentDeepEquals(other.marks)
    }
    override fun hashCode(): Int {
        return visible.contentDeepHashCode() +
                mines.contentDeepHashCode() * 31 +
                marks.contentDeepHashCode() * 31 * 31
    }
}

fun main() {
    print("How many mines do you want on the field? > ")
    val numMines = readln().toInt()
    val field = initializeField(numMines)

    var gameWon = false
    while (!gameWon) {
        printField(field)
        gameWon = processMove(field)
    }

    printField(field)
    println("Congratulations! You found all the mines!")
}

fun initializeField(numMines: Int): Field {
    val visible = Array(9) { Array(9) { '.' } }
    val mines = Array(9) { Array(9) { false } }
    val marks = Array(9) { Array(9) { false } }

    var minesPlaced = 0
    while (minesPlaced < numMines) {
        val row = Random.nextInt(9)
        val col = Random.nextInt(9)
        if (!mines[row][col]) {
            mines[row][col] = true
            minesPlaced++
        }
    }

    for (row in visible.indices) {
        for (col in visible[row].indices) {
            if (!mines[row][col]) {
                val count = countAdjacentMines(mines, row, col)
                visible[row][col] = if (count > 0) count.toString()[0] else '.'
            }
        }
    }

    return Field(visible, mines, marks)
}

fun countAdjacentMines(mines: Array<Array<Boolean>>, row: Int, col: Int): Int {
    var count = 0
    for (r in maxOf(0, row - 1)..minOf(mines.lastIndex, row + 1)) {
        for (c in maxOf(0, col - 1)..minOf(mines[0].lastIndex, col + 1)) {
            if (r == row && c == col) continue
            if (mines[r][c]) count++
        }
    }
    return count
}

fun printField(field: Field) {
    println("\n │123456789│")
    println("—│—————————│")
    for (i in field.visible.indices) {
        print("${i + 1}│")
        for (j in field.visible[i].indices) {
            print(when {
                field.marks[i][j] -> '*'
                else -> field.visible[i][j]
            })
        }
        println("│")
    }
    println("—│—————————│")
}

fun processMove(field: Field): Boolean {
    while (true) {
        print("Set/delete mines marks (x and y coordinates): > ")
        val input = readln().split(" ")
        if (input.size != 2) continue

        try {
            val col = input[0].toInt() - 1
            val row = input[1].toInt() - 1

            if (row !in 0..8 || col !in 0..8) continue

            if (field.visible[row][col] in '1'..'8') {
                println("There is a number here!")
                continue
            }

            field.marks[row][col] = !field.marks[row][col]

            return checkWin(field)

        } catch (e: NumberFormatException) {
            continue
        }
    }
}

fun checkWin(field: Field): Boolean {
    for (i in field.mines.indices) {
        for (j in field.mines[i].indices) {
            if (field.mines[i][j] != field.marks[i][j]) {
                return false
            }
        }
    }
    return true
}
