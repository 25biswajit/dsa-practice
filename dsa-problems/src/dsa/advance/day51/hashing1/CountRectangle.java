package dsa.advance.day51.hashing1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Given,
X Axis = [1, 1, 2, 2, 3, 3]
Y Axis = [1, 2, 1, 2, 1, 2]
Find and return the number of unordered quadruplet (i, j, k, l) such that
(A[i], B[i]), (A[j], B[j]), (A[k], B[k]) and (A[l], B[l])
form a rectangle with the rectangle having all the sides parallel to either x-axis or y-axis.

3 quadruplets which make a rectangle are: (1, 1), (2, 1), (2, 2), (1, 2)
                                           (1, 1), (3, 1), (3, 2), (1, 2)
                                           (2, 1), (3, 1), (3, 2), (2, 2)
*/

import java.util.HashSet;

public class CountRectangle {

    @Test
    public void test(){
        int[] x = {1, 1, 2, 2, 3, 3};
        int[] y = {1, 2, 1, 2, 1, 2};
        Assertions.assertEquals(3, countRectangle(x,y));
    }

    //TC: O(N) SC:O(N)
    public int countRectangle(int[] x, int[] y){
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<x.length;i++){
            set.add(x[i]+"_"+y[i]);
        }
        int count = 0;
        for(int i=0; i<x.length; i++){
            for(int j=i+1; j<x.length; j++){
                int x1 = x[i], y1 = y[i];
                int x2 = x[j], y2 = y[j];
                
                if(!isParallel(x1,y1,x2,y2)){
                    int x3 = x2, y3 = y1;
                    int x4 = x1, y4 = y2;

                    if(set.contains(x3+"_"+y3) && set.contains(x4+"_"+y4)){
                        count++;
                    }
                }
            }
        }
        return count/2;
    }

    private boolean isParallel(int x1, int y1, int x2, int y2) {
        return (x1==x2 && y1!=y2) || (x1!=x2 && y1==y2);
    }
}
