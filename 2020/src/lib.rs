#![allow(dead_code)]

mod day1;

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
