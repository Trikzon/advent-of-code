use criterion::{criterion_group, criterion_main, Criterion};
use aoc_2020::*;

pub fn criterion_benchmark(c: &mut Criterion) {
    c.bench_function("day_1_1", |b| b.iter(|| day1::Day1::part1()));
    c.bench_function("day_1_2", |b| b.iter(|| day1::Day1::part2()));
    c.bench_function("day_2_1", |b| b.iter(|| day2::Day2::part1()));
    c.bench_function("day_2_2", |b| b.iter(|| day2::Day2::part2()));
    c.bench_function("day_3_1", |b| b.iter(|| day3::Day3::part1()));
    c.bench_function("day_3_2", |b| b.iter(|| day3::Day3::part2()));
}

criterion_group!(benches, criterion_benchmark);
criterion_main!(benches);