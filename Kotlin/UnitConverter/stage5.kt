package converter

import java.util.*

enum class UnitType {LENGTH, WEIGHT, TEMPERATURE, UNKNOWN}

enum class Unit(val type: UnitType, val normalizedName: String, val factor: Double) {
    METER(UnitType.LENGTH, "meter", 1.0),
    KILOMETER(UnitType.LENGTH, "kilometer", 1_000.0),
    CENTIMETER(UnitType.LENGTH, "centimeter", 0.01),
    MILLIMETER(UnitType.LENGTH, "millimeter", 0.001),
    MILE(UnitType.LENGTH, "mile", 1_609.35),
    YARD(UnitType.LENGTH, "yard", 0.9144),
    FOOT(UnitType.LENGTH, "foot", 0.3048),
    INCH(UnitType.LENGTH, "inch", 0.0254),

    GRAM(UnitType.WEIGHT, "gram", 1.0),
    KILOGRAM(UnitType.WEIGHT, "kilogram", 1_000.0),
    MILLIGRAM(UnitType.WEIGHT, "milligram", 0.001),
    POUND(UnitType.WEIGHT, "pound", 453.592),
    OUNCE(UnitType.WEIGHT, "ounce", 28.3495),

    CELSIUS(UnitType.TEMPERATURE, "degree Celsius", 0.0),
    KELVIN(UnitType.TEMPERATURE, "kelvin", 0.0),
    FAHRENHEIT(UnitType.TEMPERATURE, "degree Fahrenheit", 0.0),

    UNKNOWN(UnitType.UNKNOWN, "???", 0.0);
}

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")

        val input = readln().trimStart().lowercase(Locale.getDefault())
        if (input == "exit") {
            break
        }

        val parsedInput = processInput(input)
        if (parsedInput == null) {
            println("Parse error")
            continue
        }

        val (fromValue, from, to) = parsedInput


        if (from == Unit.UNKNOWN || to == Unit.UNKNOWN) {
            val displayFrom = plural(from.normalizedName)
            val displayTo = plural(to.normalizedName)

            println("Conversion from $displayFrom to $displayTo is impossible")
            continue
        }


        if (from.type != to.type) {
            println("Conversion from ${plural(from.normalizedName)} to ${plural(to.normalizedName)} is impossible")
            continue
        }
        if (fromValue < 0.0) {
            if (from.type == UnitType.LENGTH) {
                println("Length shouldn't be negative")
                continue
            } else if (from.type == UnitType.WEIGHT) {
                println("Weight shouldn't be negative")
                continue
            }
        }

        var result = 0.0

        if (from.type == UnitType.TEMPERATURE && to.type == UnitType.TEMPERATURE) {
            when {
                from == Unit.FAHRENHEIT && to == Unit.FAHRENHEIT -> result = fromValue
                from == Unit.CELSIUS && to == Unit.CELSIUS -> result = fromValue
                from == Unit.KELVIN && to == Unit.KELVIN -> result = fromValue
                from == Unit.FAHRENHEIT && to == Unit.CELSIUS -> result = (fromValue - 32.0) * 5.0 / 9.0
                from == Unit.CELSIUS && to == Unit.FAHRENHEIT -> result = fromValue * 9.0 / 5.0 + 32.0
                from == Unit.CELSIUS && to == Unit.KELVIN -> result = fromValue + 273.15
                from == Unit.KELVIN && to == Unit.CELSIUS -> result = fromValue - 273.15
                from == Unit.KELVIN && to == Unit.FAHRENHEIT -> result = fromValue * 9.0 / 5.0 - 459.67
                from == Unit.FAHRENHEIT && to == Unit.KELVIN -> result = (fromValue + 459.67) * 5.0 / 9.0
            }
        } else {
            result = fromValue * from.factor / to.factor
        }

        val displayFromUnit = if (fromValue == 1.0) from.normalizedName else plural(from.normalizedName)
        val displayResultUnit = if (result == 1.0) to.normalizedName else plural(to.normalizedName)

        println("$fromValue $displayFromUnit is $result $displayResultUnit")
    }
}

fun processInput(input: String): Triple<Double, Unit, Unit>? {
    val cleanedInput = input
        .replace("degrees ", "")
        .replace("degree ", "")

    val (fromValue, fromUnit, _, toUnit) = cleanedInput.split(" ")
    val from = getNormalizedUnit(fromUnit)
    val to = getNormalizedUnit(toUnit)

    return try {
        Triple(fromValue.toDouble(), from, to)
    } catch (_: NumberFormatException) {
        null
    }
}

fun getNormalizedUnit(unit :String): Unit {
    return when (unit.lowercase(Locale.getDefault())) {
        "m", "meter", "meters" -> Unit.METER
        "km", "kilometer", "kilometers" -> Unit.KILOMETER
        "cm", "centimeter", "centimeters" -> Unit.CENTIMETER
        "mm", "millimeter", "millimeters" -> Unit.MILLIMETER
        "mi", "mile", "miles" -> Unit.MILE
        "yd", "yard", "yards" -> Unit.YARD
        "ft", "foot", "feet" -> Unit.FOOT
        "in", "inch", "inches" -> Unit.INCH
        "g", "gram", "grams" -> Unit.GRAM
        "kg", "kilogram", "kilograms" -> Unit.KILOGRAM
        "mg", "milligram", "milligrams" -> Unit.MILLIGRAM
        "lb", "pound", "pounds" -> Unit.POUND
        "oz", "ounce", "ounces" -> Unit.OUNCE
        "c", "dc", "celsius" -> Unit.CELSIUS
        "k", "kelvin", "kelvins" -> Unit.KELVIN
        "f", "df", "fahrenheit" -> Unit.FAHRENHEIT
        else -> Unit.UNKNOWN
    }
}

fun plural(unit: String) = when (unit) {
    "foot" -> "feet"
    "inch" -> "inches"
    "degree Celsius" -> "degrees Celsius"
    "degree Fahrenheit" -> "degrees Fahrenheit"
    "???" -> "???"
    else -> unit + "s"
}
