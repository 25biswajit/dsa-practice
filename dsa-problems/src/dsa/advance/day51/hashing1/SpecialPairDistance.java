package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given Array consisting of N elements.
We call a pair of distinct indices in that array a special if elements at those indices in the array are equal.
A = [7, 1, 3, 4, 1, 7]
Here we have 2 options:
1. Values of A[1] and A[4] are both 1, so (1,4) is a special pair and |1-4|=3.
2. Values of  A[0] and A[5] are both 7, so (0,5) is a special pair and |0-5|=5.
Ans = the minimum possible distance is 3.
*/

public class SpecialPairDistance {
    @Test
    public void test(){
        int[] array = {7,1,3,4,1,7};
        Assertions.assertEquals(3 , minDistance(array));
    }

    // TC: O(N) , SC: O(N)
    public int minDistance(int[] array){
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        for(int i=0; i<array.length;i++){
            List<Integer> list = hashMap.getOrDefault(array[i], new ArrayList<>());
            list.add(i);
            hashMap.put(array[i], list);
        }
        for(Map.Entry<Integer, List<Integer>> entry: hashMap.entrySet()){
            List<Integer> indexes = entry.getValue();
            if(indexes.size() > 1){
                minDist = Integer.min( minDist , deriveMinDist(indexes));
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int deriveMinDist(List<Integer> indexes) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<indexes.size()-1;i++){
            min = Integer.min( min, indexes.get(i+1) - indexes.get(i));
        }
        return min;
    }
}
