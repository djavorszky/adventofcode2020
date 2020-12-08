use std::clone::Clone;
use std::collections::{BTreeMap, HashSet};
use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(&input);

    println!("v1: {}", v1_result);

    let v2_result = task_v2(&input);

    println!("v2: {}", v2_result);

    Ok(())
}

fn task_v2(input: &str) -> i32 {
    let instructions = input_to_map(input);

    let mut updated_instructions = instructions.clone();

    for (line, instruction) in instructions.iter() {
        match instruction.typ {
            IType::NOP => {
                updated_instructions.get_mut(line).unwrap().typ = IType::JMP;

                let (looped, acc) = find_acc(&updated_instructions);

                if !looped {
                    return acc;
                } else {
                    updated_instructions.get_mut(line).unwrap().typ = IType::NOP;
                }
            }
            IType::JMP => {
                updated_instructions.get_mut(line).unwrap().typ = IType::NOP;

                let (looped, acc) = find_acc(&updated_instructions);

                if !looped {
                    return acc;
                } else {
                    updated_instructions.get_mut(line).unwrap().typ = IType::JMP;
                }
            }
            _ => (),
        }
    }

    0
}

fn task_v1(input: &str) -> i32 {
    let instructions = input_to_map(input);
    let (_, acc) = find_acc(&instructions);

    acc
}

fn find_acc(instructions: &BTreeMap<i32, Instruction>) -> (bool, i32) {
    let mut seen = HashSet::new();
    let mut loc: i32 = 1;

    let mut looped = true;
    let mut acc = 0;
    while seen.insert(loc) {
        let current = instructions.get(&loc).unwrap();

        match current.typ {
            IType::ACC => {
                acc += current.offset;
                loc += 1
            }
            IType::NOP => loc += 1,
            IType::JMP => loc += current.offset,
        }

        if loc > instructions.len() as i32 {
            looped = false;
            break;
        }
    }

    (looped, acc)
}

fn input_to_map(input: &str) -> BTreeMap<i32, Instruction> {
    let mut instructions = BTreeMap::new();

    input.lines().enumerate().for_each(|(idx, line)| {
        instructions.insert((idx + 1) as i32, Instruction::from(line));
    });

    instructions
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
struct Instruction {
    typ: IType,
    offset: i32,
}

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
enum IType {
    NOP,
    ACC,
    JMP,
}

impl Instruction {
    fn from(line: &str) -> Self {
        let parts: Vec<&str> = line.split(" ").collect();

        let itype = match parts[0] {
            "acc" => IType::ACC,
            "jmp" => IType::JMP,
            "nop" => IType::NOP,
            _ => panic!("Unexpected instruction type: {}", parts[0]),
        };

        Self {
            typ: itype,
            offset: parts[1].parse::<i32>().unwrap(),
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const TEST_INPUT: &str = "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6";

    #[test]
    fn test_task_v1() {
        assert_eq!(task_v1(TEST_INPUT), 5);
    }

    #[test]
    fn test_find_acc_with_looping() {
        let instructions = input_to_map(TEST_INPUT);

        let (looped, acc) = find_acc(&instructions);

        assert_eq!(looped, true);
        assert_eq!(acc, 5);
    }

    #[test]
    fn test_find_acc_no_loop() {
        let input = "nop +0
acc +5
acc +3
jmp +1
acc +2
nop +0";

        let instructions = input_to_map(input);

        let (looped, acc) = find_acc(&instructions);

        assert_eq!(looped, false);
        assert_eq!(acc, 10);
    }

    #[test]
    fn test_task_v2() {
        assert_eq!(task_v2(TEST_INPUT), 8);
    }

    #[test]
    fn test_instruction_creation() {
        let tests = vec![
            ("acc +4", IType::ACC, 4),
            ("acc -4", IType::ACC, -4),
            ("acc 0", IType::ACC, 0),
            ("nop +4", IType::NOP, 4),
            ("jmp +4", IType::JMP, 4),
            ("acc +32", IType::ACC, 32),
        ];

        for (idx, (input, exp_itype, exp_offset)) in tests.into_iter().enumerate() {
            let actual = Instruction::from(input);

            assert_eq!(actual.typ, exp_itype, "test #{}", idx + 1);
            assert_eq!(actual.offset, exp_offset, "test #{}", idx + 1);
        }
    }

    #[test]
    fn test_input_to_map() {
        let test_input = "acc +1
jmp -32
nop +0";

        let map = input_to_map(test_input);

        let first = map.get(&1).unwrap();
        assert_eq!(first.typ, IType::ACC);
        assert_eq!(first.offset, 1);

        let second = map.get(&2).unwrap();
        assert_eq!(second.typ, IType::JMP);
        assert_eq!(second.offset, -32);

        let third = map.get(&3).unwrap();
        assert_eq!(third.typ, IType::NOP);
        assert_eq!(third.offset, 0);
    }
}
