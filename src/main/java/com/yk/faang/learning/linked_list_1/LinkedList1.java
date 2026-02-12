package com.yk.faang.learning.linked_list_1;

import java.util.ArrayList;

/**
 * Singly linked list.
 */
public class LinkedList1 implements MyList {

  private Node head;
  private Node tail;
  private int size;

  @Override
  public void add(int value) {
    if (tail == null) {
      head = new Node(value);
      tail = head;
      size++;
      return;
    }

    tail.next = new Node(value);
    tail = tail.next;
    size++;
  }

  @Override
  public void addNewHead(int value) {
    Node node = new Node(value);
    if (head == null) {
      head = node;
      tail = node;
      size++;
      return;
    }

    node.next = head;
    head = node;
    size++;
  }

  /**
   * We assume that the node is present in the list.
   */
  @Override
  public void remove(Node node) {
    if (isEmpty()) {
      return;
    }

    Node previous = null;
    Node current = head;
    while (current != null) {
      if (current == node) {
        break;
      }

      previous = current;
      current = current.next;
    }

    if (current == null) {
      throw new IllegalArgumentException("We could not find the node in the list.");
    }

    if (previous == null) {
      head = current.next;
    } else {
      previous.next = current.next;
    }

    if (node == tail) {
      tail = previous;
    }

    size--;
  }

  @Override
  public void addNodeAfter(Node node, int newValue) {
    if (node == tail) {
      add(newValue);
      return;
    }

    Node inserted = new Node(newValue);
    Node nextNode = node.next;
    node.next = inserted;
    inserted.next = nextNode;
    size++;
  }

  @Override
  public void addNodeBefore(Node node, int newValue) {
    if (head == node) {
      addNewHead(newValue);
      return;
    }

    Node previous = null;
    Node current = head;

    while (current != null) {
      if (current == node) {
        break;
      }

      previous = current;
      current = current.next;
    }

    Node inserted = new Node(newValue);
    // previous is not null, otherwiese, the node is head, which is handled before.
    //noinspection ReassignedVariable,DataFlowIssue
    previous.next = inserted;
    inserted.next = current;
    size++;
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
  public java.util.List<Integer> toList() {
    java.util.List<Integer> res = new ArrayList<>(size);
    Node current = head;
    while (current != null) {
      res.add(current.value);
      current = current.next;
    }

    return res;
  }

  @Override
  public Node getHead() {
    return head;
  }

  @Override
  public Node getTail() {
    return tail;
  }
}
