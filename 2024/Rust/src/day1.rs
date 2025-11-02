use crate::utils;
use std::collections::HashMap;

fn parse_input(example: bool) -> (Vec<i64>, Vec<i64>) {
    utils::read_puzzle_input_lines(1, example)
        .map(|line| {
            let nums: Vec<i64> = line
                .split_whitespace()
                .map(|part| part.parse::<i64>().expect("invalid number"))
                .collect();
            assert!(
                nums.len() == 2,
                "each line should contain exactly two numbers"
            );
            (nums[0], nums[1])
        })
        .unzip()
}

pub fn part_one(example: bool) -> i64 {
    let (mut left, mut right) = parse_input(example);

    left.sort_unstable();
    right.sort_unstable();

    left.iter().zip(&right).map(|(&a, &b)| (a - b).abs()).sum()
}

pub fn part_two(example: bool) -> i64 {
    let (left, right) = parse_input(example);

    let mut frequency = HashMap::new();
    for num in right {
        *frequency.entry(num).or_insert(0) += 1;
    }

    left.into_iter()
        .map(|num| num * frequency.get(&num).copied().unwrap_or(0))
        .sum()
}
