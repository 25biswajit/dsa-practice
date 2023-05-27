package dsa.advance.day79.dp2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
/*
The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of M x N rooms laid out in a 2D grid.
Our valiant knight was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer.
If at any point his health point drops to 0 or below, he dies immediately.
Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health so that he is able to rescue the princess.
*/

public class DungeonPrincess {
    Map<String, Integer> map = null;
    @Test
    public void test(){
        int[][] matrix = {{-1,-3,1},{-3,-4,4},{10,20,-2}};
        map = new HashMap<>();
        Assertions.assertEquals(5, dungeonPrincess(matrix));
    }

    // TC O(N*M) SC O(N*M)
    public int dungeonPrincess(int[][] matrix) {
        dungeonPrincess(0,0,matrix);
        return map.get("0-0");
    }

    private int dungeonPrincess(int row, int col, int[][] matrix){
        int rowLimit = matrix.length;
        int colLimit = matrix[0].length;
        String key = row +"-"+ col;
        if(row >= rowLimit || col >= colLimit){
            return Integer.MAX_VALUE;
        }
        else if(row == rowLimit-1 && col == colLimit-1){
            Integer minHealth = Math.max(1, 1-matrix[row][col]);
            map.put(key, minHealth);
            return minHealth;
        }
        else if(!map.containsKey(key)){
            Integer rightHealth = dungeonPrincess(row,col+1,matrix);
            Integer bottomHealth = dungeonPrincess(row+1,col,matrix);
            Integer minHealth = Math.max(1, Math.min(rightHealth, bottomHealth)-matrix[row][col]);
            map.put(key, minHealth);
        }
        return map.get(key);
    }
}

/*There are only 2 positions you can directly go to from i, j. (i+1, j) and (i, j + 1).
So if you knew the optimal path requirements for (i + 1, j) and (i, j + 1), you could choose the minimum of the two and be done with it.
Build the dp array, start from the bottom right cornor , let’s say hp[i][j] represents the min health point needed at position (i, j).
So, hp[i][j] = max(1, min(hp[i][j+1], hp[i+1][j]) - dungeaon[i][j])
The final answer value is stored at hp[0][0].*/
