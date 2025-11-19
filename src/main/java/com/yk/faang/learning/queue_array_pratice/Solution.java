package com.yk.faang.learning.queue_array_pratice;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A custom task to implement a queue using an array under the hood.
 */
public class Solution {

  static final class Queue {
    private final int[] elements;
    private final int maxSize;

    private int currentSize;
    private int start = 0;

    public Queue(int maxSize) {
      this.maxSize = maxSize;
      this.elements = new int[maxSize];
    }

    public void enqueue(int val) {
      if (this.currentSize == this.maxSize) {
        throw new IllegalStateException("Queue is full");
      }

      int index = (this.start + this.currentSize) % this.maxSize;
      this.elements[index] = val;
      this.currentSize++;
    }

    public int dequeue() {
      if (currentSize == 0) {
        throw new IllegalStateException("Queue is empty");
      }

      int value = this.elements[this.start];
      this.elements[this.start] = 0;

      this.currentSize--;
      this.start = this.currentSize == 0 ? 0 : (start + 1) % this.maxSize;
      return value;
    }

    public int peek() {
      if (currentSize == 0) {
        throw new IllegalStateException("Queue is empty");
      }

      return this.elements[this.start];
    }

    @Override
    public String toString() {
      if (currentSize == 0) {
        return "[]";
      }

      StringBuilder sb = new StringBuilder("[");
      int currentIndex = start;
      for (int i = 0; i < this.currentSize - 1; i++) {
        sb.append(this.elements[currentIndex]).append(", ");
        currentIndex = (currentIndex + 1) % this.maxSize;
      }

      sb.append(this.elements[currentIndex]).append(", ");
      sb.append("] | ");
      sb.append("[");
      for (int i = 0;i < this.maxSize - 1; i++) {
        sb.append(this.elements[i]).append(", ");
      }

      sb.append(this.elements[this.maxSize - 1]).append("]");
      sb.append("]");
      return sb.toString();
    }
  }

  public static void main() {
    Queue queue = new Queue(5);
    queue.enqueue(1);
    assertThat(queue.elements).isEqualTo(new int[]{1, 0, 0, 0, 0});
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    assertThat(queue.elements).isEqualTo(new int[]{1, 2, 3, 4, 0});
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(5);
    assertThat(queue.elements).isEqualTo(new int[]{1, 2, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(1);

    int value = queue.dequeue();
    assertThat(queue.elements).isEqualTo(new int[]{0, 2, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(value).isEqualTo(1);

    queue.enqueue(6);
    assertThat(queue.elements).isEqualTo(new int[]{6, 2, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(2);

    value = queue.dequeue();
    assertThat(queue.elements).isEqualTo(new int[]{6, 0, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(3);
    assertThat(value).isEqualTo(2);

    value = queue.dequeue();
    assertThat(value).isEqualTo(3);

    value = queue.dequeue();
    assertThat(queue.elements).isEqualTo(new int[]{6, 0, 0, 0, 5});
    assertThat(value).isEqualTo(4);
    assertThat(queue.peek()).isEqualTo(5);

    value = queue.dequeue();
    assertThat(queue.elements).isEqualTo(new int[]{6, 0, 0, 0, 0});
    assertThat(queue.peek()).isEqualTo(6);
    assertThat(value).isEqualTo(5);

    value = queue.dequeue();
    assertThat(queue.elements).isEqualTo(new int[]{0, 0, 0, 0, 0});
    assertThat(value).isEqualTo(6);

    queue.enqueue(7);
    assertThat(queue.elements).isEqualTo(new int[]{7, 0, 0, 0, 0});
    assertThat(queue.peek()).isEqualTo(7);
  }
}
