#pragma once

#include <map>
#include <string>
#include "../aoc.h"

namespace aoc {
    const std::map<int, Solution(*)(const std::string&)> &days();

    Solution day01(const std::string&);
    Solution day02(const std::string&);
}
