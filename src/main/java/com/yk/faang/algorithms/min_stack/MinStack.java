package com.yk.faang.algorithms.min_stack;

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
class MinStack {

  // Simple implementation that keeps pair of inserted elements and current min value.
  // if newly inserted element has a value smaller than the current value,
  // all the following inserted elements will be paired with the next min value.

  // Time - as original stack - O(n) n - number of stack operations
  // in other words O(1) for all the required operations

  // Space O(n) - where n is maximum number of inserted elements
  private final Deque<Pair> stack;

  public MinStack() {
    stack = new LinkedList<>();
  }

  public void push(int val) {
    if (stack.isEmpty()) {
      stack.addLast(Pair.of(val, val));
      return;
    }

    int currentMin = stack.getLast().minValue;
    if (currentMin > val) {
      currentMin = val;
    }

    stack.addLast(Pair.of(val, currentMin));
  }

  public void pop() {
    stack.removeLast();
  }

  public int top() {
    return stack.getLast().value;
  }

  public int getMin() {
    return stack.getLast().minValue;
  }

  static class Pair {

    int value;
    int minValue;

    public Pair(int value, int minValue) {
      this.value = value;
      this.minValue = minValue;
    }

    public static Pair of(int value, int minValue) {
      return new Pair(value, minValue);
    }
  }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack(); obj.push(val);
 * obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
 */
@SuppressWarnings("DuplicatedCode")
class Main {

  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    minStack.push(5);
    minStack.push(10);
    assertThat(minStack.top()).isEqualTo(10);
    assertThat(minStack.getMin()).isEqualTo(5);
    minStack.push(1);
    assertThat(minStack.top()).isEqualTo(1);
    assertThat(minStack.getMin()).isEqualTo(1);
    minStack.pop();
    assertThat(minStack.top()).isEqualTo(10);
    assertThat(minStack.getMin()).isEqualTo(5);
    minStack.pop();
    assertThat(minStack.top()).isEqualTo(5);
    assertThat(minStack.getMin()).isEqualTo(5);
  }
}