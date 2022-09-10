package dsa.basic.day25.hashing2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSum {
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(2, 7, 11, 15);
        ArrayList<Integer> output = new ArrayList<>(Arrays.asList(1,2));
        Assertions.assertArrayEquals(output.toArray(), twoSum(list, 9).toArray());

        list = Arrays.asList(2,2,7,7);
        output = new ArrayList<>(Arrays.asList(1,3));
        Assertions.assertArrayEquals(output.toArray(), twoSum(list, 9).toArray());

        list = Arrays.asList(4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4, -7, 7, 9, -4, 4, -8);
        output = new ArrayList<>(Arrays.asList(4,8));
        Assertions.assertArrayEquals(output.toArray(), twoSum(list, -3).toArray());

        list = Arrays.asList(1,1,1,1);
        output = new ArrayList<>(Arrays.asList(1,2));
        Assertions.assertArrayEquals(output.toArray(), twoSum(list, 2).toArray());
    }

    public ArrayList<Integer> twoSum1(final List<Integer> list, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        Integer index1,index2;
        for(int i=0;i<list.size();i++){
            int x = list.get(i);
            int y = target - x;
            if(hashMap.containsKey(y)){
                index1 = hashMap.get(y);
                index2 = x == y || !hashMap.containsKey(x) ? i : hashMap.get(x);
                result.add(index1+1);
                result.add(index2+1);
                return result;
            }else{
                if(!hashMap.containsKey(x)){
                    hashMap.put(x, i);
                }
            }
        }
        return result;
    }

    public ArrayList<Integer> twoSum(final List<Integer> list, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        Integer index1,index2;
        for(int i=0;i<list.size();i++){
            int x = list.get(i);
            int y = target - x;
            if(hashMap.containsKey(y)){
                index1 = hashMap.get(y);
                index2 = x==y ? i : hashMap.getOrDefault(x, i);
                result.add(index1+1);
                result.add(index2+1);
                return result;
            }else{
                if(!hashMap.containsKey(x)){
                    hashMap.put(x, i);
                }
            }
        }
        return result;
    }
}
