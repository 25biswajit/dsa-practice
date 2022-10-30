package dsa.advance.day46.binarySearch2;

import dsa.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AthMagicalNumber {
    @Test
    public void test1(){
        int B=5,C=7,K=3;
        Assertions.assertEquals(10, KthMagicalNumber(K,B,C));
        Assertions.assertEquals(10, KthMagicalNumber_bf(K,B,C));
    }
    @Test
    public void test2(){
        int B=4,C=6,K=6;
        Assertions.assertEquals(18, KthMagicalNumber(K,B,C));
        Assertions.assertEquals(18, KthMagicalNumber_bf(K,B,C));
    }
    @Test
    public void test3(){
        int K=19,B=11,C=13;
        Assertions.assertEquals(117, KthMagicalNumber(K,B,C));
        Assertions.assertEquals(117, KthMagicalNumber_bf(K,B,C));
    }
    @Test
    public void test4(){
        int K=33488383,B=565,C=29473;
        Assertions.assertEquals(565660879, KthMagicalNumber_bf(K,B,C));
        Assertions.assertEquals(565660879, KthMagicalNumber(K,B,C));
    }

    public int KthMagicalNumber_bf(int K, int B, int C){
        long num1 = new Long(B);
        long num2 = new Long(C);
        long N = K * Long.min(num1,num2);
        int count = 0;
        long ans = 0;
        for(long i = 1L; i <=N; i++){
            if(i%num2 == 0 || i%num1 == 0){
                count = (count + 1)%Constants.mod_prime;
                if(count == K){
                    ans = i;
                    break;
                }
            }
        }
        return (int)(ans % Constants.mod_prime);
    }

    public int KthMagicalNumber(int K, int B, int C){
        long num1 = new Long(B);
        long num2 = new Long(C);
        long ans = -1;
        long low = 1L;
        long high = K * Long.min(num1,num2);
        while (low <= high){
            long mid = low + (high-low)/2;
            long pos = findPositionMagical(new Long(num1),new Long(num2),mid);
            if(pos == K){ ans = mid; high = mid -1; }
            else if(pos > K){ high = mid -1; }
            else { low = mid + 1; }
        }
        return (int)(ans % Constants.mod_prime);
    }
    private long findPositionMagical(long num1, long num2, long range){
        long lcm = (num1*num2) / gcd(num1,num2);
        return range/num1 + range/num2 - range/lcm;
    }
    private long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}
