package com.test.day18.bitmanipulation;

import org.junit.jupiter.api.Test;

public class ReverseBits {
    @Test
    public void test(){
        System.out.println( reverse(1L));
        System.out.println( reverse(3L));
        System.out.println( reverse(3221225472L));
        System.out.println( reverse(2147483648L));
    }

    public long reverse(long a) {
        long res = 0;
        for(int i = 0 ; i <32 ; i++){
            if( ((a >> i) & 1) == 1 ){
                long add = (long) (1 << 31-i);
                if(add < 0){
                    add = (~add)+1;
                }
                res = res + add;
            }
        }
        return res;
    }

    public long reverseNew(long a) {
        long res = 0;
        for(int i = 0 ; i <32 ; i++){
            if( ((a >> i) & 1) == 1 ){
                
            }
        }
        return res;
    }
}
