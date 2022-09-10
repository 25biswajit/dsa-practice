package dsa.basic.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Pair_Diff_k_II {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7,4,1,4,10,7,10,10));
        int expected = diffPossibleBruteForce(list,3);
        int actual = diffPossibleOptimized(list,3);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0));
        int expected = diffPossibleBruteForce(list,0);
        int actual = diffPossibleOptimized(list,0);
        Assertions.assertEquals(expected,actual);
    }


    public int diffPossibleOptimized(final List<Integer> list, int target) {
        int count = 0;
        Map<Integer,Integer> freqMap = new HashMap<>();
        list.forEach(x -> freqMap.put(x, freqMap.getOrDefault(x, 0)+1));
        for(Integer a : list){
            int b = a - target;
            count = count + freqMap.getOrDefault(b , 0);
        }
        if (target == 0) { count = count - freqMap.size(); }
        return count > 0 ? 1 : 0;
    }

    public int diffPossibleBruteForce(final List<Integer> list, int target) {
        int count = 0;
        for(int i = 0; i< list.size();i++){
            for(int j= 0; j<list.size();j++){
                if(i != j && list.get(i)-list.get(j)==target){
                    ++count;
                }
            }
        }
        return count > 0 ? 1 : 0;
    }
}
