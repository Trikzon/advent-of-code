#![allow(dead_code)]

pub mod day1;
pub mod day2;
pub mod day3;
pub mod day4;
pub mod day5;
pub mod day6;

pub trait Day {
    type Output: std::fmt::Debug + PartialEq;

    fn part1() -> Self::Output;
    fn part2() -> Self::Output;
}

#[test]
fn day_1_1() {
    assert_eq!(1019904, day1::Day1::part1());
}

#[test]
fn day_1_2() {
    assert_eq!(176647680, day1::Day1::part2());
}

#[test]
fn day_2_1() {
    assert_eq!(622, day2::Day2::part1());
}

#[test]
fn day_2_2() {
    assert_eq!(263, day2::Day2::part2());
}

#[test]
fn day_3_1() {
    assert_eq!(167, day3::Day3::part1());
}

#[test]
fn day_3_2() {
    assert_eq!(736527114, day3::Day3::part2());
}

#[test]
fn day_4_1() {
    assert_eq!(202, day4::Day4::part1());
}

#[test]
fn day_4_2() {
    assert_eq!(137, day4::Day4::part2());
}

#[test]
fn day_5_1() {
    assert_eq!(980, day5::Day5::part1());
}

#[test]
fn day_5_2() {
    assert_eq!(607, day5::Day5::part2());
}

#[test]
fn day_6_1() {
    assert_eq!(6506, day6::Day6::part1());
}

#[test]
fn day_6_2() {
    assert_eq!(3243, day6::Day6::part2());
}
