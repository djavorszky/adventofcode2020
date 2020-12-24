package com.djavorszky.adventofcode.day7;

import com.djavorszky.adventofcode.Day;
import com.djavorszky.adventofcode.helper.InputSource;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 implements Day {
  private final Map<String, List<String>> task1ContainsRelationship = new HashMap<>();
  private Map<String, List<Bags>> task2ContainsRelationship;

  @Override
  public long task1(InputSource inputSource) {
    inputSource.getInput().stream()
        .map(Holder::from)
        .forEach(
            holder ->
                holder
                    .getContainedBags()
                    .forEach(
                        containedBag -> {
                          String name = containedBag.name();
                          if (task1ContainsRelationship.containsKey(name)) {
                            task1ContainsRelationship.get(name).add(holder.getName());
                          } else {
                            List<String> containingBags = new ArrayList<>();
                            containingBags.add(holder.getName());
                            task1ContainsRelationship.put(name, containingBags);
                          }
                        }));

    return (long) getNumberOfContainingBags("shiny gold").size() - 1;
  }

  @Override
  public long task2(InputSource inputSource) {
    task2ContainsRelationship =
        inputSource.getInput().stream()
            .map(Holder::from)
            .collect(Collectors.toMap(Holder::getName, Holder::getContainedBags, (a, b) -> b));

    return getNumberOfContainedBags("shiny gold") - 1;
  }

  private Set<String> getNumberOfContainingBags(String name) {
    Set<String> result = new HashSet<>();
    result.add(name);

    List<String> containingBags = task1ContainsRelationship.get(name);
    if (containingBags == null) {
      return result;
    }

    for (String containingBag : containingBags) {
      result.addAll(getNumberOfContainingBags(containingBag));
    }

    return result;
  }

  private long getNumberOfContainedBags(String name) {
    List<Bags> containedBags = task2ContainsRelationship.get(name);

    long numberOfBags = 1;
    for (Bags bags : containedBags) {
      numberOfBags += bags.amount() * getNumberOfContainedBags(bags.name());
    }

    return numberOfBags;
  }
}
