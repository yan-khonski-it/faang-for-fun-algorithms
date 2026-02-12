package com.yk.faang.leetcode.l0014_longest_common_prefix;

import java.util.HashMap;
import java.util.Map;

class Node2 {

  boolean isEndOfWord;
  char value;
  Map<Character, Node2> children = new HashMap<>();

  public Node2() {
    this.value = ' ';
  }

  public Node2(char value) {
    this.value = value;
  }
}
