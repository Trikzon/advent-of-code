package com.trikzon.aoc2021

fun getInputStringFromFile(path: String): String {
    return object {}.javaClass.getResource("/day1.txt").readText()
}
