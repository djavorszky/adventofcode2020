use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(&mut input_to_vec(&input));

    println!("v1: {}", v1_result);

    let v2_result = task_v2(&mut input_to_vec(&input));

    println!("v2: {}", v2_result);

    Ok(())
}

fn task_v2(numbers: &mut Vec<usize>) -> usize {
    numbers.push(0);
    numbers.sort();

    find_arrangements(0, numbers)
}

fn find_arrangements(loc: usize, numbers: &Vec<usize>) -> usize {
    if loc > numbers.len() - 2 {
        1
    } else {
        let current_num = &numbers[loc];

        let next_num = &numbers[loc + 1];

        let mut arrangements = 0;
        if next_num - current_num > 3 {
            return 1;
        } else {
            let mut other_arrangements = false;
            if loc + 2 < numbers.len() && numbers[loc + 2] - current_num < 4 {
                other_arrangements = true;
                arrangements += find_arrangements(loc + 2, &numbers);
            }
            if loc + 3 < numbers.len() && numbers[loc + 3] - current_num < 4 {
                other_arrangements = true;
                arrangements += find_arrangements(loc + 3, &numbers);
            }

            if other_arrangements {
                arrangements += find_arrangements(loc + 1, &numbers);
            } else {
                return find_arrangements(loc + 1, &numbers);
            }
        }

        arrangements
    }
}

fn task_v1(numbers: &mut Vec<usize>) -> usize {
    numbers.sort();

    let mut one_diff = 1;
    let mut three_diff = 1;
    for (idx, next_num) in numbers.iter().enumerate().skip(1) {
        let prev_num = numbers[idx - 1];

        match next_num - prev_num {
            1 => one_diff += 1,
            2 => (),
            3 => three_diff += 1,
            _ => break,
        }
    }

    one_diff * three_diff
}

fn input_to_vec(input: &str) -> Vec<usize> {
    input
        .lines()
        .map(|line| line.parse::<usize>().unwrap())
        .collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_input_to_vec() {
        let test_input = "1\n10\n6\n24\n11";

        assert_eq!(input_to_vec(test_input), vec![1, 10, 6, 24, 11]);
    }

    #[test]
    fn test_task_v1_1() {
        let mut input = vec![16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4];

        assert_eq!(task_v1(&mut input), 35)
    }

    #[test]
    fn test_task_v1_2() {
        let mut input = vec![
            28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35,
            8, 17, 7, 9, 4, 2, 34, 10, 3,
        ];

        assert_eq!(task_v1(&mut input), 220)
    }

    #[test]
    fn test_task_v2_1() {
        let mut input = vec![16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4];

        assert_eq!(task_v2(&mut input), 8)
    }

    #[test]
    fn test_task_v2_2() {
        let mut input = vec![
            28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35,
            8, 17, 7, 9, 4, 2, 34, 10, 3,
        ];

        assert_eq!(task_v2(&mut input), 19208)
    }

    #[test]
    fn test_find_arrangements() {
        let input = vec![0, 1, 2, 3, 4];

        assert_eq!(find_arrangements(0, &input), 7);
    }

    #[test]
    fn test_find_arrangements_v2() {
        let input = vec![0, 1, 2, 3, 4, 5];

        assert_eq!(find_arrangements(0, &input), 13);
    }

    #[test]
    fn test_find_arrangements_v3() {
        let input = vec![0, 1, 2, 3, 4, 5, 6];

        assert_eq!(find_arrangements(0, &input), 24);
    }

    #[test]
    fn test_find_arrangements_v4() {
        let input = vec![1, 2, 3, 6];

        assert_eq!(find_arrangements(0, &input), 2);
    }
}
