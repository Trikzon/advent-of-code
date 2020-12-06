const INPUT: &str = include_str!("day6.input");

pub struct Day6;
impl crate::Day for Day6 {
    type Output = usize;

    fn part1() -> Self::Output {
        let mut total_yes_counter = 0;

        let mut group_answers = [false; 26];
        for line in INPUT.lines() {
            if line.is_empty() {
                let group_yes = group_answers.iter().filter(|v| **v).count();

                total_yes_counter += group_yes;
                group_answers = [false; 26];
            }
            else {
                for char in line.chars() {
                    // Convert ascii char to 0-26 index.
                    let alphabet_index = char as usize - 97;
                    group_answers[alphabet_index] = true;
                }
            }
        }
        total_yes_counter
    }

    fn part2() -> Self::Output {
        let mut total_yes_counter = 0;

        let mut group_answers = [0; 26];
        let mut group_size = 0;
        for line in INPUT.lines() {
            if line.is_empty() {
                total_yes_counter += group_answers.iter().filter(|v| **v == group_size).count();

                group_answers = [0; 26];
                group_size = 0;
            }
            else {
                group_size += 1;
                for char in line.chars() {
                    // Convert ascii char to 0-26 index.
                    let alphabet_index = char as usize - 97;
                    group_answers[alphabet_index] += 1;
                }
            }
        }
        total_yes_counter
    }
}
