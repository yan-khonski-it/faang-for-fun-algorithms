package com.yk.faang.leetcode.l1925_count_square_sum_triples;

/**
 * https://leetcode.com/problems/count-square-sum-triples/
 * <p>
 * 1925. Count Square Sum Triples
 * <p>
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2. Given an integer n, return the
 * number of square triples such that 1 <= a, b, c <= n.
 */
class Solution {

  // O(n^2).
  // Iterate through all a and b and sqrt (a * a + b * b) < n AND it's integer.
  public int countTriples(int n) {
    int count = 0;
    int n2 = n * n;
    for (int a = 1; a <= n; a++) {
      int a2 = a * a;
      for (int b = 1; b <= n; b++) {
        int b2 = b * b;
        int a2_b2 = a2 + b2;
        int c = (int) Math.sqrt(a2_b2);
        if (c <= n && c * c == a2_b2) {
          count++;
        }

        if (a2_b2 > n2) {
          break;
        }
      }
    }

    return count;
  }
}
