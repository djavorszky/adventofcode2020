use std::fs;
use std::io;

fn main() -> Result<(), io::Error> {
    let contents = fs::read_to_string("input.txt")?;

    let map = AreaMap::from(contents);

    let result = map.traverse_and_count_trees_v1();

    println!("{}", result);

    let second_result = map.traverse_and_count_trees_v2();

    println!("{}", second_result);

    Ok(())
}

struct AreaMap {
    width: usize,
    fields: Vec<MapElement>,
}

#[derive(Debug, PartialEq)]
enum MapElement {
    Tree,
    Open,
    Unknown,
}

impl AreaMap {
    fn from(map: String) -> Self {
        let elems: Vec<MapElement> = map
            .lines()
            .flat_map(|line| {
                line.chars()
                    .map(|c| match c {
                        '#' => MapElement::Tree,
                        '.' => MapElement::Open,
                        _ => MapElement::Unknown,
                    })
                    .collect::<Vec<MapElement>>()
            })
            .collect();

        let first_line = map.lines().next().take().unwrap();

        AreaMap {
            width: first_line.len(),
            fields: elems,
        }
    }

    fn traverse_and_count_trees_v1(self: &Self) -> usize {
        let mut pos = 0;

        let mut found_elements = Vec::new();

        loop {
            pos = self.step_v2(pos, 3, 1);

            if let Some(elem) = self.fields.get(pos) {
                found_elements.push(elem);
            } else {
                break;
            }
        }

        found_elements
            .into_iter()
            .filter(|element| *element == &MapElement::Tree)
            .count()
    }

    fn traverse_and_count_trees_v2(self: &Self) -> usize {
        let mut found_trees = Vec::new();

        for (right, down) in vec![(1, 1), (3, 1), (5, 1), (7, 1), (1, 2)] {
            let mut pos = 0;
            let mut trees = 0;

            loop {
                pos = self.step_v2(pos, right, down);
                if let Some(elem) = self.fields.get(pos) {
                    if elem == &MapElement::Tree {
                        trees += 1;
                    }
                } else {
                    break;
                }
            }

            found_trees.push(trees);
        }

        found_trees.into_iter().product()
    }

    fn step_v2(self: &Self, current_loc: usize, step_right: usize, step_down: usize) -> usize {
        let current_loc_in_line = current_loc % self.width;

        if current_loc_in_line + step_right < self.width {
            return current_loc + step_right + (self.width * step_down);
        }
        let total_lines = current_loc / self.width;

        let new_pos_on_line = (current_loc + step_right) % self.width;

        (total_lines + step_down) * self.width + new_pos_on_line
    }
}

#[cfg(test)]
mod test {
    use super::*;

    const TEST_INPUT: &str = "..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
";

    #[test]
    fn given_test_input_v1_works_correctly() {
        let am = AreaMap::from(String::from(TEST_INPUT));

        assert_eq!(am.traverse_and_count_trees_v1(), 7);
    }

    #[test]
    fn given_test_input_v2_works_correctly() {
        let am = AreaMap::from(String::from(TEST_INPUT));

        assert_eq!(am.traverse_and_count_trees_v2(), 336);
    }
}
