const INPUT: &str = include_str!("./day9.input");

const PREAMBLE: usize = 25;

pub struct Day9;
impl crate::Day for Day9 {
    type Output = usize;

    fn part1() -> Self::Output {
        let numbers = INPUT
            .lines()
            .map(|line| line.parse::<usize>().unwrap())
            .collect::<Vec<usize>>();

        let mut index = PREAMBLE;
        while index < numbers.len() {
            let previous25 = &numbers[index - PREAMBLE..index];
            let current_num = numbers[index];

            let mut invalid = true;

            'previous25_loop: for x in previous25 {
                for y in previous25 {
                    if x != y && x + y == current_num {
                        invalid = false;
                        break 'previous25_loop;
                    }
                }
            }

            if invalid {
                return current_num;
            }

            index += 1;
        }
        unreachable!()
    }

    fn part2() -> Self::Output {
        let invalid_num = Day9::part1();

        let numbers = INPUT
            .lines()
            .map(|line| line.parse::<usize>().unwrap())
            .collect::<Vec<usize>>();

        for (i, num) in numbers.iter().enumerate() {
            let mut list_len = 1;
            let mut accumulator = *num;

            for next in &numbers[i + 1..] {
                accumulator += next;
                list_len += 1;

                match accumulator.cmp(&invalid_num) {
                    std::cmp::Ordering::Less => (),
                    std::cmp::Ordering::Equal => {
                        let mut min = *num;
                        let mut max = *num;

                        for num_in_list in &numbers[i + 1..i + list_len] {
                            min = std::cmp::min(min, *num_in_list);
                            max = std::cmp::max(max, *num_in_list);
                        }

                        return min + max;
                    }
                    std::cmp::Ordering::Greater => break,
                }
            }
        }
        unreachable!()
    }
}
