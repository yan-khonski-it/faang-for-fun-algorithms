package com.yk.faang.fun.algorithms.smallest_letter_after_target;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {

  private int compareCharacters(char a, char b, char[] letters) {
    if (a == letters[0] && b >= letters[letters.length - 1]) {
      return 1;
    }

    int delta = a - b;
    if (delta == 0) {
      return delta;
    } else {
      return delta > 0 ? 1 : -1;
    }
  }

  public char nextGreatestLetter(char[] letters, char target) {
    if (letters.length == 0) {
      return '0';
    }

    if (compareCharacters(letters[0], target, letters) > 0) {
      return letters[0];
    }

    int start = 0;
    int end = letters.length;
    char foundChar = letters[0];

    while (start <= end) {
      int middle = (start + end) / 2;
      int delta = compareCharacters(letters[middle], target, letters);
      if (delta <= 0) {
        start = middle + 1;
      } else {
        end = middle - 1;
        foundChar = letters[middle];
      }
    }

    return foundChar;
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    char[] letters = {'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
    char target = 'e';
    char res = solution.nextGreatestLetter(letters, target);
    assertThat(res).isEqualTo('n');
  }
}
