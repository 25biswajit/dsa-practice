package dsa.basic.day20.modarithmatic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LCM {
    @Test
    public void test(){
        Assertions.assertEquals(calculateLCM(9,6), calculateLCM_recursion(9,6,1));
        Assertions.assertEquals(calculateLCM(9,6), calculateLCMUsingGCD(9,6));
        Assertions.assertEquals(calculateGCD(9,6), calculateGCD_recursion(9,6));
    }

//    Time Complexity: O(log(min(a,b))
//    Auxiliary Space: O(log(min(a,b))
    public int calculateLCMUsingGCD(int a,int b){
        int product = a * b;
        int gcd = calculateGCD_recursion(a,b);
        int lcm = product / gcd;
        return lcm;
    }

    public int calculateLCM(int a,int b){
        int lcm = (a > b) ? a : b;  // maximum number between a and b is stored in lcm
        while(!(lcm%a == 0 && lcm%b==0)){
            lcm++;
        }
        System.out.println(String.format("LCM (%d , %d) = %d",a,b,lcm));
        return lcm;
    }

    public int calculateLCM_recursion(int a, int b, int lcm)
    {
        boolean isDivisibleByBoth = lcm%a == 0 && lcm%b==0;
        if (isDivisibleByBoth){ return lcm;}
        return calculateLCM_recursion(a, b, lcm+1);
    }

    public int calculateGCD(int x, int y)
    {
        int gcd=0, a, b;
        a = (x > y) ? x : y; // a is greater number
        b = (x < y) ? x : y; // b is smaller number
        gcd = b;
        while(a % b != 0)
        {
            gcd = a % b;
            a = b;
            b = gcd;
        }
        System.out.println(String.format("GCD (%d , %d) = %d",a,b,gcd));
        return gcd;
    }

    public int calculateGCD_recursion(int a, int b)
    {
        if (b == 0) return a;
        return calculateGCD_recursion(b, a % b);
    }

}
