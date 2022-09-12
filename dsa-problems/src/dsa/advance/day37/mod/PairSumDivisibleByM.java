package dsa.advance.day37.mod;

import static dsa.utils.Constants.mod_prime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/*
Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.
Since the answer may be large, return the answer modulo (109 + 7).
*/
public class PairSumDivisibleByM {
    @Test
    public void test1(){
        int[] array = {29,11,21,17,2,5,4,6,23,13,26,14,18,15,30,35,50,20,40, 9};
        int mod = 10;
        Assertions.assertEquals(20, countPair(array,mod));
    }
    @Test
    public void test2(){
        int[] array = {1, 2, 3, 4, 5};
        int mod = 2;
        Assertions.assertEquals(4, countPair(array,mod));
    }

    public int countPair(int[] array, int m) {
        HashMap<Integer, Integer> freqModMap = deriveFreqModMap(array,m);
        long n = 1L * freqModMap.getOrDefault(0,0);
        long count = 1L * n * (n-1) / 2L;
        System.out.println("0 Count:" + count);

        if(m%2 == 0){
            n = 1L * freqModMap.getOrDefault(m/2,0);
            count += 1L * n * (n-1) / 2L;
            System.out.println(m/2 + " " + n + " Count:" + count);
        }

        int i = 1,j=m-1;
        while(i < j){
            long n0 = 1L * freqModMap.getOrDefault(i,0);
            long n1 = 1L * freqModMap.getOrDefault(j,0);
            count = (count % mod_prime + (n0 % mod_prime * n1 % mod_prime) % mod_prime);
            System.out.println(i+","+ j + "("+ n0 + "," + n1 + ")"+ " Count:" + count);
            i++;
            j--;
        }


        return (int)(count % mod_prime + mod_prime)%mod_prime;
    }

    private HashMap<Integer, Integer> deriveFreqModMap(int[] array, int m) {
        HashMap<Integer, Integer> freqModMap = new HashMap<>();
        for(int i = 0 ; i < array.length; i++){
            int key = array[i] % m;
            int count = freqModMap.getOrDefault( key, 0);
            freqModMap.put(key, count+1);
        }
        return freqModMap;
    }
/*
    public int solve(int[] a, int k) {
        int n = a.length;
        long mod = (long)(1e9 + 7);
        long cnt[] = new long[k];
        for(int x : a)    cnt[x % k]++;
        long ans = cnt[0] * (cnt[0] - 1) / 2;
        for(int i = 1, j = k-1; i <= j; i++, j--) {
            if(i == j)    ans = (ans + cnt[i] * (cnt[i] - 1) / 2) % mod;
            else    ans = (ans + cnt[i] * cnt[j]) % mod;
        }
        return (int)ans;
    }
    */
}

/*
Let’s optimize using the fact that the value is up to 106, and using the modulo operator, we can reduce all the elements in the range 0 to B-1.
We make an auxiliary array cnt, the index i denotes the number of elements which gives i as the remainder when divided by B.
Now, we know that the sum of the pair modulo B should be equal to 0.
So we will count the pairs that give the sum of the pair modulo B is 0.
We can do this by adding cnt[i]*cnt[j] in the answer such that (i + j)%B=0.
Note: Keep in mind the base case when i==0 and j==0 and i==j.
*/
