package com.yk.faang.learning.queue;

class Node {

  int value;
  Node next;

  public Node(int value) {
    this.value = value;
  }
}

public class MyLinkedListQueue implements MyQueue {

  private Node head = null;
  private Node tail = null;
  private int size = 0;

  @Override
  public void enqueue(int value) {
    if (isEmpty()) {
      head = new Node(value);
      tail = head;
    } else {
      tail.next = new Node(value);
      tail = tail.next;
    }

    size++;
  }

  @Override
  public int dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }

    int value = head.value;
    if (size == 1) {
      head = null;
      tail = null;
    } else {
      head = head.next;
    }

    size--;
    return value;
  }

  @Override
  public int peek() {
    return head.value;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public int[] toArray() {
    int[] array = new int[size];
    Node current = head;
    for (int i = 0; i < size; i++) {
      array[i] = current.value;
      current = current.next;
    }

    return array;
  }
}
