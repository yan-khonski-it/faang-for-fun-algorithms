package com.yk.faang.learning.dijkstra;

import java.util.Arrays;

public interface Dijkstra {

  // To prevent overflow when addition
  int INF = Integer.MAX_VALUE / 4;

  static int[] createEmptyDistances(int size) {
    int[] res = new int[size];
    Arrays.fill(res, INF);
    return res;
  }

  int[] searchDijkstra(int start);
}
