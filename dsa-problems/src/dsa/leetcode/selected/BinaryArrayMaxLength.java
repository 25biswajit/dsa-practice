package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

//https://leetcode.com/problems/contiguous-array/
public class BinaryArrayMaxLength {

    @Test
    public void test1(){
        int[] arr = {0,1};
        Assertions.assertEquals(2, findMaxLength(arr));
        Assertions.assertEquals(2, findMaxLength_optimised(arr));
    }
    @Test
    public void test2(){
        int[] arr = {0,1,0,1};
        Assertions.assertEquals(4, findMaxLength(arr));
        Assertions.assertEquals(4, findMaxLength_optimised(arr));
    }
    @Test
    public void test3(){
        int[] arr = {0,0,1,0,1,0,1,1,0,0,1,1,1};
        Assertions.assertEquals(12, findMaxLength(arr));
        Assertions.assertEquals(12, findMaxLength_optimised(arr));
    }

    public int findMaxLength_optimised(int[] a) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0; i < a.length; i++){
            sum += a[i] == 0 ? -1 : a[i];
            if(map.containsKey(sum)){
                max = Integer.max(max, i - map.get(sum));
            }
            else{
                map.put(sum, i);
            }
        }
        return max;
    }

    public int findMaxLength(int[] a) {
        int max = 0;
        int n = a.length;
        int[] zero = new int[n];
        int[] one = new int[n];

        if(a[0] == 0){
            zero[0] = 1;
        }else{
            one[0] = 1;
        }

        for(int i = 1; i < n; i ++){
            if(a[i] == 0){
                zero[i] = zero[i-1]+1;
                one[i] = one[i-1];
            }else{
                one[i] = one[i-1]+1;
                zero[i] = zero[i-1];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int count0 = i == 0 ? zero[j] : zero[j] - zero[i-1];
                int count1 = i == 0 ? one[j] : one[j] - one[i-1];

                if(count0 == count1){
                    max = Integer.max(max, j-i+1);
                }
            }
        }

        return max;
    }
}
