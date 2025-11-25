package com.yk.faang.algorithms.find_the_index_of_the_first_occurrence_in_a_string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  static void main() {
    Solution solution = new Solution();
    String haystack = "bbbbababbbaabbba";
    String needle = "abb";
    int expectedIndex = haystack.indexOf(needle);
    int index = solution.strStr(haystack, needle);
    LOGGER.info("expectedIndex: {}. Actual index: {}.", expectedIndex, index);
  }
}
