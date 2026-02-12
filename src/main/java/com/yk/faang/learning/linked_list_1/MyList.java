package com.yk.faang.learning.linked_list_1;

import java.util.List;
import org.assertj.core.util.VisibleForTesting;

public interface MyList {

  void add(int value);

  void addNewHead(int value);

  void remove(Node node);

  void addNodeAfter(Node node, int newValue);

  void addNodeBefore(Node node, int newValue);

  int size();

  boolean isEmpty();

  List<Integer> toList();

  // Used in tests.
  @VisibleForTesting
  Node getHead();

  // Used in tests.
  @VisibleForTesting
  Node getTail();
}
