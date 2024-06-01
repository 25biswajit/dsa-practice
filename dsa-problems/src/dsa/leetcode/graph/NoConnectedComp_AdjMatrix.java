package dsa.leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*
547. Number of Provinces

*/
public class NoConnectedComp_AdjMatrix {
    @Test
    public void test1(){
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        Assertions.assertEquals( 2, countConnectedComp(isConnected));
    }

    public int countConnectedComp(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];

        // Moving row wise
        for(int i = 0; i < n; i++) {
            if(!visited[i]){
                dfs(i, isConnected, visited);
                count++;
            }
        }

        return count;
    }

    public void dfs(int currentNode, int[][] isConnected, boolean[] visited){
        int n = isConnected.length;
        visited[currentNode] = true;

        // Moving col wise & checking Are they connected
        for(int j = 0; j < n; j++){
            if(isConnected[currentNode][j] == 1 && !visited[j]){
                dfs(j, isConnected, visited);
            }
        }
    }
}
