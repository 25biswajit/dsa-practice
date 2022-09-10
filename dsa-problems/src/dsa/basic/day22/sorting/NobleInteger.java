package dsa.basic.day22.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class NobleInteger {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,3));
        Assertions.assertEquals(solveBruteForce(list),solve(list));
    }
    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,3,4,4,5,5,6,6));
        Assertions.assertEquals(solveBruteForce(list),solve(list));
    }
    @Test
    public void test3(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,6,2));
        Assertions.assertEquals(solveBruteForce(list),solve(list));
    }
    @Test
    public void test4(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3,0,2,2,5,5,5,5,8,8,10,10,10,13));
        Assertions.assertEquals(solveBruteForce(list),solve(list));
    }

    @Test
    public void test5(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-4, -2, 0, -1, -6));
        Assertions.assertEquals(solveBruteForce(list),solve(list));
    }

    public int solve(ArrayList<Integer> list) {
        Set<Integer> nobelIntegerList = new HashSet<>();
        Comparator<Integer> reverseComp = Comparator.reverseOrder();
        Collections.sort(list, reverseComp);

        // Edge Case Test 5
        if(list.get(0) == 0){
            nobelIntegerList.add(list.get(0));
        }

        int i = 1;
        while (i < list.size()){
            int current = list.get(i);
            int prev = list.get(i-1);
            if(current != prev){
                if(i == current){
                    nobelIntegerList.add(current);
                }
            }
            i++;
        }
        System.out.println(nobelIntegerList);
        return !nobelIntegerList.isEmpty() ? 1 : -1;
    }

    public int solveBruteForce(ArrayList<Integer> list) {
        Set<Integer> nobelIntegerList = new HashSet<>();
        for(int i = 0; i< list.size();i++){
            int p = list.get(i);
            int count = 0;
            for(int j = 0; j<list.size(); j++){
                int current = list.get(j);
                if(p < current){
                    count = count + 1;
                }
            }
            if(count == p){
                nobelIntegerList.add(p);
            }
        }
        System.out.println("Expected :" + nobelIntegerList);
        return !nobelIntegerList.isEmpty() ? 1 : -1;
    }

}
