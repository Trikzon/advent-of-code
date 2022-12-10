#include "./aoc.h"

namespace aoc {
    std::vector<std::string> getLines(std::string input) {
        std::vector<std::string> result;

        std::stringstream ss { std::string{input} };
        std::string line;

        while (std::getline(ss, line)) {
            result.push_back(line);
        }

        return result;
    }
}
