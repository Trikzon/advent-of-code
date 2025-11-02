use std::fs::File;
use std::io::{BufRead, BufReader};

pub fn read_puzzle_input(day: u8, example: bool) -> BufReader<File> {
    let path = format!(
        "../Puzzles/day{}.{}",
        day,
        if example { "example" } else { "input" }
    );
    BufReader::new(File::open(&path).expect(&format!("puzzle input {} should be readable", path)))
}

pub fn read_puzzle_input_lines(day: u8, example: bool) -> impl Iterator<Item = String> {
    read_puzzle_input(day, example)
        .lines()
        .filter_map(|line| line.ok())
}
