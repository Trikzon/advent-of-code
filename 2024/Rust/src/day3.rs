use crate::utils;
use regex::{Match, Regex};

fn solve(example: bool, always_enabled: bool) -> i64 {
    let input = utils::read_puzzle_input_string(3, example);

    let re_mul = Regex::new(r"mul\((\d+),(\d+)\)").expect("mul regex should be valid");
    let re_do = Regex::new(r"do\(\)").expect("do regex should be valid");
    let re_dont = Regex::new(r"don't\(\)").expect("don't regex should be valid");

    enum Token<'a> {
        Do(Match<'a>),
        Dont(Match<'a>),
        Mul(Match<'a>, i64, i64),
    }

    let mut tokens = Vec::new();

    tokens.extend(re_do.find_iter(&input).map(Token::Do));
    tokens.extend(re_dont.find_iter(&input).map(Token::Dont));
    tokens.extend(re_mul.captures_iter(&input).filter_map(|captures| {
        let m = captures.get(0)?;
        let a = captures[1].parse().ok()?;
        let b = captures[2].parse().ok()?;
        Some(Token::Mul(m, a, b))
    }));
    
    tokens.sort_by_key(|t| match t {
        Token::Do(m) => m.start(),
        Token::Dont(m) => m.start(),
        Token::Mul(m, _, _) => m.start()
    });
    
    let mut acc = 0;
    let mut enabled = true;
    
    for token in tokens {
        match token {
            Token::Do(_) => enabled = true,
            Token::Dont(_) => enabled = false,
            Token::Mul(_, a, b) if enabled || always_enabled => acc += a * b,
            _ => {}
        }
    }
    
    acc
}

pub fn part_one(example: bool) -> i64 {
    solve(example, true)
}

pub fn part_two(example: bool) -> i64 {
    solve(example, false)
}
