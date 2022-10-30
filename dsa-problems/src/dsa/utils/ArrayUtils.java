package dsa.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static void printArray(Integer[] array){
        System.out.print("\n");
        Arrays.stream(array).forEach(x -> System.out.print(x +","));
        System.out.print("\n");
    }

    public static void printArray(int[] array){
        System.out.print("\n");
        Arrays.stream(array).forEach(x -> System.out.print(x +","));
        System.out.print("\n");
    }

    public static int[][] toArray(List<List<Integer>> matrix){
        int[][] arr = matrix.stream().map(u -> u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
        return arr;
    }
}
