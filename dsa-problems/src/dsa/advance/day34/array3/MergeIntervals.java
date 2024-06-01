package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import static dsa.advance.day34.array3.MergeOverlappingIntervals.mergeSortedIntervals;


// Insert New Interval into NonOverlapping Intervals
public class MergeIntervals {

    @Test
    public void test1(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));
        Interval newIntervals = new Interval(2,5);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,5));
        expected.add(new Interval(6,9));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test2(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(4,7));
        intervals.add(new Interval(10,14));
        intervals.add(new Interval(16,19));
        intervals.add(new Interval(21,24));
        intervals.add(new Interval(27,30));
        intervals.add(new Interval(32,35));
        intervals.add(new Interval(36,40));
        Interval newIntervals = new Interval(12,22);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,3));
        expected.add(new Interval(4,7));
        expected.add(new Interval(10,24));
        expected.add(new Interval(27,30));
        expected.add(new Interval(32,35));
        expected.add(new Interval(36,40));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test3(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,5));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(11,14));
        intervals.add(new Interval(15,20));
        intervals.add(new Interval(20,24));
        Interval newIntervals = new Interval(12,22);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,5));
        expected.add(new Interval(8,10));
        expected.add(new Interval(11,24));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test4(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,6));
        Interval newIntervals = new Interval(10,8);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,2));
        expected.add(new Interval(3,6));
        expected.add(new Interval(8,10));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test5(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(8,10));
        Interval newIntervals = new Interval(5,7);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(1,3));
        expected.add(new Interval(5,7));
        expected.add(new Interval(8,10));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test6(){
        ArrayList<Interval> intervals = new ArrayList<>();
        Interval newIntervals = new Interval(10,8);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(8,10));
        Assertions.assertTrue(expected.equals(results));
    }
    @Test
    public void test7(){
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(8,10));
        Interval newIntervals = new Interval(5,7);
        ArrayList<Interval> results = insert(intervals,newIntervals);
        System.out.println(results);
        ArrayList<Interval> expected = new ArrayList<>();
        expected.add(new Interval(5,7));
        expected.add(new Interval(8,10));
        Assertions.assertEquals(expected, results);
    }

    public ArrayList<Interval> insert0(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> intervalsSortedBasedOnStartTime = prepareOneList(intervals,newInterval);
        return mergeSortedIntervals(intervalsSortedBasedOnStartTime);
    }

    public ArrayList<Interval> insert1(ArrayList<Interval> intervals, Interval newInterval) {
        newInterval = new Interval(newInterval.start, newInterval.end);
        intervals.add(newInterval);
        intervals.sort(new IntervalComparator());
        return mergeSortedIntervals(intervals);
    }

    // Best Solution
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        //intervals.sort(new IntervalComparator());
        ArrayList<Interval> list = new ArrayList<>();
        newInterval = new Interval(newInterval.start, newInterval.end);

        if(intervals.isEmpty()){
            list.add(newInterval);
            return list;
        }

        for(int i = 0; i < intervals.size(); i++){
            Interval current = intervals.get(i);

            if(current.end < newInterval.start){
                list.add(current);
            }
            else if(current.start > newInterval.end){
                list.add(newInterval);
                newInterval = current;
            }
            else{
                newInterval.start = Math.min(newInterval.start, current.start);
                newInterval.end = Math.max(newInterval.end, current.end);
            }
        }
        list.add(newInterval);

        return list;
    }

    private ArrayList<Interval> prepareOneList(ArrayList<Interval> intervals, Interval newInterval) {
        int i = 0;
        int n = intervals.size();
        if(newInterval.start > newInterval.end){
            int temp = newInterval.start;
            newInterval.start = newInterval.end;
            newInterval.end = temp;
        }
        if(n == 0){
            intervals.add(newInterval);
            return intervals;
        }
        // If NewInterval is greater than last interval
        Interval last = intervals.get(n-1);
        if( last.start < newInterval.start){
            intervals.add(newInterval);
            return intervals;
        }
        // Insert New Interval into the list of Interval based on Start time
        else{
            ArrayList<Interval> overlappedIntervals = new ArrayList<>();
            while(i < n){
                Interval current = intervals.get(i);
                if(current.start < newInterval.start){
                    overlappedIntervals.add(current);
                }else{
                    overlappedIntervals.add(newInterval);
                    break;
                }
                i++;
            }
            while(i < intervals.size()){
                Interval current = intervals.get(i);
                overlappedIntervals.add(current);
                i++;
            }
            return overlappedIntervals;
        }
    }

}


