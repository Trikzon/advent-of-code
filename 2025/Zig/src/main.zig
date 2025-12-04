const std = @import("std");

const day1 = @import("day1.zig");
const day2 = @import("day2.zig");
const day3 = @import("day3.zig");

pub fn main() !void {
    const gpa = std.heap.smp_allocator;

    std.debug.print("Solution for Day 1, Part 1: {}\n", .{try day1.part_one(gpa, false)});
    std.debug.print("Solution for Day 1, Part 2: {}\n", .{try day1.part_two(gpa, false)});

    std.debug.print("Solution for Day 2, Part 1: {}\n", .{try day2.part_one(gpa, false)});
    std.debug.print("Solution for Day 2, Part 2: {}\n", .{try day2.part_two(gpa, false)});
    
    std.debug.print("Solution for Day 3, Part 1: {}\n", .{try day3.part_one(gpa, false)});
    std.debug.print("Solution for Day 3, Part 2: {}\n", .{try day3.part_two(gpa, false)});
}
