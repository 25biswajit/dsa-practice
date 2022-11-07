package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/*
Given an integer A, you have to find the Ath Perfect Number.
A Perfect Number has the following properties:
It comprises only 1 and 2.
The number of digits in a Perfect number is even.
It is a palindrome number.
For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.
First few perfect numbers are:
11,22,1111,1221,1221,2112,2222,111111,112211,121121,122221....
*/

public class PerfectNumbers {
    @Test
    public void test1(){
        Assertions.assertEquals("1111", perfectNumber(3));
        Assertions.assertEquals("122221", perfectNumber(10));
        Assertions.assertEquals("121121", perfectNumber(9));
    }

    // TC: O(N) SC: O(N)
    public String perfectNumber(int a){
        if(a == 1) return "11";
        if(a == 2) return "22";
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("22");
        int count = 2;
        int topCounter = 1;
        while (count < a){
            String front = list.get(topCounter-1);
            topCounter++;

            String half1 = front.substring(0,front.length()/2);
            String half2 = front.substring(front.length()/2);
            list.add(half1 + "11" + half2);
            list.add(half1 + "22" + half2);
            count = count + 2;
        }
        while (topCounter < a){
            topCounter++;
        }
        return list.get(topCounter-1);
    }

}
