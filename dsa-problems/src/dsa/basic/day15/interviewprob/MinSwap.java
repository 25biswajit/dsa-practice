package dsa.basic.day15.interviewprob;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MinSwap {
    @Test
    public void test(){
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(
                52, 7, 93, 47, 68, 26, 51, 44, 5, 41, 88, 19, 78, 38, 17,
                13, 24, 74, 92, 5, 84, 27, 48, 49, 37, 59, 3, 56, 79, 26,
                55, 60, 16, 83, 63, 40, 55, 9, 96, 29, 7, 22, 27, 74, 78,
                38, 11, 65, 29, 52, 36, 21, 94, 46, 52, 47, 87, 33, 87, 70));
        System.out.println( solve(integers, 19) );

        integers = new ArrayList<>(Arrays.asList(
                1, 12, 10, 3, 14, 10, 5));
        System.out.println( solve(integers, 8) );
    }
    public int solveOld(ArrayList<Integer> A, int B) {
        int p1 = 0;
        int p2 = A.size()-1;
        int c = 0;
        while(p2 >= p1){
            if(A.get(p1) > B && A.get(p2) < B){
                System.out.println(String.format("Swap A[%d]=%d & A[%d]=%d", p1, A.get(p1), p2, A.get(p2)));
                swap(A, p1, p2);
                c++;
            }
            else{
                if(A.get(p1) <= B){
                    p1++;
                }
                if(A.get(p2) >= B){
                    p2--;
                }
            }
        }

        System.out.println("Final Array: "+ A);
        return c;
    }
    private void swap(ArrayList<Integer> A, int i, int j){
        int temp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, temp);
    }

    public int solveNotCorrect(ArrayList<Integer> A, int B) {
        int p1 = 0;
        int p2 = 1;
        int c = 0;
        while(p2 < A.size()){

            if(A.get(p1) > B && A.get(p2) < B){
                swap(A, p1, p2);
                p1++;
                p2++;
                c++;
            }
            else {
                if(A.get(p1) <= B){
                    p1++;
                }
                if(A.get(p2) >= B){
                    p2++;
                }
            }

        }
        System.out.println("Final Array: "+ A);
        return c;
    }

    public int solve(ArrayList<Integer> A, int B) {
        int goodNumbers = 0;
        for(Integer x : A){
            if(x <= B){
                goodNumbers++;
            }
        }
        System.out.println("GoodNumbers :" + goodNumbers);
        int start = 0;
        int end = goodNumbers-1;
        int badNumbersNeedToSwapWindow = 0;
        int c = 0;
        for(int i =start; i<=end; i++){
            if(A.get(i) > B){
                badNumbersNeedToSwapWindow++;
            }
        }
        int ans = badNumbersNeedToSwapWindow;
        c++; System.out.println(String.format("Ans Window [%d : %d to %d]: %d" , c, start, end, ans));
        start++;
        end++;

        while(end < A.size()){
            int preCount = A.get(start-1) > B ? 1 : 0;
            int postCount = A.get(end) > B ? 1 : 0;
            badNumbersNeedToSwapWindow = badNumbersNeedToSwapWindow - preCount + postCount;
            ans = Integer.min(ans, badNumbersNeedToSwapWindow);
            c++; System.out.println(String.format("Ans Window [%d : %d to %d]: %d" , c, start, end, ans));
            start++;
            end++;
        }
        return  ans;
    }
}

