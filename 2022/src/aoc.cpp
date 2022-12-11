#include "./aoc.h"

namespace aoc {
    std::vector<std::string> getLines(const std::string& input) {
        std::vector<std::string> result;

        std::stringstream ss { input };
        std::string line;

        while (std::getline(ss, line)) {
            result.push_back(line);
        }

        return result;
    }
}
