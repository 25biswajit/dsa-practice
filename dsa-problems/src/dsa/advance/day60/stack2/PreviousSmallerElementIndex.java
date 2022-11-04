package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
Problem Description
Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.
More formally,
G[i] for an element A[i] = an element A[j] such that
j is maximum possible AND
j < i AND
A[j] < A[i]
Elements for which no smaller element exist, consider the next smaller element as -1.
A = [4, 5, 2, 10, 8]
Explanation 1:
index 1: No element less than 4 in left of 4, G[1] = -1
index 2: A[1] is only element less than A[2], G[2] = A[1]
index 3: No element less than 2 in left of 2, G[3] = -1
index 4: A[3] is nearest element which is less than A[4], G[4] = A[3]
index 4: A[3] is nearest element which is less than A[5], G[5] = A[3]
*/

public class PreviousSmallerElementIndex {
    @Test
    public void test1(){
        int[] array = {4, 5, 2, 10, 8};
        int[] expected = {-1, 4, -1, 2, 2};
        Assertions.assertArrayEquals(expected, prevSmallerElement(array));
        int[] expectedIndex = {-1, 0, -1, 2, 2};
        Assertions.assertArrayEquals(expectedIndex, prevSmallerIndex(array));
    }
    @Test
    public void test2(){
        int[] array = {34, 35, 27, 42, 5, 28, 39, 20, 28};
        int[] expected = {-1,34,-1,27,-1,5,28,5,20 };
        Assertions.assertArrayEquals(expected, prevSmallerElement(array));
    }
    // TC: O(N), SC: O(N)
    public int[] prevSmallerElement(int[] array) {
        int[] ansArray = new int[array.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(array[0]);
        ansArray[0]=-1;
        for(int i = 1; i < array.length; i++){
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
    // TC: O(N), SC: O(N)
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
Solution Approach
The naive solution would look something like this:

for i = 0 to size(A):
G[i] = -1
for j = i - 1 to 0:
if A[j] < A[i]:
G[i] = j
break
Now look at A[i-1]. All elements with index smaller than i - 1 and greater than A[i-1] are useless to us because they would never qualify for G[i], G[i+1], ...

We know that we only need previous numbers in descending order using the above fact.

The pseudocode would look something like this:
1) Create a new empty stack st
2) Iterate over array `A`,
where `i` goes from `0` to `size(A) - 1`.
a) We are looking for value just smaller than `A[i]`. So keep popping from `st` till elements in `st.top() >= A[i]` or st becomes empty.
b) If `st` is non-empty, then the top element is our previous element. Else the previous element does not exist.
c) push `A[i]` onto st
*/
