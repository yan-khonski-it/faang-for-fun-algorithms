package com.yk.faang.learning.linked_list_2;

import java.util.ArrayList;

/**
 * Double linked list.
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

    Node inserted = new Node(value);
    tail.next = inserted;
    inserted.prev = tail;
    tail = inserted;

    size++;
  }

  @Override
  public void addNewHead(int value) {
    Node inserted = new Node(value);
    if (head == null) {
      head = inserted;
      tail = inserted;
      size++;
      return;
    }

    inserted.next = head;
    head.prev = inserted;
    head = inserted;
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
      removeHead();
      return;
    }

    if (node == tail) {
      removeTail();
      return;
    }

    Node prev = node.prev;
    Node next = node.next;

    prev.next = next;
    next.prev = prev;

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

    Node inserted = new Node(newValue);
    Node prevNode = node.prev;

    prevNode.next = inserted;
    inserted.prev = prevNode;
    inserted.next = node;
    node.prev = inserted;
    size++;
  }

  private void removeHead() {
    Node nextNode = head.next;
    if (nextNode == null) {
      head = null;
      tail = null;
    } else {
      nextNode.prev = null;
      head.next = null;
      head = nextNode;
    }

    size--;
  }

  private void removeTail() {
    Node prevNode = tail.prev;
    if (prevNode == null) {
      head = null;
      tail = null;
    } else {
      prevNode.next = null;
      tail.prev = null;
      tail = prevNode;
    }

    size--;
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
