package com.test.day12.array.carryforward;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/*
You are given an integer array A of length N comprising of 0's & 1's, and an integer B.
You have to tell all the indices of array A that can act as a center of 2 * B + 1 length 0-1 alternating subarray.
A 0-1 alternating array is an array containing only 0's & 1's, and having no adjacent 0's or 1's.
For e.g. arrays [0, 1, 0, 1], [1, 0] and [1] are 0-1 alternating, while [1, 1] and [0, 1, 0, 0, 1] are not.
*/

public class AlternatingSubArraysEasy {
    @Test
    public void test(){
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1,0,1,0,1,0,0,0,1,0,1,0));
        //System.out.println(solve(list1, 2));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 0, 1));
        //System.out.println(solve(list1, 0));

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(3, 4));
        subArraySum(list3);
    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = A.size();
        int window = 2 * B + 1;
        for(int i=0; i<= n - window;i++){
            int start = i;
            int end = i + window;
            int index = getCenterOfAlternativeSubArray(start,end,A);
            if(index != -1){
                result.add(index);
            }
        }
        return result;
    }

    private int getCenterOfAlternativeSubArray(int start, int end, ArrayList<Integer> A){
        int index = -1;
        boolean flag = false;
        for(int i=start;i<end-1; i++){
            if(A.get(i) + A.get(i+1) != 1){
                flag = true;
                break;
            }
        }
        if(!flag){
            index = start + ((end - start) / 2);;
        }
        return index;
    }

    public void subArraySum(ArrayList<Integer> A) {
        int n = A.size();
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j= i; j< n; j++){
                sum = sum + A.get(j);
                System.out.println(sum);
            }
        }
    }
}
