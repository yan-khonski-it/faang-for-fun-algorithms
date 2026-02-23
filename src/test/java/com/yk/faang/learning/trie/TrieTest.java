package com.yk.faang.learning.trie;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TrieTest {

  static List<Trie> getTries() {
    return List.of(new MyTrie1(), new MyTrie2());
  }

  static Stream<Arguments> containsCases() {
    // Expectations after inserting INSERTED
    Map<String, Boolean> cases = Map.ofEntries(
        Map.entry("app", true),
        Map.entry("apple", true),
        Map.entry("apply", false),
        Map.entry("appl", true),    // prefix only
        Map.entry("apps", false),
        Map.entry("bat", true),
        Map.entry("bath", true),
        Map.entry("ba", false),      // prefix only
        Map.entry("bad", false),
        Map.entry("bake", false),
        Map.entry("z", true),
        Map.entry("zz", false)
    );

    return cases.entrySet().stream()
        .map(e -> Arguments.of(e.getKey(), e.getValue()));
  }

  static Stream<Arguments> startsWithCases() {
    Map<String, Boolean> cases = Map.ofEntries(
        Map.entry("a", true),
        Map.entry("ap", true),
        Map.entry("app", true),
        Map.entry("appl", true),
        Map.entry("apple", true),
        Map.entry("applex", false),

        Map.entry("b", true),
        Map.entry("ba", true),
        Map.entry("bat", true),
        Map.entry("bath", true),
        Map.entry("bathe", false),

        Map.entry("z", true),
        Map.entry("zz", false)
    );

    return cases.entrySet().stream()
        .map(e -> Arguments.of(e.getKey(), e.getValue()));
  }

  @ParameterizedTest(name = "contains(\"{0}\") == {1}")
  @MethodSource("containsCases")
  void testContains(String word, boolean expected) {
    for (Trie trie : getTries()) {
      trie.add("apple");
      trie.add("appl");
      trie.add("app");
      trie.add("b");
      trie.add("bat");
      trie.add("bath");
      trie.add("z");

      assertThat(trie.contains(word)).isEqualTo(expected);
    }
  }

  @ParameterizedTest(name = "startsWith(\"{0}\") == {1}")
  @MethodSource("startsWithCases")
  void testStartsWith(String prefix, boolean expected) {
    for (Trie trie : getTries()) {
      trie.add("apple");
      trie.add("bath");
      trie.add("z");

      assertThat(trie.startsWith(prefix)).isEqualTo(expected);
    }
  }

  @Test
  void testAddingIsIdempotent() {
    for (Trie trie : getTries()) {
      trie.add("apple");
      trie.add("apple");
      assertThat(trie.contains("apple")).isTrue();
      assertThat(trie.startsWith("appl")).isTrue();
    }
  }

  @MethodSource("getTries")
  @ParameterizedTest
  void prefix_is_not_a_word_unless_added(Trie trie) {
    assertThat(trie.contains("appl")).isFalse();
    assertThat(trie.startsWith("appl")).isFalse();

    trie.add("apple");
    assertThat(trie.startsWith("appl")).isTrue();
  }
}