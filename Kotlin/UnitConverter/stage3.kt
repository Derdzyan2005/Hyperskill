package converter

fun main() {
    val conversionRates = mapOf(
        "meter" to 1.0,
        "meters" to 1.0,
        "m" to 1.0,
        "kilometer" to 1000.0,
        "kilometers" to 1000.0,
        "km" to 1000.0,
        "centimeter" to 0.01,
        "centimeters" to 0.01,
        "cm" to 0.01,
        "millimeter" to 0.001,
        "millimeters" to 0.001,
        "mm" to 0.001,
        "mile" to 1609.35,
        "miles" to 1609.35,
        "mi" to 1609.35,
        "yard" to 0.9144,
        "yards" to 0.9144,
        "yd" to 0.9144,
        "foot" to 0.3048,
        "feet" to 0.3048,
        "ft" to 0.3048,
        "inch" to 0.0254,
        "inches" to 0.0254,
        "in" to 0.0254
    )

    print("Enter a number and a measure of length: ")
    val input = readlnOrNull()?.trim() ?: ""

    val inputParts = input.split(" ")
    if (inputParts.size != 2) {
        println("Wrong input format")
        return
    }

    val value = inputParts[0].toDoubleOrNull()
    val unit = inputParts[1].lowercase()

    if (value == null) {
        println("Wrong input format")
        return
    }

    val conversionRate = conversionRates[unit]
    if (conversionRate == null) {
        println("Wrong input. Unknown unit $unit")
        return
    }

    val valueInMeters = value * conversionRate
    val inputUnitFormatted = when (unit) {
        "m", "meter", "meters" -> if (value == 1.0) "meter" else "meters"
        "km", "kilometer", "kilometers" -> if (value == 1.0) "kilometer" else "kilometers"
        "cm", "centimeter", "centimeters" -> if (value == 1.0) "centimeter" else "centimeters"
        "mm", "millimeter", "millimeters" -> if (value == 1.0) "millimeter" else "millimeters"
        "mi", "mile", "miles" -> if (value == 1.0) "mile" else "miles"
        "yd", "yard", "yards" -> if (value == 1.0) "yard" else "yards"
        "ft", "foot", "feet" -> if (value == 1.0) "foot" else "feet"
        "in", "inch", "inches" -> if (value == 1.0) "inch" else "inches"
        else -> unit
    }

    val metersUnit = if (valueInMeters == 1.0) "meter" else "meters"

    println("$value $inputUnitFormatted is $valueInMeters $metersUnit")
}
