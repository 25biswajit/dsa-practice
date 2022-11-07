package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
Asked In Amazon ( Very Hard )
You are given an integer A. You have to find smallest multiple of A which consists of digits 0 and 1 only.
Since this multiple could be large, return it in form of a string.
NOTE: Returned string should not contain leading zeroes.
Input : 55  Ans : 110
Input : 2  Ans : 10
Input : 6  Ans : 1110
Input : 7  Ans : 1001
Input : 9  Ans : 111111111
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SmallestMultipleWith0And1 {
    @Test
    public void test1(){
        Assertions.assertEquals("110", smallestMultiple_Naive(55));
        Assertions.assertEquals("110", smallestMultiple_Optimised(55));
        Assertions.assertEquals("10", smallestMultiple_Naive(2));
        Assertions.assertEquals("10", smallestMultiple_Optimised(2));
        Assertions.assertEquals("1110", smallestMultiple_Optimised(6));
        Assertions.assertEquals("1001", smallestMultiple_Optimised(7));
        Assertions.assertEquals("111111111", smallestMultiple_Optimised(9));
        //Assertions.assertEquals("110111111111011111101", smallestMultiple_Naive(91377));//TLE
        Assertions.assertEquals("110111111111011111101", smallestMultiple_Optimised(91377));
    }
    // Not Working for Big Numbers due to Number format exception.
    // 1, 10, 11, 100, 101, 110, 111.... generate all binary number combinations and then check it is div by N or not.
    public String smallestMultiple_Naive(int number){
        if(number <= 1) return ""+number;
        Queue<String> queue = new LinkedList<>();
        queue.add("1");

        while (!queue.isEmpty()){
            String front = queue.poll();
            if(!front.equals("1") && findMod(front,number) == 0){
                return front;
            }
            queue.add(front + "0");
            queue.add(front + "1");
        }
        return "";
    }

    public int findMod(String word, int mod) {
        String[] arr = word.split("");
        long sum = 0;
        long temp = 1;
        for(int i=arr.length-1;i>=0;i--){
            int num = Integer.parseInt(arr[i]);
            sum = ( sum + ( num * temp ) ) % mod;
            temp = (temp * 10) % mod;
        }
        return (int)sum;
    }

/*
Very Very Important Concept :
100 % 7 = 2 , Now How to calculate 1001 % 7 ?
1001 % 7 ?
    = ( 100 * 10 + 1) % 7
    = ( 10 * 100 % 7 + 1 % 7) % 7
    = ( 10 * {100 % 7} + 1) % 7
    = ( 10 * 2 + 1) % 7
    = 21 % 7
    = 0
That means lets say : X % M = R
Now, For the Number : XY % M = ( R * 10 + Y ) % M

X = 100, Y = 1, M = 7, R = 2
That means lets say : 100 % 7 = 2
Now, For the Number : 1001 % 7 = ( 2 * 10 + 1 ) % 7 = 21 % 7 = 0

** Why Set ?
Let say, 1011 % 6 == 10111 % 6 == 3. Should we consider 10111 ? No!... why ?

"1011" + "10" = 101110 % 6 = 4
"10111" + "10" = 1011110 % 6 = 4, We are getting same reminder.

Generalized : If X % M = R1, Y % M = R1, and Y > X, Ignore Y Path, i.e. if reminder repeats ignore.

*/
    // Optimised - https://www.youtube.com/watch?v=Om47LiGTy8o
    public String smallestMultiple_Optimised(int number){
        if(number <= 1) return ""+number;
        Queue<Pair> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(new Pair("1", 1));
        String answer = "";
        while (!queue.isEmpty()){
            Pair front = queue.poll();
            String front_val = front.value;
            int front_rem = front.remainder;
            if(front_rem%number==0 && front_val.startsWith("1")){
                answer = front_val;
                break;
            }

            int next_rem_0 = (front_rem *10 ) % number;
            int next_rem_1 = (front_rem *10 + 1) % number;
            if(visited.add( next_rem_0 )) {
                queue.add(new Pair(front_val + "0", (front_rem*10)%number));
            }
            if(visited.add(next_rem_1)) {
                queue.add(new Pair(front_val + "1", (front_rem*10 + 1)%number));
            }
        }
        return answer;
    }
}

class Pair {
    public String value;
    public int remainder;
    Pair(String s, int value) {
        this.value = s;
        this.remainder = value;
    }
}
/*
Let’s represent our numbers as strings here. Now, consider there are N states,
where i’th state stores the smallest string which when take modulo with N gives i. Our aim is to reach state 0. Now,
we start from state “1” and at each step we have two options, either to append “0” or “1” to current state.
We try to explore both the options, but note that if I have already visited a state,
why would I visit it again? It already stores the smallest string which achieves that state and
if I visit it again with a new string it will surely have more characters than already stored string.
So, this is basically a BFS on the states. We’ll visit a state atmost once, hence overall complexity is O(N).
Interesting thing is that I need not store the whole string for each state, I can just store the value modulo N and
I can easily see which two new states I am going to.

But, how do we build the solution?
If I reach a state x, I store two values

From which node I arrived at node x from. Say this is node y.
What(0 or 1) did I append at string at node y to reach node x
Using this information, I can build my solution by repeatedly going to parents starting from node 0.
*/
