package com.yk.faang.leetcode.l0014_longest_common_prefix;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
 * <p>
 * This approach is simple brute force. But we start with the shortest string as prefix. Then check if all if following strings contain part of it.
 */
public class Solution2 implements ISolution {

  @Override
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String prefix = findShortestString(strs);
    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) {
          return "";
        }
      }
    }

    return prefix;
  }

  private String findShortestString(String[] strs) {
    String shortestString = strs[0];
    int minLength = shortestString.length();

    for (int i = 1; i < strs.length; i++) {
      if (strs[i].length() < minLength) {
        shortestString = strs[i];
        minLength = shortestString.length();
      }
    }

    return shortestString;
  }
}
