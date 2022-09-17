package dsa.advance.day41.recursion1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GreyCode {
    @Test
    public void test(){
        ArrayList<Integer> result = grayCode(3);
        int[] expected = {0, 1, 3, 2, 6, 7, 5, 4};
        int[] actual = result.stream().mapToInt(i -> i).toArray();
        Assertions.assertArrayEquals(expected, actual);
    }


    public ArrayList<Integer> grayCode(int n) {
        if(n==1){
            return new ArrayList<>(Arrays.asList(0,1));
        }
        ArrayList<Integer> list = grayCode(n-1);
        ArrayList<Integer> result = new ArrayList<>(list);
        for(int i = list.size()-1; i >= 0 ; i--){
            result.add( list.get(i) + (1<<(n-1)));
        }
        return result;
    }
}
