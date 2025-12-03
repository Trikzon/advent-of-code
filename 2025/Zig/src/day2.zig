const std = @import("std");
const utils = @import("utils.zig");

pub fn part_one(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 2, example);

    var invalid_id_sum: i64 = 0;

    var ranges_it = std.mem.tokenizeAny(u8, input, ",\n");
    while (ranges_it.next()) |range| {
        const dashIdx = std.mem.indexOf(u8, range, "-");
        const range_min = try std.fmt.parseInt(usize, range[0..dashIdx.?], 10);
        const range_max = try std.fmt.parseInt(usize, range[dashIdx.? + 1 ..], 10);

        var id_str_buf: [256]u8 = undefined;
        for (range_min..range_max + 1) |id| {
            const id_str = try std.fmt.bufPrint(&id_str_buf, "{}", .{id});

            if (id_str.len % 2 != 0) {
                continue;
            }

            const middle = id_str.len / 2;
            if (std.mem.eql(u8, id_str[0..middle], id_str[middle..])) {
                invalid_id_sum += @intCast(id);
            }
        }
    }

    return invalid_id_sum;
}

pub fn part_two(gpa: std.mem.Allocator, example: bool) !i64 {
    const input = try utils.read_puzzle_input(gpa, 2, example);

    var invalid_id_sum: i64 = 0;

    var ranges_it = std.mem.tokenizeAny(u8, input, ",\n");
    while (ranges_it.next()) |range| {
        const dashIdx = std.mem.indexOf(u8, range, "-");
        const range_min = try std.fmt.parseInt(usize, range[0..dashIdx.?], 10);
        const range_max = try std.fmt.parseInt(usize, range[dashIdx.? + 1 ..], 10);

        var id_str_buf: [256]u8 = undefined;
        for (range_min..range_max + 1) |id| {
            const id_str = try std.fmt.bufPrint(&id_str_buf, "{}", .{id});

            const middle = id_str.len / 2;
            part_loop: for (1..middle + 1) |part_len| {
                if (id_str.len % part_len != 0) {
                    continue;
                }

                const part = id_str[0..part_len];
                var i: usize = part_len;
                while (i <= id_str.len - part_len) : (i += part_len) {
                    if (!std.mem.eql(u8, part, id_str[i .. i + part_len])) {
                        continue :part_loop;
                    }
                }

                invalid_id_sum += @intCast(id);
                break;
            }
        }
    }

    return invalid_id_sum;
}
