package com.yk.faang.learning.linked_list_2;

import org.assertj.core.util.VisibleForTesting;

public class Node {

  final int value;
  Node prev;
  Node next;

  Node(int value) {
    this.value = value;
  }

  @VisibleForTesting
  Node getNext() {
    return next;
  }

  @VisibleForTesting
  void setNext(Node next) {
    this.next = next;
  }
}
