package dsa.advance.day49.binarySearch4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given a matrix of integers A of size N x M in which each row is sorted.
Find and return the overall median of matrix A.
NOTE: No extra memory is allowed.
NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
1 <= N, M <= 10^5
1 <= N*M <= 10^6
1 <= A[i] <= 10^9
N*M is odd

A = [   [1, 3, 5],
        [2, 6, 9],
        [3, 6, 9]   ]
A = [1, 2, 3, 3, 5, 6, 6, 9, 9]
Median is 5. So, we return 5.
*/

public class FindMedianMatrix {
    @Test
    public void test1(){
        int[][] matrix = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        Assertions.assertEquals(5, findMedianMatrix(matrix));
    }

    // TC : N + ( Log X * [ COL * Log ROW ] ) , here X = max - min + 1
    public int findMedianMatrix(int[][] matrix){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int length = 0;
        int col = matrix[0].length;
        for(int i = 0; i < matrix.length; i++){
            min = Integer.min(min, matrix[i][0]);
            max = Integer.max(max, matrix[i][col-1]);
            length += col;
        }
        int k = length / 2;
        int ans = -1;
        int low = min, high = max;
        while (low <= high){
            int mid =  ( low + high )/2;
            int count = countElemLessThan(matrix,mid);
            if(count > k) { high = mid - 1;}
            else {ans = mid;low = mid + 1;}
        }
        return ans;
    }

    // TC: Col * log Row
    private int countElemLessThan(int[][] matrix,int mid) {
        int count = 0;
        for(int i=0; i< matrix.length; i++){
            count += countElemLessThan(matrix[i], mid);
        }
        return count;
    }

    private int countElemLessThan(int[] array,int key) {
        int count = -1;
        int low = 0, high = array.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(array[mid] < key){
                count = mid;
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return count + 1;
    }
}
