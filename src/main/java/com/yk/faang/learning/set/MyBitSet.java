package com.yk.faang.learning.set;

import static java.lang.String.format;

import java.util.BitSet;

/**
 * Custom implementation of a set. It is an improvement of {@link MyArraySet}.
 * <p>
 * Instead of using array of integers, we use bitset to track elements. It allows numbers from 1 to N, it always uses memory of size: O(N), where N is the
 * maximum allowed element.
 * <p>
 * Operations time complexity: contains O(1), add O(1), remove O(1), clear O(N).
 */
public class MyBitSet implements MySet {

  private final int n;
  private final BitSet bitSet;

  private int size;

  public MyBitSet(int n) {
    this.n = n;
    this.bitSet = new BitSet(n + 1);
  }

  @Override
  public void add(int value) {
    if (value > n) {
      throw new IllegalArgumentException(format("Max allowed value is N: %s, but you are inserting value: %s.", n, value));
    }

    if (isFull()) {
      throw new IllegalStateException("The set is full");
    }

    bitSet.set(value);
    size++;
  }

  @Override
  public void remove(int value) {
    if (value > n) {
      throw new IllegalArgumentException(format("Max allowed value is N: %s, but you are removing value: %s.", n, value));
    }

    if (isEmpty()) {
      return;
    }

    bitSet.clear(value);
    size--;
  }

  @Override
  public boolean contains(int value) {
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return size == n;
  }

  @Override
  public void clear() {
    bitSet.clear();
    size = 0;
  }

  @Override
  public int[] toArray() {
    int[] res = new int[size];
    int index = 0;
    for (int i = 0; i <= n; i++) {
      if (bitSet.get(i)) {
        res[index] = i;
        index++;
      }
    }

    return res;
  }
}
