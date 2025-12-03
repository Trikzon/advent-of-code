const std = @import("std");

const day1 = @import("day1.zig");

pub fn main() !void {
    const gpa = std.heap.smp_allocator;

    std.debug.print("Solution for Day 1, Part 1: {}\n", .{try day1.part_one(gpa, false)});
    std.debug.print("Solution for Day 1, Part 2: {}\n", .{try day1.part_two(gpa, false)});
}
