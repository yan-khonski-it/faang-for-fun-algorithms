package com.yk.faang.algorithms.sqrtx;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/sqrtx/
 * <p>
 * Given a non-negative integer x, compute and return the square root of x.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is
 * returned.
 * <p>
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 */
class Solution {

  // use only integer, so we replace a * a == x, with division, x / a == a
  // sqrt <= x / 2
  public int mySqrt(int x) {
    // Simple cases
    if (x < 2) {
      return x;
    }

    int start = 2;
    int end = x / 2;

    while (start <= end) {
      int middle = start + (end - start) / 2;
      int division = x / middle;
      if (division == middle) {
        return middle;
      } else if (division > middle) {
        start = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    if (x / end >= end) {
      return end;
    } else {
      return start;
    }
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int x = 1000000;
    int sqrtX = solution.mySqrt(x);
    assertThat(sqrtX).isEqualTo(1000);
  }
}
