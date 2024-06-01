package dsa.leetcode;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicateInArray_0_N_1 {

    @Test
    public void test1(){
        int[] arr = {8,7,1,10,17,15,18,11,16,9,19,12,5,14,3,4,2,13,18,18};
        int ans = findDuplicate(arr);
        Assertions.assertEquals(18, ans);
    }

    @Test
    public void test2(){
        int[] arr = {1, 2, 3, 6, 3, 6, 1};
        int[] ans = findDuplicateGFG(arr);
        int[] expected = {1,3,6};
        Assertions.assertArrayEquals(expected, ans);
    }

    @Test
    public void test3(){
        int[] arr = {4, 3, 2, 7, 0, 2, 3, 1};
        int[] ans = findDuplicateGFG(arr);
        int[] expected = {2,3,6};
        Assertions.assertArrayEquals(expected, ans);
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int repeat = -1;
        for(int i=0; i<n ;i++){
            int ai = nums[i];
            if(ai-1 == i) continue;
            else if(ai == nums[ai-1]){
                repeat = ai;
                break;
            }
            else{
                swap(i, ai - 1, nums);
                i--;
            }
        }
        return repeat;
    }

    private void swap(int i, int j, int[] a){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public int[] findDuplicateGFG_(int[] nums) {
        int n = nums.length;
        List<Integer> repeat = new ArrayList<>();
        for(int i=0; i<n ;i++){
            int ai = nums[i];
            if(ai-1 == i) continue;
            else if(ai == nums[ai-1]){
                repeat.add(ai);
            }
            else{
                swap(i, ai - 1, nums);
                i--;
            }
        }
        System.out.println(repeat);
        return repeat.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] findDuplicateGFG(int[] a) {
        int n = a.length;
        List<Integer> repeat = new ArrayList<>();
        for(int i=0; i<n ;i++){
            a [ a[i]%n ] += n;
        }
        ArrayUtils.printArray(a);
        for(int i=0; i<n ;i++){
            if(a[i]/n > 1){
                repeat.add(i);
            }
        }
        System.out.println(repeat);
        return repeat.stream().mapToInt(Integer::intValue).toArray();
    }
}
