#pragma once

#include <map>
#include <string>
#include "../aoc.h"

namespace aoc {
    const std::map<int, Solution(*)(std::string_view)> &days();

    Solution day01(std::string_view);
}
