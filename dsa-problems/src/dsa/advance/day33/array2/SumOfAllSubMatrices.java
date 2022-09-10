package dsa.advance.day33.array2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SumOfAllSubMatrices {
    @Test
    public void test(){
        int[][] matrix = {
                { 9 , 6 },
                { 5 , 4 }
        };
        Assertions.assertEquals(sum(matrix), 96);
    }

    public int sum(int[][] matrix){
        int n = matrix.length;
        int sum = 0;
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<n ; j++){
                sum += matrix[i][j] * (i+1) * (j+1) * (n-i) * (n-j);
            }
        }
        return sum;
    }
}
