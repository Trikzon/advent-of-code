package com.trikzon.aoc2021

fun main() {
    val input = getInputStringFromFile("/day14.txt")
    benchmark(Part.One, ::day14Part1, input, 3009, 5000)
    benchmark(Part.Two, ::day14Part2, input, 3459822539451L, 5000)
}

fun day14Part1(input: String): Int {
    val lines = input.lines()
    var template = lines[0]
    val rules = HashMap<String, String>()

    for (i in 2 until lines.size) {
        val rule = lines[i].split(" -> ")
        rules[rule[0]] = rule[1]
    }

    for (i in 0 until 10) {
        val newTemplate = StringBuilder()
        for (j in 0 until template.length - 1) {
            val firstElement = template[j].toString()
            val secondElement = template[j + 1].toString()
            val insertElement = rules[firstElement + secondElement]
            newTemplate.append(firstElement + insertElement)
            if (j == template.length - 2) {
                newTemplate.append(secondElement)
            }
        }
        template = newTemplate.toString()
    }

    val counts = HashMap<Char, Int>()
    for (char in template) {
        if (counts.contains(char)) {
            counts[char] = counts[char]!! + 1
        } else {
            counts[char] = 1
        }
    }

    var mostCommonQuantity = Int.MIN_VALUE
    var leastCommonQuantity = Int.MAX_VALUE
    for (char in counts.keys) {
        if (counts[char]!! > mostCommonQuantity) {
            mostCommonQuantity = counts[char]!!
        } else if (counts[char]!! < leastCommonQuantity) {
            leastCommonQuantity = counts[char]!!
        }
    }

    return mostCommonQuantity - leastCommonQuantity
}

fun day14Part2(input: String): Long {
    val rules = HashMap<Pair<Char, Char>, Char>()
    var pairCounts = HashMap<Pair<Char, Char>, Long>()
    var charCounts = HashMap<Char, Long>()

    val lines = input.lines()
    for (i in 2 until lines.size) {
        val rule = lines[i].split(" -> ")
        rules[Pair(rule[0][0], rule[0][1])] = rule[1][0]
    }

    // Insert initial template into hashmaps
    val template = lines[0]
    val lastChar = template[template.lastIndex]
    for (i in 0 until template.length - 1) {
        val char = template[i]
        if (charCounts.contains(char)) charCounts[char] = charCounts[char]!! + 1
        else charCounts[char] = 1

        val pair = Pair(char, template[i + 1])
        if (pairCounts.contains(pair)) pairCounts[pair] = pairCounts[pair]!! + 1
        else pairCounts[pair] = 1

        if (i == template.length - 2) {
            if (charCounts.contains(template[i + 1])) charCounts[template[i + 1]] = charCounts[template[i + 1]]!! + 1
            else charCounts[template[i + 1]] = 1
        }
    }

    for (i in 0 until 40) {
        val newPairCounts = HashMap<Pair<Char, Char>, Long>()
        val newCharCounts = HashMap<Char, Long>()
        for ((left, right) in pairCounts.keys) {
            val pairCount = pairCounts[Pair(left, right)]!!
            val insertChar = rules[Pair(left, right)]!!

            if (newPairCounts.contains(Pair(left, insertChar))) newPairCounts[Pair(left, insertChar)] = newPairCounts[Pair(left, insertChar)]!! + pairCount
            else newPairCounts[Pair(left, insertChar)] = pairCount
            if (newPairCounts.contains(Pair(insertChar, right))) newPairCounts[Pair(insertChar, right)] = newPairCounts[Pair(insertChar, right)]!! + pairCount
            else newPairCounts[Pair(insertChar, right)] = pairCount

            if (newCharCounts.contains(left)) newCharCounts[left] = newCharCounts[left]!! + pairCount
            else newCharCounts[left] = pairCount
            if (newCharCounts.contains(insertChar)) newCharCounts[insertChar] = newCharCounts[insertChar]!! + pairCount
            else newCharCounts[insertChar] = pairCount
        }
        newCharCounts[lastChar] = newCharCounts[lastChar]!! + 1
        pairCounts = newPairCounts
        charCounts = newCharCounts
    }

    var mostCommonQuantity = Long.MIN_VALUE
    var leastCommonQuantity = Long.MAX_VALUE
    for (counts in charCounts.values) {
        if (counts > mostCommonQuantity) mostCommonQuantity = counts
        else if (counts < leastCommonQuantity) leastCommonQuantity = counts
    }

    return mostCommonQuantity - leastCommonQuantity
}
