package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day8.txt")
    benchmark(Part.One, ::day8Part1, input, 512, 50000)
    benchmark(Part.Two, ::day8Part2, input, 1091165, 5000)
}

fun day8Part1(input: String): Int {
    var instances = 0
    input.lines().forEach { line ->
        val outputValues = line.split(" | ")[1].split(' ').map { str -> str.trim() }
        for (outputValue in outputValues) {
            if (outputValue.length == 2 || outputValue.length == 4 || outputValue.length == 3 || outputValue.length == 7) {
                instances++
            }
        }
    }
    return instances
}

fun day8Part2(input: String): Int {
    var output = 0
    input.lines().forEach { line ->
        val inputValues = line.split(" | ")[0].split(' ')
        val digits = Array(10) { "" }
        val fiveSegments = ArrayList<String>()
        val sixSegments = ArrayList<String>()
        for (inputValue in inputValues) {
            when (inputValue.length) {
                2 -> digits[1] = inputValue
                4 -> digits[4] = inputValue
                3 -> digits[7] = inputValue
                7 -> digits[8] = inputValue
                5 -> fiveSegments.add(inputValue)
                6 -> sixSegments.add(inputValue)
            }
        }
        var c: Char? = null
        var f: Char? = null

        // Decode c and f
        var occurrences = 0
        for (sixSegment in sixSegments) {
            if (sixSegment.contains(digits[1][0])) occurrences++
        }
        if (occurrences == 3) {
            c = digits[1][1]
            f = digits[1][0]
        } else {
            c = digits[1][0]
            f = digits[1][1]
        }
        // Find 6
        for (sixSegment in sixSegments) {
            if (!sixSegment.contains(c)) {
                digits[6] = sixSegment
            }
        }
        // Find 2, 3, and 5
        for (fiveSegment in fiveSegments) {
            if (fiveSegment.contains(c)) {
                if (fiveSegment.contains(f)) {
                    digits[3] = fiveSegment
                } else {
                    digits[2] = fiveSegment
                }
            } else {
                digits[5] = fiveSegment
            }
        }
        val adg = digits[2].chars()
            .filter { char -> digits[3].contains(char.toChar()) && digits[5].contains(char.toChar()) }
            .toArray()
            .map { i -> i.toChar() }
        // Find 0 and 9
        for (sixSegment in sixSegments) {
            if (sixSegment != digits[6]) {
                if (sixSegment.contains(adg[0]) && sixSegment.contains(adg[1]) && sixSegment.contains(adg[2])) {
                    digits[9] = sixSegment
                } else {
                    digits[0] = sixSegment
                }
            }
        }
        val outputValues = line.split(" | ")[1].split(' ')
        val outputDigits = Array(4) { 0 }
        for (i in outputValues.indices) {
            for (j in digits.indices) {
                var numberMatching = 0
                for (char in digits[j].chars()) {
                    if (outputValues[i].contains(char.toChar())) numberMatching++
                }
                if (numberMatching == outputValues[i].length && numberMatching == digits[j].length) {
                    outputDigits[i] = j
                    break
                }
            }
        }
        output += outputDigits[0] * 1000 + outputDigits[1] * 100 + outputDigits[2] * 10 + outputDigits[3]
    }
    return output
}
