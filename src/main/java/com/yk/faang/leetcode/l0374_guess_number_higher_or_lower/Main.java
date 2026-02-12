package com.yk.faang.leetcode.l0374_guess_number_higher_or_lower;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

class Main {

  static void main() {
    Random random = new Random();
    int n = random.nextInt(Integer.MAX_VALUE - 1);
    Solution solution = new Solution();
    solution.guessedNumber = random.nextInt(n);
    int actualGuessedNumber = solution.guessNumber(n);
    assertThat(actualGuessedNumber).isEqualTo(solution.guessedNumber);
  }
}
