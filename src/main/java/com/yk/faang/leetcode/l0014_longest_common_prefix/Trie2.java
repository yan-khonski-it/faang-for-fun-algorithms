package com.yk.faang.leetcode.l0014_longest_common_prefix;

import java.util.Map;

class Trie2 {

  Node2 root = new Node2();

  public void insert(String word) {
    Node2 current = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      current = current.children.computeIfAbsent(ch, _ -> new Node2(ch));
    }

    current.isEndOfWord = true;
  }

  public String longestPrefix() {
    StringBuilder prefix = new StringBuilder();
    Node2 current = root;
    while (current != null && !current.isEndOfWord && current.children.size() == 1) {
      Map.Entry<Character, Node2> entry = current.children.entrySet().iterator().next();
      prefix.append(entry.getKey());
      current = entry.getValue();
    }

    return prefix.toString();
  }
}
