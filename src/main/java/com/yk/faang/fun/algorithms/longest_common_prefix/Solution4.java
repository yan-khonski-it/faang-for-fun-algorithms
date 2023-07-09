package com.yk.faang.fun.algorithms.longest_common_prefix;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.fun.utils.TestCaseUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 * <p>
 * 14. Longest Common Prefix
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings. If there
 * is no common prefix, return an empty string "".
 * <p>
 * We will build a Trie from all strings expect the shortest and then find the common prefix.
 */
public class Solution4 {

  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    Trie trie = new Trie();
    for (int i = 0; i < strs.length; i++) {
      if (strs[i].isEmpty()) {
        return "";
      }

      trie.insert(strs[i]);
    }

    return trie.longestPrefix();
  }
}

class Node {

  boolean isEndOfWord;
  char value;
  Map<Character, Node> children = new HashMap<>();

  public Node() {
    this.value = ' ';
  }

  public Node(char value) {
    this.value = value;
  }
}

class Trie {

  Node root = new Node();

  public void insert(String word) {
    Node current = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      Node node = current.children.computeIfAbsent(ch, k -> new Node(ch));
      current = node;
    }

    current.isEndOfWord = true;
  }

  public String longestPrefix() {
    StringBuilder prefix = new StringBuilder();
    Node current = root;
    while (current != null && !current.isEndOfWord && current.children.size() == 1) {
      Map.Entry<Character, Node> entry = current.children.entrySet().iterator().next();
      prefix.append(entry.getKey());
      current = entry.getValue();
    }

    return prefix.toString();
  }

  public boolean contains(String word) {
    Node current = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      Node node = current.children.get(ch);
      if (node == null) {
        return false;
      }

      current = node;
    }

    return current.isEndOfWord;
  }
}


@SuppressWarnings("DuplicatedCode")
class Main4 {

  public static void main(String[] args) {
    Solution4 solution = new Solution4();
    String prefix1 = solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
    assertThat(prefix1).isEqualTo("fl");

    String[] testCase0Strings = TestCaseUtils.readLines("test_cases/longest_common_prefix/test01.txt").toArray(new String[0]);
    String prefix2 = runTestCaseWithTimerNs(() -> solution.longestCommonPrefix(testCase0Strings));
    assertThat(prefix2).isEqualTo("fl");
  }
}