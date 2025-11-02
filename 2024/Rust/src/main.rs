use std::error::Error;

mod day1;
mod utils;

fn main() -> Result<(), Box<dyn Error>> {
    println!("Solution for Day 1, Part 1: {}", day1::part_one(false));
    println!("Solution for Day 1, Part 2: {}", day1::part_two(false));

    Ok(())
}
