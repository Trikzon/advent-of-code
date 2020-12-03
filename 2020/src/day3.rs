const INPUT: &str = include_str!("day3.input");

pub struct Day3;
impl crate::Day for Day3 {
    type Output = usize;

    fn part1() -> Self::Output {
        count_trees_from_slope(1, 3)
    }

    fn part2() -> Self::Output {
        let first = count_trees_from_slope(1, 1);
        let second = count_trees_from_slope(1, 3);
        let third = count_trees_from_slope(1, 5);
        let fourth = count_trees_from_slope(1, 7);
        let fifth = count_trees_from_slope(2, 1);

        first * second * third * fourth * fifth
    }
}

fn count_trees_from_slope(fall: usize, run: usize) -> usize {
    let max_width = INPUT.lines().nth(0).unwrap().len();

    let mut next_x = run;
    let mut next_y = fall;

    INPUT.lines().enumerate().filter(|(y, line)| {
        line.chars().enumerate().filter(|(x, c)| {
            if &next_x == x && &next_y == y {
                next_y += fall;
                next_x += run;
                if next_x >= max_width {
                    next_x = next_x - max_width;
                }

                c == &'#'
            } else {
                false
            }
        }).count() > 0
    }).count()
}
