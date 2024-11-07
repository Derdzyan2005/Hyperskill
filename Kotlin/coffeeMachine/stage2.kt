package machine

fun main() {
    val waterPerCoffee = 200
    val milkPerCoffee = 50
    val beansPerCoffee = 15

    println("Write how many cups of coffee you will need:")
    val cups = readln().toInt()
    println("For $cups cups of coffee you will need:")
    println("${waterPerCoffee*cups} ml of water")
    println("${milkPerCoffee*cups} ml of milk")
    println("${beansPerCoffee*cups} g of coffee beans")
}

