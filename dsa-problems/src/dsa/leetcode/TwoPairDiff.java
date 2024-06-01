package dsa.leetcode;


/*
Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.

Submitted & Accepted
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TwoPairDiff {

    @Test
    public void test1(){
        int[] arr = {3,1,4,1,5};
        int result = findPairs_MySolution(arr, 2);
        Assertions.assertEquals(2, result);

        result = findPairs_Map(arr, 2);
        Assertions.assertEquals(2, result);

        result = findPairs_BinarySearch(arr, 2);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void test2(){
        int[] arr = {1,3,1,4,5};
        int result = findPairs_MySolution(arr, 0);
        Assertions.assertEquals(1, result);

        result = findPairs_Map(arr, 0);
        Assertions.assertEquals(1, result);

        result = findPairs_BinarySearch(arr, 0);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void test3(){
        int[] arr = {1,2,3,4,5};
        int result = findPairs_MySolution(arr, 1);
        Assertions.assertEquals(4, result);

        result = findPairs_Map(arr, 1);
        Assertions.assertEquals(4, result);

        result = findPairs_BinarySearch(arr, 1);
        Assertions.assertEquals(4, result);
    }

    public int findPairs_MySolution(int[] nums, int k) {
        Set<Pair> out = new HashSet<>();
        Set<Integer> hs = new HashSet<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int j1 = nums[i] - k;
            if(hs.contains(j1)){
                out.add(new Pair(nums[i], j1));
            }


            j1 = nums[i] + k;
            if(hs.contains(j1)){
                out.add(new Pair(nums[i], j1));
            }

            hs.add(nums[i]);
        }
        System.out.println(out);
        return out.size();
    }

    public int findPairs_Map(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int result = 0;
        for (int i : map.keySet())
            if (k > 0 && map.containsKey(i + k) || k == 0 && map.get(i) > 1)
                result++;
        return result;
    }

    public int findPairs_BinarySearch(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;
        for (int i=0; i < n-1; i++){
            if(i!=0 && nums[i-1] == nums[i]) continue;
            int second = nums[i] + k;
            if(Arrays.binarySearch(nums, i+1, n, second) > 0){
                count++;
            }
        }
        return count;
    }
}

class Pair{
    int x;
    int y;

    Pair(int x1, int x2){
        this.x = Math.min(x1,x2);
        this.y = Math.max(x1,x2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pair(" + x +","+ y +')';
    }
}