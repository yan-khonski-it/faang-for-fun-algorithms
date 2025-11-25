package com.yk.faang.leetcode.l0014_longest_common_prefix;

public class Trie {

  Node root = new Node();

  public void insert(String word) {
    Node current = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      int chIndex = ch - 'a';
      if (current.children[chIndex] == null) {
        current.children[chIndex] = new Node(ch);
      }

      current = current.children[chIndex];
    }

    current.isEndOfWord = true;
  }

  public String longestPrefix() {
    StringBuilder prefix = new StringBuilder();
    Node current = root;
    while (current != null && !current.isEndOfWord && current.countChildren() == 1) {
      Node child = current.getFirstChild();
      prefix.append(child.value);
      current = child;
    }

    return prefix.toString();
  }
}
