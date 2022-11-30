#include "days.h"

namespace aoc {
    const std::map<int, Solution(*)(std::string_view)> &days() {
        static const std::map<int, Solution(*)(std::string_view)> days {
                {1, day01},
        };
        return days;
    }
}
