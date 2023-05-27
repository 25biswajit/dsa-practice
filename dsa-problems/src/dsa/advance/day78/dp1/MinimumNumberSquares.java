package dsa.advance.day78.dp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MinimumNumberSquares {
    Map<Integer, Integer> dpTable = Collections.EMPTY_MAP;

    @Test
    public void test(){
        dpTable = new HashMap<>();
        Assertions.assertEquals(3, countMinSquares(12));
        Assertions.assertEquals(2, countMinSquaresIterative(10));
    }

    // TC O(N Root N), SC O(N)
    public int countMinSquares(int number) {
        if(number == 0) return 0;
        if(!dpTable.containsKey(number)){
            int currentDpState = dpTable.getOrDefault(number, Integer.MAX_VALUE-1);
            for(int i=1;i<=Math.sqrt(number);i++){
                int temp = countMinSquares(number-(i*i))+1;
                currentDpState = Math.min( temp, currentDpState );
            }
            dpTable.put(number, currentDpState);
        }
        return dpTable.get(number);
    }

    public int countMinSquaresIterative(int input) {
        dpTable = new HashMap<>();
        dpTable.put(0,0);
        dpTable.put(1,1);
        for(int num = 2 ; num <= input ; num++){
            int currentDpState = dpTable.getOrDefault(num,num);
            for(int i=1;i<=Math.sqrt(num);i++){
                int temp = dpTable.getOrDefault(num-(i*i),0)+1;
                currentDpState = Math.min( temp, currentDpState );
            }
            dpTable.put(num, currentDpState);
        }
        return dpTable.get(input);
    }
}
