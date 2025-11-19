package com.yk.faang.algorithms.longest_common_prefix;

import static com.yk.faang.utils.TimerUtils.runTestCaseWithTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.utils.TestCaseUtils;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there
 * is no common prefix, return an empty string "".
 * <p>
 * We take the shortest string as starting prefix. We will use binary search to check if the part of prefix is present in all strings.
 */
public class Solution3 {

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


@SuppressWarnings("DuplicatedCode")
class Main3 {

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    String prefix1 = solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    assertThat(prefix1).isEqualTo("fl");

    String[] testCase0Strings = TestCaseUtils.readLines("test_cases/longest_common_prefix/test01.txt").toArray(new String[0]);
    String prefix2 = runTestCaseWithTimerNs(() -> solution.longestCommonPrefix(testCase0Strings));
    assertThat(prefix2).isEqualTo("fl");
  }
}