package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day11.txt")
    benchmark(Part.One, ::day11Part1, input, 1688, 50000)
    benchmark(Part.Two, ::day11Part2, input, 403, 50000)
}

var flashes = 0

fun day11Part1(input: String): Int {
    val grid = Array(10) { Array(10) { 0 } }
    for (x in input.lines().indices) {
        for (y in input.lines()[x].indices) {
            grid[x][y] = input.lines()[x][y].digitToInt()
        }
    }
    for (i in 0 until 100) {
        // Increase all by 1
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                grid[x][y]++
            }
        }
        // Flash
        val flashed = ArrayList<Pair<Int, Int>>()
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                if (grid[x][y] > 9) {
                    flash(grid, x, y, flashed)
                }
            }
        }
        // Set to 0
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                if (grid[x][y] > 9) {
                    grid[x][y] = 0
                }
            }
        }
    }
    return flashes
}

fun flash(grid: Array<Array<Int>>, x: Int, y: Int, flashed: ArrayList<Pair<Int, Int>>) {
    if (flashed.contains(Pair(x, y))) return
    flashed.add(Pair(x, y))
    flashes++
    if (x > 0) {
        if (y > 0 && grid[x - 1][y - 1] <= 9) {
            grid[x - 1][y - 1]++
            if (grid[x - 1][y - 1] > 9) flash(grid, x - 1, y - 1, flashed)
        }
        if (grid[x - 1][y] <= 9) {
            grid[x - 1][y]++
            if (grid[x - 1][y] > 9) flash(grid, x - 1, y, flashed)
        }
        if (y < 9 && grid[x - 1][y + 1] <= 9) {
            grid[x - 1][y + 1]++
            if (grid[x - 1][y + 1] > 9) flash(grid, x - 1, y + 1, flashed)
        }
    }
    if (y > 0 && grid[x][y - 1] <= 9) {
        grid[x][y - 1]++
        if (grid[x][y - 1] > 9) flash(grid, x, y - 1, flashed)
    }
    if (y < 9 && grid[x][y + 1] <= 9) {
        grid[x][y + 1]++
        if (grid[x][y + 1] > 9) flash(grid, x, y + 1, flashed)
    }
    if (x < 9) {
        if (y > 0 && grid[x + 1][y - 1] <= 9) {
            grid[x + 1][y - 1]++
            if (grid[x + 1][y - 1] > 9) flash(grid, x + 1, y - 1, flashed)
        }
        if (grid[x + 1][y] <= 9) {
            grid[x + 1][y]++
            if (grid[x + 1][y] > 9) flash(grid, x + 1, y, flashed)
        }
        if (y < 9 && grid[x + 1][y + 1] <= 9) {
            grid[x + 1][y + 1]++
            if (grid[x + 1][y + 1] > 9) flash(grid, x + 1, y + 1, flashed)
        }
    }
}

fun day11Part2(input: String): Int {
    val grid = Array(10) { Array(10) { 0 } }
    for (x in input.lines().indices) {
        for (y in input.lines()[x].indices) {
            grid[x][y] = input.lines()[x][y].digitToInt()
        }
    }
    var steps = 0
    while (flashes != 10 * 10) {
        flashes = 0
        steps++
        // Increase all by 1
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                grid[x][y]++
            }
        }
        // Flash
        val flashed = ArrayList<Pair<Int, Int>>()
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                if (grid[x][y] > 9) {
                    flash(grid, x, y, flashed)
                }
            }
        }
        // Set to 0
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                if (grid[x][y] > 9) {
                    grid[x][y] = 0
                }
            }
        }
    }
    return steps
}
