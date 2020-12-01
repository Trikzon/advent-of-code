#![allow(dead_code)]

mod day1;

pub trait Day {
    fn part1() -> Option<i32>;
    fn part2() -> Option<i32>;
}

#[test]
fn day_1_1() {
    assert_eq!(1019904, day1::Day1::part1().unwrap());
}

#[test]
fn day_1_2() {
    assert_eq!(176647680, day1::Day1::part2().unwrap());
}
