package com.yk.faang.learning.set;

import static java.lang.String.format;

/**
 * Custom implementation of a set. It is an improvement of {@link MyLinearArraySet}.
 * It allows numbers from 1 to N, it always uses memory of size: O(N),
 * where N is the maximum allowed element.
 * <p>
 * Operations time complexity:
 * contains O(1)
 * put O(1)
 * remove O(1)
 * clear O(N)
 */
public class MyArraySet implements MySet {

  private final int n;
  private final int[] array;

  private int size = 0;

  public MyArraySet(int n) {
    this.n = n;
    array = new int[n + 1];
  }

  @Override
  public void add(int value) {
    if (value > n) {
      throw new IllegalArgumentException(format("Max allowed value is N: %s, but you are inserting value: %s.", n, value));
    }

    if (isFull()) {
      throw new IllegalStateException("The set is full");
    }

    array[value] = value;
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

    array[value] = 0;
    size--;
  }

  @Override
  public boolean contains(int value) {
    if (value > n) {
      throw new IllegalArgumentException(format("Max allowed value is N: %s, but you are searching for value: %s.", n, value));
    }

    return array[value] != 0;
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
    for (int i = 0; i < size; i++) {
      array[i] = 0;
    }

    size = 0;
  }

  @Override
  public int[] toArray() {
    int[] res = new int[size];
    int index = 0;
    for (int i = 0; i <= n; i++) {
      if (array[i] != 0) {
        res[index] = i;
        index++;
      }
    }

    return res;
  }
}
