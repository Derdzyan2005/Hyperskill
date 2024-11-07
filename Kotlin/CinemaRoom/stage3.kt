fun printCinema(rows: Int, seatsPerRow: Int, selectedRow: Int = -1, selectedSeat: Int = -1) {
    println("\nCinema:")

    print("  ")
    for (seatNum in 1..seatsPerRow) {
        print("$seatNum ")
    }
    println()

    for (row in 1..rows) {
        print("$row ")
        for (seat in 1..seatsPerRow) {
            if (row == selectedRow && seat == selectedSeat) {
                print("B ")
            } else {
                print("S ")
            }
        }
        println()
    }
}

fun calculateTicketPrice(rows: Int, seatsPerRow: Int, selectedRow: Int): Int {
    val totalSeats = rows * seatsPerRow

    return if (totalSeats <= 60) {
        10
    } else {
        val frontHalf = rows / 2
        if (selectedRow <= frontHalf) 10 else 8
    }
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()

    printCinema(rows, seatsPerRow)

    println("\nEnter a row number:")
    val selectedRow = readln().toInt()

    println("Enter a seat number in that row:")
    val selectedSeat = readln().toInt()

    val ticketPrice = calculateTicketPrice(rows, seatsPerRow, selectedRow)
    println("\nTicket price: \$$ticketPrice")

    printCinema(rows, seatsPerRow, selectedRow, selectedSeat)
}
