package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

/*
Given (x,y) coordinate in a 2D Matrix, Find and return the number of unique points
A = [[5, 6],
     [2, 8],
     [-1, -1],
     [2, -3],
     [2, 8],
     [7, 7],
     [2, -3]]
Ans : 5
*/
public class Unique2DPoints {
    @Test
    public void test(){
        int[][] array = {{5, 6},{2, 8},{-1, -1},{2, -3},{2, 8},{7, 7},{2, -3}};
        Assertions.assertEquals(5, countUniquePoints(array));
    }

    // TC: O(N) , SC: O(N)
    public int countUniquePoints(int[][] array) {
        HashSet<String> set = new HashSet<>();
        for(int[] points : array){
            set.add(points[0]+"_"+points[1]);
        }
        return set.size();
    }

}
