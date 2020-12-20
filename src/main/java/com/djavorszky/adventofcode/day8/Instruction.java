package com.djavorszky.adventofcode.day8;

public record Instruction(InstructionType type, int amount) {

  public static Instruction parse(String input) {
    String[] inputParts = input.split(" ");

    InstructionType type = switch(inputParts[0]) {
      case "nop" -> InstructionType.NOP;
      case "acc" -> InstructionType.ACC;
      case "jmp" -> InstructionType.JMP;
      default -> throw new IllegalArgumentException("Unexpected instruction type: " + inputParts[0]);
    };

    int amount = Integer.parseInt(inputParts[1]);

    return new Instruction(type, amount);
  }
}
