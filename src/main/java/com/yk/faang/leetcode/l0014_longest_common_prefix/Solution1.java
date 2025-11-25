package com.yk.faang.leetcode.l0014_longest_common_prefix;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
 * <p>
 * This approach is simple brute force. We take first string as a prefix and then check if all following strings contain part of it.
 */

public class Solution1 implements ISolution {

  @Override
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String prefix = strs[0];
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
}

