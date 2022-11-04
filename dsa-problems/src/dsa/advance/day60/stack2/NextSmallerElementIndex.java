package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class NextSmallerElementIndex {
    @Test
    public void test1(){
        int[] array = {80, 100, 20, 50, 40};
        int[] expected = {20, 20, -1, 40, -1};
        Assertions.assertArrayEquals(expected, nextSmallerElement(array));
        int[] expectedIndex = {2, 2, -1, 4, -1};
        Assertions.assertArrayEquals(expectedIndex, nextSmallerIndex(array));
    }
    // TC: O(N), SC: O(N)
    public int[] nextSmallerElement(int[] array) {
        int n = array.length;
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[n-1]);
        ansArray[n-1]=-1;
        for(int i = n-2; i >=0; i--){
            if(!stack.isEmpty() && stack.peek() < array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && stack.peek() >= array[i]){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    ansArray[i] = stack.peek();
                }else {
                    ansArray[i] = -1;
                }
            }
            stack.push(array[i]);
        }
        return ansArray;
    }
    public int[] nextSmallerIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);
        ansArray[n-1]=-1;
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
                    ansArray[i] = -1;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
}
