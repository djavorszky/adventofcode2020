use std::collections::BTreeSet;
use std::fs;
use std::io;

fn main() -> Result<(), io::Error> {
    let numbers = read_file_to_set("input.txt")?;

    match find_two(&numbers) {
        Some((n1, n2)) => println!("{} * {} = {}", n1, n2, n1 * n2),
        None => println!("Didn't find 2 numbers that add up to 2020"),
    }

    match find_three(&numbers) {
        Some((n1, n2, n3)) => println!("{} * {} * {} = {}", n1, n2, n3, n1 * n2 * n3),
        None => println!("Didn't find 2 numbers that add up to 2020"),
    }
    Ok(())
}

fn read_file_to_set(filename: &str) -> Result<BTreeSet<i32>, io::Error> {
    let contents = fs::read_to_string(filename)?;

    let mut numbers = BTreeSet::new();

    contents.lines().for_each(|line| {
        numbers.insert(line.parse::<i32>().unwrap());
    });

    Ok(numbers)
}

fn find_two(numbers: &BTreeSet<i32>) -> Option<(i32, i32)> {
    for num in numbers {
        let complement = 2020 - num;

        if numbers.contains(&complement) {
            return Some((*num, complement));
        }
    }

    None
}

fn find_three(numbers: &BTreeSet<i32>) -> Option<(i32, i32, i32)> {
    for num in numbers {
        for num2 in numbers {
            let complement = 2020 - num2 - num;

            if numbers.contains(&complement) {
                return Some((*num, *num2, complement));
            }
        }
    }

    None
}
