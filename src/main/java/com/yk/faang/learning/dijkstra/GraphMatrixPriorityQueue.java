package com.yk.faang.learning.dijkstra;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Directed graph is presented as a matrix. 0 means no edge, other values are weights.
 */
public class GraphMatrixPriorityQueue implements Dijkstra {

  private final int[][] matrix;
  private final int size;

  public GraphMatrixPriorityQueue(int[][] matrix) {
    this.matrix = matrix;
    this.size = matrix.length;
  }

  public int getSize() {
    return size;
  }

  @Override
  public int[] searchDijkstra(int start) {
    int[] distances = Dijkstra.createEmptyDistances(size);
    distances[start] = 0;
    PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0])); // (dist, node)
    priorityQueue.add(new int[]{0, start});

    while (!priorityQueue.isEmpty()) {
      int[] top = priorityQueue.poll();

      // pick the unused vertex with smallest dist
      int vertex = top[1];
      int distance = top[0];

      if (distance > distances[vertex]) {
        continue; // stale entry
      }

      // relax all outgoing edges from v
      for (int neighbor = 0; neighbor < size; neighbor++) {
        int weight = matrix[vertex][neighbor];
        if (weight == 0) {
          continue; // no edge (use w == 0 if you chose 0 as "no edge")
        }

        int newDistance = distance + weight;
        if (newDistance < distances[neighbor]) {
          distances[neighbor] = newDistance;
          priorityQueue.add(new int[]{newDistance, neighbor});
        }
      }
    }

    return distances;
  }
}
