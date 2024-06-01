package dsa.basic.day14.matrix;

import org.junit.jupiter.api.Test;

public class Diagonal {

    @Test
    public void test(){
        int[][] a = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        diagonal(a);
    }



    public int[][] diagonal(int[][] A) {
        int n = A.length;
        int[][] o = new int[n*2-1][n];

        int i = 0, r = 0;
        for(int j = 0; j < n; j++){
            diagonal(A, i , j, o, r);
            r++;
        }
        int j = n-1;
        for(i = 1; i < n; i++){
            diagonal(A, i , j, o, r);
            r++;
        }
        return o;
    }

    private void diagonal(int[][] A, int i, int j, int[][] o, int r){
        int n = A.length;
        int c = 0;
        while(i >=0 && i<n && j>=0 && j<n){
            o[r][c] = A[i][j];
            c++;
            i++;
            j--;
        }
    }
}
