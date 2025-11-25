package com.yk.faang.leetcode.l0074_search_in_matrix;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * 74. Search a 2D Matrix
 * <p>
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right. The first integer of each row is greater than the last integer of the previous row.
 */
class Solution {

  // Note, in this approach, I build an imaginary array of m * n and just perform a binary search.
  // I have to take care of index mapping.
  // O (log (m * m)) = O(log(n) + log(m))
  // However, this approach has a drawback. What if matrix rows were written on a hard drive, while only a single row could fix RAM.
  // Let's imagine, that all first elements of each row could be kept in RAM - similar to indexes.
  // In this case, the complexity of the algorithm would be the same: finding the row - O(log(m)) and finding the column - O(log(m)).

  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;

    if (m == 1 && n == 1) {
      return matrix[0][0] == target;
    }

    int start = 0;
    int end = m * n - 1;

    while (start <= end) {
      int median = (start + end) / 2;
      int row = median / n;
      int column = median % n;

      if (matrix[row][column] == target) {
        return true;
      } else if (matrix[row][column] < target) {
        start = median + 1;
      } else {
        end = median - 1;
      }
    }

    return false;
  }
}

