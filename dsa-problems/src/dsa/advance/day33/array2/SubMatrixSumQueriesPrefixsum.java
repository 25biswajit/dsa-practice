package dsa.advance.day33.array2;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SubMatrixSumQueriesPrefixsum {
//    @Test
//    public void testPrefixSumMatrix() {
//        int[][] matrix = {
//                {1, 2, 2, 1},
//                {2, 4, 1, 2},
//                {2, 2, 1, 2},
//                {2, 1, 1, 2}
//        };
//        int [][] prefixSumMatrix = prefixSumMatrix(matrix);
//        int [][] prefixSumRowColMatrix = prefixSumRowCol(matrix);
//        Assertions.assertTrue(MatrixUtils.isEqual(prefixSumMatrix,prefixSumRowColMatrix));
//    }

    @Test
    public void test1(){
        int[][] matrix = {
                { 1, 2, 2, 1 },
                { 2, 4, 1, 2 },
                { 2, 2, 1, 2 },
                { 2, 1, 1, 2 }
        };
        int[] query_top_left_X = {1,2,1};
        int[] query_top_left_Y = {2,2,2};
        int[] query_bottom_right_X = {4,3,3};
        int[] query_bottom_right_Y = {4,3,4};
        int[] result = solve(matrix,query_top_left_X,query_top_left_Y,query_bottom_right_X,query_bottom_right_Y);
        int[] expected = {21, 8, 17};
        Assertions.assertArrayEquals(expected,result);
        int[] resultOptimized = solveOptimised(matrix,query_top_left_X,query_top_left_Y,query_bottom_right_X,query_bottom_right_Y);
        Assertions.assertArrayEquals(expected,resultOptimized);
    }

    @Test
    public void test2(){
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        int[] query_top_left_X = {1,2};
        int[] query_top_left_Y = {1,2};
        int[] query_bottom_right_X = {2,3};
        int[] query_bottom_right_Y = {2,3};
        int[] result = solve(matrix,query_top_left_X,query_top_left_Y,query_bottom_right_X,query_bottom_right_Y);
        int[] expected = {12, 28};
        Assertions.assertArrayEquals(expected,result);
        int[] resultOptimized = solveOptimised(matrix,query_top_left_X,query_top_left_Y,query_bottom_right_X,query_bottom_right_Y);
        Assertions.assertArrayEquals(expected,resultOptimized);
    }

    //O(NM) + O(NQ)
    public int[] solve(int[][] matrix,int[] query_top_left_X,int[] query_top_left_Y,int[] query_bottom_right_X,int[] query_bottom_right_Y){
        ArrayList<Integer> results = new ArrayList<>();
        int[][] matrixPrefixSumRow = prefixSumRow(matrix);
        for(int i=0;i<query_top_left_X.length;i++){
            int tx=query_top_left_X[i]-1;
            int ty=query_top_left_Y[i]-1;
            int bx=query_bottom_right_X[i]-1;
            int by=query_bottom_right_Y[i]-1;
            int ans = getSubMatrixSumByQuery(tx,ty,bx,by,matrixPrefixSumRow);
            results.add(ans);
        }
        System.out.println(results);
        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    //O(NM) + O(Q)
    public int[] solveOptimised(int[][] matrix,int[] query_top_left_X,int[] query_top_left_Y,int[] query_bottom_right_X,int[] query_bottom_right_Y){
        ArrayList<Integer> results = new ArrayList<>();
            long[][] matrixPrefixSum = prefixSumMatrix(matrix);
        for(int i=0;i<query_top_left_X.length;i++){
            int tx=query_top_left_X[i]-1;
            int ty=query_top_left_Y[i]-1;
            int bx=query_bottom_right_X[i]-1;
            int by=query_bottom_right_Y[i]-1;
            int ans = (int)getSubMatrixSumByQueryOptimized(tx,ty,bx,by,matrixPrefixSum);
            results.add(ans);
        }
        System.out.println(results);
        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    public static long getSubMatrixSumByQueryOptimized(int tx, int ty, int bx, int by, long[][] psm){
        long biggerSubMatrix = psm[bx][by];
        long topSubMatrix = tx == 0 ? 0 : psm[tx-1][by] ;
        long leftSubMatrix = ty == 0 ? 0 : psm[bx][ty-1];
        long topCornerSubMatrix = tx == 0 || ty == 0 ? 0 : psm[tx-1][ty-1];
        long sum = (biggerSubMatrix - topSubMatrix - leftSubMatrix + topCornerSubMatrix)%1000000007;
        return sum;
    }


    public int getSubMatrixSumByQuery(int tx, int ty, int bx, int by, int[][] prefixSumRowMatrix){
        int sum = 0;
        for(int i = tx; i<= bx;i++){
            if(ty == 0){
                sum += prefixSumRowMatrix[i][by];
            }else{
                sum += prefixSumRowMatrix[i][by] - prefixSumRowMatrix[i][ty-1];
            }
        }
        return (int) sum;
    }

    private int[][] prefixSumRow(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] matrixPrefixSumRow = new int[row][col];

        for(int i = 0; i< matrix.length; i++){
            matrixPrefixSumRow[i][0] = matrix[i][0];
            for(int j = 1; j< matrix[i].length ; j++){
                matrixPrefixSumRow[i][j] = (matrixPrefixSumRow[i][j-1] + matrix[i][j])%1000000007;
            }
        }
        System.out.println("Row Wise Prefix Sum");
        MatrixUtils.printMatrix(matrixPrefixSumRow);
        return matrixPrefixSumRow;
    }

    private int[][] prefixSumCol(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] matrixPrefixSumCol = new int[row][col];

        for(int j = 0; j< col; j++){
            matrixPrefixSumCol[0][j] = matrix[0][j];
            for(int i = 1; i< row ; i++){
                matrixPrefixSumCol[i][j] = (matrixPrefixSumCol[i-1][j] + matrix[i][j])%1000000007;
            }
        }
        System.out.println("Col Wise Prefix Sum");
        MatrixUtils.printMatrix(matrixPrefixSumCol);
        return matrixPrefixSumCol;
    }

    private int[][] prefixSumRowCol(int[][] matrix){
        int[][] rowPrefixSum = prefixSumRow(matrix);
        int[][] colPrefixSum = prefixSumCol(rowPrefixSum);
        return colPrefixSum;
    }

    public static long[][] prefixSumMatrix(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;
        long[][] matrixPrefixSum = new long[row][col];

        matrixPrefixSum[0][0] = matrix[0][0];

        for(int j=1;j<col;j++){
            matrixPrefixSum[0][j] = (matrixPrefixSum[0][j-1] + matrix[0][j])%1000000007;
        }
        for(int i=1; i<row;i++){
            matrixPrefixSum[i][0] = (matrixPrefixSum[i-1][0] + matrix[i][0])%1000000007;
        }
        for(int i=1; i<row;i++){
            for(int j=1; j<col; j++){
                matrixPrefixSum[i][j] = (matrixPrefixSum[i-1][j] +matrixPrefixSum[i][j-1] -matrixPrefixSum[i-1][j-1] +matrix[i][j])%1000000007;
            }
        }
        //MatrixUtils.printMatrix(matrixPrefixSum);
        return matrixPrefixSum;
    }
}
