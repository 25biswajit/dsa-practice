package dsa.advance.day50.TwoPointer;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClosestPairSortedArrays {
    @Test
    public void test1(){
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 4, 6, 8};
        int C=7;
        int[] expected = {1,6};
        int[] result = closestPairSum(A,B,C);
        Assertions.assertArrayEquals(expected, result);
    }
    @Test
    public void test2(){
        int[] A = {5, 10, 20 };
        int[] B = {1, 2, 30};
        int C=13;
        int[] expected = {10,2};
        int[] result = closestPairSum(A,B,C);
        Assertions.assertArrayEquals(expected, result);
    }

    public int[] closestPairSum(int[] A, int[] B, int C) {
        int[] ans = new int[2];
        int diff = Integer.MAX_VALUE;
        ans[0] = A.length;
        ans[1] = B.length;
        int i = 0;
        int j = B.length-1;
        while (i < A.length && j >= 0){
            int sum = A[i] + B[j];
            int currentDiff = Math.abs(sum - C);
            if(diff >= currentDiff){
                if(diff > currentDiff) {
                    diff = currentDiff;
                    ans[0] = i;
                    ans[1] = j;
                }
                else {
                    if(ans[0] > i){
                        ans[0] = i;
                        ans[1] = j;
                    }
                    else if(ans[0] == i && ans[1] > j){
                        ans[1] = j;
                    }
                }

            }
            if(sum > C){ j--; }
            else if(sum < C){ i++; }
            else if(sum == C){
                i++;
                j--;
            }
        }
        ans[0] = A[ans[0]];
        ans[1] = B[ans[1]];
        return ans;
    }

}
