package dsa.advance.day38.gcd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*There are N players, each with strength A[i].
when player i attack player j, player j strength reduces to max(0, A[j]-A[i]).
When a player's strength reaches zero, it loses the game, and the game continues in the same manner among other players until only 1 survivor remains.
Can you tell the minimum health last surviving person can have?
A = [2, 3, 4]
Given strength array A = [2, 3, 4]
First player attack third player twice. [2, 3, 0]
First player attack second player. [2, 1, 0]
Second player attack first player twice. [0, 1, 0]
*/
public class Pubg {
    @Test
    public void test(){
        int[] array = {2,3,4};
        Assertions.assertEquals(1, solve(array));
    }

    public int solve(int[] A) {
        int ans = A[0];
        for(int i = 1; i < A.length ; i++){
            ans = gcd(ans, A[i]);
        }
        return ans;
    }
    private int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}
