use std::cmp::min;

const INPUT: &str = include_str!("day2.input");

pub struct Day2;
impl crate::Day for Day2 {
    type Output = usize;

    fn part1() -> Self::Output {
        let volumes = Volume::from_input();

        let mut total = 0;
        volumes.iter().for_each(|v| {
            let bottom = v.l * v.w;
            let left = v.l * v.h;
            let right = v.w * v.h;

            let smallest = min(bottom, min(left, right));

            total += 2 * bottom + 2 * left + 2 * right + smallest;
        });

        total
    }

    fn part2() -> Self::Output {
        let volumes = Volume::from_input();

        let mut total = 0;
        volumes.iter().for_each(|v| {
            let bottom = v.l * 2 + v.w * 2;
            let left = v.l * 2 + v.h * 2;
            let right = v.w * 2 + v.h * 2;

            let smallest = min(bottom, min(left, right));

            let volume = v.l * v.w * v.h;

            total += smallest + volume;
        });

        total
    }
}

#[derive(Debug)]
struct Volume {
    l: usize,
    w: usize,
    h: usize,
}

impl Volume {
    fn from_input() -> Vec<Volume> {
        let mut vec = Vec::new();

        INPUT.split('\n').for_each(|line| {
            let mut dims: Vec<usize> = Vec::new();
            line.split('x').for_each(|s| {
                if let Ok(num) = s.parse() {
                    dims.push(num);
                }
            });

            vec.push(Volume {
                l: *dims.get(0).unwrap(),
                w: *dims.get(1).unwrap(),
                h: *dims.get(2).unwrap(),
            });
        });

        vec
    }
}
