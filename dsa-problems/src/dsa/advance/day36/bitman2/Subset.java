package dsa.advance.day36.bitman2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of distinct integers A, return all possible subsets.Also, the subsets should be sorted in ascending ( lexicographic ) order.
The list is not necessarily sorted.
*/


public class Subset {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println("Subsets are not sorted in ascending ( lexicographic ) order : Wrong Output");
        subsets_wrong_output(list);
        int[] array = {1, 2, 3};
        int[][] expected = {
                {},
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 3},
                {2},
                {2, 3},
                {3}
        };
        Assertions.assertArrayEquals(expected, subsets(array));
    }

    //Actual Solution
    List<List<Integer>> returnList = new ArrayList<>();

    public int[][] subsets(int[] A) {
        Arrays.sort(A);
        permutate(A, 0, new ArrayList<Integer>());
        return toArray();
    }

    private void permutate(int[] arr, int index, List<Integer> current) {
        returnList.add(current);
        for (int i = index; i < arr.length; i++) {
            List<Integer> tempList = new ArrayList<>(current);
            tempList.add(arr[i]);
            permutate(arr, i+1, tempList);
        }
    }

    private int[][] toArray() {
        int[][] array = new int[returnList.size()][];
        for (int i = 0; i < returnList.size(); i++) {
            List<Integer> row = returnList.get(i);
            int[] intRow = new int[row.size()];
            for (int g = 0; g < row.size(); g++) {
                intRow[g] = row.get(g);
            }
            array[i] = intRow;
        }
        return array;
    }

    // Problem with this approach is order is not lexicographic
    public ArrayList<ArrayList<Integer>> subsets_wrong_output(ArrayList<Integer> inputList) {
        int n = inputList.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (isIthBitSet(i, j)) {
                    list.add(inputList.get(j));
                }
            }
            System.out.println(list);
            result.add(list);
        }
        return result;
    }

    private boolean isIthBitSet(int num, int i) {
        return ((num >> i) & 1 ) == 1;
    }
}


