use std::cmp::Eq;
use std::collections::{HashMap, HashSet};
use std::rc::Rc;
use std::{fs, io};

fn main() -> Result<(), io::Error> {
    let contents = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(contents.as_str());

    println!("v1: {}", v1_result);

    Ok(())
}

#[derive(Debug, Hash, PartialEq, Eq)]
struct Bags {
    name: String,
    amount: usize,
}

fn task_v1(input: &str) -> usize {
    let rels = build_relationship(input);
    let reverse_rels = build_reverse_relationship(rels);

    let parents = reverse_rels.get("shiny gold").unwrap();

    println!("{:?}", parents);

    let mut combinations = 0;

    let seen: &mut HashSet<&str> = &mut HashSet::new();

    for parent in parents {
        combinations += walk(parent, &reverse_rels, seen);
    }

    combinations
}

fn walk<'a>(
    name: &'a str,
    parents: &HashMap<String, Vec<&'a str>>,
    seen: &mut HashSet<&'a str>,
) -> usize {
    seen.insert(name);

    let mut found = 1;

    let node_parents = parents
        .get(name)
        .expect(format!("not found: {}, {:?}", name, parents).as_str());

    for parent in node_parents {
        if !seen.contains(parent) {
            found += walk(parent, parents, seen);
        }
    }

    println!("name: {}, found: {}", name, found);

    found
}

fn build_reverse_relationship(
    relationships: HashMap<&str, Vec<Rc<Bags>>>,
) -> HashMap<String, Vec<&str>> {
    let mut reverse_relationship = HashMap::new();

    for (container, contained_bags) in relationships.iter() {
        reverse_relationship
            .entry(String::from(*container))
            .or_insert(Vec::new());

        for bag in contained_bags {
            let contained_list = reverse_relationship
                .entry(bag.name.clone())
                .or_insert(Vec::new());

            contained_list.push(*container);
        }
    }

    reverse_relationship
}

fn build_relationship(input: &str) -> HashMap<&str, Vec<Rc<Bags>>> {
    let mut result = HashMap::new();

    input.lines().for_each(|line| {
        result.insert(get_containing_bag(line), get_contained_bags(line));
    });

    result
}

fn get_contained_bags(input: &str) -> Vec<Rc<Bags>> {
    let mut result = Vec::new();

    let start_idx = input.find("contain").unwrap() + 8;

    let input_contained = &input[start_idx..];

    for bags in input_contained.split(", ") {
        if bags.starts_with("no") {
            continue;
        }
        let amount = bags.chars().nth(0).unwrap().to_digit(10).unwrap();
        let end_idx = bags.find(" bag").unwrap();

        result.push(Rc::new(Bags {
            amount: amount as usize,
            name: String::from(&bags[2..end_idx]),
        }))
    }

    result
}

fn get_containing_bag(input: &str) -> &str {
    let end_idx = input.find(" bags").unwrap();
    &input[..end_idx]
}

#[cfg(test)]
mod tests {

    use super::*;

    #[test]
    fn test_get_container() {
        let tests = vec![
            (
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "light red",
            ),
            (
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "dark orange",
            ),
            (
                "bright white bags contain 1 shiny gold bag.",
                "bright white",
            ),
        ];

        for (idx, (input, expectation)) in tests.into_iter().enumerate() {
            assert_eq!(get_containing_bag(input), expectation, "test #{}", idx + 1);
        }
    }

    #[test]
    fn test_build_relationship() {
        let tests = vec![
            (
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "light red",
                vec![
                    Rc::new(Bags {
                        name: String::from("bright white"),
                        amount: 1,
                    }),
                    Rc::new(Bags {
                        name: String::from("muted yellow"),
                        amount: 2,
                    }),
                ],
            ),
            (
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "dark orange",
                vec![
                    Rc::new(Bags {
                        name: String::from("bright white"),
                        amount: 3,
                    }),
                    Rc::new(Bags {
                        name: String::from("muted yellow"),
                        amount: 4,
                    }),
                ],
            ),
            (
                "bright white bags contain 1 shiny gold bag.",
                "bright white",
                vec![Rc::new(Bags {
                    name: String::from("shiny gold"),
                    amount: 1,
                })],
            ),
            (
                "dotted black bags contain no other bags.",
                "dotted black",
                Vec::new(),
            ),
        ];
        for (idx, (input, container, contained)) in tests.into_iter().enumerate() {
            let mut expectation = HashMap::new();

            expectation.insert(container, contained);
            assert_eq!(build_relationship(input), expectation, "test #{}", idx + 1);
        }
    }

    #[test]
    fn test_get_contained_bags() {
        let tests = vec![
            (
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                vec![
                    Rc::new(Bags {
                        name: String::from("bright white"),
                        amount: 1,
                    }),
                    Rc::new(Bags {
                        name: String::from("muted yellow"),
                        amount: 2,
                    }),
                ],
            ),
            (
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                vec![
                    Rc::new(Bags {
                        name: String::from("bright white"),
                        amount: 3,
                    }),
                    Rc::new(Bags {
                        name: String::from("muted yellow"),
                        amount: 4,
                    }),
                ],
            ),
            (
                "bright white bags contain 1 shiny gold bag.",
                vec![Rc::new(Bags {
                    name: String::from("shiny gold"),
                    amount: 1,
                })],
            ),
            ("dotted black bags contain no other bags.", Vec::new()),
        ];
        for (idx, (input, expectation)) in tests.into_iter().enumerate() {
            assert_eq!(get_contained_bags(input), expectation, "test #{}", idx + 1);
        }
    }

    #[test]
    fn test_get_reverse_relationship() {
        let tests = vec![
            (
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                vec!["bright white", "muted yellow"],
                Bags {
                    name: String::from("light red"),
                    amount: 1,
                },
            ),
            (
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                vec!["bright white", "muted yellow"],
                Bags {
                    name: String::from("dark orange"),
                    amount: 1,
                },
            ),
            (
                "bright white bags contain 1 shiny gold bag.",
                vec!["shiny gold"],
                Bags {
                    name: String::from("bright white"),
                    amount: 1,
                },
            ),
            (
                "dotted black bags contain no other bags.",
                Vec::new(),
                Bags {
                    name: String::from("dotted black"),
                    amount: 0,
                },
            ),
        ];
        for (idx, (input, children, parent)) in tests.into_iter().enumerate() {
            let relationship = build_relationship(input);
            let mut expectation = HashMap::new();

            expectation.insert(parent.name.clone(), Vec::new());

            for child in children {
                let mut parents = Vec::new();
                parents.push(parent.name.as_str());
                expectation.insert(String::from(child), parents);
            }

            assert_eq!(
                build_reverse_relationship(relationship),
                expectation,
                "test #{}",
                idx + 1
            );
        }
    }

    #[test]
    fn test_multiline_relationship_building() {
        let test_input = "bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.";

        let relationship = build_relationship(test_input);

        assert_eq!(relationship.len(), 2);

        let line1_contained_bags = relationship.get("bright white").unwrap();
        assert_eq!(line1_contained_bags.len(), 1);

        let line1_bag1 = line1_contained_bags.get(0).unwrap();
        assert_eq!(line1_bag1.name, "shiny gold");
        assert_eq!(line1_bag1.amount, 1);
        let line2_contained_bags = relationship.get("muted yellow").unwrap();
        assert_eq!(line2_contained_bags.len(), 2);

        let line2_bag1 = line2_contained_bags.get(0).unwrap();
        assert_eq!(line2_bag1.name, "shiny gold");
        assert_eq!(line2_bag1.amount, 2);
        let line2_bag2 = line2_contained_bags.get(1).unwrap();
        assert_eq!(line2_bag2.name, "faded blue");
        assert_eq!(line2_bag2.amount, 9);
    }

    #[test]
    fn test_multiline_reverse_relationship_building() {
        let test_input = "bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 potted black bags, 9 faded blue bags, 1 shiny gold bag";

        let relationship = build_relationship(test_input);

        let rev_relationship = build_reverse_relationship(relationship);

        assert_eq!(rev_relationship.len(), 5);

        let rev_child_1_parents = rev_relationship.get("shiny gold").unwrap();
        assert_eq!(rev_child_1_parents.len(), 2);
        assert_eq!(rev_child_1_parents.contains(&"bright white"), true);
        assert_eq!(rev_child_1_parents.contains(&"muted yellow"), true);

        let rev_child_2_parents = rev_relationship.get("potted black").unwrap();
        assert_eq!(rev_child_2_parents.len(), 1);
        assert_eq!(rev_child_2_parents.contains(&"muted yellow"), true);

        let rev_child_3_parents = rev_relationship.get("potted black").unwrap();
        assert_eq!(rev_child_3_parents.len(), 1);
        assert_eq!(rev_child_3_parents.contains(&"muted yellow"), true);

        let rev_child_4_parents = rev_relationship.get("bright white").unwrap();
        assert_eq!(rev_child_4_parents.is_empty(), true);

        let rev_child_5_parents = rev_relationship.get("muted yellow").unwrap();
        assert_eq!(rev_child_5_parents.is_empty(), true);
    }

    #[test]
    fn test_task_v1() {
        let test_input = "light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.";

        assert_eq!(task_v1(test_input), 4);
    }
}
