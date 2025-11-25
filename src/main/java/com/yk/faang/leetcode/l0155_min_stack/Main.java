package com.yk.faang.leetcode.l0155_min_stack;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    IMinStack minStack = new MinStack();
    testMinStack(minStack);

    IMinStack minStack1 = new MinStack1();
    testMinStack(minStack1);
  }

  /**
   * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack(); obj.push(val);
   * obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
   */
  private static void testMinStack(IMinStack minStack) {
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
