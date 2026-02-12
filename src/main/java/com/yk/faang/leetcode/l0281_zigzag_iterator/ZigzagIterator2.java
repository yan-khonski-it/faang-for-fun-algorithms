package com.yk.faang.leetcode.l0281_zigzag_iterator;

import java.util.Iterator;

/**
 * https://leetcode.com/problems/zigzag-iterator/
 * <p>
 * Given N iterators
 * <p>
 * Implement the ZigzagIterator class:
 * <p>
 * ZigzagIterator(Iterator<Iterator> iterators) initializes the object with the iterators. boolean hasNext() returns true if the iterator still has elements,
 * and false otherwise. int next() returns the current element of the iterator and moves the iterator to the next element.
 */
@SuppressWarnings("PMD.UnusedAssignment")
public class ZigzagIterator2 {

  private Node head = new Node();
  private Node tail = head;
  private Node current = null;
  private Node previous = null;
  private boolean empty = true;

  public ZigzagIterator2(Iterator<Iterator<Integer>> iterators) {
    while (iterators.hasNext()) {
      Iterator<Integer> iterator = iterators.next();
      if (iterator.hasNext()) {
        tail.next = new Node(iterator);
        tail = tail.next;
        empty = false;
      }
    }

    if (!empty) {
      Node next = head.next;
      head.next = null;
      head = next;
    }

    tail.next = head;
    current = head;
    previous = tail;
  }

  public int next() {
    Integer res = (Integer) current.iterator.next();

    if (!current.iterator.hasNext()) {
      if (current.next == current) {
        empty = true;
      } else {
        Node next = current.next;
        current.next = null;
        current = next;
        previous.next = current;
      }
    } else {
      previous = current;
      current = current.next;
    }

    return res;
  }

  public boolean hasNext() {
    if (empty || current.iterator == null) {
      return false;
    }

    return current.iterator.hasNext();
  }
}
