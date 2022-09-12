package dsa.advance.day32.array1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

// Kadane's Algorithm
public class MaxSumSubArray {
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(4,3,-2,6,-14,7,-1,4,5,7,-10,2,9,-10,-5,-9,6,1);
        Assertions.assertEquals(23, maxSubArray(list));
    }
    @Test
    public void test2(){
        List<Integer> list = Arrays.asList(4,3,-2,6,7,-10,-10,4,5,9,-3,4,7,-28,2,9,3,2,11);
        Assertions.assertEquals(27, maxSubArray(list));
    }
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(-2,11,-3,7);
        Assertions.assertEquals(15, maxSubArray(list));
    }

    public int maxSubArray(final List<Integer> list) {
        Integer currentSum = list.get(0);
        Integer maxSum = list.get(0);
        for(int i = 1; i<=list.size()-1;i++){
            Integer num = list.get(i);
            currentSum = Integer.max( num, currentSum + num);
            maxSum = Integer.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
