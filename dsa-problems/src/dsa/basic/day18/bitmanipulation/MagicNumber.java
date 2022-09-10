package dsa.basic.day18.bitmanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MagicNumber {
    @Test
    public void test(){
        Assertions.assertEquals(650, solve(10));
        Assertions.assertEquals(93875, solve(100));
    }

    // new process
    public int solve(int A) {
        int x = 1;
        int number = 5;
        int ans = 0;
        while( A > 0){
            x = x * number;
            if((A & 1) == 1){
                ans += x;
            }
            A = A >> 1;
        }
        return ans;
    }

    // old process
    public int solve1(int A) {
        List<Integer> list = new ArrayList<>();
        int c = 0;
        int power = 1;
        int number = 5;

        while(c <= A){
            int lastIndex = list.size() - 1;
            if(lastIndex >= 0){
                int lastValue = list.get(lastIndex);
                for(int i=0; i <= lastIndex-1 ; i++){
                    if(c < A){
                        list.add(lastValue + list.get(i));
                        System.out.println(c +" - "+ (c+1) + " - "+ (list.size()-1) +" - " + list);
                        c++;
                    }else{
                        break;
                    }
                }
            }
            if(c < A){
                list.add((int)(Math.pow((int)number, power)));
                System.out.println(c +" *- "+ (c+1) + " *- "+ (list.size()-1) +" *- " + list);
                power++;
                c++;
            }else{
                break;
            }
        }
        System.out.println(list);
        return list.get(list.size()-1);
    }
}
