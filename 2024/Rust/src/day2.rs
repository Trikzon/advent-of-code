use crate::utils;

fn parse_input(example: bool) -> Vec<Vec<i64>> {
    utils::read_puzzle_input_lines(2, example)
        .map(|report| {
            report
                .split_whitespace()
                .map(|part| part.parse::<i64>().expect("invalid number"))
                .collect()
        })
        .collect()
}

fn is_report_valid(report: &[i64]) -> bool {
    if report.len() < 2 {
        return false;
    }
    
    let sign = (report[0] - report[1]).signum();
    
    report
        .windows(2)
        .map(|pair| pair[0] - pair[1])
        .all(|diff| diff.abs() >= 1 && diff.abs() <= 3 && diff.signum() == sign)
}

pub fn part_one(example: bool) -> i64 {
    parse_input(example)
        .iter()
        .filter(|report| is_report_valid(report))
        .count()
        .try_into()
        .expect("count should be convertible to i64")
}

pub fn part_two(example: bool) -> i64 {
    parse_input(example)
        .iter()
        .filter(|report| {
            let mut subreport = Vec::with_capacity(report.len() - 1);
            (0..report.len()).any(|i| {
                subreport.clear();
                subreport.extend_from_slice(&report[..i]);
                subreport.extend_from_slice(&report[i + 1..]);
                is_report_valid(&subreport)
            })
        })
        .count()
        .try_into()
        .expect("count should be convertible to i64")
}
