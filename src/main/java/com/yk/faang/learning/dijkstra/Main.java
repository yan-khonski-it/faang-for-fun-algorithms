package com.yk.faang.learning.dijkstra;

import static com.yk.faang.utils.MatrixUtils.matrixToString;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  static void main() {
    int[][] m = new int[4][4];
    m[0][1] = 1;
    m[0][2] = 4;
    m[1][2] = 2;
    m[1][3] = 6;
    m[2][3] = 3;

    List<List<Edge>> edges = new ArrayList<>();
    edges.add(new ArrayList<>());
    edges.add(new ArrayList<>());
    edges.add(new ArrayList<>());
    edges.add(new ArrayList<>());
    edges.get(0).add(new Edge(1, 1));
    edges.get(0).add(new Edge(2, 4));
    edges.get(1).add(new Edge(2, 2));
    edges.get(1).add(new Edge(3, 6));
    edges.get(2).add(new Edge(3, 3));

    Dijkstra graphMatrix1 = new GraphMatrix(m);
    Dijkstra graphMatrix2 = new GraphMatrixPriorityQueue(m);
    Dijkstra graphList = new GraphList(edges);

    String matrixStr = matrixToString(m);
    LOGGER.info(matrixStr);

    int[] distances1 = graphMatrix1.searchDijkstra(0);
    assertThat(distances1).isEqualTo(new int[]{0, 1, 3, 6});

    int[] distances2 = graphMatrix2.searchDijkstra(0);
    assertThat(distances2).isEqualTo(new int[]{0, 1, 3, 6});

    int[] distances3 = graphList.searchDijkstra(0);
    assertThat(distances3).isEqualTo(new int[]{0, 1, 3, 6});
  }
}
