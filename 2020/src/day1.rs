const INPUT: &str = include_str!("day1.input");

pub struct Day1;
impl crate::Day for Day1 {
    fn part1() -> Option<i32> {
        let input = parse_input();

        for i in &input {
            let required_addend = 2020 - i;
            if input.contains(&required_addend) {
                return Some(i * required_addend);
            }
        }
        None
    }

    fn part2() -> Option<i32> {
        let input = parse_input();

        for x in &input {
            for y in &input {
                let required_addend = 2020 - x - y;
                if input.contains(&required_addend) {
                    return Some(x * y * required_addend);
                }
            }
        }
        None
    }
}

fn parse_input() -> Vec<i32> {
    let mut vec: Vec<i32> = Vec::new();
    INPUT.split('\n').for_each(|s| {
        if let Ok(value) = s.parse() {
            vec.push(value);
        };
    });
    assert_eq!(vec.len(), 200);
    vec
}
