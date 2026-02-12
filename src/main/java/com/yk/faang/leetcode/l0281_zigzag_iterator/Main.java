package com.yk.faang.leetcode.l0281_zigzag_iterator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  static final Logger LOGGER = LogManager.getLogger(Main.class);

  static void main() {
    test1();
    test2();
  }

  private static void test1() {
    List<Integer> collectedItems = new ArrayList<>();

    ZigzagIterator zigzagIterator = new ZigzagIterator(List.of(1, 2), List.of(3, 4, 5, 6));
    while (zigzagIterator.hasNext()) {
      Integer value = zigzagIterator.next();
      collectedItems.add(value);
    }

    LOGGER.info("Test1:\n{}\n\n", collectedItems);
    assertThat(collectedItems).containsExactly(1, 3, 2, 4, 5, 6);
  }

  private static void test2() {
    List<Integer> collectedItems = new ArrayList<>();

    Iterator<Integer> list1 = List.of(1, 2, 3, 4).iterator();
    Iterator<Integer> list2 = List.of(5, 6).iterator();
    Iterator<Integer> list3 = List.of(7, 8, 9).iterator();
    Iterator<Integer> list4 = Collections.emptyIterator();
    Iterator<Iterator<Integer>> iterators = List.of(list1, list2, list3, list4).iterator();

    ZigzagIterator2 zigzagIterator2 = new ZigzagIterator2(iterators);
    while (zigzagIterator2.hasNext()) {
      Integer value = zigzagIterator2.next();
      collectedItems.add(value);
    }

    LOGGER.info("Test2:\n{}\n\n", collectedItems);
    assertThat(collectedItems).containsExactly(1, 5, 7, 2, 6, 8, 3, 9, 4);
  }
}
