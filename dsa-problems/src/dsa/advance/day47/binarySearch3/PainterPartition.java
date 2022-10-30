package dsa.advance.day47.binarySearch3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PainterPartition {
    @Test
    public void test1(){
        int[] boards = {3,5,1,2,3,3,2,5,4,3,4};
        int painters = 8;
        int timeUnit = 3;
        Assertions.assertEquals(18, minTimeRequired(boards,painters,timeUnit));
    }
    @Test
    public void test2(){
        int[] boards = {1000000, 1000000 };
        int painters = 1;
        int timeUnit = 1000000;
        Assertions.assertEquals(9400003, minTimeRequired(boards,painters,timeUnit));
    }

    public int minTimeRequired (int[]  boards, int painters, int timeUnit){
        int maxElement = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < boards.length; i++){
            maxElement = Integer.max(maxElement, boards[i]);
            sum += boards[i];
        }
        int low = maxElement;
        int high = sum;
        int minBoardLength = 0;
        while (low <= high){
            int mid = ( low + high) / 2;
            int paintersRequired = paintersReqForGivenLength(boards, mid);
            if(paintersRequired > painters){
                low = mid + 1;
            }else {
                minBoardLength = mid;
                high = mid - 1;
            }
        }
        long result = (1L * minBoardLength * timeUnit) % 10000003;
        return (int)result;
    }

    private int paintersReqForGivenLength(int[] boards, int mid) {
        int sum = boards[0];
        int paintersCount = 1;
        for(int i = 1; i < boards.length; i++){
            if(sum + boards[i] > mid){
                paintersCount++;
                sum = boards[i];
            }
            else {
                sum += boards[i];
            }
        }
        return paintersCount;
    }
}
