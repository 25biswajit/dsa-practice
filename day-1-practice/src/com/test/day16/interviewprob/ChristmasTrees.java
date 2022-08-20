package com.test.day16.interviewprob;
/*

You are given an array A consisting of heights of Christmas trees and an array B of the same size consisting of the cost of each of the trees (Bi is the cost of tree Ai, where 1 ≤ i ≤ size(A)), and you are supposed to choose 3 trees (let's say, indices p, q, and r), such that Ap < Aq < Ar, where p < q < r.
The cost of these trees is Bp + Bq + Br.
You are to choose 3 trees such that their total cost is minimum. Return that cost.
If it is not possible to choose 3 such trees return -1.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ChristmasTrees {

    @Test
    public void test(){
        ArrayList<Integer> inputs = new ArrayList<>(Arrays.asList(1, 6, 4, 2, 6, 9));
        ArrayList<Integer> costs = new ArrayList<>(Arrays.asList(2, 5, 7, 3, 2, 7));
        Assertions.assertEquals(7 , solve(inputs,costs));
    }

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        int n = A.size();
        int cost = Integer.MAX_VALUE;
        for(int i = 1; i< n; i++){
            int currentVal = A.get(i);
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();

            for(int j=i-1; j>=0; j--){
                if(A.get(j) < currentVal){
                    left.add(B.get(j));
                }
            }
            for(int k=i+1; k< n;k++){
                if(currentVal < A.get(k)){
                    right.add(B.get(k));
                }
            }
            if(!left.isEmpty() && !right.isEmpty()){
                cost = Integer.min(cost, getMinCostForTriplets(left,right,B.get(i)));
            }
        }
        return cost == Integer.MAX_VALUE ? -1 : cost;
    }

    private int getMinCostForTriplets(ArrayList<Integer> left, ArrayList<Integer> right, Integer middleCost) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i< left.size(); i++){
            for(int j = 0; j< right.size(); j++){
                min = Integer.min( min, left.get(i) + right.get(j) + middleCost);
            }
        }
        return min;
    }
}
