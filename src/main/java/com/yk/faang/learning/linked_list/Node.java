package com.yk.faang.learning.linked_list;

import org.assertj.core.util.VisibleForTesting;

public class Node {

  final int value;
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
