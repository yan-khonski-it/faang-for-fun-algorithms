package com.yk.faang.leetcode.l0014_longest_common_prefix;

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

