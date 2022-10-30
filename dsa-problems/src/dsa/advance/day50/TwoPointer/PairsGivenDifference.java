package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

// How to solve without extra space ?
public class PairsGivenDifference {
    @Test
    public void test1(){
        int[] arr = {1, 1, 1, 2, 2};
        Assertions.assertEquals(2, pairDiffCount_bruteForce(arr,0));
        Assertions.assertEquals(2, pairDiffCount_optimised(arr,0));
    }
    @Test
    public void test2(){
        int[] arr = {8, 12, 16, 4, 0, 20};
        Assertions.assertEquals(5, pairDiffCount_bruteForce(arr,4));
        Assertions.assertEquals(5, pairDiffCount_optimised(arr,4));
    }
    @Test
    public void test3(){
        int[] arr = {1, 5, 3, 4, 2};
        Assertions.assertEquals(2, pairDiffCount_bruteForce(arr,3));
        Assertions.assertEquals(2, pairDiffCount_optimised(arr,3));
    }
    @Test
    public void test4(){
        int[] arr = {1, 2};
        Assertions.assertEquals(0, pairDiffCount_bruteForce(arr,0));
        Assertions.assertEquals(0, pairDiffCount_optimised(arr,0));
    }

    public int pairDiffCount_bruteForce(int[] array, int B){
        int diffCount = 0;
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i< array.length; i++){
            for(int j = i+1; j < array.length; j++){
                if(Math.abs(array[i]-array[j])==B){
                    if(set.contains(array[i]+"_"+array[j]) || set.contains(array[j]+"_"+array[i])){
                        continue;
                    }else {
                        diffCount++;
                        set.add(array[i]+"_"+array[j]);
                    }
                }
            }
        }
        return diffCount;
    }

    // TC: O(N log N) + O(N) SC: O(N)
    public int pairDiffCount_optimised(int[] array, int B){
        Arrays.sort(array);
        int diffCount = 0;
        HashSet<String> set = new HashSet<>();
        int p2 = array.length-1;
        int p1 = array.length-2;
        while (p1 >= 0){
            if(p1 == p2){
                p1--;
                continue;
            }
            int pairDiff = Math.abs(array[p1]-array[p2]);
            if(pairDiff > B){
                p2--;
            }
            else if(pairDiff < B){
                p1--;
            }
            else {
                if(set.contains(array[p1]+"_"+array[p2]) || set.contains(array[p1]+"_"+array[p2])){
                    // DO Nothing
                }else {
                    diffCount++;
                    set.add(array[p1]+"_"+array[p2]);
                }
                p1--;
            }
        }
        return diffCount;
    }


}
