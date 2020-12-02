const INPUT: &str = include_str!("day2.input");

pub struct Day2;
impl crate::Day for Day2 {
    type Output = usize;

    fn part1() -> Self::Output {
        Password::from_input().iter().filter(|pass| {
            let req_char_count = pass.password.chars().filter(|c| {
                &c.to_string() == &pass.letter
            }).count();

            req_char_count >= pass.min && req_char_count <= pass.max
        }).count()
    }

    fn part2() -> Self::Output {
        Password::from_input().iter().filter(|pass| {
            let first = pass.password.len() >= pass.min
                && &pass.password[pass.min - 1..pass.min] == pass.letter;
            let second = pass.password.len() >= pass.max
                && &pass.password[pass.max - 1..pass.max] == pass.letter;

            (first && !second) || (!first && second)
        }).count()
    }
}

struct Password<'a> {
    min: usize,
    max: usize,
    letter: &'a str,
    password: &'a str,
}

impl Password<'_> {
    fn from_input() -> Vec<Password<'static>> {
        INPUT.lines().map(|line| {
            // These unwraps are fine because if they panic then the input isn't correct.
            let space_split = line.split(' ');
            let range_split = space_split.clone().nth(0).unwrap().split('-');

            let min = range_split.clone().nth(0).unwrap().parse().unwrap();
            let max = range_split.clone().nth(1).unwrap().parse().unwrap();

            let letter = &space_split.clone().nth(1).unwrap()[0..1];

            let password = space_split.clone().nth(2).unwrap();

            return Password {
                min, max,
                letter,
                password
            }
        }).collect()
    }
}
