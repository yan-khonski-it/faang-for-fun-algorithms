package com.yk.faang.fun.algorithms.find_the_index_of_the_first_occurrence_in_a_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO - fix it, this solution is broken
@SuppressWarnings("PMD.LooseCoupling")
class Solution {

  public int strStr(String haystack, String needle) {
    // using character map
    if (haystack == null || needle == null) {
      return -1;
    }

    if (needle.isEmpty()) {
      return 0;
    }

    if (haystack.isEmpty()) {
      return -1;
    }

    int n = haystack.length();
    int m = needle.length();
    if (n < m) {
      return -1;
    }

    if (n == m) {
      return haystack.equals(needle) ? 0 : -1;
    }

    Map<Character, List<Integer>> needleMap = new HashMap<>();
    for (int i = 0; i < m; i++) {
      char ch = needle.charAt(i);
      List<Integer> list = needleMap.computeIfAbsent(ch, k -> new ArrayList<>());

      list.add(i);
    }

    List<LinkedList<Integer>> needleCharacterMap = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      needleCharacterMap.add(new LinkedList<>());
    }

    // Now we know where each character of needle is located in haystack.
    for (int i = 0; i < n; i++) {
      char ch = haystack.charAt(i);
      List<Integer> charPositions = needleMap.get(ch);
      if (charPositions != null) {
        for (Integer position : charPositions) {
          // each character that is present in the needle map, now will have haystack index in needleCharacterMap.
          needleCharacterMap.get(position).add(i);
        }
      }
    }

    // check non-existing characters, if a character from the needle is not present in the haystack.
    for (int i = 0; i < m; i++) {
      LinkedList<Integer> charPositions = needleCharacterMap.get(i);
      if (charPositions.isEmpty()) {
        return -1;
      }
    }

    if (needle.length() == 1) {
      LinkedList<Integer> charPositions = needleCharacterMap.get(0);
      return charPositions.isEmpty() ? -1 : charPositions.getFirst();
    }

    return checkPreviousCharacters(needleCharacterMap, needle);
  }

  private int checkPreviousCharacters(List<LinkedList<Integer>> needleCharacterMap, String needle) {
    int m = needle.length();
    LinkedList<Integer> lastCharPositions = needleCharacterMap.get(m - 1);

    if (lastCharPositions.isEmpty()) {
      return -1;
    }

    while (!lastCharPositions.isEmpty()) {
      // check all previous characters positions
      for (int i = m - 2; i >= 0; i--) {
        LinkedList<Integer> currentCharPositions = needleCharacterMap.get(i);
        if (currentCharPositions.isEmpty()) {
          return -1;
        }

        boolean lastCharacterPositionWasCleaned = false;
        while (!lastCharPositions.isEmpty()
            && currentCharPositions.getFirst() > lastCharPositions.getFirst()) {
          lastCharacterPositionWasCleaned = true;
          lastCharPositions.removeFirst();
        }

        if (lastCharPositions.isEmpty()) {
          return -1;
        }

        while (!currentCharPositions.isEmpty()
            && currentCharPositions.getFirst() < lastCharPositions.getFirst() - 1) {
          lastCharacterPositionWasCleaned = true;
          currentCharPositions.removeFirst();
        }

        if (currentCharPositions.isEmpty()) {
          return -1;
        }

        if (lastCharacterPositionWasCleaned) {
          break;
        }

        if (currentCharPositions.getFirst() == lastCharPositions.getFirst() - 1) {
          if (i == 0) {
            return currentCharPositions.getFirst();
          }

          lastCharPositions = currentCharPositions;
        } else {
          lastCharPositions = needleCharacterMap.get(m - 1);
          lastCharPositions.removeFirst();
          break;
        }
      }


    }

    return -1;
  }
}

class Main {

  static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    Solution solution = new Solution();
    String haystack = "bbbbababbbaabbba";
    String needle = "abb";
    int expectedIndex = haystack.indexOf(needle);
    int index = solution.strStr(haystack, needle);
    LOGGER.info("expectedIndex: {}. Actual index: {}.", expectedIndex, index);
  }
}