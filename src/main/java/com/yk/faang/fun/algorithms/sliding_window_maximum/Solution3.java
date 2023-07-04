package com.yk.faang.fun.algorithms.sliding_window_maximum;


import com.yk.faang.fun.utils.ArrayAndK;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimer;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArray;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArrayAndK;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * 239. Sliding Window Maximum
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of
 * the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position.
 * <p>
 * Return the max sliding window.
 */
public class Solution3 {
    // Sparse table based solution
    // O(n*log(n))
    int query(int l, int r, int [][] lookup){

        int len = r - l + 1;

        int k = 0;
        while (1 << (k + 1) < len){
            k++;
        }

        return Math.max(lookup[l][k], lookup[r - (1 << k) + 1][k]);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        final int len = nums.length;
        final int logN = (int) Math.ceil(Math.log(len) / Math.log(2));

        int [][] lookup = new int[len][logN];
        int [] res = new int[len - k + 1];


        for (int i = 0; i < len; i++){
            lookup[i][0] = nums[i];
        }

        for(int step = 1; step < logN; step++){
            int currPower2 = 1 << step;
            int prevPower2 = 1 << (step - 1);

            for(int i = 0; i + currPower2 - 1 < len; i++){
                lookup[i][step] = Math.max(lookup[i][step - 1], lookup[i + prevPower2][step - 1]);
            }
        }

        for(int i = 0; i + k <= len; i++){
            res[i] = query(i, i + (k - 1), lookup);
        }
        return res ;
    }
}


@SuppressWarnings("DuplicatedCode")
class Main3 {

    public static void main(String[] args) {

        Solution3 solution3 = new Solution3();
        int[] array1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        int[] res1 = solution3.maxSlidingWindow(array1, k1);
        assertThat(res1).isEqualTo(new int[]{3, 3, 5, 5, 6, 7});

        ArrayAndK arrayAndK2 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test52.txt");
        int[] expectedArray2 = readInputAsArray("test_cases/sliding_window_maximum/test52_expected.txt");
        int[] array2 = arrayAndK2.getArray();
        int k2 = arrayAndK2.getK();

        int[] res2 = runTestCaseWithTimer(() -> { // 13 ms
            return solution3.maxSlidingWindow(array2, k2);
        });
        assertThat(res2).isEqualTo(expectedArray2);

        ArrayAndK arrayAndK3 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test49.txt");
        int[] expectedArray3 = readInputAsArray("test_cases/sliding_window_maximum/test49_expected.txt");
        int[] array3 = arrayAndK3.getArray();
        int k3 = arrayAndK3.getK();
        int[] res3 = runTestCaseWithTimer(() -> { // 19 ms
            return solution3.maxSlidingWindow(array3, k3);
        });

        assertThat(res3).isEqualTo(expectedArray3);
    }
}