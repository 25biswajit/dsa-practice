package dsa.advance.day59.stack1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
There is a football event going on in your city. In this event, you are given A passes and players having ids between 1 and 106.
Initially, some player with a given id had the ball in his possession. You have to make a program to display the id of the player who possessed the ball after exactly A passes.
There are two kinds of passes:
1) ID
2) 0
For the first kind of pass, the player in possession of the ball passes the ball "forward" to the player with id = ID.
For the second kind of pass, the player in possession of the ball passes the ball back to the player who had forwarded the ball to him.
In the second kind of pass "0" just means Back Pass.
Return the ID of the player who currently possesses the ball.
A = 10
B = 23
C = [86, 63, 60, 0, 47, 0, 99, 9, 0, 0]
Initially, Player having  id = 23  posses ball.
After pass  1,  Player having  id = 86  posses ball.
After pass  2,  Player having  id = 63  posses ball.
After pass  3,  Player having  id = 60  posses ball.
After pass  4,  Player having  id = 63  posses ball.
After pass  5,  Player having  id = 47  posses ball.
After pass  6,  Player having  id = 63  posses ball.
After pass  7,  Player having  id = 99  posses ball.
After pass  8,  Player having  id = 9   posses ball.
After pass  9,  Player having  id = 99  posses ball.
After pass  10, Player having  id = 63   posses ball.
*/

public class PassingGame {
    @Test
    public void test(){
        int totalPasses=10, start=23; int[] passes = {86, 63, 60, 0, 47, 0, 99, 9, 0, 0};
        Assertions.assertEquals(63, playPassingGame(totalPasses,start,passes));
    }

    public int playPassingGame(int totalPasses, int start, int[] passes) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        for(int i=0;i<totalPasses;i++){
            if(passes[i]==0){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(passes[i]);
            }
        }
        if(stack.isEmpty()) return -1;
        else return stack.peek();
    }
}
