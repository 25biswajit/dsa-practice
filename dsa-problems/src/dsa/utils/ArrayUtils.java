package dsa.utils;

import java.util.Arrays;

public class ArrayUtils {

    public static void printArray(int[] array){
        System.out.print("\n");
        Arrays.stream(array).forEach(x -> System.out.print(x +","));
        System.out.print("\n");
    }
}
