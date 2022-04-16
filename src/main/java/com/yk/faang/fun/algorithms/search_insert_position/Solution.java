package com.yk.faang.fun.algorithms.search_insert_position;

import static org.assertj.core.api.Assertions.assertThat;

class Solution {

  public int searchInsert(int[] nums, int target) {
    int minIndex = 0;
    int maxIndex = nums.length - 1;

    while (minIndex <= maxIndex) {
      int middle = (minIndex + maxIndex) / 2;
      if (nums[middle] == target) {
        return middle;
      } else if (nums[middle] < target) {
        minIndex = middle + 1;
      } else {
        maxIndex = middle - 1;
      }
    }

    return minIndex;
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[] array = {1, 3, 5, 6};
    int target1 = 5;
    int res1 = solution.searchInsert(array, target1);
    assertThat(res1).isEqualTo(2);

    int target2 = 2;
    int res2 = solution.searchInsert(array, target2);
    assertThat(res2).isEqualTo(1);
  }
}
