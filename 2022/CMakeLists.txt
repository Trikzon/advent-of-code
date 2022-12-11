cmake_minimum_required(VERSION 3.23)
project(2022)

set(CMAKE_CXX_STANDARD 23)

add_executable(2022 src/main.cpp src/aoc.h src/days/days.h src/days/days.cpp src/days/day01.cpp src/aoc.cpp src/days/day02.cpp)

add_custom_command(
        TARGET 2022 POST_BUILD
        PRE_BUILD COMMAND ${CMAKE_COMMAND} -E
        create_symlink ${CMAKE_CURRENT_SOURCE_DIR}/inputs $<TARGET_FILE_DIR:2022>/inputs
)
