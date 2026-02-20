package com.yk.faang.learning.queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MyQueueTest {

    static Stream<Supplier<MyQueue>> queueSuppliers() {
        return Stream.of(
            MyLinkedListQueue::new,
            () -> new MyArrayQueue(5)
        );
    }

    @ParameterizedTest
    @MethodSource("queueSuppliers")
    void testEnqueueAndDequeue(Supplier<MyQueue> queueSupplier) {
        MyQueue queue = queueSupplier.get();
        assertThat(queue.isEmpty()).isTrue();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.peek()).isEqualTo(1);

        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(2);
        assertThat(queue.dequeue()).isEqualTo(3);

        assertThat(queue.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("queueSuppliers")
    void testDequeueEmpty(Supplier<MyQueue> queueSupplier) {
        MyQueue queue = queueSupplier.get();
        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @ParameterizedTest
    @MethodSource("queueSuppliers")
    void testToArray(Supplier<MyQueue> queueSupplier) {
        MyQueue queue = queueSupplier.get();
        queue.enqueue(10);
        queue.enqueue(20);
        assertThat(queue.toArray()).containsExactly(10, 20);
    }

    @ParameterizedTest
    @MethodSource("queueSuppliers")
    void testCircularBufferBehaviorForArrayQueue(Supplier<MyQueue> queueSupplier) {
        MyQueue queue = queueSupplier.get();
        // This test is specific for bounded queues or verifying correct behavior over time
        // Fill the queue
        if (queue instanceof MyArrayQueue) {
             // capacity is 5
             queue.enqueue(1);
             queue.enqueue(2);
             queue.enqueue(3);
             queue.enqueue(4);
             queue.enqueue(5);
             assertThat(queue.isFull()).isTrue();
             assertThrows(IllegalStateException.class, () -> queue.enqueue(6));

             queue.dequeue(); // remove 1
             assertThat(queue.isFull()).isFalse();

             queue.enqueue(6); // wrap around
             assertThat(queue.toArray()).containsExactly(2, 3, 4, 5, 6);
        }
    }
}
