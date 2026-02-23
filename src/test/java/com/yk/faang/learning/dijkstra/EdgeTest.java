package com.yk.faang.learning.dijkstra;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EdgeTest {

    @Test
    void testCreation() {
        Edge edge = new Edge(1, 10);
        assertThat(edge.toVertex).isEqualTo(1);
        assertThat(edge.distance).isEqualTo(10);
    }
}
