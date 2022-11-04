package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;
/*
Given an integer array A of size N. You have to generate it's all subarrays having a size greater than 1.
Then for each subarray, find Bitwise XOR of its maximum and second maximum element.
Find and return the maximum value of XOR among all subarrays.
A = [2, 3, 1, 4]
Explanation 1:
All subarrays of A having size greater than 1 are:
Subarray            XOR of maximum and 2nd maximum no.
1. [2, 3]           1
2. [2, 3, 1]        1
3. [2, 3, 1, 4]     7
4. [3, 1]           2
5. [3, 1, 4]        7
6. [1, 4]           5
So maximum value of Xor among all subarrays is 7.
*/

// Asked in Microsoft
public class MaximumXORSubArray {
    @Test
    public void test(){
        int[] array = {2, 3, 1, 4};
        Assertions.assertEquals(7, maxXorSubArrayMySolution(array)); // Accepted
        Assertions.assertEquals(7, optimalSolution(array));
    }

    // TC: O(N)
    public int maxXorSubArrayMySolution(int[] array){
        int max = 0;
        int[] prevGreaterIndex = prevGreaterIndex(array);
        int[] nextGreaterIndex = nextGreaterIndex(array);
        for(int i = 0; i < array.length; i++){
            if(prevGreaterIndex[i] >= 0 && prevGreaterIndex[i]<array.length){
                max = Integer.max(max , (array[i] ^ array[prevGreaterIndex[i]]));
            }
            if(nextGreaterIndex[i] >= 0 && nextGreaterIndex[i]<array.length){
                max = Integer.max(max , (array[i] ^ array[nextGreaterIndex[i]]));
            }
        }
        return max;
    }

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

    // Need to understand
    public int optimalSolution(int[] A) {
        int ans = 0;
        int n = A.length;
        // create a stack to store the maximum value of all subarrays
        Stack < Integer > s = new Stack <> ();

        for (int i = 0; i < n; i++) {
            // while stack is not empty take xor of its top and current element
            while (!s.empty()) {
                // store the maximum value of xor
                int topElement = s.peek();
                ans = Math.max(ans, (A[topElement] ^ A[i]));

                // if top of the stack is greater than current element than break the loop
                if (A[topElement] > A[i]) break;
                else s.pop(); //pop out the top of the stack

            }
            // push the current element into the stack
            s.push(i);
        }
        // return the answer
        return ans;
    }
}
/*
Naive approach: A naive approach will be to simply iterate over all the subarrays one by one and then find the required values.
This approach requires O(N3) time complexity.
Efficient approach: Let arr[i] be the second maximum element of some subarray then the maximum element can be the first element
larger than arr[i] in the forward or the backward direction. Hence, it can be shown that each element except the first and the last can act as
the second maximum element at most 2 times only. Now, just calculate the next greater element of each element in the forward and the backward direction
and return the maximum XOR of them.
*/

/*
Optimal Solution:
The subArray can only be reflected on its maximum element and second maximum element, so apparently,
there must be a lot of meaningless subarrays which we needn’t check them at all. But how can we skip them?
Maintain a monotone-decreasing-stack can help us.
While a new element came into the view, pop the top element in the stack, and check the corresponding interval, until the new element is greater than the top element in the stack. We can easily see it is correct since we won’t lost the answer as long as it exists.
*/
