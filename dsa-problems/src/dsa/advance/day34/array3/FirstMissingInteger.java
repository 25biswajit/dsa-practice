package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

/*
Given an unsorted integer array, A of size N. Find the first missing positive integer.
Note: Your algorithm should run in O(n) time and use constant space.
*/
public class FirstMissingInteger {
    @Test
    public void test1(){
        int[] array = {8,-10,1,-3,2,5};
        Assertions.assertEquals(3 , firstMissingPositive_bruteforce_sort(array.clone()));
        Assertions.assertEquals(3 , firstMissingPositive_bruteforce_hashset(array));
        Assertions.assertEquals(3 , firstMissingPositive_send_home(array));
    }
    @Test
    public void test2(){
        int[] array = {6,5,3,1,4,2};
        Assertions.assertEquals(7 , firstMissingPositive_bruteforce_sort(array.clone()));
        Assertions.assertEquals(7 , firstMissingPositive_bruteforce_hashset(array));
        Assertions.assertEquals(7 , firstMissingPositive_send_home(array));
    }
    @Test
    public void test3(){
        int[] array = {8,10,2,1,-1,2,8,9};
        Assertions.assertEquals(3 , firstMissingPositive_bruteforce_sort(array.clone()));
        Assertions.assertEquals(3 , firstMissingPositive_bruteforce_hashset(array));
        Assertions.assertEquals(3 , firstMissingPositive_send_home(array));
    }
    @Test
    public void test4(){
        int[] array = {-5,-9,-8, 0};
        Assertions.assertEquals(1 , firstMissingPositive_bruteforce_sort(array.clone()));
        Assertions.assertEquals(1 , firstMissingPositive_bruteforce_hashset(array));
        Assertions.assertEquals(1 , firstMissingPositive_send_home(array));
    }

    public int firstMissingPositive_bruteforce_sort(int[] array) {
        Arrays.sort(array);
        int index = 0;
        while (index < array.length && array[index] < 0){
            index++;
        }
        int i = 1;
        while (index < array.length && i == array[index]){
            i++;
            index++;
        }
        return i;
    }
    public int firstMissingPositive_bruteforce_hashset(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        int index = 0;
        while (index < array.length){
            if(array[index] > 0){
                set.add(array[index]);
            }
            index++;
        }
        int i = 1;
        while (!set.isEmpty() && set.contains(i)){
            i++;
        }
        return i;
    }
    public int firstMissingPositive_send_home(int[] array) {
        int n = array.length;
        for(int i = 0; i < array.length ; i++){
            if(array[i] > n || array[i] <= 0 || array[i] == i+1 || array[i] == array[array[i]-1]){
                continue;
            }else {
                swap(array, i, array[i]-1);
                i--;
            }
        }

        for(int i = 0; i < array.length ; i++){
            if(array[i] != i+1){
                return i+1;
            }
        }
        return n+1;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
