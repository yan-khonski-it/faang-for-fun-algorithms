package com.yk.faang.learning.linked_list_2;

import java.util.ArrayList;

/**
 * Double linked list with Dummy nodes.
 */
public class LinkedList2 implements MyList {

  private final Node head = new Node(0);
  private final Node tail = new Node(0);
  private int size;

  public LinkedList2() {
    head.next = tail;
    tail.prev = head;
  }

  @Override
  public void add(int value) {
    Node prevNode = tail.prev;
    Node inserted = new Node(value);

    inserted.prev = prevNode;
    prevNode.next = inserted;

    inserted.next = tail;
    tail.prev = inserted;

    size++;
  }

  @Override
  public void addNewHead(int value) {
    Node nextNode = head.next;
    Node inserted = new Node(value);
    head.next = inserted;
    inserted.prev = head;
    inserted.next = nextNode;
    nextNode.prev = inserted;

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

    if (node == head || node == tail) {
      throw new IllegalArgumentException("Cannot remove a dummy node.");
    }

    Node prevNode = node.prev;
    Node nextNode = node.next;

    prevNode.next = nextNode;
    nextNode.prev = prevNode;

    // clean up for GC
    node.prev = null;
    node.next = null;

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
    inserted.prev = node;
    inserted.next = nextNode;
    nextNode.prev = inserted;
    size++;
  }

  @Override
  public void addNodeBefore(Node node, int newValue) {
    if (head == node) {
      addNewHead(newValue);
      return;
    }

    Node prevNode = node.prev;
    Node inserted = new Node(newValue);
    prevNode.next = inserted;
    inserted.prev = prevNode;
    inserted.next = node;
    node.prev = inserted;

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
    while (current != tail) {
      res.add(current.value);
      current = current.next;
    }

    return res;
  }

  @Override
  public Node getHead() {
    return head.next;
  }

  @Override
  public Node getTail() {
    return tail.prev;
  }
}
