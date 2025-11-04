use std::error::Error;

mod day1;
mod day2;
mod day3;
mod day4;
mod utils;

fn main() -> Result<(), Box<dyn Error>> {
    println!("Solution for Day 1, Part 1: {}", day1::part_one(false));
    println!("Solution for Day 1, Part 2: {}", day1::part_two(false));
    println!("Solution for Day 2, Part 1: {}", day2::part_one(false));
    println!("Solution for Day 2, Part 2: {}", day2::part_two(false));
    println!("Solution for Day 3, Part 1: {}", day3::part_one(false));
    println!("Solution for Day 3, Part 2: {}", day3::part_two(false));
    println!("Solution for Day 4, Part 1: {}", day4::part_one(false));
    println!("Solution for Day 4, Part 2: {}", day4::part_two(false));

    Ok(())
}
