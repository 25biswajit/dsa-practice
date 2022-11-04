package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class PreviousGreaterElementIndex {

    @Test
    public void test1(){
        int[] array = {4, 5, 2, 10};
        int[] expected = {-1, -1, 5, -1};
        Assertions.assertArrayEquals(expected, prevGreaterElement(array));
        int[] expectedIndex = {-1, -1, 1, -1};
        Assertions.assertArrayEquals(expectedIndex, prevGreaterIndex(array));
    }
    @Test
    public void test2(){
        int[] array = {10, 2, 5 , 4};
        int[] expected = {-1, 10, 10, 5};
        Assertions.assertArrayEquals(expected, prevGreaterElement(array));
        int[] expectedIndex = {-1, 0, 0, 2};
        Assertions.assertArrayEquals(expectedIndex, prevGreaterIndex(array));
    }

    public int[] prevGreaterElement(int[] array) {
        int n = array.length;
        int[] ansArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[0]);
        ansArray[0]=-1;
        for(int i = 1; i < n; i++){
            if(!stack.isEmpty() && stack.peek() > array[i]){
                ansArray[i] = stack.peek();
            }else {
                while (!stack.isEmpty() && stack.peek() <= array[i]){
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
    public int[] prevGreaterIndex(int[] array) {
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
}
