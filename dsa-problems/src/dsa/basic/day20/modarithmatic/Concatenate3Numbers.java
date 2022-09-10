package dsa.basic.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Concatenate3Numbers {
    @Test
    public void test(){
        Assertions.assertEquals(434553, solve(53,43,45));
    }


    public int solve(int A, int B, int C) {
        Integer[] arr = new Integer[3];
        arr[0]=A;
        arr[1]=B;
        arr[2]=C;
        Arrays.sort(arr);
        return 10000*arr[0]+100*arr[1]+arr[2];
    }
}
