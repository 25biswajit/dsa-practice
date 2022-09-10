package dsa.basic.day17.bitmanipulation;

import org.junit.jupiter.api.Test;

public class BinaryAddition {

    @Test
    public void test(){
        System.out.println( addBinary("101", "111") );
        System.out.println( addBinary("101", "101101") );
    }


    public String addBinary(String A, String B) {
        String sum = "";
        if(A.length() > B.length()){
            B = addZeros(B, A.length() - B.length());
        }
        else if(B.length() > A.length()){
            A = addZeros(A, B.length() - A.length());
        }

        //System.out.println( A + " + " + B);

        String[] arr1 = A.split("");
        String[] arr2 = B.split("");
        int carry = 0,i = arr1.length - 1;

        while(i >= 0){
            Integer a1 = Integer.parseInt( arr1[i] );
            Integer a2 = Integer.parseInt( arr2[i] );

            int digitSum = (carry + a1 + a2);
            carry = digitSum / 2;
            sum = (digitSum % 2) +""+ sum;
            //System.out.println(String.format("a1[%d]=%d + a2[%d]=%d = %d , Carry = %d, Sum = %s ", i, a1, i, a2, digitSum, carry, sum));
            i--;
        }

        if(carry > 0){
          sum = carry + sum;
        }
        return sum;
    }

    private String addZeros(String b, int c) {
        for(int i = 1; i<= c;i++){
            b = "0"+ b;
        }
        return b;
    }
}
