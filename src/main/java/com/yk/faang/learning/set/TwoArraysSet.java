package com.yk.faang.learning.set;

import java.util.Arrays;

/**
 * Implementation of set. Average time complexity:
 * <pre>
 * add O(1)
 * remove O(1)
 * contains O(1)
 * </pre>
 * <p>
 * The difference of this set and other sets for limited keys ({@link MyBitSet} or {@link MyArraySet}), is that this set allocates memory proportionally the
 * number of the elements it stores. While the previous sets would allocate memory based on the maximum key.
 */
public class TwoArraysSet implements MySet {

  /**
   * Statuses. We need to mark REMOVED, so after the element was removed, and status is set to 2, we need to distiungish it from 0.
   * <p>
   * When we search for an element, that could appear at the index or above position
   * <pre>
   *   {@code
   *   index = Math.abs(value) % capacity
   *   }
   * </pre>
   * we will start searching for that value. And if we see status 0, we will stop searching, but the element is present somewhere above index.
   */
  private static final int EMPTY = 0;
  private static final int ADDED = 1;
  private static final int REMOVED = 2;

  private static final float LOAD_FACTOR = 0.7f;
  private static final int INITIAL_CAPACITY = 20;

  private int[] values;
  private int[] statuses;
  private int capacity;
  private int size;

  public TwoArraysSet() {
    this(INITIAL_CAPACITY);
  }

  public TwoArraysSet(int capacity) {
    this.capacity = capacity;
    this.values = new int[capacity];
    this.statuses = new int[capacity];
  }

  @Override
  public boolean add(int value) {
    validate(value);

    if (size >= capacity * LOAD_FACTOR) {
      resize();
    }

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;
    int firstRemoved = -1;

    while (maxIterations > 0 && statuses[index] != EMPTY) {
      if (values[index] == value && statuses[index] == ADDED) {
        return false;
      }

      if (statuses[index] == REMOVED && firstRemoved == -1) {
        firstRemoved = index;
      }

      index = (index + 1) % capacity;
      maxIterations--;
    }

    if (firstRemoved != -1) {
      index = firstRemoved;
    }

    values[index] = value;
    statuses[index] = ADDED;
    size++;

    return true;
  }

  @Override
  public boolean remove(int value) {
    validate(value);

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;
    while (maxIterations > 0 && statuses[index] != EMPTY) {
      if (values[index] == value && statuses[index] == ADDED) {
        statuses[index] = REMOVED;
        size--;
        return true;
      } else {
        index = (index + 1) % capacity;
      }

      maxIterations--;
    }

    return false;
  }

  @Override
  public boolean contains(int value) {
    validate(value);

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;
    while (maxIterations > 0 && statuses[index] != EMPTY) {
      if (values[index] == value && statuses[index] == ADDED) {
        return true;
      } else {
        index = (index + 1) % capacity;
      }

      maxIterations--;
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
  public void clear() {
    size = 0;
    Arrays.fill(statuses, 0);
    Arrays.fill(values, 0);
  }

  @Override
  public int[] toArray() {
    int[] res = new int[size];

    int writeIndex = 0;
    for (int i = 0; i < capacity; i++) {
      if (statuses[i] == ADDED) {
        res[writeIndex] = values[i];
        writeIndex++;
      }
    }
    return res;
  }

  private void resize() {
    int newCapacity = capacity * 2;

    int[] newValues = new int[newCapacity];
    int[] newStatuses = new int[newCapacity];

    // Copy elements
    for (int i = 0; i < capacity; i++) {
      if (statuses[i] == ADDED) {
        int value = values[i];
        int newIndex = Math.abs(value) % newCapacity;

        while (newStatuses[newIndex] == ADDED) {
          newIndex = (newIndex + 1) % newCapacity;
        }

        newValues[newIndex] = value;
        newStatuses[newIndex] = ADDED;
      }
    }

    this.values = newValues;
    this.statuses = newStatuses;
    this.capacity = newCapacity;
  }

  private void validate(int value) {
    if (value == Integer.MIN_VALUE || value == Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Border values are not allowed.");
    }
  }
}
