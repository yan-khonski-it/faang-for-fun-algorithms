package com.yk.faang.leetcode.l0281_zigzag_iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://leetcode.com/problems/zigzag-iterator/
 * <p>
 * Given N iterators
 * <p>
 * Implement the ZigzagIterator class:
 * <p>
 * ZigzagIterator(Iterator<Iterator> iterators) initializes the object with the iterators.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
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

class Main2 {

  static final Logger LOGGER = LoggerFactory.getLogger(Main2.class);

  static void main() {
    Iterator<Integer> list1 = List.of(1, 2, 3, 4).iterator();
    Iterator<Integer> list2 = List.of(5, 6).iterator();
    Iterator<Integer> list3 = List.of(7, 8, 9).iterator();
    Iterator<Integer> list4 = Collections.emptyIterator();
    Iterator<Iterator<Integer>> iterators = List.of(list1, list2, list3, list4).iterator();

    ZigzagIterator2 zigzagIterator2 = new ZigzagIterator2(iterators);
    while (zigzagIterator2.hasNext()) {
      Integer value = zigzagIterator2.next();
      LOGGER.info("{} ", value);
    }
  }
}
