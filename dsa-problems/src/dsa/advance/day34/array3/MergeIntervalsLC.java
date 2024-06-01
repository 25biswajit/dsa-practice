package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static dsa.advance.day34.array3.MergeOverlappingIntervals.mergeSortedIntervals;


// Merge all Overlapping Intervals
public class MergeIntervalsLC {

    @Test
    public void test1(){
        int[][] intervals = {{2,3},{5,5},{2,2},{3,4},{3,4}};
        int[][] result = merge(intervals);
        int[][] expected = {{2,4},{5,5}};
        for(int i = 0; i < result.length; i++){
            Assertions.assertArrayEquals(expected[i], result[i]);
        }
    }
    @Test
    public void test2(){
        int[][] intervals = {{2,3},{1,4}};
        int[][] result = merge(intervals);
        int[][] expected = {{1,4}};
        for(int i = 0; i < result.length; i++){
            Assertions.assertArrayEquals(expected[i], result[i]);
        }
    }
    @Test
    public void test3(){
        int[][] intervals = {{0,0},{1,2},{5,5},{2,4},{3,3},{5,6},{5,6},{4,6},{0,0},{1,2},{0,2},{4,5}};
        int[][] result = merge(intervals);
        int[][] expected = {{0,6}};
        for(int i = 0; i < result.length; i++){
            Assertions.assertArrayEquals(expected[i], result[i]);
        }
    }


    public int[][] merge(int[][] intervals) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        Arrays.sort(intervals, (a, b) -> {
            if(a[0] == b[0]) { return Integer.compare(a[1], b[1]); }
            else return Integer.compare(a[0], b[0]);
        });

//        Arrays.sort(intervals, (o1, o2) -> {
//            int start1 = o1[0];
//            int end1 = o1[1];
//            int start2 = o2[0];
//            int end2 = o2[1];
//
//            if(start1 < start2){
//                return -1;
//            }
//            else if(start1 == start2 && end1 < end2){
//                return -1;
//            }
//            else if(start1 == start2 && end1 == end2){
//                return 0;
//            }
//            else
//                return 1;
//        });

        int[] pair = {intervals[0][0], intervals[0][1]};
        int n = intervals.length;
        for(int i = 1; i<n; i++){
            int pairEnd = pair[1];
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];

            if(pairEnd >= curStart){
                pair[1] = Integer.max(pair[1],curEnd);
            }
            else {
                // (pairEnd < curStart)
                // add existing pair
                map.put(pair[0], pair[1]);

                //create new pair
                pair[0] = curStart;
                pair[1] = curEnd;
            }
        }
        //Last Entry
        map.put(pair[0], pair[1]);

        int[][] result = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet())  {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }

        return result;
    }

}


