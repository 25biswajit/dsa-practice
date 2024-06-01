package dsa.basic.day15.interviewprob;

import org.junit.jupiter.api.Test;

public class MinSwap {
    @Test
    public void test(){
        int[] integers = new int[]{
                52, 7, 93, 47, 68, 26, 51, 44, 5, 41, 88, 19, 78, 38, 17,
                13, 24, 74, 92, 5, 84, 27, 48, 49, 37, 59, 3, 56, 79, 26,
                55, 60, 16, 83, 63, 40, 55, 9, 96, 29, 7, 22, 27, 74, 78,
                38, 11, 65, 29, 52, 36, 21, 94, 46, 52, 47, 87, 33, 87, 70};
        System.out.println( solve(integers, 19) );
    }

    // 19.01.2024
    public int solve(int[] A, int B) {
        int n = A.length;
        int sw = 0;

        for(int i = 0; i < n; i++){
            if(A[i] <= B){
                sw++;
            }
        }
        int countSwap = 0;
        int p2 = 0;
        for(; p2 < sw; p2++){
            if(A[p2] > B){
                countSwap++;
            }
        }
        int minSwap = countSwap;
        int p1 = 01;
        while( p2 < n){
            if(A[p1-1] > B){
                countSwap--;
            }
            if(A[p2] > B){
                countSwap++;
            }
            minSwap = Math.min(minSwap ,countSwap);
            p1++;
            p2++;
        }
        return minSwap;
    }

    /*
    First, count the number of elements <= B. Let the count comes out to be X.

    Create a window of first X elements. To find the number of swaps to bring all elements <= B together in the first window,
    you just need to find count of elements > B in first window.
    So, count of swaps required in 1 window = count of elements greater than B in that window.

    For every window, find the count of elements greater than B, using sliding window technique.

    Time Complexity: O(N)
    Space Complexity: O(1)
     */
}

