use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(&input);

    println!("v1: {}", v1_result);

    Ok(())
}

fn task_v1(input: &str) -> usize {
    let mut floor = Floor::from(input);

    floor.tick();

    floor
        .tiles
        .into_iter()
        .filter(|t| matches!(t, Tile::Occupied(_, _)))
        .count()
}

#[derive(Debug, PartialEq)]
struct Floor {
    width: usize,
    height: usize,
    tiles: Vec<Tile>,
}

impl Floor {
    fn from(input: &str) -> Self {
        let tiles = input
            .lines()
            .enumerate()
            .flat_map(|(line_num, line)| {
                line.chars()
                    .enumerate()
                    .map(|(col_num, c)| Tile::from(c, line_num, col_num))
                    .collect::<Vec<Tile>>()
            })
            .collect();

        let width = input.lines().nth(0).unwrap().chars().count();
        let height = input.lines().count();

        Self {
            tiles: tiles,
            width: width,
            height: height,
        }
    }

    fn tick(&mut self) {
        loop {
            let mut layout_changed = false;

            self.tiles = self
                .tiles
                .iter()
                .map(|tile| match tile {
                    Tile::Empty(x, y) => {
                        if !self
                            .get_neighbours(*x, *y)
                            .iter()
                            .any(|n| matches!(n, Tile::Occupied(_, _)))
                        {
                            layout_changed = true;
                            Tile::Occupied(*x, *y)
                        } else {
                            Tile::Empty(*x, *y)
                        }
                    }
                    Tile::Occupied(x, y) => {
                        if self
                            .get_neighbours(*x, *y)
                            .iter()
                            .filter(|n| matches!(n, Tile::Occupied(_, _)))
                            .count()
                            >= 4
                        {
                            layout_changed = true;
                            Tile::Empty(*x, *y)
                        } else {
                            Tile::Occupied(*x, *y)
                        }
                    }
                    Tile::Floor(x, y) => Tile::Floor(*x, *y),
                })
                .collect();

            if !layout_changed {
                return;
            }
        }
    }

    fn is_first_row(&self, x: usize) -> bool {
        x == 0
    }

    fn is_last_row(&self, x: usize) -> bool {
        x == self.height - 1
    }

    fn is_first_col(&self, y: usize) -> bool {
        y == 0
    }

    fn is_last_col(&self, y: usize) -> bool {
        y == self.width - 1
    }

    fn get_neighbours(&self, x: usize, y: usize) -> Vec<&Tile> {
        let mut neighbours = Vec::new();

        let location = x * self.height + y;

        if !self.is_first_row(x) {
            if !self.is_first_col(y) {
                self.tiles
                    .get(location - self.height - 1)
                    .and_then(|tile| Some(neighbours.push(tile)));
            }

            self.tiles
                .get(location - self.height)
                .and_then(|tile| Some(neighbours.push(tile)));

            if !self.is_last_col(y) {
                self.tiles
                    .get(location - self.height + 1)
                    .and_then(|tile| Some(neighbours.push(tile)));
            }
        }

        if !self.is_first_col(y) {
            self.tiles
                .get(location - 1)
                .and_then(|tile| Some(neighbours.push(tile)));
        }

        if !self.is_last_col(y) {
            self.tiles
                .get(location + 1)
                .and_then(|tile| Some(neighbours.push(tile)));
        }

        if !self.is_last_row(x) {
            if !self.is_first_col(y) {
                self.tiles
                    .get(location + self.height - 1)
                    .and_then(|tile| Some(neighbours.push(tile)));
            }

            self.tiles
                .get(location + self.height)
                .and_then(|tile| Some(neighbours.push(tile)));

            if !self.is_last_col(y) {
                self.tiles
                    .get(location + self.height + 1)
                    .and_then(|tile| Some(neighbours.push(tile)));
            }
        }

        neighbours
    }
}

#[derive(Debug, PartialEq)]
enum Tile {
    Occupied(usize, usize),
    Empty(usize, usize),
    Floor(usize, usize),
}

impl Tile {
    fn from(c: char, x: usize, y: usize) -> Self {
        match c {
            '.' => Tile::Floor(x, y),
            'L' => Tile::Empty(x, y),
            '#' => Tile::Occupied(x, y),
            _ => panic!("Unexpected tile found: {}", c),
        }
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn test_parse_floor_plan() {
        let input = ".#\nL.\nL#";

        let expected = Floor {
            width: 2,
            height: 3,
            tiles: vec![
                Tile::Floor(0, 0),
                Tile::Occupied(0, 1),
                Tile::Empty(1, 0),
                Tile::Floor(1, 1),
                Tile::Empty(2, 0),
                Tile::Occupied(2, 1),
            ],
        };

        let actual = Floor::from(input);

        assert_eq!(actual, expected);
    }

    #[test]
    fn test_get_neighbours_start() {
        let test_floor = Floor::from(".#LL\n.#LL\n.#LL\n.#LL");

        assert_eq!(
            test_floor.get_neighbours(0, 0),
            vec![
                &Tile::Occupied(0, 1),
                &Tile::Floor(1, 0),
                &Tile::Occupied(1, 1)
            ]
        )
    }

    #[test]
    fn test_get_neighbours_row_start() {
        let test_floor = Floor::from(".#LL\n.#LL\n.#LL\n.#LL");

        assert_eq!(
            test_floor.get_neighbours(1, 0),
            vec![
                &Tile::Floor(0, 0),
                &Tile::Occupied(0, 1),
                &Tile::Occupied(1, 1),
                &Tile::Floor(2, 0),
                &Tile::Occupied(2, 1)
            ]
        )
    }

    #[test]
    fn test_get_neighbours_row_end() {
        let test_floor = Floor::from(".#LL\n.#LL\n.#LL\n.#LL");

        assert_eq!(
            test_floor.get_neighbours(1, 3),
            vec![
                &Tile::Empty(0, 2),
                &Tile::Empty(0, 3),
                &Tile::Empty(1, 2),
                &Tile::Empty(2, 2),
                &Tile::Empty(2, 3)
            ]
        )
    }

    #[test]
    fn test_get_neighbours_end() {
        let test_floor = Floor::from(".#LL\n.#LL\n.#LL\n.#LL");

        assert_eq!(
            test_floor.get_neighbours(3, 3),
            vec![&Tile::Empty(2, 2), &Tile::Empty(2, 3), &Tile::Empty(3, 2)]
        )
    }

    #[test]
    fn test_get_neighbours_middle() {
        let test_floor = Floor::from(".#LL\n.#LL\n.#LL\n.#LL");

        assert_eq!(
            test_floor.get_neighbours(1, 1),
            vec![
                &Tile::Floor(0, 0),
                &Tile::Occupied(0, 1),
                &Tile::Empty(0, 2),
                &Tile::Floor(1, 0),
                &Tile::Empty(1, 2),
                &Tile::Floor(2, 0),
                &Tile::Occupied(2, 1),
                &Tile::Empty(2, 2)
            ]
        )
    }

    #[test]
    fn test_task_v1() {
        let input = "L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL";

        assert_eq!(task_v1(input), 37);
    }

    #[test]
    fn test_task_v1_step2() {
        let input = "#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##";

        assert_eq!(task_v1(input), 37);
    }

    #[test]
    fn test_task_v1_step3() {
        let input = "#.LL.L#.##
#LLLLLL.L#
L.L.L..L..
#LLL.LL.L#
#.LL.LL.LL
#.LLLL#.##
..L.L.....
#LLLLLLLL#
#.LLLLLL.L
#.#LLLL.##";

        assert_eq!(task_v1(input), 37);
    }

    #[test]
    fn test_task_v1_step4() {
        let input = "#.##.L#.##
#L###LL.L#
L.#.#..#..
#L##.##.L#
#.##.LL.LL
#.###L#.##
..#.#.....
#L######L#
#.LL###L.L
#.#L###.##";

        assert_eq!(task_v1(input), 37);
    }

    #[test]
    fn test_task_v1_step5() {
        let input = "#.#L.L#.##
#LLL#LL.L#
L.L.L..#..
#LLL.##.L#
#.LL.LL.LL
#.LL#L#.##
..L.L.....
#L#LLLL#L#
#.LLLLLL.L
#.#L#L#.##";

        assert_eq!(task_v1(input), 37);
    }

    #[test]
    fn test_task_v1_step6() {
        let input = "#.#L.L#.##
#LLL#LL.L#
L.#.L..#..
#L##.##.L#
#.#L.LL.LL
#.#L#L#.##
..L.L.....
#L#L##L#L#
#.LLLLLL.L
#.#L#L#.##";

        assert_eq!(task_v1(input), 37);
    }
}
