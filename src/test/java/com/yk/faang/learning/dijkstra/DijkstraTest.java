package com.yk.faang.learning.dijkstra;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class DijkstraTest {

    interface DijkstraFactory {
        Dijkstra create(int[][] matrix);
    }

    static Stream<DijkstraFactory> dijkstraFactories() {
        return Stream.of(
            GraphMatrix::new,
            GraphMatrixPriorityQueue::new,
            (matrix) -> {
                List<List<Edge>> edges = new ArrayList<>();
                for (int i = 0; i < matrix.length; i++) {
                    List<Edge> rowEdges = new ArrayList<>();
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (matrix[i][j] != 0) {
                            rowEdges.add(new Edge(j, matrix[i][j]));
                        }
                    }
                    edges.add(rowEdges);
                }
                return new GraphList(edges);
            }
        );
    }

    @ParameterizedTest
    @MethodSource("dijkstraFactories")
    void testStandardGraph(DijkstraFactory factory) {
        int[][] matrix = {
            {0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        Dijkstra dijkstra = factory.create(matrix);
        int[] distances = dijkstra.searchDijkstra(0);

        // Expected distances from node 0
        int[] expected = {0, 4, 12, 19, 21, 11, 9, 8, 14};
        assertThat(distances).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("dijkstraFactories")
    void testDisconnectedGraph(DijkstraFactory factory) {
        int[][] matrix = {
            {0, 10, 0},
            {10, 0, 0},
            {0, 0, 0}
        };

        Dijkstra dijkstra = factory.create(matrix);
        int[] distances = dijkstra.searchDijkstra(0);

        assertThat(distances[0]).isEqualTo(0);
        assertThat(distances[1]).isEqualTo(10);
        assertThat(distances[2]).isEqualTo(Dijkstra.INF);
    }

    @ParameterizedTest
    @MethodSource("dijkstraFactories")
    void testSingleNodeGraph(DijkstraFactory factory) {
        int[][] matrix = {
            {0}
        };

        Dijkstra dijkstra = factory.create(matrix);
        int[] distances = dijkstra.searchDijkstra(0);

        assertThat(distances).containsExactly(0);
    }
}
