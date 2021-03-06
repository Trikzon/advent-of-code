use aoc_2020::*;
use criterion::{criterion_group, criterion_main, Criterion};

pub fn criterion_benchmark(c: &mut Criterion) {
    c.bench_function("day_1_1", |b| b.iter(|| day1::Day1::part1()));
    c.bench_function("day_1_2", |b| b.iter(|| day1::Day1::part2()));
    c.bench_function("day_2_1", |b| b.iter(|| day2::Day2::part1()));
    c.bench_function("day_2_2", |b| b.iter(|| day2::Day2::part2()));
    c.bench_function("day_3_1", |b| b.iter(|| day3::Day3::part1()));
    c.bench_function("day_3_2", |b| b.iter(|| day3::Day3::part2()));
    c.bench_function("day_4_1", |b| b.iter(|| day4::Day4::part1()));
    c.bench_function("day_4_2", |b| b.iter(|| day4::Day4::part2()));
    c.bench_function("day_5_1", |b| b.iter(|| day5::Day5::part1()));
    c.bench_function("day_5_2", |b| b.iter(|| day5::Day5::part2()));
    c.bench_function("day_6_1", |b| b.iter(|| day6::Day6::part1()));
    c.bench_function("day_6_2", |b| b.iter(|| day6::Day6::part2()));
    c.bench_function("day_8_1", |b| b.iter(|| day8::Day8::part1()));
    c.bench_function("day_8_2", |b| b.iter(|| day8::Day8::part2()));
    c.bench_function("day_9_1", |b| b.iter(|| day9::Day9::part1()));
    c.bench_function("day_9_2", |b| b.iter(|| day9::Day9::part2()));
}

criterion_group!(benches, criterion_benchmark);
criterion_main!(benches);
