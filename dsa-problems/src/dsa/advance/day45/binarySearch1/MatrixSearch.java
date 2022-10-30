package dsa.advance.day45.binarySearch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixSearch {
    @Test
    public void test(){
        int[][] matrix = {{1, 3, 5, 7},{10, 11, 16, 20}, {23, 30, 34, 50}};
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i< row; i++){
            for( int j = 0; j< col; j++){
                int key = matrix[i][j];
                Assertions.assertEquals(1, searchMatrix(matrix, key));
            }
        }
        Assertions.assertEquals(0, searchMatrix(matrix, 100));
    }

    public int searchMatrix(int[][] matrix, int key) {
        int row = matrix.length;
        int col = matrix[0].length;
        int low = 0;
        int high = derivePosition(row-1,col-1, col);
        while (low <= high){
            int mid = (low + high) / 2;
            int midValue = getValueByPosition(matrix, mid);
            if (midValue == key){
                return 1;
            }
            else if(midValue > key){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return 0;
    }
    private int derivePosition(int x,int j, int col){
        return x * col + j;
    }
    private int getValueByPosition(int[][] matrix, int position){
        int col = matrix[0].length;
        return matrix[position / col][position % col];
    }
}
