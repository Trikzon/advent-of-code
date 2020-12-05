const INPUT: &str = include_str!("day5.input");

pub struct Day5;
impl crate::Day for Day5 {
    type Output = usize;

    fn part1() -> Self::Output {
        *get_ids_from_input().iter().max().unwrap()
    }

    fn part2() -> Self::Output {
        let mut ids = get_ids_from_input();
        ids.sort();

        for i in 1..ids.len() {
            if ids[i] == ids[i - 1] + 2 {
                return ids[i] - 1;
            }
        }

        unreachable!()
    }
}

fn get_ids_from_input() -> Vec<usize> {
    INPUT.lines().map(|line| {
        let row = parse_instructions(&line[0..7], 0, 127);
        let col = parse_instructions(&line[7..], 0, 7);

        row * 8 + col
    }).collect()
}

fn parse_instructions(instructions: &str, min: usize, max: usize) -> usize {
    let mut min = min;
    let mut max = max;
    instructions.chars().for_each(|c| match c {
        'F' | 'L' => max = ((max - min) / 2) + min,
        'B' | 'R' => min = (max + 1 - min) / 2 + min,
        _ => panic!("Found unknown character '{}' while parsing.", c)
    });

    assert_eq!(min, max);

    min
}