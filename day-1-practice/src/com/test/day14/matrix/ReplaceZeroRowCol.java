package com.test.day14.matrix;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ReplaceZeroRowCol {

    @Test
    public void test(){
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3,4));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(5,6,0,0));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(9,2,0,4));

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(l1);
        matrix.add(l2);
        matrix.add(l3);

//        int[][] intArray1 = matrix.stream().map(u -> u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
//        printMatrix(intArray1);

        solve(matrix);
        printMatrix(matrix);
    }


    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int m = A.get(0).size();
        for(int i=0; i< n; i++){
            for(int j=0; j< m; j++){
                if( A.get(i).get(j) == 0){
                    markrow(A, i);
                    markcol(A, j);
                }
            }
        }
        for(int i=0; i< n; i++){
            for(int j=0; j< m; j++){
                if( A.get(i).get(j) == -1){
                    A.get(i).set(j, 0);
                }
            }
        }
        return A;
    }

    private void markrow(ArrayList<ArrayList<Integer>> A, int row){
        int m = A.get(0).size();
        for(int j=0; j< m; j++){
            if(A.get(row).get(j)!=0){
                A.get(row).set(j, -1);
            }
        }
    }
    private void markcol(ArrayList<ArrayList<Integer>> A, int col){
        int n = A.size();
        for(int i=0; i< n; i++){
            if(A.get(i).get(col)!=0) {
                A.get(i).set(col, -1);
            }
        }
    }

    public void printMatrix(ArrayList<ArrayList<Integer>> matrix){
        int[][] arr = matrix.stream().map(u -> u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
