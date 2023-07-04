package com.yk.faang.fun.algorithms.zigzag_iterator;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://leetcode.com/problems/zigzag-iterator/
 * <p>
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements
 * alternately.
 * <p>
 * Implement the ZigzagIterator class:
 * <p>
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise. int
 * next() returns the current element of the iterator and moves the iterator to the next element.
 */
@SuppressWarnings("PMD.UnusedAssignment")
public class ZigzagIterator {

  private Node head = new Node();
  private Node tail = head;
  private Node current = null;
  private Node previous = null;
  private boolean empty = true;

  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    Iterator<?> iterator1 = v1.iterator();
    if (iterator1.hasNext()) {
      tail.next = new Node(iterator1);
      tail = tail.next;
      empty = false;
    }

    Iterator<?> iterator2 = v2.iterator();
    if (iterator2.hasNext()) {
      tail.next = new Node(iterator2);
      tail = tail.next;
      empty = false;
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

class Main {

  static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    ZigzagIterator zigzagIterator = new ZigzagIterator(List.of(1, 2), List.of(3, 4, 5, 6));
    while (zigzagIterator.hasNext()) {
      Integer value = zigzagIterator.next();
      LOGGER.info("{} ", value);
    }
  }
}