import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field? > ")
    val numMines = readln().toInt()
    val field = generateField(numMines)
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

fun printField(field: Array<Array<Char>>) {
    for (row in field) {
        println(row.joinToString(""))
    }
}
