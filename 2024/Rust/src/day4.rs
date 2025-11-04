use crate::utils;

fn get_char (lines: &[Vec<char>], x: usize, y: usize, dx: isize, dy: isize) -> Option<char> {
    let y = y.checked_add_signed(dy)?;
    let x = x.checked_add_signed(dx)?;
    lines.get(y).and_then(|line| line.get(x)).copied()
}

pub fn part_one(example: bool) -> i64 {
    let lines = utils::read_puzzle_input_grid(4, example);

    const LETTERS: [char; 4] = ['X', 'M', 'A', 'S'];
    let mut acc = 0;

    for (y, line) in lines.iter().enumerate() {
        for (x, &c) in line.iter().enumerate() {
            if c != 'X' {
                continue;
            }
            
            // Try in all 8 directions.
            for dy in -1..=1 {
                for dx in -1..=1 {
                    if dy == 0 && dx == 0 {
                        continue;
                    }
                    
                    if (1..4).all(|i| {
                        matches!(
                            get_char(&lines, x, y, dx * i, dy * i),
                            Some(c) if c == LETTERS[i as usize]
                        )
                    }) {
                        acc += 1
                    }
                }
            }
        }
    }

    acc
}

pub fn part_two(example: bool) -> i64 {
    let lines = utils::read_puzzle_input_grid(4, example);

    let mut acc = 0;

    for (y, line) in lines.iter().enumerate() {
        for (x, c) in line.iter().enumerate() {
            if *c != 'A' {
                continue;
            }

            // Check first diagonal
            if !matches!(
                (get_char(&lines, x, y, -1, 1), get_char(&lines, x, y, 1, -1)),
                (Some('M'), Some('S')) | (Some('S'), Some('M'))
            ) {
                continue;
            }

            // Check second diagonal
            if !matches!(
                (get_char(&lines, x, y, 1, 1), get_char(&lines, x, y, -1, -1)),
                (Some('M'), Some('S')) | (Some('S'), Some('M'))
            ) {
                continue;
            }

            acc += 1;
        }
    }

    acc
}
