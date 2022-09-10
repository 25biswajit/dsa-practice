package dsa.basic.day14.matrix;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TransposeMatrix {
    @Test
    public void test(){
        int[][] arr = { {1,2,3},{4,5,6},{7,8,9} };
        transpose(arr);
        printMatrix(arr);

        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(10,20,30));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(40,50,60));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(70,80,90));

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(l1);
        matrix.add(l2);
        matrix.add(l3);

        solve(matrix);
        int[][] intArray = matrix.stream().map(u -> u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
        printMatrix(intArray);
    }

    public int[][] transpose(int[][] arr){
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j) break;
                swap(arr, i, j);
            }
        }
        return arr;
    }

    /*public void swap(int[][] arr, int i, int j){
        int temp = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = temp;
    }*/

    public void swap(int[][] arr, int i, int j){
        int temp = arr[i][j];
        arr[i][j] = arr[j][i];
        arr[j][i] = temp;
    }

    public void printMatrix(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public void printMatrix(Integer[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j) break;
                swap(A, i, j);
            }
        }
        return A;
    }

    public void swap(ArrayList<ArrayList<Integer>> A, int i, int j){
        int temp = A.get(i).get(j);
        A.get(i).set(j, A.get(j).get(i));
        A.get(j).set(i, temp);
    }
}
