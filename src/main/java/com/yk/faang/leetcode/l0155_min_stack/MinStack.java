package com.yk.faang.leetcode.l0155_min_stack;

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
 * MinStack() initializes the stack object. void push(int val) pushes the element val onto the stack. void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack. int getMin() retrieves the minimum element in the stack.
 */
class MinStack implements IMinStack {

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

  @Override
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

  @Override
  public void pop() {
    stack.removeLast();
  }

  @Override
  public int top() {
    return stack.getLast().value;
  }

  @Override
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
