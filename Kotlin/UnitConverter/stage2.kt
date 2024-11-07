package converter

fun main() {
    println("Enter a number and a measure:")
    val input = readln().trim().split(" ")

    if (input.size != 2) {
        println("Wrong input")
        return
    }

    val number = input[0].toIntOrNull()
    val unit = input[1].lowercase()

    if (number == null || !isValidUnit(unit)) {
        println("Wrong input")
        return
    }

    val meters = number * 1000
    val unitString = if (number == 1) "kilometer" else "kilometers"

    println("$number $unitString is $meters meters")
}

fun isValidUnit(unit: String): Boolean {
    return unit == "km" || unit == "kilometer" || unit == "kilometers"
}
