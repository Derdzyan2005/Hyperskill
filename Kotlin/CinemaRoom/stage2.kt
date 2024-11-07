fun calculateTotalIncome(rows: Int, seatsPerRow: Int): Int {
    val totalSeats = rows * seatsPerRow

    return if (totalSeats <= 60) {
        totalSeats * 10
    } else {
        val frontHalf = rows / 2
        val backHalf = rows - frontHalf

        val frontIncome = frontHalf * seatsPerRow * 10
        val backIncome = backHalf * seatsPerRow * 8

        frontIncome + backIncome
    }
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()

    val totalIncome = calculateTotalIncome(rows, seatsPerRow)
    println("Total income:\n$$totalIncome")
}
