package dsa.recursion;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Objects;

/*
* There is N X M matrix, filled with O & 1.
* One will start from (0,0) and ends at (N-1,M-1)
* One can move 1 step UP,DOWN,LEFT,RIGHT
* One can use only 0 marked cells, 1 marked cells are blocker
* In one path same cell can't be visited twice
* Print All possible path
*/
public class FloodFillPath {
    @Test
    public void test(){
        int[][] matrix = new int[5][4];
        matrix[0][1] = 1;
        matrix[3][0] = 1;
        matrix[3][2] = 1;
        matrix[1][1] = 1;

        FloodFillPath floodFillPath = new FloodFillPath();
        floodFillPath.main(matrix);
    }

    void main(int[][] matrix){
        String path = "";

        // My Solution - Disadvantage creating new set every time, high space complexity
        printAllPossibleFloodFillPath(matrix, new LinkedHashSet<Pointer>(), new Pointer(0,0), path);

        // Learnt Solution - Good Solution
        boolean[][] visitedCells = new boolean[matrix.length][matrix[0].length];
        printAllPossibleFloodFillPathNew(matrix, visitedCells, new Pointer(0,0), path);
    }

    private void printAllPossibleFloodFillPath(int[][] matrix,
                                               LinkedHashSet<Pointer> visitedCells,
                                               Pointer cell,
                                               String path) {
        // Print Path If Destination Reached
        if(isDestinationReached(cell, matrix)){
            System.out.println(path);
            return;
        }

        // Move top if possible else skip to next step
        Pointer cellAtTop = new Pointer(cell.x-1 , cell.y);
        if(isValidCell(matrix, visitedCells, cellAtTop)){
            visitedCells.add(cellAtTop);
            printAllPossibleFloodFillPath(matrix,new LinkedHashSet<>(visitedCells),cellAtTop,path+"T");
        }

        // Move down if possible else skip to next step
        Pointer cellAtDown = new Pointer(cell.x+1 , cell.y);
        if(isValidCell(matrix, visitedCells, cellAtDown)){
            visitedCells.add(cellAtDown);
            printAllPossibleFloodFillPath(matrix,new LinkedHashSet<>(visitedCells),cellAtDown,path+"D");
        }

        // Move Left if possible else skip to next step
        Pointer cellAtLeft = new Pointer(cell.x , cell.y-1);
        if(isValidCell(matrix, visitedCells, cellAtLeft)){
            visitedCells.add(cellAtLeft);
            printAllPossibleFloodFillPath(matrix,new LinkedHashSet<>(visitedCells),cellAtLeft,path+"L");
        }

        // Move Right if possible else skip to next step
        Pointer cellAtRight = new Pointer(cell.x , cell.y+1);
        if(isValidCell(matrix, visitedCells, cellAtRight)){
            visitedCells.add(cellAtRight);
            printAllPossibleFloodFillPath(matrix,new LinkedHashSet<>(visitedCells),cellAtRight,path+"R");
        }
    }

    private void printAllPossibleFloodFillPathNew(int[][] matrix, boolean[][] visitedCells, Pointer cell, String path) {

        if(!isValidAndNotVisitedCell(matrix, visitedCells, cell)){
            return;
        }

        // Print Path If Destination Reached
        if(isDestinationReached(cell, matrix)){
            System.out.println(path);
            return;
        }

        visitedCells[cell.x][cell.y] = true;

        Pointer cellAtTop = new Pointer(cell.x-1 , cell.y);
        printAllPossibleFloodFillPathNew(matrix,visitedCells,cellAtTop,path+"T");

        Pointer cellAtDown = new Pointer(cell.x+1 , cell.y);
        printAllPossibleFloodFillPathNew(matrix,visitedCells,cellAtDown,path+"D");

        Pointer cellAtLeft = new Pointer(cell.x , cell.y-1);
        printAllPossibleFloodFillPathNew(matrix,visitedCells,cellAtLeft,path+"L");

        Pointer cellAtRight = new Pointer(cell.x , cell.y+1);
        printAllPossibleFloodFillPathNew(matrix,visitedCells,cellAtRight,path+"R");

        // Most Important Step
        visitedCells[cell.x][cell.y] = false;
    }

    private boolean isValidCell(int[][] matrix,
                                LinkedHashSet<Pointer> visitedCells,
                                Pointer cell) {
        // Is the cell Out Of Boundary ?
        if(matrix.length-1 < cell.x || matrix[0].length-1 < cell.y || cell.x < 0 || cell.y < 0){
            return false;
        }
        // Is the cell already visited ?
        else if(visitedCells.contains(cell)){
            return false;
        }
        // Is it blocked ?
        else if(matrix[cell.x][cell.y] == 1){
            return false;
        }
        return true;
    }

    // Is last cell reached ?
    private boolean isDestinationReached(Pointer cell,int[][] matrix) {
        return matrix.length-1 == cell.x && matrix[0].length-1 == cell.y;
    }

    private boolean isValidAndNotVisitedCell(int[][] matrix, boolean[][] visitedCells, Pointer cell) {
        // Is the cell Out Of Boundary ?
        if(matrix.length-1 < cell.x || matrix[0].length-1 < cell.y || cell.x < 0 || cell.y < 0){
            return false;
        }
        // Is the cell already visited ?
        else if(visitedCells[cell.x][cell.y]){
            return false;
        }
        // Is it blocked ?
        else if(matrix[cell.x][cell.y] == 1){
            return false;
        }
        return true;
    }
}
class Pointer{
    Integer x;
    Integer y;

    public Pointer(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pointer{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pointer)) return false;
        Pointer pointer = (Pointer) o;
        return Objects.equals(x, pointer.x) && Objects.equals(y, pointer.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
