package com.yk.faang.learning.trie;

public interface Trie {

  void add(String word);

  boolean contains(String word);

  boolean startsWith(String prefix);

}
