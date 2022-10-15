package dsa.advance.day52.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

//https://just4once.gitbooks.io/leetcode-notes/content/leetcode/hash-table/149-max-points-on-a-line.html
//https://afteracademy.com/blog/max-points-on-the-straight-line
//https://leetcode.com/problems/max-points-on-a-line/solution/

/*
Given two arrays of integers A and B describing a pair of (A[i], B[i]) coordinates in a 2D plane.
A[i] describe x coordinates of the ith point in the 2D plane,
whereas B[i] describes the y-coordinate of the ith point in the 2D plane.
Find and return the maximum number of points that lie on the same line.
Problem Constraints : 1 <= (length of the array A = length of array B) <= 1000
A = [-1, 0, 1, 2, 3, 3]
B = [1, 0, 1, 2, 3, 4]
The maximum number of point which lie on same line are 4, those points are {0, 0}, {1, 1}, {2, 2}, {3, 3}.
*/

public class PointsOnSameLine {
    @Test
    public void test1(){
        int[] a = {3,-1, 0, 1, 2, 3};
        int[] b = {3, 1, 0, 1, 2, 4};
        Assertions.assertEquals(4, pointsOnSameLineBruteForce(a,b));
        Assertions.assertEquals(4, pointsOnSameLine(a,b));
    }
    @Test
    public void test2(){ // BF Not working
        int[] a = {3, 1, 4, 5, 7, -9, -8, 6};
        int[] b = {4, -8, -3, -2, -1, 5, 7, -4};
        Assertions.assertEquals(2, pointsOnSameLine(a,b));
    }
    //Duplicates
    @Test
    public void test3(){
        int[] a = {-44, 44, 32, -43, -41, 18, -29, 49, 19, 28, 26, 29, 39, -4, 2, -36, 6, -45, -17, 16, -9, 34, 20, -48, -21, -19, -28, -45, -8, 8, 13, -42, -35, 15, -12, -36, 42, 24, 41};
        int[] b = {40, -9, -31, 43, 33, -34, -40, 25, 32, -5, 34, 41, -11, 24, -31, 9, -27, -20, -40, 35, -44, 29, 45, 40, 40, 33, -33, -20, -11, 20, -7, -28, 48, -44, 3, 39, 26, 21, 8};
        Assertions.assertEquals(4, pointsOnSameLineBruteForce(a,b));
        Assertions.assertEquals(4, pointsOnSameLine(a,b));
    }
    @Test
    public void test4(){
        int[] a = {1,2,1,2};
        int[] b = {2,2,4,4};
        Assertions.assertEquals(2, pointsOnSameLineBruteForce(a,b));
        Assertions.assertEquals(2, pointsOnSameLine(a,b));
    }
    // No working with brute force
    @Test
    public void test5(){
        int[] a = {48, 45, -3, 7, -25, 38, 2, 13, 13, 18, -44, 34, -27, -46, 48, -39, -41, -32, -16, 17, -6, 44, -28, -44, -6, -43, -16, 30, -3, -27, 32, 38, -26, 20, 4, -44, -37};
        int[] b = {47, -42, 41, 22, -4, -35, -45, -22, 5, -20, 21, -13, 47, 32, -48, 47, 17, -23, 30, -30, 37, 42, 44, 23, 1, -40, -9, 34, -34, 49, 16, -35, 2, -19, 31, 23, -37};
        Assertions.assertEquals(4, pointsOnSameLineBruteForce(a,b));
        Assertions.assertEquals(4, pointsOnSameLine(a,b));
    }

    // Brute force is not working for every input as because of wrong slope calculation
    public int pointsOnSameLineBruteForce(int[] pointsA, int[] pointsB) {
        int n = pointsA.length;
        if(n < 3) return n;
        int max = 2;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int pointsOnSameLine = 2;
                for(int k = j+1; k < n; k++){
                        int x1=pointsA[i],y1=pointsB[i];
                        int x2=pointsA[j],y2=pointsB[j];
                        int x3=pointsA[k],y3=pointsB[k];

                        if(isOnSameLine(x1,y1,x2,y2,x3,y3)){
                            pointsOnSameLine = pointsOnSameLine + 1;
                        }
                }
                max = Integer.max(max, pointsOnSameLine);
            }
        }
        return max;
    }

    private boolean isOnSameLine(int x1,int y1,int x2,int y2,int x3,int y3) {
        if((x1 == x2 && y1 == y2) || (x1 == x3 && y1 == y3) || (x2 == x3 && y2 == y3)){
            return false;
        }
        boolean result = Math.abs(y2-y1) * Math.abs(x3-x1) == Math.abs(y3-y1)*Math.abs(x2-x1);
        if(result){
            System.out.println(String.format("(%d,%d), (%d,%d), (%d,%d) = on Same Line", x1,y1,x2,y2,x3,y3));
//            System.out.println(Math.abs(y2-y1) + " * " + Math.abs(x3-x1) + " = " + (Math.abs(y2-y1) * Math.abs(x3-x1)));
//            System.out.println(Math.abs(y3-y1) + " * " + Math.abs(x2-x1) + " = " + (Math.abs(y3-y1) * Math.abs(x2-x1)));
        }
        return result;
    }

    // TC: N^2 log N, SC : N
    public int pointsOnSameLine(int[] pointsA, int[] pointsB) {
        if (pointsA.length <= 2) return pointsA.length;
        int res = 0;
        int n = pointsA.length;
        for(int i=0; i<n-1;i++){
            int sameP = 0, max = 0;
            HashMap<String, Integer> map = new HashMap<>();
            for(int j=i+1; j<n; j++){
                int x1 = pointsA[i]; int y1 = pointsB[i];
                int x2 = pointsA[j]; int y2 = pointsB[j];
                int yDif = y2 - y1;
                int xDif = x2 - x1;
                if (xDif == yDif && xDif == 0){
                    sameP++;
                    continue;
                }
                else{
                    int g = gcd(xDif, yDif);
                    yDif /= g;
                    xDif /= g;
                    String pair = (yDif) + "_" + (xDif);
                    map.put(pair, map.getOrDefault(pair,0)+1);
                    max = Integer.max(max, map.get(pair));
                }
                // +1 to include original point
                res = Math.max(res, max + sameP + 1);
            }
        }
        return res;
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
