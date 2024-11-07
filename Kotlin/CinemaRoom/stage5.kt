class Cinema(private val rows: Int, private val seatsPerRow: Int) {
    private val seats = Array(rows) { Array(seatsPerRow) { 'S' } }
    private var purchasedTickets = 0
    private var currentIncome = 0

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
        while (true) {
            println("\nEnter a row number:")
            val selectedRow = readln().toInt()

            println("Enter a seat number in that row:")
            val selectedSeat = readln().toInt()

            if (selectedRow < 1 || selectedRow > rows ||
                selectedSeat < 1 || selectedSeat > seatsPerRow) {
                println("\nWrong input!")
                continue
            }

            if (seats[selectedRow-1][selectedSeat-1] == 'B') {
                println("\nThat ticket has already been purchased!")
                continue
            }

            val ticketPrice = calculateTicketPrice(selectedRow)
            println("\nTicket price: \$$ticketPrice")

            seats[selectedRow-1][selectedSeat-1] = 'B'
            purchasedTickets++
            currentIncome += ticketPrice
            break
        }
    }

    fun showStatistics() {
        val totalSeats = rows * seatsPerRow
        val percentage = purchasedTickets.toDouble() / totalSeats * 100
        val formatPercentage = "%.2f".format(percentage)

        println("\nNumber of purchased tickets: $purchasedTickets")
        println("Percentage: $formatPercentage%")
        println("Current income: \$$currentIncome")
        println("Total income: \$${calculateTotalIncome()}")
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

    private fun calculateTotalIncome(): Int {
        val totalSeats = rows * seatsPerRow
        return if (totalSeats <= 60) {
            totalSeats * 10
        } else {
            val frontHalf = rows / 2
            val backHalf = rows - frontHalf
            (frontHalf * seatsPerRow * 10) + (backHalf * seatsPerRow * 8)
        }
    }
}

fun printMenu() {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
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
            3 -> cinema.showStatistics()
            0 -> break
        }
    }
}
