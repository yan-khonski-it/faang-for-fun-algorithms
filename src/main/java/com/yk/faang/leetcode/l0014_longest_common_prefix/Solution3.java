package com.yk.faang.leetcode.l0014_longest_common_prefix;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there is no common prefix, return an empty string "".
 * <p>
 * We take the shortest string as starting prefix. We will use binary search to check if the part of prefix is present in all strings.
 */
public class Solution3 implements ISolution {

  @Override
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String smallestPrefix = findShortestString(strs);
    if (smallestPrefix.isEmpty()) {
      return "";
    }

    int start = 0;
    int end = smallestPrefix.length();

    while (start <= end) {
      int middle = (start + end) / 2;
      String commonPrefix = smallestPrefix.substring(0, middle);
      if (isPrefixPresentInAllStrings(commonPrefix, strs)) {
        start = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    return smallestPrefix.substring(0, (start + end) / 2);
  }

  private boolean isPrefixPresentInAllStrings(String prefix, String[] strs) {
    for (String str : strs) {
      if (!str.startsWith(prefix)) {
        return false;
      }
    }

    return true;
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
