package com.yk.faang.learning.queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    MyQueue myArrayQueue = new MyArrayQueue(5);
    testQueue(myArrayQueue);

    MyQueue myLinkedListQueue = new MyLinkedListQueue();
    testQueue(myLinkedListQueue);
  }

  private static void testQueue(MyQueue queue) {
    queue.enqueue(1);
    assertThat(queue.toArray()).isEqualTo(new int[]{1});
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    assertThat(queue.toArray()).isEqualTo(new int[]{1, 2, 3, 4});
    assertThat(queue.peek()).isEqualTo(1);

    queue.enqueue(5);
    assertThat(queue.toArray()).isEqualTo(new int[]{1, 2, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(1);

    int value = queue.dequeue();
    assertThat(queue.toArray()).isEqualTo(new int[]{2, 3, 4, 5});
    assertThat(queue.peek()).isEqualTo(2);
    assertThat(value).isEqualTo(1);

    queue.enqueue(6);
    assertThat(queue.toArray()).isEqualTo(new int[]{2, 3, 4, 5, 6});
    assertThat(queue.peek()).isEqualTo(2);

    value = queue.dequeue();
    assertThat(queue.toArray()).isEqualTo(new int[]{3, 4, 5, 6});
    assertThat(queue.peek()).isEqualTo(3);
    assertThat(value).isEqualTo(2);

    value = queue.dequeue();
    assertThat(value).isEqualTo(3);

    value = queue.dequeue();
    assertThat(queue.toArray()).isEqualTo(new int[]{5, 6});
    assertThat(value).isEqualTo(4);
    assertThat(queue.peek()).isEqualTo(5);

    value = queue.dequeue();
    assertThat(queue.toArray()).isEqualTo(new int[]{6});
    assertThat(queue.peek()).isEqualTo(6);
    assertThat(value).isEqualTo(5);

    value = queue.dequeue();
    assertThat(queue.toArray()).isEqualTo(new int[0]);
    assertThat(value).isEqualTo(6);

    queue.enqueue(7);
    assertThat(queue.toArray()).isEqualTo(new int[]{7});
    assertThat(queue.peek()).isEqualTo(7);
  }
}
