use std::collections::HashSet;
use std::fs;

fn main() {
    let contents = fs::read_to_string("input.txt").expect("Failed reading file.");

    let mut set = HashSet::new();

    contents.lines().for_each(|line| {
        set.insert(line.parse::<i32>().unwrap());
    });

    match find_two(&set) {
        Some((n1, n2)) => println!("{} * {} = {}", n1, n2, n1 * n2),
        None => println!("Didn't find 2 numbers that add up to 2020"),
    }

    match find_three(&set) {
        Some((n1, n2, n3)) => println!("{} * {} * {} = {}", n1, n2, n3, n1 * n2 * n3),
        None => println!("Didn't find 2 numbers that add up to 2020"),
    }
}

fn find_two(set: &HashSet<i32>) -> Option<(i32, i32)> {
    for num in set {
        let complement = 2020 - num;

        if set.contains(&complement) {
            return Some((*num, complement));
        }
    }

    None
}

fn find_three(set: &HashSet<i32>) -> Option<(i32, i32, i32)> {
    for num in set {
        for num2 in set {
            let complement = 2020 - num2 - num;

            if set.contains(&complement) {
                return Some((*num, *num2, complement));
            }
        }
    }

    None
}
