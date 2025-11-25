package com.yk.faang.leetcode.l0054_spiral_matrix;

enum Direction {

  RIGHT,
  DOWN,
  LEFT,
  UP;

  private static final Direction[] vals = values();

  public Direction next() {
    return vals[(ordinal() + 1) % vals.length];
  }
}
