const std = @import("std");

pub fn read_puzzle_input(gpa: std.mem.Allocator, day: u8, example: bool) ![]u8 {
    var puzzles_dir = try std.fs.cwd().openDir("../Puzzles", .{});
    defer puzzles_dir.close();

    const file_name = try std.fmt.allocPrint(gpa, "day{d}.{s}", .{ day, if (example) "example" else "input" });

    return puzzles_dir.readFileAlloc(gpa, file_name, std.math.maxInt(u32));
}
