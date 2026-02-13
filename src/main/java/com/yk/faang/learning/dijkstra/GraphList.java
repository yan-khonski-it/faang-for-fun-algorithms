package com.yk.faang.learning.dijkstra;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Directed graph is presented as a adjacency list.
 */
public class GraphList implements Dijkstra {

  private final List<List<Edge>> edges;
  private final int size;

  public GraphList(List<List<Edge>> edges) {
    this.edges = edges;
    this.size = edges.size();
  }

  @Override
  public int[] searchDijkstra(int start) {
    int[] distances = Dijkstra.createEmptyDistances(size);
    distances[start] = 0;

    PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // (dist, node)
    priorityQueue.add(new int[]{0, start});

    while (!priorityQueue.isEmpty()) {
      int[] top = priorityQueue.poll();
      int distance = top[0];
      int vertex = top[1];

      if (distance > distances[vertex]) {
        continue; // stale entry
      }

      List<Edge> neighbors = edges.get(vertex);
      for (Edge neighbor : neighbors) {
        int newDistance = distance + neighbor.distance;
        if (newDistance < distances[neighbor.toVertex]) {
          distances[neighbor.toVertex] = newDistance;
          priorityQueue.add(new int[]{newDistance, neighbor.toVertex});
        }
      }
    }

    return distances;
  }
}
