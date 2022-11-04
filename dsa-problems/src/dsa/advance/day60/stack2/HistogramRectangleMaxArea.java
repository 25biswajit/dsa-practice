package dsa.advance.day60.stack2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
Given an array of integers A.
A represents a histogram i.e A[i] denotes the height of the ith histogram's bar. Width of each bar is 1.
Find the area of the largest rectangle formed by the histogram.
A = [2, 1, 5, 6, 2, 3]
The largest rectangle has area = 10 unit. Formed by A[3] to A[4].
*/

public class HistogramRectangleMaxArea {
    @Test
    public void test1(){
        int[] array = {2, 1, 4, 7, 5, 6, 1, 3, 4, 5, 6, 4, 3, 4, 2, 1};
        Assertions.assertEquals(21, largestRectangleArea(array));
    }
    @Test
    public void test2(){
        int[] array = {2, 1, 5, 6, 2, 3};
        Assertions.assertEquals(10, largestRectangleArea(array));
    }
    @Test
    public void test3(){
        int[] array = {2,3};
        Assertions.assertEquals(4, largestRectangleArea(array));
    }
    @Test
    public void test4(){
        int[] array = {47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18, 1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65, 7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66, 87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43, 27};
        Assertions.assertEquals(418, largestRectangleArea(array));
    }

    public int largestRectangleArea(int[] array) {
        int maxArea = 0;
        int[] left = prevSmallerIndex(array);
        int[] right = nextSmallerIndex(array);
        for(int i=0; i < array.length; i++){
            int l = left[i];
            int r = right[i];
            int area = (r-l-1) * array[i];
            maxArea = Integer.max(maxArea, area);
        }
        return maxArea;
    }

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
}

/*
Hint1:
-----
What’s the brute force approach here?

We know that the height of the largest rectangle will be one of the heights of the histogram bars (Think, why?)
If that is the case, we can iterate on all the histogram bars, and for each histogram bar H, we try to create the maximum rectangle with H as the height.
We keep going left L and right R until we encounter a histogram bar with a height less than H.
Now, (R - L - 1) * H is one of the possibilities for the largest rectangle.

Doing this for all the histogram bars will give us the right solution.

Following is rough pseudocode for the approach mentioned above :


max_rectangle = 0

for index = 0 to all_histograms.length
  H = all_histograms[index]
  L = index, R = index
  while L >= 0 AND all_histograms[L] >= H
    L = L - 1
  while R < all_histograms.length AND all_histograms[R] >= H
    R = R + 1
  max_rectangle = MAX(max_rectangle, (R - L - 1) * H)

return max_rectangle


This approach is, however, O(N^2) time complexity in the worst case. How can we do better than this approach?

Hint2:
------
As discussed in the previous hint, the height of the maximum rectangle will be the height of one of the histogram bars. For each histogram H, we need to know the index of the first smaller (smaller than H) bar on the left of H (let’s call it L) and the index of the first smaller bar on the right of H.
We have already tried the brute force approach. How can we do better?

Important observation:

Lets consider 2 consecutive histogram bars H[i] and H[i+1]. Lets assume H[i+1] < H[i]
In such a case, for all histogram bars X with index > i + 1 when traversing left for L, there is no point looking at H[i] after looking at H[i+1]. If H[i+1] > X, obviously H[i] > X as we already know H[i] > H[i+1]

Then, what is the next entry we would want to look at? We want to look at the first histogram bar left of H[i+1] with a height less than H[i+1].

Can you think of a stack-based approach based on the above observation?

Solution Approach:
-----------------
We already know from our previous observation that when I traverse left, I only need entries in decreasing order.

We traverse all histograms from left to right and maintain a stack of histograms. Every histogram is pushed to stack once. A histogram is popped from the stack when a histogram of smaller height is seen. We calculate the area with the popped histogram as the smallest histogram when a histogram is popped. How do we get left and right indexes of the popped histogram – the current index tells us the ‘right index’ R, and the index of the previous item in the stack is the ‘left index’ L. Following is a rough pseudocode.

max_rectangle = 0
stack = an instance of empty stack
for index = 0 to all_histograms.length
    if stack.empty OR H[index] > H[stack.top]
        Push index to the stack
    if H[index] < H[stack.top]
        while !stack.empty && H[stack.top] > H[index]
            h = H[stack.pop]
            # Calculate the area with h as the smallest height.
            R = index
            L = stack.top
            max_rectangle = MAX(max_rectangle, (R - L - 1) * h)
        Push index to the stack
if stack is not empty
Repeat removing entries one by one from the stack and calculating the max_rectangle as done earlier.
*/
