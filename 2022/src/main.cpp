#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include "./aoc.h"
#include "days/days.h"

std::string readInput(int day, bool example) {
    std::string fileName{std::to_string(day)};

    fileName.insert(0, day < 10 ? "0" : "");
    fileName.append(example ? ".example" : ".input");

    std::ifstream fileStream {"./inputs/day" + fileName};
    std::stringstream stream;
    stream << fileStream.rdbuf();
    return stream.str();
}

void runDay(int day) {
    static const auto days = aoc::days();

    if (!days.contains(day)) {
        std::cout << "Day " << day << " undefined" << std::endl;
        return;
    }

    const auto input = readInput(day, false);

    const auto solution = days.find(day)->second(input);

    std::cout << "Day " << day;
    std::cout << " { " << solution.partA;
    std::cout << ", "  << solution.partB << " }" << std::endl;
}

int main() {
    for (int i = 1; i <= aoc::days().size(); i++) {
        runDay(i);
    }
}
