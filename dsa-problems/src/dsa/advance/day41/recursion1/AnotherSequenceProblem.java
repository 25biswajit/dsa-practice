package dsa.advance.day41.recursion1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnotherSequenceProblem {
    @Test
    public void test(){
        Assertions.assertEquals(7, solve(3));
        Assertions.assertEquals(672, solve(10));
    }

    // TC: O(N) , SC: O(3^N)
    public int solve(int num) {
        if(num == 0 || num == 1){
            return 1;
        }
        if (num == 2){
            return 2;
        }
        else {
            return num + solve(num - 1) + solve( num - 2) + solve( num - 3);
        }
    }
}

/*You can improve the time complexity by converting your recursive solution into a dp solution.
Like when u will find solve(num), then first u will call solve(num -1),
then going in solve(num -1) recursively u will call solve(num - 2), solve(num - 3), and solve(num - 4).
Now store this answers in a dp array. Now when u will again return to solve(num), u already found num - 1,
now u will make call for solve(num - 2). But solve(num - 2) is alredy calculated when u recursilvely called solve(num - 1).
So no need to calculate it again. Directly return ans from dp array. The TC of your approach is 3^n whereas my approach is O(n).
So it is lot optimal*/
