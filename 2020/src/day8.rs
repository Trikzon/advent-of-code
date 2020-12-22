const INPUT: &str = include_str!("./day8.input");

pub struct Day8;
impl crate::Day for Day8 {
    type Output = isize;

    fn part1() -> Self::Output {
        let instructions: Vec<Instruction> = INPUT.lines().map(|line| Instruction::new(line)).collect();
        run_instructions(&instructions).err().unwrap()
    }

    fn part2() -> Self::Output {
        let instructions: Vec<Instruction> = INPUT.lines().map(|line| Instruction::new(line)).collect();

        for (i, instruction) in instructions.iter().enumerate() {
            let mut variant_instructions = instructions.clone();
            match instruction {
                Instruction::JMP(_) => {
                    variant_instructions.push(Instruction::NOP(0));
                },
                Instruction::NOP(value) => {
                    variant_instructions.push(Instruction::JMP(*value));
                },
                _ => continue
            }
            variant_instructions.swap(i, instructions.len());
            variant_instructions.pop();

            if let Ok(accumulator) = run_instructions(&variant_instructions) {
                return accumulator;
            }
        }
        unreachable!("A solution was not found.")
    }
}

/// Returns Ok if it exits. Returns Err if it loops. Result contains last accumulator value.
fn run_instructions(instructions: &[Instruction]) -> Result<isize, isize> {
    let mut accumulator = 0;
    let mut prev_reached_pointers = Vec::new();
    let mut pointer = 0;
    loop {
        if prev_reached_pointers.contains(&pointer) {
            return Err(accumulator);
        }
        if pointer == instructions.len() as isize {
            return Ok(accumulator);
        }
        if pointer < 0 {
            unreachable!("Pointer should never be less than 0.")
        }
        prev_reached_pointers.push(pointer);

        let instruction = &instructions[pointer as usize];
        match instruction {
            Instruction::ACC(value) => {
                accumulator += value;
                pointer += 1;
            },
            Instruction::JMP(value) => {
                pointer += value;
            },
            Instruction::NOP(_) => {
                pointer += 1;
            }
        }
    }
}

#[derive(Debug, Clone, Copy)]
enum Instruction {
    ACC(isize),
    JMP(isize),
    NOP(isize),
}

impl Instruction {
    fn new(line: &str) -> Self {
        let mut operation = "";
        let mut value = 0;

        for (i, part) in line.split(' ').enumerate() {
            match i {
                0 => {
                    operation = part;
                },
                1 => {
                    value = part.parse().unwrap();
                },
                _ => unreachable!("Parsing doesn't work. i: {}, part: {}", i, part)
            }
        }

        match operation {
            "acc" => Instruction::ACC(value),
            "jmp" => Instruction::JMP(value),
            "nop" => Instruction::NOP(value),
            _ => unreachable!("Operation is not a valid operation.")
        }
    }
}

