package com.yk.faang.learning.trie;

class Node1 {

  private static final int ALPHABET_SIZE = 26;

  Node1[] children = new Node1[ALPHABET_SIZE];
  boolean isEndOfWord;
}
