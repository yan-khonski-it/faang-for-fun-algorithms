package com.yk.faang.learning.set;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MySetTest {

  public static Stream<Arguments> getMySets() {
    int n = 1000;
    int capacity = 10;

    return Stream.of(
        Arguments.of(new MyLinearArraySet(n)),
        Arguments.of(new MyArraySet(n)),
        Arguments.of(new MyBitSet(n)),
        Arguments.of(new TwoArraysSet(capacity))
    );
  }

  @MethodSource("getMySets")
  @ParameterizedTest
  public void testFixedSearchChain(MySet mySet) {

    // 1. Add 5.
    // Math.abs(5) % 10 = 5. It is placed at index 5.
    mySet.add(5);

    // 2. Add 15.
    // Math.abs(15) % 10 = 5. Index 5 is taken, so it moves to index 6.
    mySet.add(15);

    // 3. Remove 5.
    // This finds index 5 and sets statuses[5] = 0.
    mySet.remove(5);

    // 4. Check if 15 is still in the set.
    // Expected result: true
    // Actual result in your code: false

    assertThat(mySet.contains(15)).isTrue();
    mySet.add(5);
    mySet.add(25);
    assertThat(mySet.contains(15)).isTrue();
    assertThat(mySet.contains(5)).isTrue();
    assertThat(mySet.contains(25)).isTrue();
    mySet.remove(5);
    mySet.remove(25);
    mySet.add(35);
    mySet.remove(15);
    assertThat(mySet.contains(35)).isTrue();
    assertThat(mySet.contains(45)).isFalse();
  }

  @MethodSource("getMySets")
  @ParameterizedTest
  public void testAdd(MySet mySet) {
    assertThat(mySet.contains(5)).isFalse();
    assertThat(mySet.add(5)).isTrue();
    assertThat(mySet.contains(15)).isFalse();
    assertThat(mySet.contains(5)).isTrue();

    assertThat(mySet.add(5)).isFalse();

    assertThat(mySet.remove(5)).isTrue();
    assertThat(mySet.remove(5)).isFalse();
    assertThat(mySet.contains(5)).isFalse();
    assertThat(mySet.contains(15)).isFalse();
  }

  @MethodSource("getMySets")
  @ParameterizedTest
  void testAddNoDuplicates(MySet mySet) {
    assertThat(mySet.add(5)).isTrue();
    assertThat(mySet.add(5)).isFalse();
    assertThat(mySet.contains(5)).isTrue();
    assertThat(mySet.contains(15)).isFalse();
    assertThat(mySet.contains(25)).isFalse();

    assertThat(mySet.add(15)).isTrue();
    assertThat(mySet.add(25)).isTrue();
    assertThat(mySet.remove(5)).isTrue();
    assertThat(mySet.contains(5)).isFalse();
    assertThat(mySet.remove(5)).isFalse();
    assertThat(mySet.contains(15)).isTrue();
    assertThat(mySet.contains(25)).isTrue();
    assertThat(mySet.add(15)).isFalse();
    assertThat(mySet.add(25)).isFalse();

    int[] array = mySet.toArray();
    if (mySet instanceof MyLinearArraySet) {
      // for array set, we replace middle element that was removed with the last added element, so the order changes.
      assertThat(array).containsExactly(25, 15);
    } else {
      assertThat(array).containsExactly(15, 25);
    }
  }
}