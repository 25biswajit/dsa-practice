package dsa.advance.day41.recursion1;

import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Output [ (disk No, Source Tower, Destination Tower) ]
*/

public class TowerOfHanoi {
    @Test
    public void test(){
        int[][] actual_1 = towerOfHanoiList(3);
        int[][] actual_2 = towerOfHanoiArray(3);
        int[][] expected = {
                {1, 1, 3},
                {2, 1, 2},
                {1, 3, 2},
                {3, 1, 3},
                {1, 2, 1},
                {2, 2, 3},
                {1, 1, 3},
        };
        Assertions.assertArrayEquals(expected,actual_1);
        Assertions.assertArrayEquals(expected,actual_2);
    }

    // List Solution
    public int[][] towerOfHanoiList(int n) {
        List<List<Integer>> list = new ArrayList<>();
        move(n, 1, 2, 3, list);
        return ArrayUtils.toArray(list);
    }
    public void move(int disc, int towerSrc, int towerTemp, int towerDest, List<List<Integer>> list){
        if(disc == 0) return;
        move(disc-1, towerSrc, towerDest, towerTemp, list);
        list.add(Arrays.asList(disc, towerSrc, towerDest));
        move(disc-1, towerTemp, towerSrc, towerDest, list);
    }

    // Array Solution
    int current;
    public int[][] towerOfHanoiArray(int n) {
        current = 0;
        int r = ((int)Math.pow(2,n))-1;
        int[][] ans = new int[r][3];
        move(n, 1, 2, 3, ans);
        return ans;
    }
    public void move(int disc, int towerSrc, int towerTemp, int towerDest, int[][] ans){
        if(disc == 0) return;
        move(disc-1, towerSrc, towerDest, towerTemp, ans);
        ans[current++] = new int[]{disc, towerSrc, towerDest};
        move(disc-1, towerTemp, towerSrc, towerDest, ans);
    }
}
