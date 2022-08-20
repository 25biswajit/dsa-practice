package com.test.day17.bitmanipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeNumber {

    @Test
    public void test(){
        Assertions.assertTrue(isPrime(97));
        Assertions.assertFalse(isPrime(141));
        Assertions.assertFalse(isPrime(16347));
    }

    public boolean isPrime(int num){
        for(int i = 2; i * i <= num ; i++){
            if(num % i == 0){
                System.out.println(i+ " Num :"  + num);
                return false;
            }
        }
        return true;
    }
}
