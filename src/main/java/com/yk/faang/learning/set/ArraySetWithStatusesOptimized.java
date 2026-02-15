package com.yk.faang.learning.set;

import java.util.Arrays;
import java.util.BitSet;

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
public class ArraySetWithStatusesOptimized implements MySet {

  private static final float LOAD_FACTOR = 0.7f;
  private static final int INITIAL_CAPACITY = 20;

  private int[] values;
  private BitSet statuses;
  private int capacity;
  private int size;

  public ArraySetWithStatusesOptimized() {
    this(INITIAL_CAPACITY);
  }

  public ArraySetWithStatusesOptimized(int capacity) {
    this.capacity = capacity;
    this.values = new int[capacity];
    this.statuses = new BitSet(capacity);
  }

  public boolean add(int value) {
    validate(value);

    if (size >= capacity * LOAD_FACTOR) {
      resize();
    }

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;

    while (maxIterations > 0 && statuses.get(index)) {
      if (values[index] == value) {
        return false;
      }

      index = (index + 1) % capacity;
      maxIterations--;
    }

    values[index] = value;
    statuses.set(index);
    size++;

    return true;
  }

  @Override
  public boolean remove(int value) {
    validate(value);

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;
    while (maxIterations > 0 && statuses.get(index)) {
      if (values[index] == value) {

        // 1. Establish the gap at the deleted element
        int gapIndex = index;
        int nextIndex = (gapIndex + 1) % capacity;

        // 2. Look ahead for elements that might want to slide backward into the gap
        while (statuses.get(nextIndex)) {
          int idealIndex = Math.abs(values[nextIndex]) % capacity;

          // Calculate the circular distances
          int distToGap = (gapIndex - idealIndex + capacity) % capacity;
          int distToCurrent = (nextIndex - idealIndex + capacity) % capacity;

          // 3. If moving the element to the gap brings it closer to its ideal bucket, move it!
          if (distToGap < distToCurrent) {
            values[gapIndex] = values[nextIndex];
            gapIndex = nextIndex; // The gap has now moved to where the element used to be
          }

          nextIndex = (nextIndex + 1) % capacity;
        }

        // 4. Clear the final resting place of the gap in the BitSet
        statuses.clear(gapIndex);
        size--;
        return true;
      }

      index = (index + 1) % capacity;
      maxIterations--;
    }

    return false;
  }

  @Override
  public boolean contains(int value) {
    validate(value);

    int maxIterations = capacity;
    int index = Math.abs(value) % capacity;
    while (maxIterations > 0 && statuses.get(index)) {
      if (values[index] == value) {
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
    statuses.clear();
    Arrays.fill(values, 0);
  }

  @Override
  public int[] toArray() {
    int[] res = new int[size];

    int writeIndex = 0;

    for (int i = statuses.nextSetBit(0); i >= 0; i = statuses.nextSetBit(i + 1)) {
      res[writeIndex] = values[i];
      writeIndex++;
    }

    return res;
  }

  private void resize() {
    int newCapacity = capacity * 2;

    int[] newValues = new int[newCapacity];
    BitSet newStatuses = new BitSet(newCapacity);

    for (int i = statuses.nextSetBit(0); i >= 0; i = statuses.nextSetBit(i + 1)) {
      int value = values[i];
      int newIndex = Math.abs(value) % newCapacity;

      while (newStatuses.get(newIndex)) {
        newIndex = (newIndex + 1) % newCapacity;
      }

      newValues[newIndex] = value;
      newStatuses.set(newIndex);
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
