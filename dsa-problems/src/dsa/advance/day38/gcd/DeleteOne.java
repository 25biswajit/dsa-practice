package dsa.advance.day38.gcd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*Given an integer array A of size N.
You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.
Find the maximum value of GCD.
A = [12, 15, 18]
If you delete 12, gcd will be 3.
If you delete 15, gcd will be 6.
If you delete 18, gcd will 3.
Maximum vallue of gcd is 6.
*/
public class DeleteOne {
    @Test
    public void test1(){
        int[] array = {24,16,18,30,15};
        Assertions.assertEquals(3, getMaxGcdDeleteOne(array));
    }

    // TC: O(N), SC:O(N)
    public int getMaxGcdDeleteOne(int[] array) {
        int n = array.length;
        int[] prefixGcd = derivePrefixGcd(array);
        int[] suffixGcd = deriveSuffixGcd(array);
        int ans = Math.max( suffixGcd[1], prefixGcd[n-2] );
        for(int i = 1; i < n-1; i++){
            int leftGcd = prefixGcd[i-1];
            int rightGcd = suffixGcd[i+1];
            int currentGcd = gcd(leftGcd,rightGcd);
            ans = Math.max( ans, currentGcd);
        }
        return ans;
    }

    private int[] derivePrefixGcd(int[] array) {
        int[] prefixGcd = new int[array.length];
        prefixGcd[0] = array[0];
        for(int i = 1; i < array.length; i++){
            prefixGcd[i] = gcd(prefixGcd[i-1],array[i]);
        }
        return prefixGcd;
    }
    private int[] deriveSuffixGcd(int[] array) {
        int n = array.length;
        int[] suffixGcd = new int[n];
        suffixGcd[n-1] = array[n-1];
        for(int i = n-2; i >=0; i--){
            suffixGcd[i] = gcd(suffixGcd[i+1],array[i]);
        }
        return suffixGcd;
    }

    private int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}

/*We can maintain two arrays for prefix and suffix gcd; likewise, we do for prefix sum and suffix sum.
Then,for each index, i:1 to N calculate gcd(prefix[i-1],suffix[i+1]) and return the maximum among all.*/
