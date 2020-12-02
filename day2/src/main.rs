use std::fs;
use std::io;

fn main() -> Result<(), io::Error> {
    let pwd_policies = read_file_to_vec("input.txt")?;

    let good_passwords = pwd_policies
        .iter()
        .filter(|pwd_policy| pwd_policy.is_valid())
        .count();

    println!("{}", good_passwords);

    Ok(())
}

fn read_file_to_vec(filename: &str) -> Result<Vec<PwdPolicy>, io::Error> {
    let mut lines = Vec::new();

    fs::read_to_string(filename)?
        .lines()
        .for_each(|line| lines.push(PwdPolicy::from(line)));
    Ok(lines)
}

#[derive(Debug)]
struct PwdPolicy {
    min: usize,
    max: usize,
    letter: char,
    pwd: String,
}

impl PwdPolicy {
    fn from(string: &str) -> Self {
        let vals = string.split(" ").collect::<Vec<&str>>();
        let minmax = vals[0].split("-").collect::<Vec<&str>>();

        let min = minmax[0].parse::<usize>().unwrap();
        let max = minmax[1].parse::<usize>().unwrap();
        let letter = vals[1][..1].parse::<char>().unwrap();
        let pwd = vals[2];

        PwdPolicy {
            min: min,
            max: max,
            letter: letter,
            pwd: String::from(pwd),
        }
    }

    fn is_valid(self: &Self) -> bool {
        let count = self.pwd.chars().filter(|c| c == &self.letter).count();

        count >= self.min && count <= self.max
    }
}
