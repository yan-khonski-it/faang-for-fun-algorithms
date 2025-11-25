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
class MinStack1 implements IMinStack {

  private final Deque<Integer> stack;
  private final Deque<Pair> minValuesOccurrences;

  public MinStack1() {
    minValuesOccurrences = new LinkedList<>();
    stack = new LinkedList<>();
  }

  @Override
  public void push(int val) {
    if (stack.isEmpty()) {
      stack.addLast(val);
      minValuesOccurrences.addLast(Pair.of(val));
      return;
    }

    stack.addLast(val);
    Pair currentMinPair = minValuesOccurrences.getLast();
    if (currentMinPair.value == val) {
      currentMinPair.counter++;
    } else if (currentMinPair.value > val) {
      minValuesOccurrences.addLast(Pair.of(val));
    }
  }

  @Override
  public void pop() {
    int lastElement = stack.removeLast();
    Pair currentMinPair = minValuesOccurrences.getLast();
    if (currentMinPair.value == lastElement) {
      if (currentMinPair.counter > 1) {
        currentMinPair.counter--;
      } else {
        minValuesOccurrences.removeLast();
      }
    }
  }

  @Override
  public int top() {
    return stack.getLast();
  }

  @Override
  public int getMin() {
    return minValuesOccurrences.getLast().value;
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
