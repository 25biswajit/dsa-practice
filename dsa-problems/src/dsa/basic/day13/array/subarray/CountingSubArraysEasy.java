package dsa.basic.day13.array.subarray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountingSubArraysEasy {
    @Test
    public void test1(){
        int[] arr = {15,8,16};
        int b = 242;
        Assertions.assertEquals(6, solve(arr,b));
    }

    @Test
    public void test2(){
        int[] arr = {1, 11, 2, 3, 15};
        int b = 10;
        Assertions.assertEquals(4, solve(arr,b));
    }

    @Test
    public void test3(){
        int[] arr = {2,5,6};
        int b = 10;
        Assertions.assertEquals(4, solve(arr,b));
    }

    public int solve(int[] A, int B) {
        int n = A.length;
        int[] prefix = new int[n];
        prefix[0] = A[0];
        for(int i = 1; i <n;i++){
            prefix[i] = prefix[i-1]+A[i];
        }

        int c = 0;
        for(int i=0; i<n;i++){
            for(int j=i; j<n;j++){
                int subASum = subArraySum(prefix, i, j);
                if( subASum < B ){
                    c++;
                }
            }
        }

        return c;
    }

    private int subArraySum(int[] prefix, int i, int j){
        int subASum = 0;
        if(i == 0){
            subASum = prefix[j];
        }else{
            subASum = prefix[j] - prefix[i-1];
        }
        return subASum;
    }

}
