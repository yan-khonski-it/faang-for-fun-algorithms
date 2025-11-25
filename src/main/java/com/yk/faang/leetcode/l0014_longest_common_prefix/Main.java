package com.yk.faang.leetcode.l0014_longest_common_prefix;

import static com.yk.faang.utils.TimeUtils.withTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.utils.TestCaseUtils;

public class Main {

  static void main() {
    ISolution solution1 = new Solution1();
    testSolution(solution1);

    ISolution solution2 = new Solution2();
    testSolution(solution2);

    ISolution solution3 = new Solution3();
    testSolution(solution3);

    ISolution solution4 = new Solution4();
    testSolution(solution4);

    ISolution solution5 = new Solution5();
    testSolution(solution5);
  }

  private static void testSolution(ISolution solution) {
    String prefix1 = solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    assertThat(prefix1).isEqualTo("fl");

    String[] testCase0Strings = TestCaseUtils.readLines("test_cases/longest_common_prefix/test01.txt").toArray(new String[0]);
    String prefix2 = withTimerNs(() -> solution.longestCommonPrefix(testCase0Strings), "longestCommonPrefix(testCase0Strings)");
    assertThat(prefix2).isEqualTo("fl");
  }
}
