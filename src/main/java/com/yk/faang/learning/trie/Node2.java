package com.yk.faang.learning.trie;

import java.util.HashMap;
import java.util.Map;

class Node2 {

  Map<Character, Node2> children = new HashMap<>();
  boolean isEndOfWord;
}
