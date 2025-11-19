package com.yk.faang.leetcode.l0014_longest_common_prefix;

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
 * This approach is simple brute force. We take first string as a prefix and then check if all
 * following strings contain part of it.
 */
@SuppressWarnings("DuplicatedCode")
public class Solution1 {

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

@SuppressWarnings("DuplicatedCode")
class Main1 {

  static void main() {
    Solution1 solution = new Solution1();
    String prefix1 = solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    assertThat(prefix1).isEqualTo("fl");

    String[] testCase0Strings = TestCaseUtils.readLines("test_cases/longest_common_prefix/test01.txt").toArray(new String[0]);
    String prefix2 = runTestCaseWithTimerNs(() -> solution.longestCommonPrefix(testCase0Strings));
    assertThat(prefix2).isEqualTo("fl");
  }
}
