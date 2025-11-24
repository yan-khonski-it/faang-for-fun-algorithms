package com.yk.faang.learning.queue;

public class MyArrayQueue implements MyQueue {

  private final int[] array;
  private final int capacity;
  private int head = -1;
  private int tail = -1;
  private int size = 0;

  public MyArrayQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be greater than 0");
    }

    this.capacity = capacity;
    this.array = new int[capacity];
  }

  @Override
  public void enqueue(int value) {
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }

    if (isEmpty()) {
      head = 0;
      tail = 0;
    } else {
      tail = (tail + 1) % this.capacity;
    }

    array[tail] = value;
    size++;
  }

  @Override
  public int dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }

    int value = array[head];
    size--;
    if (isEmpty()) {
      head = -1;
      tail = -1;
    } else {
      head = (head + 1) % this.capacity;
    }

    return value;
  }

  @Override
  public int peek() {
    return array[head];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return size == this.capacity;
  }

  @Override
  public int[] toArray() {
    if (isEmpty()) {
      return new int[0];
    }

    int[] res = new int[size];
    int currentIndex = head;
    for (int copiedIndex = 0; copiedIndex < size; copiedIndex++) {
      res[copiedIndex] = array[currentIndex];
      currentIndex = (currentIndex + 1) % this.capacity;
    }

    return res;
  }
}
