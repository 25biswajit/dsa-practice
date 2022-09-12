package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static dsa.utils.Constants.mod_prime;

/*
Get all SubArray of Array, Calculate SubArray BIT WISE OR, Get the Sum
 A = [1, 2, 3, 4, 5]
 Value([1]) = 1
 Value([1, 2]) = 3
 Value([1, 2, 3]) = 3
 Value([1, 2, 3, 4]) = 7
 Value([1, 2, 3, 4, 5]) = 7
 Value([2]) = 2
 Value([2, 3]) = 3
 Value([2, 3, 4]) = 7
 Value([2, 3, 4, 5]) = 7
 Value([3]) = 3
 Value([3, 4]) = 7
 Value([3, 4, 5]) = 7
 Value([4]) = 4
 Value([4, 5]) = 5
 Value([5]) = 5
 Sum of all these values = 71
*/

public class SumSubArrayOR {
    @Test
    public void test1(){
        int[] array = {5,8,26,13,5,21};
        Assertions.assertEquals(490, sumSubArrayOR_bruteforce(array));
        Assertions.assertEquals(490, sumSubArrayOR_optimized(array));
        Assertions.assertEquals(490, solve_optimised(array));
        Assertions.assertEquals(490, solve_simple(array));
    }
    @Test
    public void test2(){
        int[] array = {1, 2, 3, 4, 5};
        Assertions.assertEquals(71, sumSubArrayOR_bruteforce(array));
        Assertions.assertEquals(71, sumSubArrayOR_optimized(array));
    }
    @Test
    public void test3(){
        int[] array = {7, 8, 9, 10};
        Assertions.assertEquals(110, sumSubArrayOR_bruteforce(array));
        Assertions.assertEquals(110, sumSubArrayOR_optimized(array));
    }

    // TC: O(N*N), SC: O(1)
    public int sumSubArrayOR_bruteforce(int[] arr){
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            int sum1 = 0;
            for (int j = i; j < n; j++)
            {
                sum1 = (sum1 | arr[j]);
                sum += sum1;
            }
        }
        return sum;
    }

    // Optimised Solution TC: O(N), SC: O(N)
    public int sumSubArrayOR_optimized(int[] array){
        long sum = 0;
        for(int i = 0 ; i < 32; i++){
            long count_subArray_OR_one_ith_bit = getCountOfSubArrayOR_One_IthBit(array, i);
            long contribution_ith_bit = ((count_subArray_OR_one_ith_bit % mod_prime) * ((1 << i) %mod_prime)) % mod_prime;
            sum = (sum % mod_prime + contribution_ith_bit % mod_prime) % mod_prime;
        }
        return (int)((sum % mod_prime + mod_prime ) % mod_prime);
    }

    private long getCountOfSubArrayOR_One_IthBit(int[] array, int i) {
        int[] binaryArrayIthBit = Arrays.stream(array).map( elem -> isIthBitSet(elem,i) ? 1 : 0 ).toArray();
        int n = binaryArrayIthBit.length;
        long countZero = 0;
        long subarrayorzerocount = 0;
        for(int j = 0 ; j < n ; j++){
            if( binaryArrayIthBit[j] == 0 ) { countZero++; }
            else {
                subarrayorzerocount += (( 1L * countZero * (countZero+1)))/2L;
                countZero = 0;
            }
        }
        subarrayorzerocount += ((1L * countZero * (countZero+1)))/2L;
        long totalSubArrayOrOneCount = ((1L * n * (n+1))) / 2L;
        long subArrayOROneCount = totalSubArrayOrOneCount - subarrayorzerocount;
        return subArrayOROneCount;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }

    // Other Solution TC: O(N), SC: O(N)
    public int solve_simple(int[] A) {
        int n = A.length;
        int[] idx = new int[32];
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            long tmp = A[i - 1];
            for (int j = 0; j <= 31; ++j) {
                long pw = 1 << j;
                if ((tmp & pw) != 0) { //if jth bit is set
                    ans += pw * i; // add its contribution in ans for all subarrays ending at index i
                    idx[j] = i; // store the index for next elements
                } else if (idx[j] != 0) // if jth bit is not set
                {
                    ans += pw * idx[j]; // add its contribution in ans for all subarrays ending at index i using
                } // the information of last element having jth bit set
            }
        }
        return (int)(ans % 1000000007);
    }

    // Optimised Solution TC: O(N), SC: O(1)
    public int solve_optimised(int[] A) {
        long ans = 0;
        int n = A.length;
        long total=(1L*n*(n+1))/2L;
        int mod=1000000007;
        for (int i = 0; i < 32; i++) {
            long num = 0;
            int cnt=0;
            for(int j=0;j<n;j++)
            {
                int bit=A[j]&(1<<i);
                if(bit>0)
                {
                    num+=(1L*cnt*(cnt+1))/2L;
                    cnt=0;
                }else
                {
                    cnt+=1;
                }
            }
            num+=(1L*(cnt)*(cnt+1))/2L;
            long temp=(total-num);
            ans=(ans%mod+temp%mod*(1<<i)%mod)%mod;
        }
        return (int)((ans%mod+mod)%mod);
    }
}
