package com.yk.faang.leetcode.l0374_guess_number_higher_or_lower;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 * <p>
 * 374. Guess Number Higher or Lower
 * <p>
 * We are playing the Guess Game. The game is as follows: I pick a number from 1 to n. You have to guess which number I
 * picked. Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * <p>
 * -1: Your guess is higher than the number I picked (i.e. num > pick). 1: Your guess is lower than the number I picked
 * (i.e. num < pick). 0: your guess is equal to the number I picked (i.e. num == pick). Return the number that I
 * picked.
 */

class GuessGame {

  int guessedNumber;

  int guess(int pick) {
    return Integer.compare(guessedNumber, pick);
  }
}

class Solution extends GuessGame {

  public int guessNumber(int n) {
    if (n == 1) {
      return 1;
    }

    int start = 1;
    int end = n;

    while (start <= end) {
      int middle = start + (end - start) / 2;
      int guessAttempt = guess(middle);
      if (guessAttempt == 0) {
        return middle;
      } else if (guessAttempt < 0) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return -1;
  }
}

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
