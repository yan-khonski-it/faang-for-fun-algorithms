package com.yk.faang.fun.algorithms.queue_list_practice;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A custom task to implement a queue using an array under the hood.
 */
public class Solution {

  static class Node {
    final int val;
    Node next;

    public Node(int val) {
      this.val = val;
    }
  }


  static final class Queue {
    private Node head = null;
    private Node tail = null;

    public void enqueue(int val) {
      if (head == null) {
        head = new Node(val);
        tail = head;
        return;
      }

      tail.next = new Node(val);
      tail = tail.next;
    }

    public int dequeue() {
      if  (head == null) {
        throw new IllegalStateException("Queue is empty");
      }

      int val = head.val;
      head = head.next;
      if (head == null) {
        tail = null;
      }

      return val;
    }

    public int peek() {
      if (head == null) {
        throw new IllegalStateException("Queue is empty");
      }

      return head.val;
    }

    @Override
    public String toString() {
      if (head == null) {
        return "[]";
      }

      StringBuilder sb = new StringBuilder("[");
      Node current = head;
      while (current != null) {
        sb.append(current.val).append(", ");
        current = current.next;
      }

      sb.delete(sb.length() - 2, sb.length());
      sb.append("]");
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    Queue queue = new Queue();
    queue.enqueue(1);
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(5);
    assertThat(queue.peek()).isEqualTo(1);

    int value = queue.dequeue();
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(value).isEqualTo(1);

    queue.enqueue(6);
    assertThat(queue.peek()).isEqualTo(2);

    value = queue.dequeue();
    assertThat(queue.peek()).isEqualTo(3);
    assertThat(value).isEqualTo(2);

    value = queue.dequeue();
    assertThat(value).isEqualTo(3);

    value = queue.dequeue();
    assertThat(value).isEqualTo(4);
    assertThat(queue.peek()).isEqualTo(5);

    value = queue.dequeue();
    assertThat(queue.peek()).isEqualTo(6);
    assertThat(value).isEqualTo(5);

    value = queue.dequeue();
    assertThat(value).isEqualTo(6);

    queue.enqueue(7);
    assertThat(queue.peek()).isEqualTo(7);
  }
}
