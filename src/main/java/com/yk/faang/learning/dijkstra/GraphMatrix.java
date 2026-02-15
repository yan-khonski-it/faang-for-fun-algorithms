package com.yk.faang.learning.dijkstra;

/**
 * Directed graph is presented as a matrix. 0 means no edge, other values are weights.
 */
public class GraphMatrix implements Dijkstra {

  private final int[][] matrix;
  private final int size;

  public GraphMatrix(int[][] matrix) {
    this.matrix = matrix;
    this.size = matrix.length;
  }

  @Override
  public int[] searchDijkstra(int start) {
    int[] distances = Dijkstra.createEmptyDistances(size);
    distances[start] = 0;
    boolean[] used = new boolean[size];

    // O(n^2) Dijkstra (no priority queue)
    for (int iteration = 0; iteration < size; iteration++) {
      // pick the unused vertex with smallest dist
      int vertex = getNearestUnusedNeighbor(distances, used);
      if (vertex == -1) {
        break; // remaining nodes are unreachable
      }

      used[vertex] = true;

      // relax all outgoing edges from v
      for (int neighbor = 0; neighbor < size; neighbor++) {
        int weight = matrix[vertex][neighbor];
        if (weight == 0) {
          continue; // no edge (use w == 0 if you chose 0 as "no edge")
        }

        int newDistance = distances[vertex] + weight;
        if (newDistance < distances[neighbor]) {
          distances[neighbor] = newDistance;
        }
      }
    }

    return distances;
  }

  private int getNearestUnusedNeighbor(int[] distances, boolean[] used) {
    int vertex = -1;
    int minDistance = INF;

    for (int v = 0; v < size; v++) {
      if (!used[v] && distances[v] < minDistance) {
        minDistance = distances[v];
        vertex = v;
      }
    }

    return vertex;
  }
}
