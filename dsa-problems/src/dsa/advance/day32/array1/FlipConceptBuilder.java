package dsa.advance.day32.array1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a binary string A(i.e., with characters 0 and 1)
By flipping, we mean changing character 0 to 1 and vice-versa, we need to maximize the count of 1 & ATMOST one flip is allowed
A = "100010001" --> flip(1,7) --> "111101111"
*/

public class FlipConceptBuilder {
    @Test
    public void test1(){
        Assertions.assertEquals(8 , flipBruteForce("100010001"));
        Assertions.assertEquals(8 , flipOptimised("100010001"));
    }
    @Test
    public void test2(){
        Assertions.assertEquals(3 , flipOptimised("111"));
        Assertions.assertEquals(3 , flipBruteForce("111"));
    }
    @Test
    public void test3(){
        Assertions.assertEquals(10 , flipOptimised("100100111101"));
        Assertions.assertEquals(10 , flipBruteForce("100100111101"));
    }
    @Test
    public void test4(){
        Assertions.assertEquals(2 , flipOptimised("010"));
        Assertions.assertEquals(2 , flipBruteForce("010"));
    }

    // O(N)
    public int flipOptimised(String binaryString) {
        char[] strArr = binaryString.toCharArray();
        int n = strArr.length;
        int totalCountOfOne = getOneCount(strArr);
        if(totalCountOfOne == n){
            return totalCountOfOne;
        }
        int[] auxArray = getAuxiliaryArray(strArr);
        int maxContribution = kadanes(auxArray);
        return totalCountOfOne + maxContribution;
    }

    private int kadanes(int[] auxArray) {
        int sum_so_far = auxArray[0];
        int max_sum_so_far = sum_so_far;
        for(int i = 1; i < auxArray.length; i++){
            sum_so_far = Math.max(sum_so_far+auxArray[i], auxArray[i]);
            max_sum_so_far = Math.max(sum_so_far, max_sum_so_far);
        }
        return max_sum_so_far;
    }

    private int[] getAuxiliaryArray(char[] strArr) {
        int[] auxArray = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++){
            auxArray[i] = strArr[i] == '0' ? 1 : -1;
        }
        return auxArray;
    }

    // Time Limit Exceeds - O(N^2)
    public int flipBruteForce(String binaryString) {
        int maxOneCount = 0;
        char[] strArr = binaryString.toCharArray();
        int n = strArr.length;
        int totalCountOfOne = getOneCount(strArr);
        if(totalCountOfOne == n){
            return totalCountOfOne;
        }
        for(int i = 0; i < n; i++){
            int countOne = 0;
            int countZero = 0;
            for(int j = i ; j < n; j++){
                if(strArr[j] == '0') countZero++;
                else countOne++;
                int currentOneCount = totalCountOfOne - countOne + countZero;
                maxOneCount = Math.max(currentOneCount,maxOneCount);
            }
        }
        return maxOneCount;
    }

    private int getOneCount(char[] strArr) {
        int countOne = 0;
        for(char c : strArr){
            if(c == '1'){
                countOne++;
            }
        }
        return countOne;
    }


}
