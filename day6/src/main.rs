use std::collections::{HashMap, HashSet};
use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let result_v1 = task_v1(input.as_str());

    println!("v1: {}", result_v1);

    let result_v2 = task_v2(input.as_str());

    println!("v2: {}", result_v2);

    Ok(())
}

fn task_v1(input: &str) -> usize {
    let groups: Vec<String> = input
        .split("\n\n")
        .map(|chunk| chunk.replace('\n', ""))
        .collect();

    groups
        .into_iter()
        .map(|group| count_unique(group.as_str()))
        .sum()
}

fn count_unique(input: &str) -> usize {
    let mut set = HashSet::new();

    input
        .chars()
        .map(|c| set.insert(c))
        .collect::<Vec<bool>>()
        .into_iter()
        .filter(|t| *t)
        .count()
}
fn task_v2(input: &str) -> usize {
    let groups: Vec<&str> = input.split("\n\n").collect();

    groups.into_iter().map(|group| count_all_yes(group)).sum()
}

fn count_all_yes(group: &str) -> usize {
    let mut map = HashMap::new();
    let mut group_size = 0;

    group.lines().for_each(|line| {
        group_size += 1;

        line.chars().for_each(|c| *map.entry(c).or_insert(0) += 1);
    });

    map.values().filter(|v| *v == &group_size).count()
}

#[cfg(test)]
mod tests {

    use super::*;

    const TEST_INPUT: &str = "abc

a
b
c

ab
ac

a
a
a
a

b";

    #[test]
    fn test_task_v1() {
        assert_eq!(task_v1(TEST_INPUT), 11);
    }

    #[test]
    fn test_task_v2() {
        assert_eq!(task_v2(TEST_INPUT), 6);
    }

    #[test]
    fn count_unique_members() {
        let tests = vec![("abc", 3), ("abac", 3), ("aaaa", 1), ("b", 1)];
        for (test_idx, (input, expectation)) in tests.into_iter().enumerate() {
            assert_eq!(count_unique(input), expectation, "test #{}", test_idx + 1);
        }
    }

    #[test]
    fn test_count_all_yes() {
        let tests = vec![
            ("abc", 3),
            ("a\nb\nc", 0),
            ("ab\nac", 1),
            ("a\na\na\na\n", 1),
            ("b", 1),
        ];
        for (test_idx, (input, expectation)) in tests.into_iter().enumerate() {
            assert_eq!(count_all_yes(input), expectation, "test #{}", test_idx + 1);
        }
    }
}
