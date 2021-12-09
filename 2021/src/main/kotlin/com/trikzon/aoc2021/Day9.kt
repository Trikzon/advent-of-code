package com.trikzon.aoc2021

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val input = getInputStringFromFile("/day9.txt")
    benchmark(Part.One, ::day9Part1, input, 498, 50)
    benchmark(Part.Two, ::day9Part2, input, 1071000, 50)
}

fun day9Part1(input: String): Int {
    val grid = Array(input.lines().size) { Array(input.lines()[0].length) { 0 } }
    for (x in input.lines().indices) {
        for (y in input.lines()[x].indices) {
            grid[x][y] = input.lines()[x][y].digitToInt()
        }
    }
    var riskLevels = 0
    for (x in grid.indices) {
        for (y in grid[0].indices) {
            val height = grid[x][y]
            var lowest = true
            if (x > 0) {
                if (grid[x - 1][y] <= height) {
                    lowest = false
                }
            }
            if (y > 0) {
                if (grid[x][y - 1] <= height) {
                    lowest = false
                }
            }
            if (x < grid.size - 1) {
                if (grid[x + 1][y] <= height) {
                    lowest = false
                }
            }
            if (y < grid[0].size - 1) {
                if (grid[x][y + 1] <= height) {
                    lowest = false
                }
            }
            if (lowest) {
                riskLevels += height + 1
            }
        }
    }
    return riskLevels
}

fun day9Part2(input: String): Int {
    val grid = Array(input.lines().size) { Array(input.lines()[0].length) { false } }
    for (x in input.lines().indices) {
        for (y in input.lines()[x].indices) {
            grid[x][y] = input.lines()[x][y].digitToInt() == 9
        }
    }
    val basins = ArrayList<Int>()
    for (x in grid.indices) {
        for (y in grid[0].indices) {
            if (grid[x][y]) continue
            grid[x][y] = true
            val coordStack = Stack<Pair<Int, Int>>().apply { push(Pair(x, y)) }
            var size = 1
            while (!coordStack.empty()) {
                val (peekX, peekY) = coordStack.peek()
                var neighbors = 0
                if (peekX > 0) {
                    if (!grid[peekX - 1][peekY]) {
                        neighbors += 1
                        grid[peekX - 1][peekY] = true
                        coordStack.push(Pair(peekX - 1, peekY))
                    }
                }
                if (peekY > 0) {
                    if (!grid[peekX][peekY - 1]) {
                        neighbors += 1
                        grid[peekX][peekY - 1] = true
                        coordStack.push(Pair(peekX, peekY - 1))
                    }
                }
                if (peekX < grid.size - 1) {
                    if (!grid[peekX + 1][peekY]) {
                        neighbors += 1
                        grid[peekX + 1][peekY] = true
                        coordStack.push(Pair(peekX + 1, peekY))
                    }
                }
                if (peekY < grid[0].size - 1) {
                    if (!grid[peekX][peekY + 1]) {
                        neighbors += 1
                        grid[peekX][peekY + 1] = true
                        coordStack.push(Pair(peekX, peekY + 1))
                    }
                }
                size += neighbors
                if (neighbors == 0) {
                    coordStack.pop()
                }
            }
            basins.add(size)
        }
    }
    val largestBasins = Array(3) { 0 }
    for (i in 0 until 3) {
        var largest = 0
        for (basin in basins) {
            if (basin > largest) {
                largest = basin
            }
        }
        largestBasins[i] = largest
        basins.remove(largest)
    }
    return largestBasins[0] * largestBasins[1] * largestBasins[2]
}
