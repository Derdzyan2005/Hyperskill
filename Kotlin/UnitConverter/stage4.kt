package converter

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val input = readlnOrNull()?.trim() ?: ""

        if (input.lowercase() == "exit") break

        processConversion(input)
    }
}

fun processConversion(input: String) {
    val parts = input.split(" ")
    if (parts.size != 4) {
        println("Invalid input format")
        return
    }

    val value = parts[0].toDoubleOrNull()
    if (value == null) {
        println("Invalid number input")
        return
    }

    val fromUnit = parts[1].lowercase()
    val toUnit = parts[3].lowercase()

    // Determine unit types and perform conversion
    val fromUnitType = determineUnitType(fromUnit)
    val toUnitType = determineUnitType(toUnit)

    if (fromUnitType == UnitType.UNKNOWN && toUnitType == UnitType.UNKNOWN) {
        println("Conversion from ??? to ??? is impossible")
        return
    }

    if (fromUnitType == UnitType.UNKNOWN) {
        println("Conversion from ??? to ${getPluralName(toUnit)} is impossible")
        return
    }

    if (toUnitType == UnitType.UNKNOWN) {
        println("Conversion from ${getPluralName(fromUnit)} to ??? is impossible")
        return
    }

    if (fromUnitType != toUnitType) {
        println("Conversion from ${getPluralName(fromUnit)} to ${getPluralName(toUnit)} is impossible")
        return
    }

    val result = when (fromUnitType) {
        UnitType.LENGTH -> convertLength(value, fromUnit, toUnit)
        UnitType.WEIGHT -> convertWeight(value, fromUnit, toUnit)
        else -> return
    }

    val targetUnitName = getAppropriateForm(toUnit, result)
    println("$value ${getAppropriateForm(fromUnit, value)} is $result $targetUnitName\n")
}

enum class UnitType {
    LENGTH, WEIGHT, UNKNOWN
}

fun determineUnitType(unit: String): UnitType = when (unit.lowercase()) {
    "m", "meter", "meters",
    "km", "kilometer", "kilometers",
    "cm", "centimeter", "centimeters",
    "mm", "millimeter", "millimeters",
    "mi", "mile", "miles",
    "yd", "yard", "yards",
    "ft", "foot", "feet",
    "in", "inch", "inches" -> UnitType.LENGTH

    "g", "gram", "grams",
    "kg", "kilogram", "kilograms",
    "mg", "milligram", "milligrams",
    "lb", "pound", "pounds",
    "oz", "ounce", "ounces" -> UnitType.WEIGHT

    else -> UnitType.UNKNOWN
}

fun convertLength(value: Double, fromUnit: String, toUnit: String): Double {
    val lengthRates = mapOf(
        "m" to 1.0, "meter" to 1.0, "meters" to 1.0,
        "km" to 1000.0, "kilometer" to 1000.0, "kilometers" to 1000.0,
        "cm" to 0.01, "centimeter" to 0.01, "centimeters" to 0.01,
        "mm" to 0.001, "millimeter" to 0.001, "millimeters" to 0.001,
        "mi" to 1609.35, "mile" to 1609.35, "miles" to 1609.35,
        "yd" to 0.9144, "yard" to 0.9144, "yards" to 0.9144,
        "ft" to 0.3048, "foot" to 0.3048, "feet" to 0.3048,
        "in" to 0.0254, "inch" to 0.0254, "inches" to 0.0254
    )

    val meters = value * (lengthRates[fromUnit] ?: 1.0)
    return meters / (lengthRates[toUnit] ?: 1.0)
}

fun convertWeight(value: Double, fromUnit: String, toUnit: String): Double {
    val weightRates = mapOf(
        "g" to 1.0, "gram" to 1.0, "grams" to 1.0,
        "kg" to 1000.0, "kilogram" to 1000.0, "kilograms" to 1000.0,
        "mg" to 0.001, "milligram" to 0.001, "milligrams" to 0.001,
        "lb" to 453.592, "pound" to 453.592, "pounds" to 453.592,
        "oz" to 28.3495, "ounce" to 28.3495, "ounces" to 28.3495
    )

    val grams = value * (weightRates[fromUnit] ?: 1.0)
    return grams / (weightRates[toUnit] ?: 1.0)
}

fun getAppropriateForm(unit: String, value: Double): String {
    val singularForms = mapOf(
        "m" to "meter", "meter" to "meter", "meters" to "meter",
        "km" to "kilometer", "kilometer" to "kilometer", "kilometers" to "kilometer",
        "cm" to "centimeter", "centimeter" to "centimeter", "centimeters" to "centimeter",
        "mm" to "millimeter", "millimeter" to "millimeter", "millimeters" to "millimeter",
        "mi" to "mile", "mile" to "mile", "miles" to "mile",
        "yd" to "yard", "yard" to "yard", "yards" to "yard",
        "ft" to "foot", "foot" to "foot", "feet" to "foot",
        "in" to "inch", "inch" to "inch", "inches" to "inch",
        "g" to "gram", "gram" to "gram", "grams" to "gram",
        "kg" to "kilogram", "kilogram" to "kilogram", "kilograms" to "kilogram",
        "mg" to "milligram", "milligram" to "milligram", "milligrams" to "milligram",
        "lb" to "pound", "pound" to "pound", "pounds" to "pound",
        "oz" to "ounce", "ounce" to "ounce", "ounces" to "ounce"
    )

    val pluralForms = mapOf(
        "meter" to "meters",
        "kilometer" to "kilometers",
        "centimeter" to "centimeters",
        "millimeter" to "millimeters",
        "mile" to "miles",
        "yard" to "yards",
        "foot" to "feet",
        "inch" to "inches",
        "gram" to "grams",
        "kilogram" to "kilograms",
        "milligram" to "milligrams",
        "pound" to "pounds",
        "ounce" to "ounces"
    )

    val singular = singularForms[unit.lowercase()] ?: return unit
    return if (value == 1.0) singular else (pluralForms[singular] ?: singular)
}

fun getPluralName(unit: String): String {
    val pluralNames = mapOf(
        "m" to "meters", "meter" to "meters", "meters" to "meters",
        "km" to "kilometers", "kilometer" to "kilometers", "kilometers" to "kilometers",
        "cm" to "centimeters", "centimeter" to "centimeters", "centimeters" to "centimeters",
        "mm" to "millimeters", "millimeter" to "millimeters", "millimeters" to "millimeters",
        "mi" to "miles", "mile" to "miles", "miles" to "miles",
        "yd" to "yards", "yard" to "yards", "yards" to "yards",
        "ft" to "feet", "foot" to "feet", "feet" to "feet",
        "in" to "inches", "inch" to "inches", "inches" to "inches",
        "g" to "grams", "gram" to "grams", "grams" to "grams",
        "kg" to "kilograms", "kilogram" to "kilograms", "kilograms" to "kilograms",
        "mg" to "milligrams", "milligram" to "milligrams", "milligrams" to "milligrams",
        "lb" to "pounds", "pound" to "pounds", "pounds" to "pounds",
        "oz" to "ounces", "ounce" to "ounces", "ounces" to "ounces"
    )
    return pluralNames[unit.lowercase()] ?: unit
}
