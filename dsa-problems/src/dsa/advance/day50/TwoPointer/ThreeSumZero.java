package dsa.advance.day50.TwoPointer;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*
Given an array A of N integers, are there elements a, b, c in S such that a + b + c = 0
Find all unique triplets in the array which gives the sum of zero.
A = [-1,0,1,2,-1,4]
Output: [ [-1,0,1], [-1,-1,2] ]
*/
public class ThreeSumZero {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, -4, 0, 0, 5, -5, 1, 0, -2, 4, -4, 1, -1, -4, 3, 4, -1, -1, -3));
        ArrayList<ArrayList<Integer>> result = threeSum_zero_withoutExtraSpace(list);
        Integer[][] expected = {{-5, 0, 5}, {-5, 1, 4}, {-4, -1, 5}, {-4, 0, 4}, {-4, 1, 3}, {-3, -2, 5}, {-3, -1, 4}, {-3, 0, 3},
                {-2, -1, 3}, {-2, 1, 1}, {-1, 0, 1}, {0, 0, 0}};
        Integer[][] actual = MatrixUtils.convertListToMatrix(result);
        Assertions.assertArrayEquals(expected, actual);

        result = threeSum_zero_extraSpace(list);
        actual = MatrixUtils.convertListToMatrix(result);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, -1, 1 , 0, 1 , 2 , 4, -1, 0, - 1, 1));
        ArrayList<ArrayList<Integer>> result = threeSum_zero_withoutExtraSpace(list);
        Integer[][] expected = {{-1, -1, 2}, {-1, 0, 1}, {0, 0, 0}};
        Integer[][] actual = MatrixUtils.convertListToMatrix(result);
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void test_bf(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, -1, 1 , 0, 1 , 2 , 4, -1, 0, - 1, 1));
        ArrayList<ArrayList<Integer>> result_bf = threeSum_zero_bruteForce(list);
        Integer[][] expected = {{-1, -1, 2}, {-1, 0, 1}, {0, 0, 0}};
        Integer[][] actual = MatrixUtils.convertListToMatrix(result_bf);
        Assertions.assertArrayEquals(expected, actual);
    }
    // TC: O(N^3) SC: O(N)
    public ArrayList<ArrayList<Integer>> threeSum_zero_bruteForce(ArrayList<Integer> list) {
        Collections.sort(list);
        HashSet<String> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                for(int k = j+1; k < list.size(); k++){
                    if(list.get(i)+list.get(j)+list.get(k) == 0){
                        Integer[] arr = new Integer[]{list.get(i),list.get(j),list.get(k)};
                        String key = arr[0]+"_"+arr[1]+"_"+arr[2];
                        if(!set.contains(key)){
                            result.add(new ArrayList<>(Arrays.asList(arr)));
                            set.add(key);
                        }
                    }
                }
            }
        }
        return result;
    }

    // TC: O(N^2) SC: O(N)
    public ArrayList<ArrayList<Integer>> threeSum_zero_extraSpace(ArrayList<Integer> list) {
        Collections.sort(list);
        HashSet<String> set = new HashSet<>();
        int n = list.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n ; i++) {
            int fixedElement = list.get(i);
            int minPointer = i + 1;
            int maxPointer = n - 1;
            while (minPointer < maxPointer && minPointer < n && maxPointer >= 0) {
                int currentSum = fixedElement + list.get(minPointer) + list.get(maxPointer);
                if (currentSum > 0) {
                    maxPointer--;
                } else if (currentSum < 0) {
                    minPointer++;
                } else {
                    Integer[] arr = new Integer[]{fixedElement, list.get(minPointer), list.get(maxPointer)};
                    String key = arr[0] + "_" + arr[1] + "_" + arr[2];
                    if (!set.contains(key)) {
                        result.add(new ArrayList<>(Arrays.asList(arr)));
                        set.add(key);
                    }
                    minPointer++;
                }
            }
        }
        System.out.println(result);
        return result;
    }

    // TC: O(N^2) SC: O(1)
    public ArrayList<ArrayList<Integer>> threeSum_zero_withoutExtraSpace(ArrayList<Integer> list) {
        Collections.sort(list);
        int n = list.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int low = 0; low < n - 2 ; low++) {
            int mid = low + 1;
            int high = n - 1;
            int sum = -list.get(low);
            if ( low > 0 && list.get(low).intValue() == list.get(low - 1).intValue()){
                continue;
            }
            while (mid < high) {
                int pointerSum = list.get(mid)+list.get(high);
                if(sum==pointerSum){
                    Integer[] arr = new Integer[]{list.get(low), list.get(mid), list.get(high)};
                    result.add(new ArrayList<>(Arrays.asList(arr)));
                    int prev = mid;
                    while (mid < high && list.get(mid).intValue() == list.get(prev).intValue())
                        mid++;
                }
                else if(pointerSum > sum){
                    high--;
                }
                else { // pointerSum < sum
                    mid++;
                }
            }
        }
        System.out.println(result);
        return result;
    }

}