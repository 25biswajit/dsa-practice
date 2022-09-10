package dsa.advance.day33.array2;

public class RotateMatrixBy90Degree {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n/2 ; i++){
            for(int j = i; j < n-i-1;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][n-i-1];
                matrix[j][n-i-1] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[n-j-1][i];
                matrix[n-j-1][i] = temp;
            }
        }
    }
}
