package dsa.advance.day43.sorting2;

import static dsa.utils.Constants.mod_prime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// Calculate Sum of difference of largest and smallest numbers for every subsequence.
/*All possible non-empty subsets are:
[1]    largest-smallest = 1 - 1 = 0
[2]    largest-smallest = 2 - 2 = 0
[1 2]  largest-smallest = 2 - 1 = 1
Sum of the differences = 0 + 0 + 1 = 1
So, the resultant number is 1*/
public class SumDifferenceSubSeq {
    @Test
    public void test1(){
        int[] array = {2,1,3};
        Assertions.assertEquals(6, sumDifferenceEachSubSequence(array));
    }

    @Test
    public void test2(){
        int[] array = {2,4,8,7,5,10};
        Assertions.assertEquals(312, sumDifferenceEachSubSequence(array));
    }

    @Test
    public void test3(){
        int[] array = {7, 8, 6, 4, 6, 7, 3, 10, 2, 3, 8, 1, 10, 4, 7, 1, 7, 3, 7, 2, 9, 8, 10, 3, 1, 3, 4, 8, 6, 10, 3, 3, 9, 10, 8, 4, 7, 2, 3, 10, 4, 2, 10, 5, 8, 9, 5, 6, 1, 4, 7, 2, 1, 7, 4, 3, 1, 7, 2, 6, 6, 5, 8, 7, 6, 7, 10, 4, 8, 5, 6, 3, 6, 5, 8, 5, 5, 4, 1, 8, 9, 7, 9, 9, 5, 4, 2, 5, 10, 3, 1, 7, 9, 10, 3, 7, 7, 5, 10, 6, 1, 5, 9, 8, 2, 8, 3, 8, 3, 3, 7, 2, 1, 7, 2, 6, 10, 5, 10, 1, 10, 2, 8, 8, 2, 2, 6, 10, 8, 8, 7, 8, 4, 7, 6, 7, 4, 10, 5, 9, 2, 3, 10, 4, 10, 1, 9, 9, 6, 1, 10, 7, 4, 9, 6, 7, 2, 2, 6, 10, 9, 5, 9, 2, 1, 4, 1, 5, 5, 5, 5, 8, 7, 4, 2, 8, 6, 10, 7, 3, 2, 8, 9, 6, 8, 5, 2, 9, 6, 10, 8, 6, 4, 9, 9, 4, 2, 9, 10, 7, 5, 4, 4, 4, 9, 7, 1, 5, 9, 9, 9, 10, 8, 8, 7, 5, 4, 1, 4, 1, 10, 3, 6, 5, 1, 6, 10, 5, 7, 10, 3, 3, 5, 8, 8, 6, 5, 9, 2, 3, 9, 10, 4, 7, 9, 1, 3, 2, 1, 6, 2, 2, 1, 9, 6, 1, 7, 5, 7, 3, 6, 9, 7, 3, 9, 5, 8, 3, 5, 1, 7, 3, 10, 10, 1, 9, 2, 4, 2, 2, 1, 4, 5, 1, 4, 10, 2, 10, 7, 10, 4, 4, 9, 1, 6, 7, 7, 5, 1, 1, 5, 7, 3, 7, 8, 6, 7, 10, 9, 8, 3, 9, 3, 10, 10, 7, 1, 3, 8, 7, 2, 4, 3, 2, 6, 10, 10, 2, 5, 10, 2, 1, 8, 6, 9, 8, 1, 5, 9, 1, 5, 3, 10, 7, 2, 1, 5, 3, 3, 3, 1, 6, 6, 3, 10, 1, 3, 9, 4, 9, 1, 5, 1, 10, 2, 10, 7, 3, 6, 5, 5, 10, 10, 4, 7, 1, 6, 1, 3, 10, 5, 4, 6, 2, 8, 5, 4, 2, 5, 7, 10, 5, 3, 3, 7, 5, 2, 3, 9, 9, 10, 3, 9, 9, 9, 7, 9, 4, 9, 4, 4, 4, 9, 1, 5, 8, 7, 9, 10, 1, 7, 9, 8, 10, 1, 4, 4, 4, 8, 4, 3, 7, 6, 3, 7, 6, 9, 8, 10, 7, 1, 5, 2, 1, 5, 9, 8, 1, 9, 7, 3, 5, 8, 10, 4, 10, 3, 9, 4, 1, 2, 8, 9, 10, 2, 6, 5, 10, 3, 6, 8, 5, 10, 10, 5, 6, 10, 4, 6, 8, 1, 9, 2, 10, 10, 8, 9, 3, 6, 4, 5, 10, 1, 3, 1, 2, 10, 7, 3, 2, 3, 1, 8, 4, 2, 2, 10, 1, 6, 7, 8, 8, 5, 1, 7, 5, 8, 5, 9, 6, 9, 3, 7, 1, 7, 7, 5, 7, 3, 9, 10, 7, 1, 8, 1, 2, 1, 2, 4, 8, 8, 3, 7, 5, 6, 3, 1, 3, 10, 1, 10, 10, 5, 6, 2, 1, 4, 8, 9, 9, 7, 1, 5, 7, 8, 7, 1, 10, 8, 6, 10, 8, 9, 6, 4, 4, 9, 4, 8, 10, 4, 8, 9, 8, 5, 2, 10, 1, 10, 9, 9, 6, 9, 5, 4, 8, 2, 4, 9, 1, 10, 8, 10, 10, 4, 3, 5, 4, 8, 2, 3, 3, 1, 3, 2, 8, 6, 2, 8, 5, 2, 8, 2, 2, 2, 8, 1, 5, 1, 9, 6, 2, 7, 7, 3, 2, 10, 7, 5, 9, 1, 9, 2, 1, 3, 3, 10, 8, 6, 7, 5, 7, 4, 8, 10, 8, 5, 10, 2, 8, 1, 7, 1, 9, 6, 4, 10, 5, 2, 6, 5, 2, 6, 6, 5, 10, 9, 4, 9, 6, 3, 3, 3, 8, 1, 4, 5, 7, 4, 7, 4, 4, 5, 5, 4, 10, 8, 3, 6, 9, 10, 1, 3, 5, 8, 7, 6, 8, 2, 4, 4, 4, 9, 6, 2, 1, 9, 8, 7, 4, 6, 1, 9, 1, 5, 2, 2, 4, 6, 10, 4, 5, 2, 6, 1, 9, 4, 6, 7, 6, 10, 10, 1, 8, 7, 4, 8, 7, 2, 6, 1, 7, 6, 1, 9, 2, 3, 3, 7, 10, 2, 1, 5, 3, 8, 5, 1, 4, 3, 9, 1, 4, 8, 1, 1, 4, 5, 10, 3, 8, 5, 3, 6, 3, 5, 5, 4, 9, 7, 1, 9, 10, 3, 3, 4, 2, 9, 4, 5, 3, 3, 5, 6, 2, 8, 6, 8, 2, 7, 10, 9, 2, 4, 4, 4, 8, 10, 9, 7, 8, 1, 5, 9, 5, 9, 2, 7, 9, 6, 3, 2, 10, 10, 7, 1, 7, 5, 10, 10, 1, 9, 10, 4, 2, 5, 9, 10, 7, 8, 8, 4, 8, 2, 3, 3, 2, 6, 1, 10, 1, 5, 1, 2, 4, 8, 5, 2, 2, 4, 1, 4, 3, 2, 8, 6, 7, 6, 5, 3, 3, 2, 8, 3};
        Assertions.assertEquals(306568771, sumDifferenceEachSubSequence(array));
    }

    // TC: N log N, SC: O(1)
    public int sumDifferenceEachSubSequence(int[] array){
        Arrays.sort(array);
        long sum = 0;
        int n = array.length;
        for(int i = 0; i< n; i++){
            long add = powerExponentiation(2, i, mod_prime);
            long subtract = powerExponentiation(2, (n-i-1), mod_prime);
            long contribution = ( array[i] * ( add - subtract )) % mod_prime;
            sum = ( sum + contribution ) % mod_prime;
        }
        return (int) ((sum % mod_prime + mod_prime ) % mod_prime);
    }

    // a ^ n % mod - TC: (log N)
    private int powerExponentiation(int a, int n, int mod){
        if(n == 0) return 1;
        if(a == 0) return 0;
        else {
            int half = n / 2;
            long res = powerExponentiation(a, half, mod);
            res = ( res * res ) % mod;
            if(n % 2 != 0){
                res = ( res * a ) % mod;
            }
            return (int) res;
        }
    }
}
