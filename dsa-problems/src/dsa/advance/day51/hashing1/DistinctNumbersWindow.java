package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of count of distinct numbers in all windows of size B.
NOTE: if B > N, return an empty array.
A=[1, 2, 1, 3, 4, 3] and B = 3
All windows of size B are
[1, 2, 1]
[2, 1, 3]
[1, 3, 4]
[3, 4, 3]
So, we return an array [2, 3, 3, 2].
*/

public class DistinctNumbersWindow {
    @Test
    public void test(){
        int[] array = {1, 2, 1, 3, 4, 3};
        int[] expected = {2, 3, 3, 2};
        Assertions.assertArrayEquals(expected, deriveDistinctNumbers(array,3));
    }

    //TC: O(N), SC: O(N)
    public int[] deriveDistinctNumbers(int[] array, int window){
        if(window > array.length){
            return new int[0];
        }
        List<Integer> ansList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i<window; i++){
            map.put(array[i], map.getOrDefault(array[i], 0)+1);
        }
        ansList.add(map.size());
        int p1 = 1;
        int p2 = window;
        while (p2 < array.length){
            int preKey = array[p1-1];
            int postKey = array[p2];
            if(map.containsKey(preKey)){
                if(map.get(preKey)==1) { map.remove(preKey); }
                else { map.put(preKey, map.get(preKey)-1); }
            }
            map.put(postKey, map.getOrDefault(postKey, 0)+1);
            ansList.add(map.size());
            p1++;
            p2++;
        }
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
}
