package dsa.leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
/*
Given n nodes labeled from 1 to n and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
*/
public class NoConnectedComp {

    @Test
    public void test1(){
        int[][] edgeList = {{1, 2},{2, 3},{4, 5},{5, 6},{6, 4}, {7,7}};
        Assertions.assertEquals( 3, countConnectedComp(edgeList, 7));
    }

    private int countConnectedComp(int[][] edgeList, int n) {
        int count = 0;
        Map<Integer, List<Integer>> adjList = new HashMap();
        HashSet<Integer> visited = new HashSet<>();

        Arrays.stream(edgeList).forEach(e -> addEdge(e,adjList));

        for(int i = 1; i <= n; i ++) {
            if(!visited.contains(i)){
                dfs(i, adjList, visited);
                count++;
            }
        }

        return count;
    }
    public void addEdge(int [] edge, Map<Integer, List<Integer>> map){
        int u = edge[0], v = edge[1];
        map.putIfAbsent(u, new ArrayList<>());
        map.putIfAbsent(v, new ArrayList<>());
        map.get(u).add(v);
        map.get(v).add(u);
    }
    public void dfs(int node, Map<Integer, List<Integer>> map, HashSet<Integer> visited){
        visited.add(node);
        System.out.println("Visited = " + node);
        for(int x : map.get(node)){
            if(visited.add(x)){
                dfs(x, map, visited);
            }
        }
    }
}
