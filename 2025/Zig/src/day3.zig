const std = @import("std");
const utils = @import("utils.zig");

pub fn part_one(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 3, example);
    defer gpa.free(input);

    var total_output: i64 = 0;

    var banks_it = std.mem.tokenizeAny(u8, input, "\n");
    while (banks_it.next()) |bank| {
        var first: u8 = 0;
        var second: u8 = 0;

        for (bank, 0..) |battery, i| {
            const joltage = battery - '0';

            if (i < bank.len - 1 and joltage > first) {
                first = joltage;
                second = 0;
            } else if (joltage > second) {
                second = joltage;
            }
        }

        const max_joltage = first * 10 + second;
        total_output += max_joltage;
    }

    return total_output;
}

pub fn part_two(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 3, example);
    defer gpa.free(input);

    var total_output: i64 = 0;

    var banks_it = std.mem.tokenizeAny(u8, input, "\n");
    while (banks_it.next()) |bank| {
        var enabled: [12]i64 = .{0} ** 12;

        for (bank, 0..) |battery, i| {
            const joltage: i64 = battery - '0';
            
            const min_enabled_idx = @as(i64, @intCast(i)) - @as(i64, @intCast(bank.len - enabled.len));
            for (@max(min_enabled_idx, 0)..enabled.len) |j| {
                if (enabled[j] < joltage) {
                    enabled[j] = joltage;
                    for (j + 1..enabled.len) |k| {
                        enabled[k] = 0;
                    }
                    break;
                }
            }
        }

        var max_joltage: i64 = 0;
        for (enabled, 0..) |battery, i| {
            max_joltage += battery * std.math.pow(i64, 10, @as(i64, @intCast(enabled.len - i - 1)));
        }
        total_output += max_joltage;
    }

    return total_output;
}
