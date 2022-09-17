package dsa.advance.day41.recursion1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnotherSequenceProblem {
    @Test
    public void test(){
        Assertions.assertEquals(7, solve(3));
        Assertions.assertEquals(672, solve(10));
    }

    public int solve(int num) {
        if(num == 0 || num == 1){
            return 1;
        }
        if (num == 2){
            return 2;
        }
        else {
            return num + solve(num - 1) + solve( num - 2) + solve( num - 3);
        }
    }
}
