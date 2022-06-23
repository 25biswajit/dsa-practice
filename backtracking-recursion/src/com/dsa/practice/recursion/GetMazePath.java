package com.dsa.practice.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

// Count number of ways to reach destination in a Maze
// Let say there is a ( 3 X 3 ) 2D matrix , We need to find out all possible ways to move one point to another
// Starting Index = 00 ---> Destination Index 22
public class GetMazePath {
    @Test
    public void test(){
        GetMazePath algorithm = new GetMazePath();
        List<String> result1 = algorithm.getMazePath(0,0,2,2);
        List<String> result2 = algorithm.getMazePathNew(0,0,2,2);
        Assertions.assertArrayEquals(result1.toArray(), result2.toArray());
        result2.forEach(System.out::println);
    }

    // Learnt Solution
    List<String> getMazePath(int sourceX, int sourceY, int destX, int destY){
        //Base case
        if(sourceX == destX && sourceY == destY){
            List<String> blankStepList = new ArrayList<>();
            blankStepList.add("");
            return blankStepList;
        }

        List<String> paths = new ArrayList<>();
        // Logic
        if(sourceX < destX){
            List<String> verticalPath = getMazePath(sourceX+1,sourceY,destX,destY);
            for(String s : verticalPath){
                String message = String.format("V(%s,%s) ", sourceX,sourceY);
                paths.add(message+ s);
            }
        }
        if(sourceY < destY){
            List<String> horizontalPath = getMazePath(sourceX,sourceY+1,destX,destY);
            for(String s : horizontalPath){
                String message = String.format("H(%s,%s) ", sourceX,sourceY);
                paths.add(message+ s);
            }
        }
        return paths;
    }

    // My Solution
    List<String> getMazePathNew(int sourceX, int sourceY, int destX, int destY){
        //Base case
        if(sourceX > destX || sourceY > destY){
            return new ArrayList<>();
        }
        if(sourceX == destX && sourceY == destY){
            List<String> blankStepList = new ArrayList<>();
            blankStepList.add("");
            return blankStepList;
        }

        List<String> paths = new ArrayList<>();
        List<String> verticalPath = getMazePath(sourceX+1,sourceY,destX,destY);
        for(String s : verticalPath){
            String message = String.format("V(%s,%s) ", sourceX,sourceY);
            paths.add(message+ s);
        }

        List<String> horizontalPath = getMazePath(sourceX,sourceY+1,destX,destY);
        for(String s : horizontalPath){
            String message = String.format("H(%s,%s) ", sourceX,sourceY);
            paths.add(message+ s);
        }
        return paths;
    }
}
