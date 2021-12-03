package com.trikzon.aoc2021

import kotlin.math.pow

fun main() {
    val input = getInputStringFromFile("/day3.txt")

    println(dayThreePartTwo(input))
}

fun dayThreePartOne(input: String): Int {
    val lines = input.lines()
    var gammaRate = 0
    var epsilonRate = 0
    for (i in 0 until lines[0].length) {
        val power = lines[0].length - i - 1
        if (getCommonBit(i, lines) == 1) {
            gammaRate += 2.0.pow(power).toInt()
        } else {
            epsilonRate += 2.0.pow(power).toInt()
        }
    }
    return gammaRate * epsilonRate
}

fun dayThreePartTwo(input: String): Int {
    val lines = input.lines()
    var oxygenLines = lines
    var co2Lines = lines
    var oxygen = ""
    var co2 = ""
    for (i in 0 until lines[0].length) {
        val commonBit = getCommonBit(i, oxygenLines)
        if (commonBit == -1) {
            println(i)
            oxygen = oxygenLines.firstOrNull { line -> line[i].digitToInt() == 1 } ?: oxygenLines[0]
            break
        }
        oxygenLines = oxygenLines.filter { line -> line[i].digitToInt() == commonBit }
        if (oxygenLines.size == 1) {
            oxygen = oxygenLines[0]
            break
        } else if (oxygenLines.size == 2 && i + 1 < lines[0].length) {
            oxygen = if (oxygenLines[0][i + 1].digitToInt() == 1) {
                oxygenLines[0]
            } else {
                oxygenLines[1]
            }
            break
        }
    }
    for (i in 0 until lines[0].length) {
        val commonBit = getCommonBit(i, co2Lines)
        if (commonBit == -1) {
            co2 = co2Lines.firstOrNull { line -> line[i].digitToInt() == 0 } ?: co2Lines[0]
            break
        }
        co2Lines = co2Lines.filter { line -> line[i].digitToInt() != commonBit }
        if (co2Lines.size == 1) {
            co2 = co2Lines[0]
            break
        } else if (co2Lines.size == 2 && i + 1 < lines[0].length) {
            co2 = if (co2Lines[0][i + 1].digitToInt() == 0) {
                co2Lines[0]
            } else {
                co2Lines[1]
            }
            break
        }
    }
    println("$oxygen, $co2")
    println("${oxygen.toInt(2)}, ${co2.toInt(2)}")
    return oxygen.toInt(2) * co2.toInt(2)
}

fun getCommonBit(index: Int, lines: List<String>): Int {
    val count = lines.count { line -> line[index].digitToInt() == 1 }
    return if (count > lines.size / 2) {
        1
    } else if (count.toFloat() == lines.size / 2f) {
        -1
    } else {
        0
    }
}
