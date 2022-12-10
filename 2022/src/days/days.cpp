#include "./days.h"

namespace aoc {
    const std::map<int, Solution(*)(std::string)> &days() {
        static const std::map<int, Solution(*)(std::string)> days {
                {1, day01},
        };
        return days;
    }
}
