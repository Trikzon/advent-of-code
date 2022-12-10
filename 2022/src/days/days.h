#pragma once

#include <map>
#include <string>
#include "../aoc.h"

namespace aoc {
    const std::map<int, Solution(*)(std::string)> &days();

    Solution day01(std::string);
}
