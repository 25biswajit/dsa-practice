package com.test.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ValidSudoku {
    @Test
    public void test1(){
        List<String> board = new ArrayList<>();
        board.add("53..7....");
        board.add("6..195...");
        board.add(".98....6.");
        board.add("8...6...3");
        board.add("4..8.3..1");
        board.add("7...2...6");
        board.add(".6....28.");
        board.add("...419..5");
        board.add("....8..79");
        Assertions.assertEquals(1, isValidSudoku(board));
    }
    @Test
    public void test2(){
        List<String> board = new ArrayList<>();
        board.add("53..7....");
        board.add("6..195...");
        board.add(".98....6.");
        board.add("8...6...3");
        board.add("4..8.3..1");
        board.add("7...2...6");
        board.add(".6....28.");
        board.add("...419..5");
        board.add("....8..76");
        Assertions.assertEquals(0, isValidSudoku(board));
    }

    public int isValidSudoku(final List<String> board) {
        HashSet<String> visitedSet = new HashSet<>();
        for(int i=0;i<board.size();i++){
            String[] line = board.get(i).split("");
            for(int j=0;j<line.length;j++){
                String element = line[j];
                if(!element.equals(".")){
                    String rowElem = "ROW"+"_"+i+"_"+element;
                    String colElem = "COL"+"_"+j+"_"+element;
                    int box = (i/3)*3 + j/3;
                    String boxElem = "BOX"+"_"+box+"_"+element;
                    if(visitedSet.contains(rowElem) || visitedSet.contains(colElem) || visitedSet.contains(boxElem)){
                        return 0;
                    }
                    visitedSet.add(rowElem);
                    visitedSet.add(colElem);
                    visitedSet.add(boxElem);
                }
            }
        }
        return 1;
    }
}
