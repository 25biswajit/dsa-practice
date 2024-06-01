package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//Minimum Number of Arrows to Burst Balloons
public class ArrowBurstBalloonsGreedy {

    @Test
    public void test1(){
        int[][] array = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        Assertions.assertEquals(2, findMinArrowShots(array));
    }
    @Test
    public void test2(){
        int[][] array = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        Assertions.assertEquals(2, findMinArrowShots(array));
    }

    public int findMinArrowShots(int[][] points) {
        //Arrays.sort(points, (a, b) -> a[1]-b[1]);
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int end = points[0][1];
        int arrow = 1;
        for(int i = 1; i < points.length; i++){
            if(end >= points[i][0]) continue;
            else {
                end = points[i][1];
                arrow++;
            }
        }
        return arrow;
    }
}
