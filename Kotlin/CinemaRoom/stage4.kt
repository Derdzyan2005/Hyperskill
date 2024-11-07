class Cinema(private val rows: Int, private val seatsPerRow: Int) {
    private val seats = Array(rows) { Array(seatsPerRow) { 'S' } }

    fun printSeats() {
        println("\nCinema:")

        print("  ")
        for (seatNum in 1..seatsPerRow) {
            print("$seatNum ")
        }
        println()


        for (row in 1..rows) {
            print("$row ")
            for (seat in 1..seatsPerRow) {
                print("${seats[row-1][seat-1]} ")
            }
            println()
        }
        println()
    }

    fun buyTicket() {
        println("\nEnter a row number:")
        val selectedRow = readln().toInt()

        println("Enter a seat number in that row:")
        val selectedSeat = readln().toInt()


        val ticketPrice = calculateTicketPrice(selectedRow)
        println("Ticket price: \$$ticketPrice")


        seats[selectedRow-1][selectedSeat-1] = 'B'
    }

    private fun calculateTicketPrice(selectedRow: Int): Int {
        val totalSeats = rows * seatsPerRow

        return if (totalSeats <= 60) {
            10
        } else {
            val frontHalf = rows / 2
            if (selectedRow <= frontHalf) 10 else 8
        }
    }
}

fun printMenu() {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("0. Exit")
}

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()

    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()

    val cinema = Cinema(rows, seatsPerRow)

    while (true) {
        printMenu()
        when (readln().toInt()) {
            1 -> cinema.printSeats()
            2 -> cinema.buyTicket()
            0 -> break
        }
    }
}
