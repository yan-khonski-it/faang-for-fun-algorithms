package com.yk.faang.learning.linked_list_1;

import java.util.ArrayList;

/**
 * Singly linked list with Dummy nodes.
 */
public class LinkedList2 implements MyList {

  private Node head = new Node(0);
  private Node tail = head;
  private int size;

  @Override
  public void add(int value) {
    tail.next = new Node(value);
    tail = tail.next;
    size++;
  }

  @Override
  public void addNewHead(int value) {
    Node inserted = new Node(value);
    Node next = head.next;
    head.next = inserted;
    inserted.next = next;

    if (tail == head) {
      tail = inserted;
      return;
    }

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

    if (node == head) {
      throw new IllegalArgumentException("Cannot remove a dummy head node.");
    }

    Node previous = head;
    Node current = head.next;
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

    previous.next = current.next;

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
      throw new IllegalArgumentException("Cannot add a dummy head node.");
    }

    Node previous = head;
    Node current = head.next;

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
    Node current = head.next;
    while (current != null) {
      res.add(current.value);
      current = current.next;
    }

    return res;
  }

  @Override
  public Node getHead() {
    // We do not want to return a dummy node.
    return head.next;
  }

  @Override
  public Node getTail() {
    // We do not want to return a dummy node.
    if (tail == head) {
      return head.next;
    }

    return tail;
  }
}
