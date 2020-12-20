package com.djavorszky.adventofcode.day9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class CodeDecoderTest {

  private final CodeDecoder codeDecoder = new CodeDecoder();

  @Test
  void testCodeDecoderTask1() {
    List<Long> testNumbers =
        List.of(
                35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309,
                576)
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toList());

    assertThat(codeDecoder.findTask1Weakness(5, testNumbers)).isEqualTo(127);
  }

  @Test
  void testCodeDecoderTask2() {
    List<Long> testNumbers =
        List.of(
                35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309,
                576)
            .stream()
            .map(Long::valueOf)
            .collect(Collectors.toList());

    assertThat(codeDecoder.findTask2Weakness(5, testNumbers)).isEqualTo(62);
  }
}
