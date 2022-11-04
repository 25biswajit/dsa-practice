package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
Given an array A, find the next greater element G[i] for every element A[i] in the array.
The next greater element for an element A[i] is the first greater element on the right side of A[i] in the array, A.
More formally:
G[i] for an element A[i] = an element A[j] such that
j is minimum possible AND
j > i AND
A[j] > A[i]
Elements for which no greater element exists, consider the next greater element as -1.
Input 1:
A = [4, 5, 2, 10]
For 4, the next greater element towards its right is 5.
For 5 and 2, the next greater element towards their right is 10.
For 10, there is no next greater element towards its right.
*/

public class NextGreaterElementIndex {

    @Test
    public void test1(){
        int[] array = {4, 5, 2, 10};
        int[] expected = {5, 10, 10, -1};
        Assertions.assertArrayEquals(expected, nextGreaterElement(array));
        int[] expectedIndex = {1, 3, 3, -1};
        Assertions.assertArrayEquals(expectedIndex, nextGreaterIndex(array));
    }

    public int[] nextGreaterElement(int[] array) {
        int n = array.length;
        int[] ansArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[n-1]);
        ansArray[n-1]=-1;
        for(int i = n-2; i >= 0; i--){
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

    public int[] nextGreaterIndex(int[] array) {
        int n = array.length;
        int[] ansArray = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(n-1);
        ansArray[n-1]=-1;
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
                    ansArray[i] = -1;
                }
            }
            stack.push(i);
        }
        return ansArray;
    }
}
/*
Solution Approach
We can use a stack to find the next greater element.
Push the first element in the stack.
Pick rest of the elements one by one and follow the following steps in loop
Mark the current element as next.
If stack is not empty, compare top element of stack with next.
If next is greater than the top element, Pop element from stack. Next is the next greater element for the popped element.
Keep popping from the stack while the popped element is smaller than next. Next becomes the next greater element for all such popped elements.
Finally, push the next in the stack.
After the loop in step 2 is over, pop all the elements from stack and print -1 as next element for them.
Time Complexity:- O(N)
*/
