package dsa.basic.day25.hashing2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// This Qs is not in assignment
// [ 1,1,1,1] k = 0, a[i]-a[j] = 0 Find All Pairs; index_i != index_j
public class AllPairWithGivenDiff {
    @Test
    public void test1(){
        List<Integer> integers = Arrays.asList(1,1,1,1);
        System.out.println(findAllPairsBruteForce(integers, 0));
        System.out.println(findAllPairsOptimized(integers, 0));
    }
    @Test
    public void test2(){
        List<Integer> integers = Arrays.asList(7, 4, 1, 4, 10, 7, 10, 10);
        System.out.println(findAllPairsBruteForce(integers, 3));
        System.out.println(findAllPairsOptimized(integers, 3));
    }

    public List<String> findAllPairsOptimized(List<Integer> list, int k){
        List<String> pairs = new ArrayList<>();
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<list.size();i++){
            int a = list.get(i);
            int b = a - k;
            if(map.containsKey(b)){
                List<Integer> allIndexList = map.get(b);
                for(Integer indexJ : allIndexList){
                    pairs.add("("+i+","+indexJ+")");
                }
            }

            if(k != 0) {
                b = a + k;
                if (map.containsKey(b)) {
                    List<Integer> allIndexList = map.get(b);
                    for (Integer indexJ : allIndexList) {
                        pairs.add("(" + i + "," + indexJ + ")");
                    }
                }
            }

            if(!map.containsKey(a)){
                map.put(a, new ArrayList<>());
            }
            List<Integer> indexList = map.get(a);
            indexList.add(i);
        }
        return pairs;
    }


    public List<String> findAllPairsBruteForce(List<Integer> list, int k){
        List<String> pairs = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            int j = k == 0 ? i+1 : 0;
            for (;j<list.size();j++){
                if(i!=j){
                    int a = list.get(i);
                    int b = list.get(j);
                    if(a-b==k){
                        pairs.add("("+i+","+j+")");
                    }

                }
            }
        }
        return pairs;
    }

}
