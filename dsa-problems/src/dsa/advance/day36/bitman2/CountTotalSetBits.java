package dsa.advance.day36.bitman2;

import dsa.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountTotalSetBits {
    @Test
    public void test1(){
        Assertions.assertEquals(4, countSetBits(3));
        Assertions.assertEquals(32, countSetBits(15));
        Assertions.assertEquals(4938, countSetBits(1000));
        //Assertions.assertEquals(314447109, countSetBits(100000000));

        test(3);
        test(15);
        test(1000);
        test(100000000);
    }

    public int countSetBits(int num) {
        long count = 0;
        for(int i = 32 ; i > 0; i--){
            for(int j = 1; j <=num ; j++){
                if(((j >> i)&1) ==1){
                    count = count % Constants.mod_prime + 1L;
                }
            }
        }
        return (int)(count % 1000000007);
    }

    public void test(int num) {
        int count = 0;
        for(int i = 0 ; i < 32; i++){
            for(int j = 1; j <=num ; j++){
                if(((j >> i)&1) ==1){
                    count = i;
                    break;
                }
            }
        }
        System.out.println(count);
        System.out.println(((1<<count) % Constants.mod_prime * (count+1) % Constants.mod_prime) % Constants.mod_prime);
    }


}
