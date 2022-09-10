package dsa.advance.day34.array3;

/*
You are given an array of N integers, A1, A2, .... AN.
Return the maximum value of f(i, j) for all 1 ≤ i, j ≤ N. f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
A = [1, 3, -1]
f(1, 1) = f(2, 2) = f(3, 3) = 0
f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5
So, we return 5.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximumAbsoluteDifference {

    @Test
    public void test1(){
        int[] array = {1,3,-1};
        Assertions.assertEquals(5, maxAbsoluteDifference_bruteforce(array));
        Assertions.assertEquals(5, maxAbsoluteDifference_optimised(array));
    }

    int maxAbsoluteDifference_bruteforce(int[] array){
        int n = array.length;
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n;j++){
                int fij = Math.abs(array[i]-array[j]) + Math.abs(i-j);
                max = Math.max(max,fij);
            }
        }
        return max;
    }
    int maxAbsoluteDifference_optimised(int[] array){
        int n = array.length;
        int x_max = Integer.MIN_VALUE;
        int x_min = Integer.MAX_VALUE;
        int y_max = Integer.MIN_VALUE;
        int y_min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int x = array[i] - i;
            int y = array[i] + i;
            x_max = Math.max(x_max, x);
            x_min = Math.min(x_min, x);
            y_max = Math.max(y_max, y);
            y_min = Math.min(y_min, y);
        }
        return Math.max(x_max - x_min, y_max - y_min);
    }
}
