package com.yk.faang.leetcode.l1015_divisible_by_k;

/**
 * 1015. Smallest Integer Divisible by K
 * <p>
 * Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.
 * <p>
 * Return the length of n. If there is no such n, return -1.
 * <p>
 * Note: n may not fit in a 64-bit signed integer.
 * <p>
 * Approach:
 * <p>
 * N, k are integers, N > k.
 * <p>
 * N % k = reminder
 * <p>
 * reminder can be 1, 2, ... k - 1. The reminder cannot be k.
 * <p>
 * % k = 0<p>
 * <pre>
 * Let A and B be integers:
 * A = q1k + r1 => A % k = r1
 * B = q2k + r2 => B % k = r2
 * Then:
 * (A + B) % k = (q1k + r1 + q2k + r2) % k = ((q1 + q2)k + (r1 + r2)) % k = (r1 + r2) % k = (A % k + B % k) % k
 * A*B % k = (q1k + r1) * (q2k + r2) % k = (q1q2*k^2 + kq1*r2 + kq2*r1 + r1*r2) % k = (r1 * r2) % k = (A % k * B % k) % k
 * A % k % k ... multiple times % k = r1
 *
 * So let: R1 be 1, R2 be 11, R_m be 11...1 (1 times m).
 * R_m+1 = R_m * 10 + 1 (from the problem description).
 * Let's apply modulo k to the both parts of the equation:
 * R_m+1 % k = (R_m * 10 + 1) % k
 * So, R1 % k is the first reminder
 * R2 % k = (R1 * 10 + 1 )% k
 * </pre>
 */
public class Solution {

  public int smallestRepunitDivByK(int k) {
    // An even numbers cannot be consist of 1 (ones). A number that is devicible by 5, can end with 0 or 5.
    if (k == 0 || k % 2 == 0 || k % 5 == 0) {
      return -1;
    }

    int currentReminder = 1;
    for (int digits = 1; digits <= k; digits++) {
      if (currentReminder % k == 0) {
        return digits;
      }

      currentReminder = (10 * currentReminder + 1) % k;
    }

    return -1;
  }
}
