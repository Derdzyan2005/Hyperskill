import kotlin.random.Random

data class Field(
    val visible: Array<Array<Char>>,
    val mines: Array<Array<Boolean>>,
    val explored: Array<Array<Boolean>>,
    val marks: Array<Array<Boolean>>
) {
    override fun equals(other: Any?) = this === other
    override fun hashCode() = System.identityHashCode(this)
}

fun main() {
    print("How many mines do you want on the field? > ")
    val numMines = readln().toInt()
    var field = initializeField()
    var firstMove = true

    while (true) {
        printField(field)

        print("Set/unset mines marks or claim a cell as free: > ")
        val input = readln().split(" ")
        if (input.size != 3) continue

        try {
            val col = input[0].toInt() - 1
            val row = input[1].toInt() - 1
            val command = input[2]

            if (row !in 0..8 || col !in 0..8) continue

            when (command) {
                "free" -> {
                    if (firstMove) {
                        placeMines(field, numMines, row, col)
                        firstMove = false
                    }

                    if (field.mines[row][col]) {
                        revealMines(field)
                        printField(field)
                        println("You stepped on a mine and failed!")
                        return
                    }

                    exploreCell(field, row, col)

                    if (checkWinByExploration(field)) {
                        printField(field)
                        println("Congratulations! You found all the mines!")
                        return
                    }
                }

                "mine" -> {
                    if (!field.explored[row][col]) {
                        field.marks[row][col] = !field.marks[row][col]

                        if (checkWinByMarking(field)) {
                            printField(field)
                            println("Congratulations! You found all the mines!")
                            return
                        }
                    }
                }
            }

        } catch (e: NumberFormatException) {
            continue
        }
    }
}

fun initializeField(): Field {
    return Field(
        Array(9) { Array(9) { '.' } },
        Array(9) { Array(9) { false } },
        Array(9) { Array(9) { false } },
        Array(9) { Array(9) { false } }
    )
}

fun placeMines(field: Field, numMines: Int, firstRow: Int, firstCol: Int) {
    var minesPlaced = 0
    while (minesPlaced < numMines) {
        val row = Random.nextInt(9)
        val col = Random.nextInt(9)

        if ((row == firstRow && col == firstCol) || field.mines[row][col]) {
            continue
        }

        field.mines[row][col] = true
        minesPlaced++
    }
}

fun countAdjacentMines(field: Field, row: Int, col: Int): Int {
    var count = 0
    for (r in maxOf(0, row - 1)..minOf(8, row + 1)) {
        for (c in maxOf(0, col - 1)..minOf(8, col + 1)) {
            if (field.mines[r][c] && (r != row || c != col)) count++
        }
    }
    return count
}

fun exploreCell(field: Field, row: Int, col: Int) {
    if (row !in 0..8 || col !in 0..8 || field.explored[row][col]) return

    field.explored[row][col] = true
    field.marks[row][col] = false

    val minesAround = countAdjacentMines(field, row, col)
    when {
        minesAround > 0 -> field.visible[row][col] = minesAround.toString()[0]
        else -> {
            field.visible[row][col] = '/'
            for (r in maxOf(0, row - 1)..minOf(8, row + 1)) {
                for (c in maxOf(0, col - 1)..minOf(8, col + 1)) {
                    if (!field.explored[r][c]) exploreCell(field, r, c)
                }
            }
        }
    }
}

fun revealMines(field: Field) {
    for (i in 0..8) {
        for (j in 0..8) {
            if (field.mines[i][j]) field.visible[i][j] = 'X'
        }
    }
}

fun checkWinByMarking(field: Field): Boolean {
    for (i in 0..8) {
        for (j in 0..8) {
            if (field.mines[i][j] != field.marks[i][j]) return false
        }
    }
    return true
}

fun checkWinByExploration(field: Field): Boolean {
    for (i in 0..8) {
        for (j in 0..8) {
            if (!field.mines[i][j] && !field.explored[i][j]) return false
        }
    }
    return true
}

fun printField(field: Field) {
    println("\n │123456789│")
    println("—│—————————│")
    for (i in 0..8) {
        print("${i + 1}│")
        for (j in 0..8) {
            print(when {
                field.marks[i][j] -> '*'
                field.explored[i][j] -> field.visible[i][j]
                else -> '.'
            })
        }
        println("│")
    }
    println("—│—————————│")
}
