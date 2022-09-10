package dsa.basic.day6.intro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareRoot {

    @Test
    public void test(){
        SquareRoot obj = new SquareRoot();
        Assertions.assertEquals(6, squareRoot(36));
        Assertions.assertEquals(11, squareRoot(121));
        Assertions.assertEquals(12, squareRoot(144));
        Assertions.assertEquals(-1, squareRoot(145));
        Assertions.assertEquals(12, floorSquareRoot(145));
    }

    private int squareRoot(int number){
        int answer = -1;
        int itr = 1;
        while (itr * itr <= number){
            if (itr * itr == number){
                answer = itr;
                break;
            }
            itr++;
        }
        return answer;
    }

    // Return nearest Integer if not perfect Square
    private int floorSquareRoot(int number){
        int answer = -1;
        int itr = 1;
        while (itr * itr <= number){
            answer = itr;
            itr++;
        }
        return answer;
    }
}
