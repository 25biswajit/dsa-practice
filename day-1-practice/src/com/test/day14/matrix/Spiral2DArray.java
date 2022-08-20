package com.test.day14.matrix;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Spiral2DArray {
    @Test
    public void test(){
        generateMatrix(5);
    }

    public ArrayList<ArrayList<Integer>> generateMatrix(int A) {
        Integer[][] result = new Integer[A][A];
        Integer c = 1;
        int minrow = 0;
        int mincol = 0;
        int maxrow = result.length-1;
        int maxcol = result.length-1;
        while(c <= A*A){
            // Upper wall
            for(int i=minrow,j=mincol;j<=maxcol;j++){
                result[i][j] = c;
                c++;
            }
            minrow++;
            // Right wall
            for(int i=minrow,j=maxcol;i<=maxrow;i++){
                result[i][j] = c;
                c++;
            }
            maxcol--;
            // Bottom wall
            for(int i=maxrow,j=maxcol;j>=mincol;j--){
                result[i][j] = c;
                c++;
            }
            maxrow--;
            // Left wall
            for(int i=maxrow,j=mincol;i>=minrow;i--){
                result[i][j] = c;
                c++;
            }
            mincol++;
        }
        print(result);
        return convertArrayToList(result);
    }

    public ArrayList<ArrayList<Integer>> convertArrayToList(Integer[][] twoDArray) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (Integer[] array : twoDArray) {
            list.addAll(new ArrayList(Arrays.asList(array)));
        }
        return list;
    }

    public void print(Integer[][] res){
        int n = res.length;
        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                System.out.print(" " + res[i][j] +" ");
            }
            System.out.println();
        }
    }
}

