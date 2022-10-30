package dsa.advance.day47.binarySearch3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AggressiveCows {
    @Test
    public void test1(){
        int[] stalls = {2,6,11,14,19,25,30,39,43};
        int cows = 3;
        Assertions.assertEquals(18, minimumLargestDistance(stalls,cows));
    }
    @Test
    public void test2(){
        int[] stalls = {2,6,11,14,19,25,30,39,43};
        int cows = 4;
        Assertions.assertEquals(12, minimumLargestDistance(stalls,cows));
    }
    @Test
    public void test3(){
        int[] stalls = {5, 17, 100, 11};
        int cows = 2;
        Assertions.assertEquals(95, minimumLargestDistance(stalls,cows));
    }
    @Test
    public void test4(){
        int[] stalls = {82, 61, 38, 88, 12, 7, 6, 12, 48, 8, 31, 90, 35, 5, 88, 2, 66, 19, 5, 96, 84, 95 };
        int cows = 8;
        Arrays.sort(stalls);
        Assertions.assertEquals(7, minimumLargestDistance(stalls,cows));
    }

    public int minimumLargestDistance(int[] stalls, int cows){
        Arrays.sort(stalls);
        int minLargeDist = 0;
        int low = minAdjacentDist(stalls);
        int high = stalls[stalls.length-1] - stalls[0];
        while (low <= high){
            int mid = (low + high)/2;
            boolean isPossible = checkDistance(stalls, cows, mid);
            if(isPossible){
                minLargeDist = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return minLargeDist;
    }

    // Try putting cows at distance >= mid
    private boolean checkDistance(int[] stalls, int cows, int distance) {
        int previousStall = stalls[0];
        cows--;
        int i = 1;
        while (i < stalls.length && cows > 0){
            int currentDist = stalls[i]-previousStall;
            if(currentDist >= distance){
                cows--;
                previousStall = stalls[i];
            }
            i++;
        }
        return cows == 0;
    }

    private int minAdjacentDist(int[] stalls) {
        int minAdjacentDist = Integer.MAX_VALUE;
        for(int i = 1; i<stalls.length; i++){
            minAdjacentDist = Integer.min(minAdjacentDist, stalls[i]-stalls[i-1]);
        }
        return minAdjacentDist;
    }

}
