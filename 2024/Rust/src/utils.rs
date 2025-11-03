use std::fs::File;
use std::io::{BufRead, BufReader, Read};

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

pub fn read_puzzle_input_string(day: u8, example: bool) -> String {
    let mut input = String::new();
    read_puzzle_input(day, example)
        .read_to_string(&mut input)
        .expect("puzzle input should be readable as a string");
    input
}
