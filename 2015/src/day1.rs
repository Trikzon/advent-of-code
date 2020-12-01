const INPUT: &str = include_str!("day1.input");

pub struct Day1;
impl crate::Day for Day1 {
    type Output = i32;

    fn part1() -> Self::Output {
        let mut floor = 0;

        INPUT.chars().for_each(|c| {
            match c {
                '(' => floor += 1,
                ')' => floor -= 1,
                _ => ()
            }
        });

        floor
    }

    fn part2() -> Self::Output {
        let mut floor = 0;

        for (i, c) in INPUT.chars().enumerate() {
            match c {
                '(' => floor += 1,
                ')' => floor -= 1,
                _ => ()
            }
            if floor == -1 {
                return (i + 1) as i32;
            }
        }
        unreachable!()
    }
}
