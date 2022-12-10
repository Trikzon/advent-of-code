#include "./days.h"

#include <vector>

namespace aoc {
    Solution day01(std::string input) {
        std::vector<std::string> lines { getLines(input) };

        std::vector<long long> calories;

        long long accumulator { 0 };
        for (const auto line : lines) {
            if (line == "") {
                calories.push_back(accumulator);
                accumulator = 0;
            } else {
                accumulator += std::stoll(line);
            }
        }

        std::sort(calories.begin(), calories.end());
        std::reverse(calories.begin(), calories.end());

        return {
            std::to_string(calories[0]),
            std::to_string(calories[0] + calories[1] + calories[2])
        };
    }
}
