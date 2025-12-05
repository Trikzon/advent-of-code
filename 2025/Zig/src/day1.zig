const std = @import("std");
const utils = @import("utils.zig");

pub fn part_one(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 1, example);
    defer gpa.free(input);

    var zero_count: i64 = 0;
    var pointing_at: i64 = 50;
    
    var lines_it = std.mem.tokenizeAny(u8, input, "\n");
    while (lines_it.next()) |line| {
        const direction: i64 = switch (line[0]) {
            'L' => -1,
            'R' => 1,
            else => unreachable,
        };
        const distance = try std.fmt.parseInt(i64, line[1..], 10);

        pointing_at = @mod(pointing_at + (distance * direction), 100);
        
        if (pointing_at == 0) {
            zero_count += 1;
        }
    }

    return zero_count;
}

pub fn part_two(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 1, example);
    defer gpa.free(input);

    var zero_count: i64 = 0;
    var pointing_at: i64 = 50;

    var lines_it = std.mem.tokenizeAny(u8, input, "\n");
    while (lines_it.next()) |line| {
        const direction: i64 = switch (line[0]) {
            'L' => -1,
            'R' => 1,
            else => unreachable,
        };
        const distance = try std.fmt.parseInt(usize, line[1..], 10);

        for (0..distance) |_| {
            if (pointing_at == 0 and direction == -1) {
                pointing_at = 99;
            } else if (pointing_at == 99 and direction == 1) {
                pointing_at = 0;
            } else {
                pointing_at += direction;
            }

            if (pointing_at == 0) {
                zero_count += 1;
            }
        }
    }

    return zero_count;
}
