package com.test.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisibilityBy8 {

    @Test
    public void test1(){
        Assertions.assertEquals(1, solve("34296"));
    }
    @Test
    public void test2(){
        Assertions.assertEquals(1, solve("8"));
    }
    @Test
    public void test3(){
        Assertions.assertEquals(1, solve("16"));
    }
    @Test
    public void test4(){
        Assertions.assertEquals(1, solve("1000"));
    }

    public int solve(String word) {
        int mod = 8;
        String[] arr = word.split("");
        int sum = 0;
        int temp = 1;
        int n = arr.length;
        int c = 3;
        for(int i=n-1;i>=0 && c!=0;i--){
            int num = Integer.parseInt(arr[i]);
            sum = sum + ( num * temp );
            temp = (temp * 10);
            c--;
        }
        System.out.println(sum);
        if(sum >0 && sum < mod){
            return 0;
        }else{
            return sum % mod == 0 ? 1 : 0;
        }
    }
}
