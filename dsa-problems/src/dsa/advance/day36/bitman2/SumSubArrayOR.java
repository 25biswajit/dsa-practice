package dsa.advance.day36.bitman2;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SumSubArrayOR {
    @Test
    public void test(){
        int[] array = {5,8,26,13,5,21};
        Assertions.assertEquals(490, sumSubArrayOR_bruteforce(array));
        Assertions.assertEquals(490, sumSubArrayOR_optimized(array));
    }

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

    public int sumSubArrayOR_optimized(int[] array){
        int sum = 0;
        for(int i = 0 ; i < 32; i++){
            int count_subArray_OR_one_ith_bit = getCountOfSubArrayOR_One_IthBit(array, i);
            System.out.println("I:" + i + " count_subArray_OR_one_ith_bit :" + count_subArray_OR_one_ith_bit);
            int contribution_ith_bit = count_subArray_OR_one_ith_bit * (1 << i);
            sum = sum + contribution_ith_bit;
        }
        return sum;
    }

    private int getCountOfSubArrayOR_One_IthBit(int[] array, int i) {
        int[] binaryArrayIthBit = Arrays.stream(array).map( elem -> isIthBitSet(elem,i) ? 1 : 0 ).toArray();
        ArrayUtils.printArray(binaryArrayIthBit);
        int n = binaryArrayIthBit.length;
        int countZero = 0;
        int subarrayorzerocount = 0;
        for(int j = 0 ; j < n ; j++){
            if( binaryArrayIthBit[j] == 0 ) { countZero++; }
            else {
                subarrayorzerocount += (countZero * (countZero+1))/2;
                countZero = 0;
            }
        }
        subarrayorzerocount += (countZero * (countZero+1))/2;
        int totalSubArrayOrOneCount = (n * (n+1)) / 2;
        int subArrayOROneCount = totalSubArrayOrOneCount - subarrayorzerocount;
        return subArrayOROneCount;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }
}
