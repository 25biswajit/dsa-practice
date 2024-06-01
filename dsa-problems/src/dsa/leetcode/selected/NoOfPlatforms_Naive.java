package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoOfPlatforms_Naive {

    @Test
    public void test1(){
        int[] start = {900,945,955,1100,1500,1800};
        int[] end = {910,1200,1130,1150,1900,2000};
        Assertions.assertEquals(3, platform(start,end));
    }
    @Test
    public void test2(){
        int[] start = {1100,945,955,1500,1800,900};
        int[] end = {1150,1200,1130,1900,2000,910};
        Assertions.assertEquals(3, platform(start,end));
    }

    //https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
    //TC: O(n 2)
    public int platform(int[] starttime, int[] endtime){
        // This section is required if data are not sorted
        List<TrainPair> input = new ArrayList<>();
        for(int i = 0; i < starttime.length; i++){
            input.add(new TrainPair( starttime[i], endtime[i]));
        }
        //input.sort(Comparator.comparingInt(a -> a.s));
        input.sort((a,b)->a.s-b.s);

        // Solution
        List<TrainPair> result = new ArrayList<>();
        result.add(input.get(0));

        for (int i = 1; i < input.size(); i++){
            boolean flag = false;
            TrainPair current = input.get(i);
            for(TrainPair pair : result){
                // Not overlapping
                if(pair.e < current.s){
                    pair.e = Math.max(pair.e, current.e);
                    flag = true;
                    break;
                }
                // if overlapping continue
            }
            if(!flag){
                result.add(current);
            }
            System.out.println(result);
        }

        return result.size();
    }

}


class TrainPair{
    int s;
    int e;

    public TrainPair(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public String toString() {
        return "TrainPair{" +
                "s=" + s +
                ", e=" + e +
                '}';
    }
}
