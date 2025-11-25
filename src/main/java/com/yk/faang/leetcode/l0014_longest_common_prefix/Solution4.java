package com.yk.faang.leetcode.l0014_longest_common_prefix;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
 * <p>
 * We will build a Trie from all strings expect the shortest and then find the common prefix.
 */
public class Solution4 implements ISolution {

  @Override
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    Trie trie = new Trie();
    for (String str : strs) {
      if (str.isEmpty()) {
        return "";
      }

      trie.insert(str);
    }

    return trie.longestPrefix();
  }
}
