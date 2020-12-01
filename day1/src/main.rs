use std::collections::HashSet;
use std::fs;

fn main() {
    let contents = fs::read_to_string("input.txt").expect("Failed reading file.");

    let mut set = HashSet::new();

    contents.lines().for_each(|line| {
        set.insert(line.parse::<i32>().unwrap());
    });

    let (num1, num2) = find_two(&set).expect("Didn't find 2 sets.");

    println!("{} * {} = {}", num1, num2, num1 * num2);

    let (num1, num2, num3) = find_three(&set).expect("Didn't find 3 sets");

    println!("{} * {} * {} = {}", num1, num2, num3, num1 * num2 * num3);
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
