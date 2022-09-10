package dsa.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    @Test
    public void test(){
        int[] input = {2,7,11,15};
        int target = 9;
        int[] result = twoSum(input, target);
        int[] expectedResult = {0,1};
        Assertions.assertArrayEquals(expectedResult, result);
    }

    // Approach 1 - TC : O(N), SC : O(N)
    public int[] twoSum1(int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] result = new int[2];

        for(int i=0; i<numbers.length ; i++){
            int a = target - numbers[i];
            if(map.containsKey(a)){
                result[0] = map.get(a);
                result[1] = i;
                return result;
            }else{
                map.put(numbers[i], i);
            }
        }
        return result;
    }

    // Approach 2 - TC : O(N), SC : O(N)
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(target - numbers[0], 0);

        for(int i=1; i<numbers.length; i++){
            if(map.get(numbers[i])!=null){
                int[] arr = { map.get(numbers[i]), i };
                return arr;
            }else{
                map.put(target - numbers[i], i);
            }
        }
        return null;
    }
}
