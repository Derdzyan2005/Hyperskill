package machine

class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var coffee = 120
    private var cups = 9
    private var money = 550
    private var state = "choosing action"

    fun input(command: String) {
        when (state) {
            "choosing action" -> handleAction(command)
            "choosing coffee" -> handleCoffeeChoice(command)
            "filling water" -> handleFillWater(command)
            "filling milk" -> handleFillMilk(command)
            "filling coffee" -> handleFillCoffee(command)
            "filling cups" -> handleFillCups(command)
        }
    }

    private fun handleAction(action: String) {
        when (action) {
            "buy" -> {
                state = "choosing coffee"
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            }
            "fill" -> {
                state = "filling water"
                println("Write how many ml of water you want to add:")
            }
            "take" -> {
                println("I gave you \$$money")
                money = 0
                prompt()
            }
            "remaining" -> {
                printState()
                prompt()
            }
            "exit" -> return
        }
    }

    private fun handleCoffeeChoice(choice: String) {
        when (choice) {
            "1" -> makeCoffee(250, 0, 16, 4)
            "2" -> makeCoffee(350, 75, 20, 7)
            "3" -> makeCoffee(200, 100, 12, 6)
            "back" -> prompt()
        }
    }

    private fun makeCoffee(waterNeeded: Int, milkNeeded: Int, coffeeNeeded: Int, price: Int) {
        if (water >= waterNeeded && milk >= milkNeeded && coffee >= coffeeNeeded && cups >= 1) {
            println("I have enough resources, making you a coffee!")
            water -= waterNeeded
            milk -= milkNeeded
            coffee -= coffeeNeeded
            cups -= 1
            money += price
        } else {
            if (water < waterNeeded) println("Sorry, not enough water!")
            if (milk < milkNeeded) println("Sorry, not enough milk!")
            if (coffee < coffeeNeeded) println("Sorry, not enough coffee beans!")
            if (cups < 1) println("Sorry, not enough disposable cups!")
        }
        prompt()
    }

    private fun handleFillWater(amount: String) {
        water += amount.toInt()
        state = "filling milk"
        println("Write how many ml of milk you want to add:")
    }

    private fun handleFillMilk(amount: String) {
        milk += amount.toInt()
        state = "filling coffee"
        println("Write how many grams of coffee beans you want to add:")
    }

    private fun handleFillCoffee(amount: String) {
        coffee += amount.toInt()
        state = "filling cups"
        println("Write how many disposable cups you want to add:")
    }

    private fun handleFillCups(amount: String) {
        cups += amount.toInt()
        prompt()
    }

    private fun printState() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffee g of coffee beans")
        println("$cups disposable cups")
        println("\$$money of money")
    }

    private fun prompt() {
        state = "choosing action"
        println("Write action (buy, fill, take, remaining, exit):")
    }
}

fun main() {
    val machine = CoffeeMachine()
    while (true) {
        val input = readlnOrNull() ?: break
        if (input == "exit") break
        machine.input(input)
    }
}
