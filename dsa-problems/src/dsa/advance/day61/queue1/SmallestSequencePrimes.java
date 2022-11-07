package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Microsoft **
https://leetcode.com/problems/ugly-number-ii/description/
Given three prime number(A, B, C) and an integer D. Find the first(smallest) D integers which have only A, B, C or a combination of them as their prime factors.
Input 1:
A = 2,B = 3,C = 5,D = 10
Output: 2 3 4 5 6 8 9 10 12 15

Hint:
-The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly.
Try to focus your effort on generating only the ugly ones.
-An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
-The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
-Assume you have Ugly (k), the kth ugly number. Then U(k+1) must be Min(L1 * 2, L2 * 3, L3 * 5).
*/

import java.util.*;

public class SmallestSequencePrimes {
    @Test
    public void test(){
        ArrayList<Integer> result = smallestSequencePrimes(2,3,5,10);
        System.out.println(result);
        //2 3 4 5 6 8 9 10 12 15
        int[] expected = {2, 3, 4, 5, 6, 8, 9, 10, 12, 15};
        Assertions.assertArrayEquals(expected, UglyNumber(2,3,5,10));
    }

    // TC : O(d log d)
    public ArrayList<Integer> smallestSequencePrimes(int a, int b, int c, int d){
        Integer[] inputs = {a,b,c};
        Arrays.sort(inputs);
        Queue<Integer> queue = new PriorityQueue<>(Arrays.asList(inputs));
        ArrayList<Integer> result = new ArrayList<>();
        result.add(inputs[0]);
        d--;
        while (d > 0 && !queue.isEmpty()){
            Integer x = result.get(result.size()-1);
            while (!queue.isEmpty() && Objects.equals(queue.peek(), x)){
                queue.poll();
            }
            for(Integer in : inputs){
                queue.add(x * in);
            }
            result.add(queue.peek());
            d--;
        }
        return result;
    }

    // O(N) - Complex - DP
    public int[] UglyNumber(int a, int b, int c, int d) {
        int[] ugly = new int[d+1];
        ugly[0] = 1;
        int pA = 0, pB = 0, pC = 0;
        // the logic is by maintaining three indices pointing to last unused numbers,
        // we know what is the next ugly must be the min of each of them multiply by
        // their group multiplier, i.e. 2, 3, 5.
        for (int i = 1; i <=d; i++) {
            ugly[i] = Math.min(ugly[pA] * a, Math.min(ugly[pB] * b, ugly[pC] * c));
            if (ugly[i] == ugly[pA] * a) pA++;
            if (ugly[i] == ugly[pB] * b) pB++;
            if (ugly[i] == ugly[pC] * c) pC++;
        }
        return Arrays.copyOfRange(ugly, 1, d+1);
    }
}

/*Thought Process
PriorityQueue
Every number polled out, we multiply by 2, 3, and 5 and add it back to queue
We also make sure we avoid the duplicate by polling the number that is equal to current value
Time complexity O(n logn)
Space complexity O(n)

Three Pointers
We use an array to store the ugly number
We use three pointer to track the last number use for each factor 2, 3, and 5
The next number is the min of three numbers times their corresponding indexed number
Increment index for the factors that matches the current ugly number
Time complexity O(n)
Space complexity O(n)*/
