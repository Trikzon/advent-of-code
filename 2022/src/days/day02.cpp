#include <utility>

#include "./days.h"

namespace aoc {
    Solution day02(const std::string& input) {
        std::vector<std::string> lines { getLines(input) };

        std::map<std::string, long long> part1Lookup {
                { "A X", 1 + 3 }, { "B X", 1 + 0 }, { "C X", 1 + 6 },
                { "A Y", 2 + 6 }, { "B Y", 2 + 3 }, { "C Y", 2 + 0 },
                { "A Z", 3 + 0 }, { "B Z", 3 + 6 }, { "C Z", 3 + 3 }
        };
        std::map<std::string, long long> part2Lookup {
                { "A X", 3 + 0 }, { "B X", 1 + 0 }, { "C X", 2 + 0 },
                { "A Y", 1 + 3 }, { "B Y", 2 + 3 }, { "C Y", 3 + 3 },
                { "A Z", 2 + 6 }, { "B Z", 3 + 6 }, { "C Z", 1 + 6 }
        };

        long long part1 { 0 };
        long long part2 { 0 };
        for (const auto& line : lines) {
            part1 += part1Lookup.at(line);
            part2 += part2Lookup.at(line);
        }

        return {
            std::to_string(part1),
            std::to_string(part2)
        };
    }
}