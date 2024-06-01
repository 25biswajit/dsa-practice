package dsa.leetcode.selected;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NoOfPlatforms_Optimised1 {

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
    public int platform(int[] starttime, int[] endtime){
        List<Entry> input = new ArrayList<>();
        for(int i = 0; i < starttime.length; i++){
            input.add(new Entry(true, starttime[i]));
        }
        for(int i = 0; i < endtime.length; i++){
            input.add(new Entry(false, endtime[i]));
        }
        input.sort(Comparator.comparingInt(a -> a.time));

        int platform = 0;
        int maxPlatform = 0;

        for(Entry entry : input){
            if(entry.isArrival){
                platform++;
                maxPlatform = Math.max(maxPlatform, platform);
            }else {
                platform--;
            }
        }
        return maxPlatform;
    }

}


class Entry{
    boolean isArrival;
    int time;

    public Entry(boolean isArrival, int time) {
        this.isArrival = isArrival;
        this.time = time;
    }
}
