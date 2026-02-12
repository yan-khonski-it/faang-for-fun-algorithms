package com.yk.faang.learning.linked_list_1;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LinkedListTest {

  private static Stream<Arguments> getMyLists() {
    return Stream.of(
        Arguments.of(new LinkedList1()),
        Arguments.of(new LinkedList2())
    );
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testAdd(MyList list) {
    list.add(10);

    List<Integer> actual = list.toList();
    assertThat(actual).containsExactly(10);

    list.add(1);
    list.add(2);
    list.add(3);
    actual = list.toList();
    assertThat(actual).containsExactly(10, 1, 2, 3);
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testAddNewHead(MyList list) {
    list.addNewHead(7);

    List<Integer> actual = list.toList();
    assertThat(actual).containsExactly(7);

    list.add(1);
    list.addNewHead(77);

    actual = list.toList();
    assertThat(actual).containsExactly(77, 7, 1);
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testRemoveFromEmpty(MyList list) {
    assertDoesNotThrow(() -> list.remove(new Node(123)));
    List<Integer> actual = list.toList();
    assertThat(actual).isEmpty();
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testRemove(MyList list) {
    list.add(10);

    // Remove head
    Node node = list.getHead();
    list.remove(node);

    List<Integer> actual = list.toList();
    assertThat(actual).isEmpty();

    list.add(1);
    list.add(2);
    list.add(3);

    // Remove tail
    Node tail = list.getTail();
    list.remove(tail);
    actual = list.toList();
    assertThat(actual).containsExactly(1, 2);

    // Remove middle
    list.add(3);
    Node middle = list.getHead().getNext();
    list.remove(middle);
    actual = list.toList();
    assertThat(actual).containsExactly(1, 3);
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testThrowsWhenIllegalNode(MyList list) {
    list.add(1);
    list.add(2);

    Node tail = list.getTail();
    Node illegalNode = new Node(3);
    illegalNode.setNext(tail);

    assertThatThrownBy(() -> {
      list.remove(illegalNode);
    }).isInstanceOf(IllegalArgumentException.class);

    Node head = list.getHead();
    Node illegalNode2 = new Node(33);
    illegalNode2.setNext(head);

    assertThatThrownBy(() -> {
      list.remove(illegalNode2);
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testAddAfter(MyList list) {
    list.add(1);
    list.add(3);

    Node head = list.getHead();

    list.addNodeAfter(head, 2);

    List<Integer> actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 3);

    Node tail = list.getTail();
    list.addNodeAfter(tail, 4);

    actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 3, 4);

    Node node2 = head.getNext();
    list.addNodeAfter(node2, 22);

    actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 22, 3, 4);

    // Previous bug
    tail = list.getTail();
    Node copyTail = tail;

    list.addNodeAfter(tail, 5);
    actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 22, 3, 4, 5);
    assertThat(copyTail.next).isSameAs(list.getTail());

  }

  @MethodSource("getMyLists")
  @ParameterizedTest
  void testAddNodeBefore(MyList list) {
    list.add(2);
    list.add(3);

    Node head = list.getHead();
    list.addNodeBefore(head, 1);

    List<Integer> actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 3);

    Node tail = list.getTail();
    list.addNodeBefore(tail, 22);

    actual = list.toList();
    assertThat(actual).containsExactly(1, 2, 22, 3);
  }
}