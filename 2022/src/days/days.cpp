#include "./days.h"

namespace aoc {
    const std::map<int, Solution(*)(const std::string&)> &days() {
        static const std::map<int, Solution(*)(const std::string&)> days {
                { 1, day01 },
                { 2, day02 }
        };
        return days;
    }
}
