use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let result_v1 = task_v1(&input).unwrap();

    println!("v1: {}", result_v1);

    Ok(())
}

fn task_v1(input: &str) -> Option<u64> {
    find_first_wrong_number(25, &read_to_vec(input))
}

fn read_to_vec(input: &str) -> Vec<u64> {
    input
        .lines()
        .map(|line| line.parse::<u64>().unwrap())
        .collect()
}

fn find_first_wrong_number(preamble_size: usize, numbers: &Vec<u64>) -> Option<u64> {
    for (idx, number) in numbers[preamble_size..].iter().enumerate() {
        let current_loc = preamble_size + idx;

        let mut found = false;

        let check_start = current_loc - preamble_size;

        for (start_idx, n1) in numbers[check_start..current_loc - 1].iter().enumerate() {
            for n2 in &numbers[check_start + start_idx + 1..current_loc] {
                if n1 + n2 == *number {
                    found = true;
                    break;
                }
            }
        }

        if !found {
            return Some(*number);
        }
    }

    None
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn read_to_vec_works() {
        let test_input = "16\n24\n12\n55\n93";

        assert_eq!(read_to_vec(test_input), vec![16, 24, 12, 55, 93]);
    }

    #[test]
    fn find_first_wrong_number_test() {
        let test_input = "35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576";

        assert_eq!(
            find_first_wrong_number(5, &read_to_vec(test_input)).unwrap(),
            127
        );
    }

    #[test]
    fn find_first_wrong_number_test_v2() {
        let test_input = vec![1, 2, 3, 5, 9, 14];

        assert_eq!(find_first_wrong_number(2, &test_input).unwrap(), 9);
    }

}
