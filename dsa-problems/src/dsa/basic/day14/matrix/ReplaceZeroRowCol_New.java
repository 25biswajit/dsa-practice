package dsa.basic.day14.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplaceZeroRowCol_New {

    @Test
    public void test1(){
        int[][] matrix = {{1,2,3,4},{5,6,7,0},{9,2,0,4}};
        int[][] matrix_expected = {{1,2,0,0},{0,0,0,0},{0,0,0,0}};
        matrix = replaceMatrixZero_MySolution(matrix);

        for(int i = 0; i < matrix.length; i++){
            Assertions.assertArrayEquals(matrix_expected[i], matrix[i]);
        }
    }

    @Test
    public void test2(){
        int[][] matrix = {{1,2,3,4},{5,6,7,0},{9,2,0,4}};
        int[][] matrix_expected = {{1,2,0,0},{0,0,0,0},{0,0,0,0}};
        matrix = replaceMatrixZero_Smart(matrix);

        for(int i = 0; i < matrix.length; i++){
            Assertions.assertArrayEquals(matrix_expected[i], matrix[i]);
        }
    }


    public int[][] replaceMatrixZero_MySolution(int[][] A) {
        List<Pair> list = new ArrayList<>();
        int r = A.length;
        int c = A[0].length;
        for(int i = 0; i < r; i++){
            for(int j = 0;j <c; j++){
                if(A[i][j] == 0){
                    list.add(new Pair(i,j));
                }
            }
        }

        if(list.size() == 0 || list.size() == r * c){
            return A;
        }

        for(Pair pair : list){
            // col wise
            for(int i = 0; i <r ; i++){
                A[i][pair.y] = 0;
            }

            // row wise
            for(int j = 0; j < c ; j++){
                A[pair.x][j] = 0;
            }

        }

        return A;
    }


    public int[][] replaceMatrixZero_Smart(int[][] A) {
        List<Pair> list = new ArrayList<>();
        int r = A.length;
        int c = A[0].length;
        for(int i = 0; i < r; i++){
            for(int j = 0;j <c; j++){
                if(A[i][j] == 0){
                    convertColumn(A, j);
                    convertRow(A, i);
                }
            }
        }

        for(int i = 0; i < r; i++){
            for(int j = 0;j <c; j++){
                if(A[i][j] == -1){
                    A[i][j] = 0;
                }
            }
        }

        return A;
    }

    private void convertRow(int[][] A, int i) {
        int c = A[i].length;
        for(int k = 0; k < c; k++){
            if(A[i][k] != 0){
                A[i][k] = -1;
            }
        }
    }

    private void convertColumn(int[][] A, int j) {
        int r = A.length;
        for(int k = 0; k < r; k++){
            if(A[k][j] != 0){
                A[k][j] = -1;
            }
        }
    }
}

class Pair{
    int x;
    int y;

    Pair(int x,int y){
        this.x = x;
        this.y = y;
    }
}
