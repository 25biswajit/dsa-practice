package dsa.basic.day16.interviewprob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// Given Binary Array ( contains 0 & 1 )
// We have to replace only one zero in such a way Max Consecutive 1's length will be max
// return max Consecutive 1's length
public class MaxOneReplace {
    @Test
    public void test1(){
        int[] array = {1,1,1,0,1,1,0,1,1,1,1, 0,0,1,1,0,1,1};
        Assertions.assertEquals(7, replace(array));
    }
    @Test
    public void test2(){
        int[] array = {1,1,1,1,1,1,1,1,1,1,1, 0,0,1,1,0,1,1};
        Assertions.assertEquals(12, replace(array));
    }
    @Test
    public void test3(){
        int[] array = {0,1,1,0,1,1,0,1,1};
        Assertions.assertEquals(5, replace(array));
    }
    @Test
    public void test4(){
        int[] array = {0,0,0,0,0,0,0};
        Assertions.assertEquals(1, replace(array));
    }
    @Test
    public void test5(){
        int[] array = {1,1,1,1,1,1,1,1,1};
        Assertions.assertEquals(9, replace(array));
    }

    public int replace(int a[]){
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int countOne = 0;

        for(int i = 0; i < a.length; i++){
            if(a[i]==0){
                map.put(i, countOne);
                countOne = 0;
            }else {
                countOne++;
            }
        }
        countOne = 0;
        for(int i = a.length-1; i >=0; i--){
            if(a[i]==0){
                int result = countOne + map.getOrDefault(i,0);
                map.put(i, result);
                maxLength = Math.max(maxLength, result);
                countOne = 0;
            }else {
                countOne++;
            }
        }

        if(map.size()==0){
            // Edge Case in case No Zero present
            maxLength = a.length;
        }else {
            maxLength = maxLength + 1;
        }
        return maxLength;
    }

}
