package dsa.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class NextGreaterElement2 {
    @Test
    public void test1(){
        int[] arr = {1,2,1};
        int[] res = nextGreaterElements(arr);
        int[] expected = {2,-1,2};
        Assertions.assertArrayEquals(expected, res);
    }
    @Test
    public void test2(){
        int[] arr = {100,1,11,1,120,111,123,1,-1,-100};
        int[] res = nextGreaterElements(arr);
        int[] expected = {120,11,120,120,123,123,-1,100,100,100};
        Assertions.assertArrayEquals(expected, res);
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for(int i = 2*n-1 ; i >= 0; i--){
            int e = nums[i % n];
            while(!stack.isEmpty() && stack.peek()<=e){
                stack.pop();
            }
            if(i < n){
                if(stack.isEmpty()){
                    nums[i] = -1;
                }else{
                    nums[i] = stack.peek();
                }
            }
            stack.push(e);
        }
        return nums;
    }
}
