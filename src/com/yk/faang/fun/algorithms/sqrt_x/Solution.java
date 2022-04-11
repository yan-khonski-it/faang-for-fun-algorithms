package com.yk.faang.fun.algorithms.sqrt_x;

/**
 * https://leetcode.com/problems/sqrtx/
 *
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer,
 * the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 */
class Solution {
  // sqrt <= x / 2, x >= 4
  // use only integer, so we replace a * a == x, with division, x / a == a
  public int mySqrt(int x) {
    // Simple cases
    if (x == 0) {
      return 0;
    } else if (x == 1) {
      return 1;
    } else if (x == 3) {
      return 1;
    } else if (x == 4) {
      return 2;
    }

    int start = 1;
    int end = x / 2;

    while (start < end - 1) {
      int middle = start + (end - start) / 2;
      int division = x / middle;
      if (division == middle) {
        return middle;
      } else if (division > middle) {
        start = middle;
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

  public static void main(String[] args) {
    Solution solution = new Solution();
    int x = 1000000;
    int sqrtX = solution.mySqrt(x);
    System.out.println(sqrtX);
  }
}