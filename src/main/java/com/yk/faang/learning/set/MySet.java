package com.yk.faang.learning.set;

public interface MySet {

  boolean add(int value);

  boolean remove(int value);

  boolean contains(int value);

  int size();

  boolean isEmpty();

  default boolean isFull() {
    return false;
  }

  void clear();

  int[] toArray();

}
