#![allow(dead_code)]

mod day1;
mod day2;

pub trait Day {
    type Output: std::fmt::Debug + PartialEq;

    fn part1() -> Self::Output;
    fn part2() -> Self::Output;
}

#[test]
fn day_1_1() {
    assert_eq!(138, day1::Day1::part1());
}

#[test]
fn day_1_2() {
    assert_eq!(1771, day1::Day1::part2());
}

#[test]
fn day_2_1() {
    assert_eq!(1606483, day2::Day2::part1());
}

#[test]
fn day_2_2() {
    assert_eq!(3842356, day2::Day2::part2());
}
