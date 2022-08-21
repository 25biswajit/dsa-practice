package com.test.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeapYearCheck {
    @Test
    public void test(){
        Assertions.assertEquals(1, solve(2020));
        Assertions.assertEquals(0, solve(2016));
    }
    public int solve(int a) {
        if(a % 400 == 0){ return 1;}
        if(a % 4 == 0 && a % 100 != 0){ return 1;}
        return 0;
    }
}
