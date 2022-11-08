package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class MergeOverlappingIntervals {
    @Test
    public void sortTest(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(2,5));
        intervals.add(new Interval(6,9));
        intervals.add(new Interval(1,3));

        Collections.sort(intervals, new IntervalComparator());

        System.out.println(intervals);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,3));
        expected.add(new Interval(2,5));
        expected.add(new Interval(6,9));
        Assertions.assertTrue(expected.equals(intervals));
    }


    @Test
    public void test(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(2,5));
        intervals.add(new Interval(6,9));
        intervals.add(new Interval(1,3));

        ArrayList<Interval> results = merge(intervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,5));
        expected.add(new Interval(6,9));
        Assertions.assertTrue(expected.equals(results));
    }

    @Test
    public void test1(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(25,30));
        intervals.add(new Interval(31,35));
        intervals.add(new Interval(19,25));
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(2,6));
        intervals.add(new Interval(15,18));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(5,8));

        ArrayList<Interval> results = merge(intervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,10));
        expected.add(new Interval(15,18));
        expected.add(new Interval(19,30));
        expected.add(new Interval(31,35));
        Assertions.assertTrue(expected.equals(results));
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());
        return mergeSortedIntervalsNew(intervals);
    }

    public static ArrayList<Interval> mergeSortedIntervals(ArrayList<Interval> intervals) {
        ArrayList<Interval> result =  new ArrayList<>();
        int n = intervals.size();
        int x = intervals.get(0).start;
        int y = intervals.get(0).end;
        for(int i = 1; i < n ;i++){
            Interval current = intervals.get(i);
            if(current.start > y){ // Non Overlapping
                result.add(new Interval(x,y));
                x = current.start;
                y = current.end;
            }else {
                y = Math.max(current.end, y);
            }
        }
        result.add(new Interval(x,y));
        return result;
    }

    public static ArrayList<Interval> mergeSortedIntervalsNew(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        int n = intervals.size();
        int p1 = 0, p2 = 1;
        while (p2 < n){
            Interval i1 = result.get(p1);
            Interval i2 = intervals.get(p2);
            if(i1.end >= i2.start){
                i1.end = i2.end;
            }else {
                result.add(i2);
                p1++;
            }
            p2++;
        }
        return result;
    }
}

