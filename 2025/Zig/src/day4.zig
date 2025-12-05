const std = @import("std");
const utils = @import("utils.zig");

pub fn parse_input(gpa: std.mem.Allocator, example: bool) ![][]u8 {
    const input = try utils.read_puzzle_input(gpa, 4, example);
    defer gpa.free(input);
    
    var lines = std.mem.tokenizeScalar(u8, input, '\n');
    
    var grid: std.ArrayList([]u8) = .empty;
    while (lines.next()) |line| {
        try grid.append(gpa, try gpa.dupe(u8, line));
    }
    
    return grid.toOwnedSlice(gpa);
}

fn remove_accessible_rolls(grid: *[][]u8) ?i64 {
    var removed: i64 = 0;
    
    for (0..grid.len) |y| {
        for (0..grid.*[y].len) |x| {
            if (grid.*[y][x] != '@') {
                continue;
            }
            
            var adjacent: u8 = 0;
            for (0..3) |dy| {
                for (0..3) |dx| {
                    if (dy == 1 and dx == 1) {
                        continue;
                    }
                    if ((dy == 0 and y == 0) or (dy == 2 and y == grid.len - 1)) {
                        continue;
                    }
                    if ((dx == 0 and x == 0) or (dx == 2 and x == grid.*[y].len - 1)) {
                        continue;
                    }
                    
                    if (grid.*[y + dy - 1][x + dx - 1] == '@' or grid.*[y + dy - 1][x + dx - 1] == '!') {
                        adjacent += 1;
                    }
                }
            }
            
            if (adjacent < 4) {
                grid.*[y][x] = '!';
                removed += 1;
            }
        }
    }
    
    for (0..grid.len) |y| {
        for (0..grid.*[y].len) |x| {
            if (grid.*[y][x] == '!') {
                grid.*[y][x] = '.';
            }
        }
    }
    
    return if (removed > 0) removed else null;
}

pub fn part_one(gpa: std.mem.Allocator, example: bool) !i64 {
    var grid = try parse_input(gpa, example);
    defer gpa.free(grid);
    
    return remove_accessible_rolls(&grid).?;
}

pub fn part_two(gpa: std.mem.Allocator, example: bool) !i64 {
    var grid = try parse_input(gpa, example);
    defer gpa.free(grid);
    
    var total_removed: i64 = 0;
    
    while (remove_accessible_rolls(&grid)) |removed| {
        total_removed += removed;
    }
    
    return total_removed;
}
