package com.yk.faang.learning.trie;

public class MyTrie2 implements Trie {

  private final Node2 root = new Node2();

  @Override
  public void add(String word) {
    Node2 current = root;

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      current = current.children.computeIfAbsent(ch, c -> new Node2());
    }

    current.isEndOfWord = true;
  }

  @Override
  public boolean contains(String word) {
    Node2 node = getNodeAsPrefix(word);
    return node != null && node.isEndOfWord;
  }

  @Override
  public boolean startsWith(String prefix) {
    return getNodeAsPrefix(prefix) != null;
  }

  private Node2 getNodeAsPrefix(String prefix) {
    Node2 current = root;

    for (int i = 0; i < prefix.length(); i++) {
      char ch = prefix.charAt(i);
      current = current.children.get(ch);
      if (current == null) {
        return null;
      }
    }

    return current;
  }
}

