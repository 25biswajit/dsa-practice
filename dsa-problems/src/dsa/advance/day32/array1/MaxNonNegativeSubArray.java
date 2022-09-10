package dsa.advance.day32.array1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxNonNegativeSubArray {

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,-1,3,1,-1,-3,5,2,-2,4,3,-1);
        Integer[] expected = {5,2};
        Integer[] actual = maxSet(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test2(){
        List<Integer> list = Arrays.asList(1, 2, 5, -7, 2, 5);
        Integer[] expected = {1,2,5};
        Integer[] actual = maxSet(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1967513926, 1540383426, -1303455736, -521595368);
        Integer[] expected = {1967513926, 1540383426};
        Integer[] actual = maxSet(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }
    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(0, 0, -1, 0);
        Integer[] expected = {0,0};
        Integer[] actual = maxSet(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> maxSet(final List<Integer> list) {
        ArrayList<Integer> tempList = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        Long currentSum = 0L;
        Long maxSum = 0L;
        int i = 0;
        while(i < list.size()){
            Integer num = list.get(i);
            if(num >= 0){
                currentSum += num;
                tempList.add(num);
                maxSum = currentSum;
                i++;
                break;
            }
            i++;
        }

        while(i < list.size()){
            Integer num = list.get(i);
            if(num >= 0 && !tempList.isEmpty()){
                currentSum += num;
                tempList.add(num);
            }
            else if(num >= 0 && tempList.isEmpty()){
                currentSum += num;
                tempList.add(num);
            }
            //if(num < 0)
            else{
                if(currentSum > maxSum ||
                        (currentSum == maxSum && result!=null && result.size() < tempList.size())){
                    result = new ArrayList<>(tempList);
                    maxSum = currentSum;
                }
                currentSum = 0L;
                tempList.clear();
            }
            i++;
        }

        if(currentSum > maxSum || (currentSum == maxSum && result!=null && result.size() < tempList.size())){
            result = new ArrayList<>(tempList);
            maxSum = currentSum;
        }
        System.out.println("Max Sum :" + maxSum + " List :" + result);
        return result;
    }
}
