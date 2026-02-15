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
  public boolean add(int value) {
    validate(value);

    if (contains(value)) {
      return false;
    }

    if (isFull()) {
      throw new IllegalStateException("The set is full");
    }

    bitSet.set(value);
    size++;

    return true;
  }

  @Override
  public boolean remove(int value) {
    validate(value);

    if (isEmpty()) {
      return false;
    }

    if (!contains(value)) {
      return false;
    }

    bitSet.clear(value);
    size--;

    return true;
  }

  @Override
  public boolean contains(int value) {
    validate(value);
    return bitSet.get(value);
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

    for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
      // operate on index i here
      res[index] = i;
      index++;

      if (i == Integer.MAX_VALUE) {
        break; // or (i+1) would overflow
      }
    }

    return res;
  }

  private void validate(int value) {
    if (value > n || value <= 0) {
      throw new IllegalArgumentException(format("Allowed value from 1 to N. value = %s", value));
    }
  }
}
