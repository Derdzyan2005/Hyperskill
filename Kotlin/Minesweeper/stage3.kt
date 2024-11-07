import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field? > ")
    val numMines = readln().toInt()
    val field = generateField(numMines)
    addHints(field)
    printField(field)
}

fun generateField(numMines: Int): Array<Array<Char>> {
    val field = Array(9) { Array(9) { '.' } }

    var minesPlaced = 0
    while (minesPlaced < numMines) {
        val row = Random.nextInt(9)
        val col = Random.nextInt(9)

        if (field[row][col] == '.') {
            field[row][col] = 'X'
            minesPlaced++
        }
    }

    return field
}

fun addHints(field: Array<Array<Char>>) {
    for (row in field.indices) {
        for (col in field[row].indices) {
            if (field[row][col] == 'X') continue

            val mineCount = countAdjacentMines(field, row, col)

            if (mineCount > 0) {
                field[row][col] = mineCount.toString()[0]
            }
        }
    }
}

fun countAdjacentMines(field: Array<Array<Char>>, row: Int, col: Int): Int {
    var count = 0

    for (r in maxOf(0, row - 1)..minOf(field.lastIndex, row + 1)) {
        for (c in maxOf(0, col - 1)..minOf(field[0].lastIndex, col + 1)) {
            if (r == row && c == col) continue

            if (field[r][c] == 'X') count++
        }
    }

    return count
}

fun printField(field: Array<Array<Char>>) {
    for (row in field) {
        println(row.joinToString(""))
    }
}
