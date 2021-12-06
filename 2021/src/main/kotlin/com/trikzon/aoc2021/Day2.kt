package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day2.txt")

    benchmark(Part.One, ::dayTwoPartOne, input, 2039912, 50000)
    benchmark(Part.Two, ::dayTwoPartTwo, input, 1942068080, 50000)
}

fun dayTwoPartOne(input: String): Int {
    val commands = input.lines().map { line ->
        val words = line.split(' ')
        Pair(words[0], words[1].toInt())
    }
    var horizontalPosition = 0
    var depth = 0
    commands.forEach { command ->
        when (command.first) {
            "forward" -> horizontalPosition += command.second
            "down"    -> depth += command.second
            "up"      -> depth -= command.second
        }
    }
    return horizontalPosition * depth
}

fun dayTwoPartTwo(input: String): Int {
    val commands = input.lines().map { line ->
        val words = line.split(' ')
        Pair(words[0], words[1].toInt())
    }
    var horizontalPosition = 0
    var depth = 0
    var aim = 0
    commands.forEach { command ->
        when (command.first) {
            "down" -> aim += command.second
            "up"   -> aim -= command.second
            "forward" -> {
                horizontalPosition += command.second
                depth += command.second * aim
            }
        }
    }
    return horizontalPosition * depth
}

