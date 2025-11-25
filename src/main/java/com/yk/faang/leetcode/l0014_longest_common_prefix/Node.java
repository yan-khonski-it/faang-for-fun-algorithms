package com.yk.faang.leetcode.l0014_longest_common_prefix;

/**
 * {@link Trie} node.
 */
public class Node {

  private static final int CHILDREN = 26;

  boolean isEndOfWord;
  char value;
  Node[] children = new Node[CHILDREN];

  public Node() {
    this.value = ' ';
  }

  public Node(char value) {
    this.value = value;
  }

  public int countChildren() {
    int count = 0;
    for (Node child : children) {
      if (child != null) {
        count++;
      }
    }
    return count;
  }

  public Node getFirstChild() {
    for (Node child : children) {
      if (child != null) {
        return child;
      }
    }

    return null;
  }
}
