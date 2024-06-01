package dsa.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static void printArray(Integer[] array){
        System.out.print("\n");
        Arrays.stream(array).forEach(x -> System.out.print(x +","));
        System.out.print("\n");
    }

    public static void printArray(int[] array){
        Arrays.stream(array).forEach(x -> System.out.print(x +","));
        System.out.print("\n");
    }

    public static int[][] toArray(List<List<Integer>> matrix){
        int[][] arr = matrix.stream().map(u -> u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
        return arr;
    }

    public static Integer[] convertToIntegerArray(int[] array){
        return Arrays.stream( array ).boxed().toArray( Integer[]::new );
    }

    public static int[] convertToIntArray(Integer[] array){
        return Arrays.stream(array).mapToInt(Integer::intValue).toArray();
    }

    public static int[] convertToIntArray(List<Integer> list){
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    public static int[] convertToIntArray(ArrayList<Integer> list){
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static Integer[] convertToIntegerArray(List<Integer> list){
        return list.stream().toArray(Integer[]::new);
    }
}
