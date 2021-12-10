package com.trikzon.aoc2021

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = getInputStringFromFile("/day10.txt")
    benchmark(Part.One, ::day10Part1, input, 436497, 50000)
    benchmark(Part.One, ::day10Part2, input, 2377613374, 50000)
}

fun day10Part1(input: String): Int {
    var errorScore = 0
    input.lines().forEach { line ->
        val stack = Stack<Char>()
        for (i in line.indices) {
            when (line[i]) {
                '(' -> stack.push(line[i])
                '[' -> stack.push(line[i])
                '{' -> stack.push(line[i])
                '<' -> stack.push(line[i])

                ')' -> if (stack.pop() != '(') { errorScore += 3; break }
                ']' -> if (stack.pop() != '[') { errorScore += 57; break }
                '}' -> if (stack.pop() != '{') { errorScore += 1197; break }
                '>' -> if (stack.pop() != '<') { errorScore += 25137; break }
            }
        }
    }
    return errorScore
}

fun day10Part2(input: String): Long {
    val scores = ArrayList<Long>()
    input.lines().forEach { line ->
        val stack = Stack<Char>()
        var isCorrupt = false
        for (i in line.indices) {
            when (line[i]) {
                '(' -> stack.push(line[i])
                '[' -> stack.push(line[i])
                '{' -> stack.push(line[i])
                '<' -> stack.push(line[i])

                ')' -> if (stack.pop() != '(') { isCorrupt = true; break }
                ']' -> if (stack.pop() != '[') { isCorrupt = true; break }
                '}' -> if (stack.pop() != '{') { isCorrupt = true; break }
                '>' -> if (stack.pop() != '<') { isCorrupt = true; break }
            }
        }
        if (!isCorrupt) {
            var score = 0L
            while (!stack.isEmpty()) {
                when (stack.pop()) {
                    '(' -> score = (score * 5) + 1
                    '[' -> score = (score * 5) + 2
                    '{' -> score = (score * 5) + 3
                    '<' -> score = (score * 5) + 4
                }
            }
            scores.add(score)
        }
    }
    scores.sort()
    return scores[scores.size / 2]
}
