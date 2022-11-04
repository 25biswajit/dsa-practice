package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
Given an array of integers A.
value of a array = max(array) - min(array).
Calculate and return the sum of values of all possible subarrays of A modulo 109+7.
value ( [4] ) = 4 - 4 = 0
value ( [7] ) = 7 - 7 = 0
value ( [3] ) = 3 - 3 = 0
value ( [8] ) = 8 - 8 = 0
value ( [4, 7] ) = 7 - 4 = 3
value ( [7, 3] ) = 7 - 3 = 4
value ( [3, 8] ) = 8 - 3 = 5
value ( [4, 7, 3] ) = 7 - 3 = 4
value ( [7, 3, 8] ) = 8 - 3 = 5
value ( [4, 7, 3, 8] ) = 8 - 3 = 5
sum of values % 10^9+7 = 26
*/

public class MaxMinSubArray {
    @Test
    public void test1(){
        int[] array = {4, 7, 3, 8};
        Assertions.assertEquals(26, solveMaxMin(array));
    }
    //TC: N, SC: N
    public int solveMaxMin(int[] array){
        long sumMaxSubArray = sumMaxSubArray(array);
        long sumMinSubArray = sumMinSubArray(array);
        long result = (sumMaxSubArray - sumMinSubArray) % 1000000007;
        return (int)result;
    }
    //TC: N, SC: N
    private long sumMinSubArray(int[] array) {
        long sum = 0;
        int[] prevSmallerIndex = prevSmallerIndex(array);
        int[] nextSmallerIndex = nextSmallerIndex(array);
        for(int i = 0; i < array.length; i++){
            sum += new Long(i - prevSmallerIndex[i]) * new Long(nextSmallerIndex[i] - i) * new Long(array[i]);
        }
        return sum;
    }
    //TC: N, SC: N
    private long sumMaxSubArray(int[] array) {
        long sum = 0;
        int[] prevGreaterIndex = prevGreaterIndex(array);
        int[] nextGreaterIndex = nextGreaterIndex(array);
        for(int i = 0; i < array.length; i++){
            sum += new Long(i - prevGreaterIndex[i]) * new Long(nextGreaterIndex[i] - i) * new Long(array[i]);
        }
        return sum;
    }
    //TC: N, SC: N
    private int[] prevGreaterIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        ansArray[0]=-1;
        for(int i = 1; i < n; i++){
            if(!stack.isEmpty() && array[stack.peek()] > array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] <= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = -1;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
    //TC: N, SC: N
    public int[] nextGreaterIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);
        ansArray[n-1]=n;
        for(int i = n-2; i >= 0; i--){
            if(!stack.isEmpty() && array[stack.peek()] > array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] <= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = n;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
    //TC: N, SC: N
    public int[] nextSmallerIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);
        ansArray[n-1]=n;
        for(int i = n-2; i >=0; i--){
            if(!stack.isEmpty() && array[stack.peek()] < array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] >= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = n;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
    //TC: N, SC: N
    public int[] prevSmallerIndex(int[] array) {
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        ansArray[0]=-1;
        for(int i = 1; i < array.length; i++){
            if(!stack.isEmpty() && array[stack.peek()] < array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && array[stack.peek()] >= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = -1;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
}
/*
Hint!!    For each number Ai calculate how many subarrays including Ai, Ai is maximum or minimum.

Calculate the next greater element and previous greater element for each element in the array. Each element Ai is the maximum of all subarrays starting at (previous greater element)i + 1 to (next greater element)i - 1.
Similarly, the next smaller element and previous smaller element can be used for minimum values of subarrays
Time Complexity:- O(N)
*/

