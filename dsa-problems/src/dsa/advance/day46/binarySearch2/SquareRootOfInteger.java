package dsa.advance.day46.binarySearch2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareRootOfInteger {
    @Test
    public void test1(){
        Assertions.assertEquals(3, sqrt(11));
        Assertions.assertEquals(3, sqrt(9));
    }

    public int sqrt(int number) {
        int ans = -1;
        int low = 0, high = number;
        while (low <= high){
            int mid = (low + high) / 2;
            long product = 1L * mid * mid;
            if(product == number){
                return mid;
            }
            else if(product > number){
                high = mid - 1;
            }
            else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }
}
