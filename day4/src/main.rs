use std::fs;
use std::io;

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(input.as_str()).unwrap();
    println!("v1 result: {}", v1_result);
    Ok(())
}

fn task_v1(input: &str) -> Result<usize, io::Error> {
    let valid_docs = input_to_docs(input)
        .iter()
        .filter(|doc| doc.is_valid_v1())
        .count();

    Ok(valid_docs)
}

fn input_to_docs(input: &str) -> Vec<Document> {
    input
        .split("\n\n")
        .map(|chunk| {
            let line = chunk.replace('\n', " ");

            Document::from(line.as_str())
        })
        .collect()
}

#[derive(Debug)]
enum DocType {
    BirthYear(String),
    IssueYear(String),
    ExpirationYear(String),
    Height(String),
    HairColor(String),
    EyeColor(String),
    PasspordId(String),
    CountryId(String),
}

impl DocType {
    fn from(id: &str, value: &str) -> Self {
        match id {
            "byr" => DocType::BirthYear(String::from(value)),
            "iyr" => DocType::IssueYear(String::from(value)),
            "eyr" => DocType::ExpirationYear(String::from(value)),
            "hgt" => DocType::Height(String::from(value)),
            "hcl" => DocType::HairColor(String::from(value)),
            "ecl" => DocType::EyeColor(String::from(value)),
            "pid" => DocType::PasspordId(String::from(value)),
            "cid" => DocType::CountryId(String::from(value)),
            _ => panic!("Unexpected doctype string encountered: {}", id),
        }
    }
}

#[derive(Debug)]
struct Document {
    fields: Vec<DocType>,
}

impl Document {
    fn from(line: &str) -> Self {
        let doctypes = line
            .split(" ")
            .collect::<Vec<&str>>()
            .into_iter()
            .map(|section| {
                let vals: Vec<&str> = section.split(":").collect();

                DocType::from(vals[0], vals[1])
            })
            .collect();

        Document { fields: doctypes }
    }

    fn is_valid_v1(self: &Self) -> bool {
        let mut required_count = 0;
        let mut optional_count = 0;
        self.fields.iter().for_each(|field| match field {
            DocType::CountryId(_) => optional_count += 1,
            _ => required_count += 1,
        });

        required_count == 7
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    const TEST_INPUT: &str = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in";

    #[test]
    fn valid_docs_are_found_v1() {
        let result = task_v1(TEST_INPUT).unwrap();

        assert_eq!(result, 2);
    }
}
