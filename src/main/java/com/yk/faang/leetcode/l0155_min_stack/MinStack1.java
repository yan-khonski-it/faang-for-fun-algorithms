package com.yk.faang.leetcode.l0155_min_stack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/min-stack/
 * <p>
 * 155. Min Stack
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object. void push(int val) pushes the element val onto the stack. void pop() removes
 * the element on the top of the stack. int top() gets the top element of the stack. int getMin() retrieves the minimum
 * element in the stack.
 */
class MinStack1 {

  private final Deque<Integer> stack;
  private final Deque<Pair> minValuesOcurrences;

  public MinStack1() {
    minValuesOcurrences = new LinkedList<>();
    stack = new LinkedList<>();
  }

  public void push(int val) {
    if (stack.isEmpty()) {
      stack.addLast(val);
      minValuesOcurrences.addLast(Pair.of(val));
      return;
    }

    stack.addLast(val);
    Pair currentMinPair = minValuesOcurrences.getLast();
    if (currentMinPair.value == val) {
      currentMinPair.counter++;
    } else if (currentMinPair.value > val) {
      minValuesOcurrences.addLast(Pair.of(val));
    }
  }

  public void pop() {
    int lastElement = stack.removeLast();
    Pair currentMinPair = minValuesOcurrences.getLast();
    if (currentMinPair.value == lastElement) {
      if (currentMinPair.counter > 1) {
        currentMinPair.counter--;
      } else {
        minValuesOcurrences.removeLast();
      }
    }
  }

  public int top() {
    return stack.getLast();
  }

  public int getMin() {
    return minValuesOcurrences.getLast().value;
  }

  static class Pair {

    int value;
    int counter;

    public Pair(int value) {
      this.value = value;
      this.counter = 1;
    }

    public static Pair of(int value) {
      return new Pair(value);
    }
  }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack(); obj.push(val);
 * obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
 */
@SuppressWarnings("DuplicatedCode")
class Main1 {

  static void main() {
    MinStack1 minStack1 = new MinStack1();
    minStack1.push(5);
    minStack1.push(10);
    assertThat(minStack1.top()).isEqualTo(10);
    assertThat(minStack1.getMin()).isEqualTo(5);
    minStack1.push(1);
    assertThat(minStack1.top()).isEqualTo(1);
    assertThat(minStack1.getMin()).isEqualTo(1);
    minStack1.pop();
    assertThat(minStack1.top()).isEqualTo(10);
    assertThat(minStack1.getMin()).isEqualTo(5);
    minStack1.pop();
    assertThat(minStack1.top()).isEqualTo(5);
    assertThat(minStack1.getMin()).isEqualTo(5);
  }
}