package com.yk.faang.leetcode.l0155_min_stack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MinStackTest {

    static Stream<Supplier<IMinStack>> getStacks() {
        return Stream.of(
            MinStack::new,
            MinStack1::new
        );
    }

    @ParameterizedTest
    @MethodSource("getStacks")
    void testMinStack(Supplier<IMinStack> stackSupplier) {
        IMinStack stack = stackSupplier.get();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        assertThat(stack.getMin()).isEqualTo(-3);

        stack.pop();

        assertThat(stack.top()).isEqualTo(0);
        assertThat(stack.getMin()).isEqualTo(-2);
    }
}
