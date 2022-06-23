package com.test;

import org.junit.jupiter.api.Test;

// Find those numbers where sum of divisor == that number.
// e.g = 28 = [ 1, 2, 4, 7 , 14 ]
// sum of 1 + 2 + 4 + 7 + 14 = 28
public class DivisorSumTest {

    @Test
    public void test(){
        DivisorSumTest obj = new DivisorSumTest();
        obj.divisorSumFinderRange(1,10);
    }

    boolean isDivisorSum(int number){
       int sum = 1;
       for(int i=2; i*i <= number; i++){
           if(number % i == 0){
               if(i == number/i){
                   sum = sum + i;
               }else{
                   sum = sum + i + number/i;
               }
           }
       }
       return sum == number;
    }

    void divisorSumFinderRange(int startNumber, int endNumber){
        for(int i=startNumber; i<=endNumber; i++){
            if(isDivisorSum(i)){
                System.out.println("Perfect Number:"+ i);
            }
        }
    }
}
