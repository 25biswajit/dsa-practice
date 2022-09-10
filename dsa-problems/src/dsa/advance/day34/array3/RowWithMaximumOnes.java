package dsa.advance.day34.array3;

/*
Given a binary sorted matrix A of size N x N. Find the row with the maximum number of 1.
A = [   [0, 1, 1]
         [0, 0, 1]
         [0, 1, 1]   ]
Output : 0 th row
Expected time complexity is O(rows).
If two rows have the maximum number of 1 then return the row which has a lower index.
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RowWithMaximumOnes {
    @Test
    public void test1(){
        int[][] matrix = {{0, 1, 1},{0, 0, 1},{0, 1, 1}};
        Assertions.assertEquals(0, getRowWithMaxOneCount(matrix));
    }
    @Test
    public void test2(){
        int[][] matrix = {{0, 0, 0, 0},{1, 1, 1, 1}};
        Assertions.assertEquals(1, getRowWithMaxOneCount(matrix));
    }

    public int getRowWithMaxOneCount(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int maxOne = 0;
        int resultRow = -1;
        for(int i = 0 ; i<row ; i++){
            int oneCount = 0;
            for(int j = col-1; j >= 0 ; j--){
                if(matrix[i][j]==1){
                    ++oneCount;
                    if(maxOne < oneCount){
                        maxOne = oneCount;
                        resultRow = i;
                    }
                    if(maxOne == col){
                        return resultRow;
                    }
                }
                else {
                    break;
                }
            }
        }
        return resultRow;
    }
}
