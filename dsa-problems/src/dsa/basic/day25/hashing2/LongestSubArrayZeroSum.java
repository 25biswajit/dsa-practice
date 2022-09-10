package dsa.basic.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LongestSubArrayZeroSum {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(30,-30,20,-20));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,5,6,9,30,-30,20,-20,90,-90,0));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }
    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,6,9,30,-30,20,-20,90,-90));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }
    @Test
    public void test4(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3,-2,5,6,-3,-1,10,-12));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }

    @Test
    public void test5(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-16, 16, 3));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }

    @Test
    public void test6(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2,8,-3,-5,2,-4,6,1,2,1,-3,4));
        Assertions.assertEquals(solveBruteForce(list), solveOptimized(list));
    }

    public int solveOptimized(ArrayList<Integer> list) {
        long sum = 0;
        int max = 0;
        int currentIndex = -1;
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(sum, currentIndex);

        while( currentIndex<list.size()-1){
            currentIndex++;
            sum += list.get(currentIndex);
            if(map.containsKey(sum)){
                int prevIndex = map.get(sum);
                int subArrayLength = currentIndex - prevIndex;
                max = Integer.max(max, subArrayLength);
            }else{
                map.put(sum, currentIndex);
            }
        }
        return max;
    }

    public int solveBruteForce(ArrayList<Integer> list){
        int max = 0;
        for (int i = 0; i< list.size(); i++){
            int sum = 0;
            for(int j = i; j <list.size() ; j++){
                sum += list.get(j);
                if(sum == 0){
                    max = Integer.max(max, j-i+1);
                }
            }
        }
        return max;
    }
}
