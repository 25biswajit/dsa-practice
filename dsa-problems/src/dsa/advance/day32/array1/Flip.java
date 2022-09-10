package dsa.advance.day32.array1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
You are given a binary string A(i.e., with characters 0 and 1) consisting of characters A1, A2, ..., AN. In a single operation, y
ou can choose two indices, L and R, such that 1 ≤ L ≤ R ≤ N and flip the characters AL, AL+1, ..., AR.
By flipping, we mean changing character 0 to 1 and vice-versa.
Your aim is to perform ATMOST one operation such that in the final string number of 1s is maximized.
If you don't want to perform the operation, return an empty array. Else, return an array consisting of two elements denoting L and R.
If there are multiple solutions, return the lexicographically smallest pair of L and R.
NOTE: Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.

A = "010"
We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
*/
// Indexing are 1 based ***
public class Flip {
    @Test
    public void test1(){
        int[] expected = {2,8};
        Assertions.assertArrayEquals(expected , flipOptimised("100010001"));
    }
    @Test
    public void test2(){
        int[] expected = {};
        Assertions.assertArrayEquals(expected , flipOptimised("111"));
    }
    @Test
    public void test3(){
        int[] expected = {2,6};
        Assertions.assertArrayEquals(expected , flipOptimised("100100111101"));
    }
    @Test
    public void test4(){
        int[] expected = {1,1};
        Assertions.assertArrayEquals(expected , flipOptimised("010"));
    }
    @Test
    public void test5(){
        int[] expected = {3,3};
        Assertions.assertArrayEquals(expected , flipOptimised("1101"));
    }
    // O(N)
    public int[] flipOptimised(String binaryString) {
        char[] strArr = binaryString.toCharArray();
        int n = strArr.length;
        int totalCountOfOne = getOneCount(strArr);
        int[] result = new int[0];
        if(totalCountOfOne == n){
            return result;
        }
        int[] auxArray = getAuxiliaryArray(strArr);
        int kadaneSResult[] = kadanes(auxArray,result);
        return kadaneSResult;
    }

    private int[] kadanes(int[] auxArray, int[] result) {
        result = new int[2];
        int startIndex = 0;
        int endIndex = 0;
        int sum_so_far = auxArray[0];
        int max_sum_so_far = sum_so_far;
        result[0] = startIndex+1;
        result[1] = endIndex+1;

        for(int i = 1; i < auxArray.length; i++){
            if( auxArray[i] > sum_so_far + auxArray[i]){
                sum_so_far = auxArray[i];
                startIndex = i;
            }else{
                sum_so_far = sum_so_far + auxArray[i];
            }
            if(sum_so_far > max_sum_so_far){
                max_sum_so_far = sum_so_far;
                endIndex = i;
                result[0] = startIndex+1;
                result[1] = endIndex+1;
            }
        }
        System.out.println("Start :" + result[0] + " End :" + result[1]);
        return result;
    }

    private int[] getAuxiliaryArray(char[] strArr) {
        int[] auxArray = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++){
            auxArray[i] = strArr[i] == '0' ? 1 : -1;
        }
        return auxArray;
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
