package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*Given an integer, A. Find and Return first positive A integers in ascending order containing only digits 1, 2, and 3.
NOTE: All the A integers will fit in 32-bit integers.
1, 2, 3, 11, 12, 13, 21, 22, 23, 31, 32, 33, 111, 112, 113, 121, 122, 123, 131, 132, 133, 211, 212, 213, 221, 222, 223, 231, 232, 233....
Input A = 7 =>  [1, 2, 3, 11, 12, 13, 21]
Input A = 15 => [1, 2, 3, 11, 12, 13, 21, 22, 23, 31, 32, 33, 111, 112, 113, 121]
*/

public class GenerateNumbers {

    @Test
    public void test1(){
        String s = "1234567890";
        System.out.println(s.substring(0,s.length()/2));
        System.out.println(s.substring(s.length()/2));

        Integer[] expected = {1, 2, 3, 11, 12, 13, 21};
        Integer[] actual_1 = generateNumber(7).toArray(new Integer[0]);
        Integer[] actual_2 = generateNumberGood(7).toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual_1);
        Assertions.assertArrayEquals(expected, actual_2);

        //System.out.println(generateNumber(29451));
    }

    // TC: O(N), SC:(N) - My Solution
    public ArrayList<Integer> generateNumber(int a){
        int limit = a;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        a = a - queue.size();
        while (a > 0){
            Integer front = queue.poll();
            list.add(front);
            queue.add(Integer.parseInt(""+front+1));
            queue.add(Integer.parseInt(""+front+2));
            queue.add(Integer.parseInt(""+front+3));
            a = a - 3;
        }
        while (list.size() < limit && !queue.isEmpty()){
            list.add(queue.poll());
        }
        return list;
    }

    // TC: O(N), SC:(N)
    public ArrayList<Integer> generateNumberGood(int a){
        int limit = a;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        a = a - queue.size();
        while (list.size() < limit){
            Integer front = queue.poll();
            list.add(front);
            if(a <= 0) continue;
            queue.add(10*front+1);
            queue.add(10*front+2);
            queue.add(10*front+3);
            a = a - 3;
        }
        return list;
    }
}
/*
We know the initial three values will be 1, 2, and 3.
Now, the upcoming values will be by appending 1, 2, and 3 in each given value.
We will use a queue to store the elements in ascending order.*/
