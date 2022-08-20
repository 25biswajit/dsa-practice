package com.test.day23.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountBoBSubString {
    @Test
    public void test(){
        Assertions.assertEquals(2, solve("bobob"));
        Assertions.assertEquals(2, solve("bobabtbobl"));
    }

    public int solve(String A) {
        String bob = "bob";
        int c = 0;
        int i = 0;
        int j = bob.length() - 1;
        while(j < A.length()){
            String s = A.substring(i,j+1);
            System.out.println(s);
            if(s.equals(bob)){
                c++;
            }
            i++;
            j++;
        }
        return c;
    }
}
