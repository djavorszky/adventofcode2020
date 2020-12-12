use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(&input);

    println!("v1: {}", v1_result);

    Ok(())
}

fn task_v1(input: &str) -> usize {
    input.len()
}
