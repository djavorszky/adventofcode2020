package com.djavorszky.adventofcode.day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HolderTest {
  @Test
  void testHolderWithNoContainedBag() {
    String testInput = "dotted black bags contain no other bags.";

    Holder holder = Holder.from(testInput);

    assertThat(holder).isNotNull();
    assertThat(holder.getName()).isEqualTo("dotted black");
    assertThat(holder.getContainedBags()).isEmpty();
  }

  @Test
  void testHolderWithOneContainedBag() {
    String testInput = "bright white bags contain 1 shiny gold bag.";

    List<Bags> expectedBags = List.of(new Bags(1, "shiny gold"));

    Holder holder = Holder.from(testInput);

    assertThat(holder).isNotNull();
    assertThat(holder.getName()).isEqualTo("bright white");
    assertThat(holder.getContainedBags()).isEqualTo(expectedBags);
  }

  @Test
  void testHolderWithTwoContainedBag() {
    String testInput = "light red bags contain 1 bright white bag, 2 muted yellow bags.";

    List<Bags> expectedBags = List.of(new Bags(1, "bright white"), new Bags(2, "muted yellow"));

    Holder holder = Holder.from(testInput);

    assertThat(holder).isNotNull();
    assertThat(holder.getName()).isEqualTo("light red");
    assertThat(holder.getContainedBags()).isEqualTo(expectedBags);
  }
}
