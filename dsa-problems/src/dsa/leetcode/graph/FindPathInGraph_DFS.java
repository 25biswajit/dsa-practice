package dsa.leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class FindPathInGraph_DFS {

    @Test
    public void test1(){
        int[][] edgeList = {{0, 1},{0, 2},{3, 5},{5, 4},{4, 3}, {1,4}};
        Assertions.assertTrue( validPath(edgeList,0,5));
    }
    @Test
    public void test2(){
        int[][] edgeList = {{0, 1},{0, 2},{3, 5},{5, 4},{4, 3}};
        Assertions.assertFalse( validPath(edgeList,0,5));
    }
    public boolean validPath(int[][] edgeList, int src, int dest) {
        if(src == dest) return true;
        Map<Integer, List<Integer>> adjList = new HashMap();
        HashSet<Integer> visited = new HashSet<>();

        Arrays.stream(edgeList).forEach(e -> addEdge(e,adjList));
        dfs(src, adjList, visited);

        return visited.contains(dest);

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
