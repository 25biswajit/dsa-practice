package com.test.day6.intro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeterminePrimeOrNot {
    @Test
    public void test(){
        DeterminePrimeOrNot obj = new DeterminePrimeOrNot();
        Assertions.assertEquals("NOT_A_PRIME", obj.determinePrime(143));
        Assertions.assertEquals("PRIME", obj.determinePrime(2));
        Assertions.assertEquals("PRIME", obj.determinePrime(999563));
    }

    public String determinePrime(int number){
        if(number == 2){
            return "PRIME";
        }else if(number == 1 || number % 2 == 0){
            return "NOT_A_PRIME";
        }else{
            for(int i=3 ; i * i <= number; i=i+2){
                if(number % i==0){
                    return "NOT_A_PRIME";
                }
            }
            return "PRIME";
        }
    }
}
