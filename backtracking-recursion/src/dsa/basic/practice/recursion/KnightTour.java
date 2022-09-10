package dsa.basic.practice.recursion;

import org.junit.jupiter.api.Test;

//
public class KnightTour {

    @Test
    public void test(){
        int[][] chessboard = new int[6][6];
        main(chessboard);
    }

    private void main(int[][] chessboard) {
        knightTour(chessboard, 2, 2, 1);
    }

    void knightTour(int[][] chess, int row, int col, int moveCount){
        if(isNotSafePlace(chess,row,col) || chess[row][col] > 0){
            return;
        }

        if(moveCount == chess.length * chess.length){
            chess[row][col] = moveCount;
            print(chess);
            return;
        }

        chess[row][col] = moveCount;
        knightTour(chess, row-2, col+1, moveCount+1);
        knightTour(chess, row-1, col+2, moveCount+1);
        knightTour(chess, row+1, col+2, moveCount+1);
        knightTour(chess, row+2, col+1, moveCount+1);
        knightTour(chess, row+2, col-1, moveCount+1);
        knightTour(chess, row-1, col-2, moveCount+1);
        knightTour(chess, row-1, col-2, moveCount+1);
        knightTour(chess, row-2, col-1, moveCount+1);
        chess[row][col] = 0;
    }

    private boolean isNotSafePlace(int[][] chess, int row, int col) {
        return row <0 || col <0 || row >= chess.length || col >= chess[0].length;
    }

    private void print(int[][] chess) {
        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess[0].length;j++){
                System.out.print(chess[i][j] +" ");
            }
            System.out.println("\n");
        }
    }
}
