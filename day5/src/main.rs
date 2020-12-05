use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;
    let v1 = task_v1(input.as_str());

    println!("v1: {}", v1);

    let v2 = task_v2(input.as_str());

    println!("v2: {}", v2.unwrap());

    Ok(())
}

fn task_v1(input: &str) -> usize {
    input
        .lines()
        .map(|line| Seat::from(line).get_id())
        .max()
        .unwrap()
}

fn task_v2(input: &str) -> Option<usize> {
    let mut ids: Vec<usize> = input
        .lines()
        .map(|line| Seat::from(line).get_id())
        .collect();

    ids.sort();

    for (idx, id) in ids.iter().enumerate() {
        let next_item = ids[idx + 1];

        if next_item != id + 1 {
            return Some(id + 1);
        }
    }

    None
}

struct Seat {
    row: usize,
    col: usize,
}

impl Seat {
    fn from(line: &str) -> Self {
        Self {
            row: Seat::get_row(&line[..7]),
            col: Seat::get_col(&line[7..]),
        }
    }

    fn get_row(line: &str) -> usize {
        let mut range = Range::new(128);

        line.chars()
            .map(|c| Half::from(c))
            .for_each(|half| range = range.halve(half));

        range.mid
    }

    fn get_col(line: &str) -> usize {
        let mut range = Range::new(8);

        line.chars()
            .map(|c| Half::from(c))
            .for_each(|half| range = range.halve(half));

        range.mid
    }

    fn get_id(self: &Self) -> usize {
        self.row * 8 + self.col
    }
}

struct Range {
    min: usize,
    max: usize,
    mid: usize,
}

impl Range {
    fn new(max: usize) -> Self {
        Self {
            min: 0,
            mid: max / 2,
            max: max,
        }
    }

    fn halve(self: &Self, half: Half) -> Self {
        match half {
            Half::Lower => {
                let diff = self.max - self.min;

                let max = self.max - diff / 2;
                let mid = self.min + (max - self.min) / 2;

                Self {
                    min: self.min,
                    mid: mid,
                    max: max,
                }
            }
            Half::Upper => {
                let diff = self.max - self.min;

                let min = self.max - diff / 2;
                let mid = min + (self.max - min) / 2;

                Self {
                    min: min,
                    mid: mid,
                    max: self.max,
                }
            }
        }
    }
}

#[derive(Debug)]
enum Half {
    Upper,
    Lower,
}

impl Half {
    fn from(c: char) -> Self {
        match c {
            'B' => Half::Upper,
            'R' => Half::Upper,
            'F' => Half::Lower,
            'L' => Half::Lower,
            _ => panic!("Can't create search dir from {}", c),
        }
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    const TEST_INPUT: &str = "BFFFBBFRRR
FFFBBBFRRR
BBFFBBFRLL";

    #[test]
    fn v1_works() {
        assert_eq!(task_v1(TEST_INPUT), 820);
    }

    #[test]
    fn correct_ids_are_returned() {
        let tests = vec![
            ("BFFFBBFRRR", 567),
            ("FFFBBBFRRR", 119),
            ("BBFFBBFRLL", 820),
        ];

        for (input, expectation) in tests {
            assert_eq!(Seat::from(input).get_id(), expectation, "input: {}", input);
        }
    }

    #[test]
    fn rows_are_correct() {
        let tests = vec![("BFFFBBF", 70), ("FFFBBBF", 14), ("BBFFBBF", 102)];

        for (input, expectation) in tests {
            assert_eq!(Seat::get_row(input), expectation, "input: {}", input);
        }
    }
    #[test]
    fn cols_are_correct() {
        let tests = vec![("RRR", 7), ("RRR", 7), ("RLL", 4)];

        for (input, expectation) in tests {
            assert_eq!(Seat::get_col(input), expectation, "input: {}", input);
        }
    }

    #[test]
    fn test_range_new() {
        let rng = Range::new(10);

        assert_eq!(rng.max, 10, "max");
        assert_eq!(rng.min, 0, "min");
        assert_eq!(rng.mid, 5, "mid");
    }

    #[test]
    fn test_range_halve_down() {
        let mut rng = Range::new(10);
        rng = rng.halve(Half::Lower);

        assert_eq!(rng.max, 5, "max");
        assert_eq!(rng.min, 0, "min");
        assert_eq!(rng.mid, 2, "mid");
    }

    #[test]
    fn test_range_halve_up() {
        let mut rng = Range::new(10);
        rng = rng.halve(Half::Upper);

        assert_eq!(rng.max, 10, "max");
        assert_eq!(rng.min, 5, "min");
        assert_eq!(rng.mid, 7, "mid");
    }

    #[test]
    fn test_range_halve_up_twice() {
        let mut rng = Range::new(100);
        rng = rng.halve(Half::Upper);
        rng = rng.halve(Half::Upper);

        assert_eq!(rng.max, 100, "max");
        assert_eq!(rng.min, 75, "min");
        assert_eq!(rng.mid, 87, "mid");
    }

    #[test]
    fn test_range_halve_down_twice() {
        let mut rng = Range::new(100);
        rng = rng.halve(Half::Lower);
        rng = rng.halve(Half::Lower);

        assert_eq!(rng.max, 25, "max");
        assert_eq!(rng.min, 0, "min");
        assert_eq!(rng.mid, 12, "mid");
    }

    #[test]
    fn test_range_halve_up_down() {
        let mut rng = Range::new(100);
        rng = rng.halve(Half::Upper);
        rng = rng.halve(Half::Lower);

        assert_eq!(rng.max, 75, "max");
        assert_eq!(rng.min, 50, "min");
        assert_eq!(rng.mid, 62, "mid");
    }

    #[test]
    fn test_range_halve_down_up() {
        let mut rng = Range::new(100);
        rng = rng.halve(Half::Lower);
        rng = rng.halve(Half::Upper);

        assert_eq!(rng.max, 50, "max");
        assert_eq!(rng.min, 25, "min");
        assert_eq!(rng.mid, 37, "mid");
    }
}
