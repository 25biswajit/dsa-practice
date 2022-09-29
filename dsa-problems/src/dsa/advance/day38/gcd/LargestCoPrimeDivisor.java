package dsa.advance.day38.gcd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
You are given two positive numbers A and B . You need to find the maximum valued integer X such that:

X divides A i.e. A % X = 0
X and B are co-prime i.e. gcd(X, B) = 1

Problem Constraints

1 <= A, B <= 109
*/
public class LargestCoPrimeDivisor {
    @Test
    public void test1(){
        Assertions.assertEquals(5, largestCoPrimeDid_bf(30,12));
        Assertions.assertEquals(4, largestCoPrimeDid_bf(100,55));
        Assertions.assertEquals(5, largestCoPrimeDid_bf_new(30,12));
        Assertions.assertEquals(4, largestCoPrimeDid_bf_new(100,55));
        Assertions.assertEquals(5, largestCoPrimeDid_optimised(30,12));
        Assertions.assertEquals(4, largestCoPrimeDid_optimised(100,55));
    }

    //TC: O(log A), SC: O(1)
    public int largestCoPrimeDid_optimised(int a, int b) {
        a = a / gcd(a,b);
        while (gcd(a,b)!=1){
                a = a / gcd(a,b);
        }
        return a;
    }

    //TC: O(A log A), SC: O(1)
    public int largestCoPrimeDid_bf(int a, int b) {
        int ans = 0;
        for(int i = 1; i < a; i++){
            if(a % i == 0 && gcd(i,b)==1){
                ans = i;
            }
        }
        return ans;
    }

    //TC: O(root(A) log A), SC: O(No Of Factors) It can be optimised up to O(1) if gcd( factor, b ) done in factor loop
    public int largestCoPrimeDid_bf_new(int a, int b) {
        List<Integer> factors = allFactors(a);
        int ans = 0;
        for(int i = 0; i < factors.size(); i++){
            if(gcd(factors.get(i),b)==1){
                ans = factors.get(i);
            }
        }
        return ans;
    }

    private List<Integer> allFactors(int a) {
        List<Integer> factors = new ArrayList<>();
        for(int i= 1; i*i <=a ; i++){
            if(a % i == 0){
                factors.add(i);
                if(i != a/i){
                    factors.add(a/i);
                }
            }

        }
        return factors;
    }

    private int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}

/*
We can try to remove the common factors of A and B from A by finding the greatest common divisor (gcd) of A and B and dividing A with that gcd.

Mathematically, A = A / gcd(A, B) —— STEP1
Now, we repeat STEP1 till we get gcd(A, B) = 1.
Atlast, we return X = A

How does this work ?

On doing prime factorization of A and B, we get :

A = p1x1 . p2x2 . … . pkxk
B = q1y1 . q2y2 . … . qlyl
Where pi ; i = 1, 2, …, k are prime factors of A and xi ; i = 1, 2, …, k are their respective powers
Similarly, qj ; i = 1, 2, …, l are prime factors of B and yi ; j = 1, 2, …, l are their respective powers

Let ri ; i = 1, 2, …, m be the common factors of A and B. By repeating STEP1, we are reducing the respective powers of ri by at least one each time (Proof of this is left to the reader). Therefore, we reach a point where powers of ri become zero, and the product of the rest prime-powers in A form the largest divisor of A that is co-prime with B.
*/
