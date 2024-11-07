package machine

fun main() {

    val waterPerCoffee = 200
    val milkPerCoffee = 50
    val coffeeBeansPerCoffee = 15

    println("Write how many ml of water the coffee machine has:")
    val water = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milk = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val coffeeBeans = readln().toInt()

    println("Write how many cups of coffee you will need:")
    val cups = readln().toInt()

    val maxCups = minOf(water / waterPerCoffee, milk / milkPerCoffee, coffeeBeans / coffeeBeansPerCoffee)

    when {
        maxCups == cups -> println("Yes, I can make that amount of coffee")
        maxCups > cups -> println("Yes, I can make that amount of coffee (and even ${maxCups - cups} more than that)")
        else -> println("No, I can make only $maxCups cups of coffee")
    }

}

