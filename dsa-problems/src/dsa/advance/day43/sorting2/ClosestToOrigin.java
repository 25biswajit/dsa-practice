package dsa.advance.day43.sorting2;

import dsa.utils.MatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ClosestToOrigin {
    @Test
    public void test1(){
        int[][] points = {{1, 3},{-2, 2}};
        int[][] expected = {{-2, 2}};
        Assertions.assertArrayEquals(expected, solve(points,1));
    }
    @Test
    public void test2(){
        int[][] points = {{1, 3},{-2, 2}};
        int[][] expected = {{-2, 2},{1, 3}};
        Assertions.assertArrayEquals(expected, solve(points,2));
    }

    @Test
    public void test3(){
        int[][] points = {{48, 18},{46, 34},{47, 30},{19, 9},{1, 39},{95, 77},{95, 106},{78, 75},{92, 108},{89, 83},{80, 76}};
        int[][] expected = {{19, 9},{1, 39},{48, 18},{47, 30},{46, 34}};
        Assertions.assertArrayEquals(expected, solve(points,5));
    }


    public int[][] solve(int[][] points, int B) {
        Arrays.sort(points, (point1, point2) -> {
            long dist1 = Math.abs( point1[0] * point1[0] + point1[1] * point1[1] );
            long dist2 = Math.abs( point2[0] * point2[0] + point2[1] * point2[1] );
            System.out.println(String.format("[%d %d] = [%d] ,[%d %d] = %d ",  point1[0],  point1[1], dist1,
                                                                                    point2[0],  point2[1], dist2));
            return Long.compare(dist1,dist2);
        });
        MatrixUtils.printMatrix(points);
        int[][] result = new int[B][2];
        for(int i=0; i<B; i++){
            result[i] = points[i];
        }
        return result;
    }
}


