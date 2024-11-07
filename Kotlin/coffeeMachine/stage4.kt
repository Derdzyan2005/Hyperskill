package machine

fun main() {
    var water = 400
    var milk = 540
    var coffee = 120
    var cups = 9
    var money = 550

    fun printState() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffee g of coffee beans")
        println("$cups disposable cups")
        println("\$$money of money")
    }

    fun buyCoffee(type: Int) {
        when (type) {
            1 -> {
                if (water >= 250 && coffee >= 16 && cups >= 1) {
                    water -= 250
                    coffee -= 16
                    cups -= 1
                    money += 4
                } else {
                    println("Not enough resources to make espresso.")
                }
            }
            2 -> {
                if (water >= 350 && milk >= 75 && coffee >= 20 && cups >= 1) {
                    water -= 350
                    milk -= 75
                    coffee -= 20
                    cups -= 1
                    money += 7
                } else {
                    println("Not enough resources to make latte.")
                }
            }
            3 -> {
                if (water >= 200 && milk >= 100 && coffee >= 12 && cups >= 1) {
                    water -= 200
                    milk -= 100
                    coffee -= 12
                    cups -= 1
                    money += 6
                } else {
                    println("Not enough resources to make cappuccino.")
                }
            }
        }
    }

    fun fillMachine(addWater: Int, addMilk: Int, addCoffee: Int, addCups: Int) {
        water += addWater
        milk += addMilk
        coffee += addCoffee
        cups += addCups
    }

    fun takeMoney() {
        println("I gave you \$$money")
        money = 0
    }

    printState()

    println("Write action (buy, fill, take):")
    when (readln()) {
        "buy" -> {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
            val number = readln().toInt()
            buyCoffee(number)
        }
        "fill" -> {
            println("Write how many ml of water you want to add: ")
            val addWater = readln().toInt()
            println("Write how many ml of milk you want to add: ")
            val addMilk = readln().toInt()
            println("Write how many grams of coffee beans you want to add: ")
            val addCoffee = readln().toInt()
            println("Write how many disposable cups you want to add: ")
            val addCups = readln().toInt()
            fillMachine(addWater, addMilk, addCoffee, addCups)
        }
        "take" -> {
            takeMoney()
        }
    }

    printState()
}
