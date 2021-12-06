package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day6.txt")

    benchmark(Part.One, ::daySixPartOne, input, 5934)
    benchmark(Part.Two, ::daySixPartTwo, input, 377263)
}

// Note: This was a slow naive solution. Part 2 is much faster. I'm keeping
// this here to show my thought process.
fun daySixPartOne(input: String): Int {
    val list = ArrayList<Int>()
    list.addAll(input.split(',').map { str -> str.toInt() })

    for (i in 0 until 80) {
        for (j in list.indices) {
            if (list[j] == 0) {
                list[j] = 6
                list.add(8)
            } else {
                list[j]--
            }
        }
    }
    return list.size
}

fun daySixPartTwo(input: String): Long {
    val fishOfAges = Array(9) { 0L }
    input.split(',').map { str -> str.toInt() }.forEach { num -> fishOfAges[num]++ }

    for (i in 0 until 256) {
        val fishOfAgeZero = fishOfAges[0]
        for (j in 1 until fishOfAges.size) {
            fishOfAges[j - 1] = fishOfAges[j]
        }
        fishOfAges[8] = fishOfAgeZero
        fishOfAges[6] += fishOfAgeZero
    }
    var totalFish = 0L
    for (fish in fishOfAges) {
        totalFish += fish
    }
    return totalFish
}
