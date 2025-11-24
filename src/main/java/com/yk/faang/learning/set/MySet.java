package com.yk.faang.learning.set;

public interface MySet {

  void add(int value);

  void remove(int value);

  boolean contains(int value);

  int size();

  boolean isEmpty();

  default boolean isFull() {
    return false;
  }

  void clear();

  int[] toArray();

}
