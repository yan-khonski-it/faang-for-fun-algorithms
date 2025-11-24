package com.yk.faang.learning.set;

/**
 * Basic set implementation used for comparing other sets in the tests.
 * <p>
 * All the elements are stored in the array.
 * <p>
 * Add - O(N), we call contains that take O(N)
 * <p>
 * Remove - O(N), we need to find the index of the removed element in the array.
 * <p>
 * Contains - O(N).
 * <p>
 * Clear - O(1), we just reset the size.
 */
public class MyLinearArraySet implements MySet {

  private final int[] array;
  private final int capacity;

  private int size = 0;

  public MyLinearArraySet(int capacity) {
    this.capacity = capacity;
    this.array = new int[capacity];
  }

  @Override
  public void add(int value) {
    if (isFull()) {
      throw new IllegalStateException("The set is full");
    }

    if (contains(value)) {
      return;
    }

    array[size] = value;
    size++;
  }

  @Override
  public void remove(int value) {
    if (isEmpty()) {
      return;
    }

    for (int i = 0; i < size; i++) {
      // If we find the value, we replace it with last stored value and decrement the set size.
      if (array[i] == value) {
        array[i] = array[size - 1];
        size--;
        return;
      }
    }
  }

  @Override
  public boolean contains(int value) {
    for (int i = 0; i < size; i++) {
      if (array[i] == value) {
        return true;
      }
    }

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
    return size == capacity;
  }

  @Override
  public void clear() {
    size = 0;
  }

  @Override
  public int[] toArray() {
    int[] res = new int[size];
    System.arraycopy(array, 0, res, 0, size);

    return res;
  }
}
