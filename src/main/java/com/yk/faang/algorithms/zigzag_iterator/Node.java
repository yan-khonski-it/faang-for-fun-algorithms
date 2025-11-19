package com.yk.faang.algorithms.zigzag_iterator;

import java.util.Iterator;

public class Node {

  public Iterator<?> iterator;
  public Node next;

  public Node() {
  }

  public Node(Iterator<?> iterator) {
    this.iterator = iterator;
  }

}
