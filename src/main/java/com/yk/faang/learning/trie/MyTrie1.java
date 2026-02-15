package com.yk.faang.learning.trie;

public class MyTrie1 implements Trie {

  private final Node1 root = new Node1();

  @Override
  public void add(String word) {
    Node1 current = root;

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      int index = ch - 'a';

      if (current.children[index] == null) {
        current.children[index] = new Node1();
        current = current.children[index];
      } else {
        current = current.children[index];
      }
    }

    current.isEndOfWord = true;
  }

  @Override
  public boolean contains(String word) {
    Node1 node = getNodeAsPrefix(word);
    return node != null && node.isEndOfWord;
  }

  @Override
  public boolean startsWith(String prefix) {
    return getNodeAsPrefix(prefix) != null;
  }

  private Node1 getNodeAsPrefix(String prefix) {
    Node1 current = root;

    for (int i = 0; i < prefix.length(); i++) {
      char ch = prefix.charAt(i);
      int index = ch - 'a';
      if (current.children[index] == null) {
        return null;
      } else {
        current = current.children[index];
      }
    }

    return current;
  }
}

