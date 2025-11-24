package com.yk.faang.learning.queue;

public interface MyQueue {

  void enqueue(int o);

  int dequeue();

  int peek();

  boolean isEmpty();

  boolean isFull();

  // Used for debugging.
  int[] toArray();
}
