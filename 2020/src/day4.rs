const INPUT: &str = include_str!("day4.input");

pub struct Day4;
impl crate::Day for Day4 {
    type Output = usize;

    fn part1() -> Self::Output {
        let mut valid_passports = 0;
        let mut valid_fields = 0;
        INPUT.lines().for_each(|line| {
            if line.is_empty() {
                if valid_fields == 7 {
                    valid_passports += 1;
                }
                valid_fields = 0;
            } else {
                valid_fields += line.split(" ")
                    .map(|s| s.trim())
                    .filter(|passport_data| {
                        let delimiter = passport_data.find(':').unwrap();
                        let field = &passport_data[0..delimiter];

                        field == "byr"
                            || field == "iyr"
                            || field == "eyr"
                            || field == "hgt"
                            || field == "hcl"
                            || field == "ecl"
                            || field == "pid"
                    }).count();
            }
        });
        valid_passports
    }

    fn part2() -> Self::Output {
        let mut valid_passports = 0;
        let mut valid_fields = 0;
        INPUT.lines().for_each(|line| {
            if line.is_empty() {
                if valid_fields == 7 {
                    valid_passports += 1;
                }
                valid_fields = 0;
            } else {
                valid_fields += line.split(" ")
                    .map(|s| s.trim())
                    .filter(|passport_data| {
                        let delimiter = passport_data.find(':').unwrap();
                        let field = &passport_data[0..delimiter];
                        let value = &passport_data[delimiter + 1..];

                        match field {
                            "byr" => is_year_valid(value, 1920, 2002),
                            "iyr" => is_year_valid(value, 2010, 2020),
                            "eyr" => is_year_valid(value, 2020, 2030),
                            "hgt" => is_height_valid(value),
                            "hcl" => is_hair_color_valid(value),
                            "ecl" => is_eye_color_valid(value),
                            "pid" => is_passport_id_valid(value),
                            _ => false
                        }
                    }).count();
            }
        });
        valid_passports
    }
}

fn is_year_valid(value: &str, min_year: usize, max_year: usize) -> bool {
    if value.len() == 4 {
        if let Ok(date) = value.parse::<usize>() {
            return date >= min_year && date <= max_year;
        }
    }
    false
}

fn is_height_valid(value: &str) -> bool {
    if value.len() > 2 {
        if let Ok(height) = (&value[..value.len() - 2]).parse::<usize>() {
            let unit = &value[value.len() - 2..];
            if unit == "cm" {
                return height >= 150 && height <= 193;
            } else if unit == "in" {
                return height >= 59 && height <= 76;
            }
        }
    }
    false
}

fn is_hair_color_valid(value: &str) -> bool {
    if value.len() == 7 {
        if &value[0..1] == "#" {
            return (&value[1..]).chars().filter(|c| {
                let ascii = *c as u8;
                // c is 0-9                  || c is a-f
                (ascii >= 48 && ascii <= 57) || (ascii >= 97 && ascii <= 102)
            }).count() == 6;
        }
    }
    false
}

fn is_eye_color_valid(value: &str) -> bool {
    value == "amb" ||
        value == "blu" ||
        value == "brn" ||
        value == "gry" ||
        value == "grn" ||
        value == "hzl" ||
        value == "oth"
}

fn is_passport_id_valid(value: &str) -> bool {
    if value.len() == 9 {
        if let Ok(_) = value.parse::<usize>() {
            return true;
        }
    }
    false
}
