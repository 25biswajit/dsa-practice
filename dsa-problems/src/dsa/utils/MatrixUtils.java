package dsa.utils;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    public static void printMatrix(int[][] matrix) {
        System.out.println("Matrix Print :");
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    public static void printMatrix(String[][] matrix) {
        System.out.println("Matrix Print :");
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    public static void printMatrix(long[][] matrix) {
        System.out.println("Matrix Print :");
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
        }
    }

    public static Integer[][] convertListToMatrix(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<ArrayList<Integer>> listMatrix = new ArrayList<>();
        for (List<Integer> list : lists) {
            listMatrix.add(new ArrayList<>(list));
        }
        Integer[][] matrix = listMatrix.stream().map(u -> u.toArray(new Integer[0])).toArray(Integer[][]::new);
        return matrix;
    }

    public static boolean isEqual(int[][] b, int[][] a) {
        int row1 = a.length;
        int col1 = a[0].length;
        int row2 = b.length;
        int col2 = b[0].length;
        if (row1 != row2 || col1 != col2) {
            return false;
        }
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
