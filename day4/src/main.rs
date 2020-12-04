use regex::Regex;
use std::fs;
use std::io;

fn main() -> Result<(), io::Error> {
    let input = fs::read_to_string("input.txt")?;

    let v1_result = task_v1(input.as_str()).unwrap();
    println!("v1 result: {}", v1_result);
    let v2_result = task_v2(input.as_str()).unwrap();

    println!("v2 result: {}", v2_result);
    Ok(())
}

fn task_v1(input: &str) -> Result<usize, io::Error> {
    let valid_docs = input_to_docs(input)
        .iter()
        .filter(|doc| doc.is_valid_v1())
        .count();

    Ok(valid_docs)
}

fn task_v2(input: &str) -> Result<usize, io::Error> {
    let valid_docs = input_to_docs(input)
        .iter()
        .filter(|doc| if doc.is_valid_v2() { true } else { false })
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
    PassportId(String),
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
            "pid" => DocType::PassportId(String::from(value)),
            "cid" => DocType::CountryId(String::from(value)),
            _ => panic!("Unexpected doctype string encountered: {}", id),
        }
    }

    fn is_valid(self: &Self) -> bool {
        match self {
            DocType::BirthYear(val) => {
                let year: usize = val.parse().unwrap_or(0);

                year >= 1920 && year <= 2002
            }
            DocType::IssueYear(val) => {
                let year: usize = val.parse().unwrap_or(0);

                year >= 2010 && year <= 2020
            }
            DocType::ExpirationYear(val) => {
                let year: usize = val.parse().unwrap_or(0);

                year >= 2020 && year <= 2030
            }
            DocType::Height(val) => {
                if val.len() < 3 {
                    false
                } else if val.ends_with("cm") {
                    let height: usize = val[..3].parse().unwrap_or(0);

                    height >= 150 && height <= 193
                } else if val.ends_with("in") {
                    let height: usize = val[..2].parse().unwrap_or(0);

                    height >= 59 && height <= 76
                } else {
                    false
                }
            }
            DocType::HairColor(val) => Regex::new(r"#[a-f0-9]{6}").unwrap().is_match(val),
            DocType::EyeColor(val) => Regex::new(r"amb|blu|brn|gry|grn|hzl|oth")
                .unwrap()
                .is_match(val),
            DocType::PassportId(val) => {
                if val.len() == 9 {
                    val.parse::<usize>().is_ok()
                } else {
                    false
                }
            }
            DocType::CountryId(_) => true,
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

    fn is_valid_v2(self: &Self) -> bool {
        let mut valid_required_fields = 0;
        let mut valid_optional_fields = 0;

        if self.fields.len() < 7 {
            return false;
        }

        self.fields.iter().for_each(|field| match field {
            DocType::CountryId(_) => valid_optional_fields += 1,
            _ => {
                if field.is_valid() {
                    valid_required_fields += 1;
                }
            }
        });

        valid_required_fields == 7
    }
}

#[cfg(test)]
mod tests {

    use super::*;

    const V1_TEST_INPUT: &str = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in";

    const V2_TEST_INPUT: &str = "eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007

pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719";

    #[test]
    fn valid_docs_are_found_v1() {
        let result = task_v1(V1_TEST_INPUT).unwrap();

        assert_eq!(result, 2);
    }

    #[test]
    fn valid_docs_are_found_v2() {
        let result = task_v2(V2_TEST_INPUT).unwrap();

        assert_eq!(result, 4);
    }

    #[test]
    fn doctype_birth_year_validation() {
        let tests = vec![
            ("1000", false),
            ("10000", false),
            ("abcd", false),
            ("", false),
            ("1920", true),
            ("2002", true),
            ("1987", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::BirthYear(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }

    #[test]
    fn doctype_issue_year_validation() {
        let tests = vec![
            ("1000", false),
            ("10000", false),
            ("abcd", false),
            ("", false),
            ("2010", true),
            ("2015", true),
            ("2020", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::IssueYear(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }

    #[test]
    fn doctype_expiration_year_validation() {
        let tests = vec![
            ("1000", false),
            ("10000", false),
            ("abcd", false),
            ("", false),
            ("2020", true),
            ("2025", true),
            ("2030", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::ExpirationYear(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }

    #[test]
    fn doctype_height_validation() {
        let tests = vec![
            ("1000", false),
            ("100cm", false),
            ("100in", false),
            ("cm", false),
            ("in", false),
            ("", false),
            ("150cm", true),
            ("193cm", true),
            ("183cm", true),
            ("59in", true),
            ("76in", true),
            ("70in", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::Height(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }
    #[test]
    fn doctype_hair_color_validation() {
        let tests = vec![
            ("#", false),
            ("1234567", false),
            ("#poopoo", false),
            ("#12345", false),
            ("#abback", false),
            ("", false),
            ("#123456", true),
            ("#7890ab", true),
            ("#cdef01", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::HairColor(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }

    #[test]
    fn doctype_eye_color_validation() {
        let tests = vec![
            ("#", false),
            ("numbers", false),
            ("kek", false),
            ("", false),
            ("amb", true),
            ("blu", true),
            ("brn", true),
            ("gry", true),
            ("grn", true),
            ("hzl", true),
            ("oth", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::EyeColor(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }
    #[test]
    fn doctype_passport_id_validation() {
        let tests = vec![
            ("12345678", false),
            ("abcdefghi", false),
            ("", false),
            ("123456725151589", false),
            ("000000000", true),
            ("123456789", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::PassportId(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }

    #[test]
    fn doctype_country_id_validation() {
        let tests = vec![
            ("12345678", true),
            ("abcdefghi", true),
            ("", true),
            ("000000000", true),
            ("123456789", true),
        ];

        for (input, expectation) in tests {
            assert_eq!(
                DocType::CountryId(String::from(input)).is_valid(),
                expectation,
                "failed for input: {}",
                input
            );
        }
    }
}
