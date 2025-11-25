package com.yk.faang.leetcode.l0014_longest_common_prefix;

import java.util.HashMap;
import java.util.Map;

public class Solution5 implements ISolution {

  @Override
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    Trie2 trie = new Trie2();
    for (String str : strs) {
      if (str.isEmpty()) {
        return "";
      }

      trie.insert(str);
    }

    return trie.longestPrefix();
  }
}

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
