package com.djavorszky.adventofcode.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InstructionTest {

  @Test
  void testCreateNopInstruction() {
    String testInputNop = "nop +1";

    Instruction nopInstruction = Instruction.parse(testInputNop);

    assertThat(nopInstruction).isNotNull();
    assertThat(nopInstruction.type()).isEqualTo(InstructionType.NOP);
    assertThat(nopInstruction.amount()).isEqualTo(1);
  }

  @Test
  void testCreateAccInstruction() {
    String testInputNop = "acc +1";

    Instruction nopInstruction = Instruction.parse(testInputNop);

    assertThat(nopInstruction).isNotNull();
    assertThat(nopInstruction.type()).isEqualTo(InstructionType.ACC);
    assertThat(nopInstruction.amount()).isEqualTo(1);
  }

  @Test
  void testCreateJmpInstruction() {
    String testInputNop = "jmp +2";

    Instruction nopInstruction = Instruction.parse(testInputNop);

    assertThat(nopInstruction).isNotNull();
    assertThat(nopInstruction.type()).isEqualTo(InstructionType.JMP);
    assertThat(nopInstruction.amount()).isEqualTo(2);
  }

  @Test
  void testCreateInstructionWithNegativeNumber() {
    String testInputNop = "jmp -16";

    Instruction nopInstruction = Instruction.parse(testInputNop);

    assertThat(nopInstruction).isNotNull();
    assertThat(nopInstruction.type()).isEqualTo(InstructionType.JMP);
    assertThat(nopInstruction.amount()).isEqualTo(-16);
  }
}
