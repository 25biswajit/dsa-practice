package dsa.advance.day78.dp1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DanceLandPartyPair {
    @Test
    public void test(){
        Assertions.assertEquals(26, numberWaysPairPeople(5));
        Assertions.assertEquals(10, numberWaysPairPeople(4));
        Assertions.assertEquals(5793, numberWaysPairPeople(17));
    }

    public int numberWaysPairPeople(int n){
        HashMap<Integer, Long> dpTable = new HashMap<>();
        dpTable.put(1,1L);
        dpTable.put(2,2L);
        for(int i = 3; i <= n; i++){
            //f(n) = f(n-1) + (n-1) * f(n-2)
            long people = ( dpTable.get(i-1) % 10003 + (i-1) * dpTable.get(i-2) % 10003 ) % 10003;
            dpTable.put(i, people);
        }
        return dpTable.get(n).intValue();
    }
}
