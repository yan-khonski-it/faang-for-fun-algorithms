package com.yk.faang.learning.set;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    int n = 1000;
    MySet myLinearArraySet = new MyLinearArraySet(n);
    testSet(myLinearArraySet);

    MySet myArraySet = new MyArraySet(n);
    testSet(myArraySet);

    MySet myBitSet = new MyBitSet(n);
    testSet(myBitSet);

    MySet twoArraysSet = new TwoArraysSet();
    testSet(twoArraysSet);
  }

  private static void testSet(MySet mySet) {
    int[] emptyArray = mySet.toArray();
    assertThat(emptyArray).isEmpty();

    mySet.add(5);
    mySet.add(15);
    mySet.remove(5);

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

    int[] array = mySet.toArray();
    assertThat(array).containsExactly(35);
  }
}
