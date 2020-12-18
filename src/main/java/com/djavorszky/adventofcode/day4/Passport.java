package com.djavorszky.adventofcode.day4;

import com.djavorszky.adventofcode.day4.metadata.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Passport {

  private final Set<PassportField> requiredFields;

  public static Passport valueOf(String input) {
    Set<PassportField> fields = new HashSet<>();

    for (String part : input.split(" ")) {
      getPassportField(part).ifPresent(fields::add);
    }

    return new Passport(fields);
  }

  private static Optional<PassportField> getPassportField(String part) {
    String[] sections = part.split(":");

    PassportField passportField = switch (sections[0]) {
      case "byr" -> BirthYear.valueOf(sections[1]);
      case "iyr" -> IssueYear.valueOf(sections[1]);
      case "eyr" -> ExpirationYear.valueOf(sections[1]);
      case "hgt" -> Height.valueOf(sections[1]);
      case "hcl" -> HairColour.valueOf(sections[1]);
      case "ecl" -> EyeColour.valueOf(sections[1]);
      case "pid" -> PassportId.valueOf(sections[1]);
      case "cid" -> null;
      default -> throw new IllegalStateException("Unexpected value: " + sections[0]);
    };

    return Optional.ofNullable(passportField);
  }

  public boolean isValidV1() {
    return requiredFields.size() == 7;
  }

  public boolean isValidV2() {
    return requiredFields.size() == 7 && requiredFields.stream().allMatch(PassportField::isValid);
  }
}
