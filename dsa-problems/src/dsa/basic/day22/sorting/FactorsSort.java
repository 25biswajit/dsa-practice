package dsa.basic.day22.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FactorsSort {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(6, 8, 9));
        Integer[] expected = {9,6,8};
        Integer[] actual = solve(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(36, 13, 13, 26, 37, 28, 27, 43, 7));
        Integer[] expected = {7,13,13,37,43,26,27,28,36};
        Integer[] actual = solve(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 36, 16, 3, 1, 35, 17));
        Integer[] expected = {1,3,7,17,35,16,36};
        Integer[] actual = solve(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    public ArrayList<Integer> solve(ArrayList<Integer> list) {
        Collections.sort(list, new FactorComparator());
        System.out.println(list);
        return list;
    }
}

class FactorComparator implements Comparator<Integer>{

    // i1 comes first than i2 = -1
    @Override
    public int compare(Integer i1, Integer i2) {
        Integer factorCount1 = getFactorCount(i1);
        Integer factorCount2 = getFactorCount(i2);
        if(factorCount1 > factorCount2){ // i2 comes first
            return 1;
        }else if(factorCount1 == factorCount2) {
            if(i1 > i2){ // i2 comes first
                return 1;
            }else{ // i2 comes first
                return -1;
            }
        }else { // i2 comes first
            return -1;
        }
    }

    public int getFactorCount(Integer num){
        int i = 1;
        int count = 0;
        while (i * i <= num){
            if(num % i == 0){
                if(i == num/i){
                    count=count+1;
                }else {
                    count=count+2;
                }
            }
            i++;
        }
        System.out.println(num +" factors :" + count);
        return count;
    }
}
