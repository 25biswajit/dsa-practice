package dsa.advance.day32.array1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/*

Given array of non-negative integers representing an elevation/height map where the width of each bar is 1,
compute how much water it is able to trap after raining.
*/
public class RainWaterTrapped {

    @Test
    public void test(){
        List<Integer> list = Arrays.asList(1,2,0,3,5,2,1,7,3);
        Assertions.assertEquals(9 , trap(list));
    }

    public int trap(List<Integer> list){
        int n = list.size();
        int[] prefixMax = derivePrefixMax(list);
        int[] suffixMax = deriveSuffixMax(list);
        int answer = 0;
        for(int i = 1;i<=n-2;i++){
            int maxLeft = prefixMax[i-1];
            int maxRight = suffixMax[i+1];
            int waterLabel = Integer.min(maxLeft,maxRight);
            answer += Integer.max(0, waterLabel-list.get(i));
        }
        return answer;
    }

    private int[] deriveSuffixMax(List<Integer> list) {
        int n = list.size();
        int[] maxArray = new int[n];
        maxArray[n-1] = list.get(n-1);
        for(int i=n-2;i>=0;i--){
            maxArray[i] = Integer.max(list.get(i),maxArray[i+1]);
        }
        return maxArray;
    }

    private int[] derivePrefixMax(List<Integer> list) {
        int n = list.size();
        int[] maxArray = new int[n];
        maxArray[0] = list.get(0);
        for(int i=1;i<n;i++){
            maxArray[i] = Integer.max(list.get(i),maxArray[i-1]);
        }
        return maxArray;
    }
}
