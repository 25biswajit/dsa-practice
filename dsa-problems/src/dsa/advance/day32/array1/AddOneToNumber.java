package dsa.advance.day32.array1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/*
Given a non-negative number represented as an array of digits, add 1 to the number
A : 0,0,0,1,2,3
Output : 1,2,4
A : 9,9,9
Output : 1,0,0,0
*/
public class AddOneToNumber {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,0,0,1,2,3));
        Integer[] expected = {1,2,4};
        Integer[] actual = plusOne(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void test2(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9,9,9,9));
        Integer[] expected = {1,0,0,0,0};
        Integer[] actual = plusOne(list).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected,actual);
    }


    public ArrayList<Integer> plusOne(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList();
        int rem = 1;
        for(int i = list.size()-1; i>=0 ; i--){
            int digit = list.get(i);
            int digitSum = rem + digit;
            rem = digitSum / 10;
            result.add(digitSum % 10);
        }
        if(rem > 0){
            result.add(rem);
        }

        Collections.reverse(result);
        return removeZeros(result);
    }

    private ArrayList<Integer> removeZeros(ArrayList<Integer> list) {
        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()){
            Integer digit = itr.next();
            if(digit == 0){
                itr.remove();
            }else{
                break;
            }
        }
        return list;
    }
}
