package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day1.txt")

    benchmark(Part.One, ::dayOnePartOne, input, 1624)
    benchmark(Part.Two, ::dayOnePartTwo, input, 1653)
}

fun dayOnePartOne(input: String): Int {
    val depths = input.lines().map { line -> line.toInt() }

    var numberOfIncreases = 0
    for (i in 1 until depths.size) {
        if (depths[i] > depths[i - 1]) {
            numberOfIncreases++
        }
    }
    return numberOfIncreases
}

fun dayOnePartTwo(input: String): Int {
    val depths = input.lines().map { line -> line.toInt() }

    var numberOfIncreases = 0
    for (i in 1 until depths.size - 2) {
        val iPlusNext = depths[i] + depths[i + 1]

        val firstSum = depths[i - 1] + iPlusNext
        val secondSum = iPlusNext + depths[i + 2]

        if (firstSum < secondSum) {
            numberOfIncreases++
        }
    }
    return numberOfIncreases
}
