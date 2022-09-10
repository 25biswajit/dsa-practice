package dsa.basic.practice.recursion;

import org.junit.jupiter.api.Test;

// 4 Queens needs to be placed into 4*4 Chess Board - Print the path
//0-1, 1-3, 2-0, 3-2
//0-2, 1-0, 2-3, 3-1
//O(N!)
public class NQueenProblem {

    @Test
    public void test(){
        NQueenProblem nQueenProblem = new NQueenProblem();
        nQueenProblem.m(4,4);
    }

    public void m(int rowChessBoard, int colChessBoard){
        int[][] chessboard = new int[rowChessBoard][colChessBoard];
        printNQueensPath(chessboard,0, "");
    }
    private void printNQueensPath(int[][] chessboard, int row, String pathSoFar){
        if(row == chessboard.length){
            System.out.println(pathSoFar.substring(0,pathSoFar.length()-2));
            return;
        }
        for(int col = 0;col < chessboard.length;col++){
            if(chessboard[row][col]==0 && isSafe(chessboard, row,col)) {
                chessboard[row][col] = 1;
                printNQueensPath(chessboard, row + 1, pathSoFar + " (" + row + "R ," + col + "C)->");
                chessboard[row][col] = 0;
            }
        }
    }

    private boolean isSafe(int[][] chessboard, int row, int col) {
        for(int i=row-1,j=col-1 ; i>=0 && j>=0; i--, j--){
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        for(int i=row-1,j=col ; i>=0; i--){
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        for(int i=row-1,j=col+1 ; i>=0 && j<chessboard[0].length; i--, j++){
            if(chessboard[i][j] == 1){
                return false;
            }
        }
        return true;
    }
}
