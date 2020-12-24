package com.djavorszky.adventofcode.day8;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 implements Day {
  @Override
  public long task1(InputSource inputSource) {
    List<Instruction> instructions =
        inputSource.getInput().stream().map(Instruction::parse).collect(Collectors.toList());

    LoopResult result = getAccumulator(instructions);

    return result.accumulator;
  }

  @Override
  public long task2(InputSource inputSource) {
    List<Instruction> instructions =
            inputSource.getInput().stream().map(Instruction::parse).collect(Collectors.toList());

    for (int i = 0; i < instructions.size(); i++) {
      Instruction instruction = instructions.get(i);

      if (instruction.type().equals(InstructionType.ACC)) {
        continue;
      }

      if (instruction.type() == InstructionType.JMP) {
        instructions.set(i, new Instruction(InstructionType.NOP, instruction.amount()));
      } else {
        instructions.set(i, new Instruction(InstructionType.JMP, instruction.amount()));
      }

      LoopResult result = getAccumulator(instructions);

      if (!result.looped) {
        return result.accumulator;
      }

      instructions.set(i, instruction);
    }

    return 0L;
  }

  private LoopResult getAccumulator(List<Instruction> instructions) {
    Set<Integer> seenInstructions = new HashSet<>();

    int currentIndex = 0;
    long accumulator = 0;

    while (!seenInstructions.contains(currentIndex)) {
      seenInstructions.add(currentIndex);
      Instruction currentInstruction = instructions.get(currentIndex);
      switch (currentInstruction.type()) {
        case NOP -> currentIndex++;
        case ACC -> {
          currentIndex++;
          accumulator += currentInstruction.amount();
        }
        case JMP -> currentIndex += currentInstruction.amount();
      }

      if (currentIndex >= instructions.size()) {
        return new LoopResult(accumulator, false);
      }
    }
    return new LoopResult(accumulator, true);
  }


  private static record LoopResult(long accumulator, boolean looped) {
  }

}
