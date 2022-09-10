package dsa.basic.day12.array.carryforward;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AverageSubArray {
    @Test
    public void test(){
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(15, 7, 11, 7, 9, 8, 18, 1, 16, 18, 6, 1, 1, 4, 18 ));
        Assertions.assertEquals(7 , solve(list1, 6));

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 7, 90, 20, 10, 50, 40));
        Assertions.assertEquals(3, solve(list2, 3));

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(9, 1, 17, 15, 19, 3, 8, 5));
        Assertions.assertEquals(1, solve(list3, 6));

        ArrayList<Integer> list4 = new ArrayList<>(Arrays.asList(5, 15, 7, 6, 3, 4, 18, 14, 13, 7, 3, 7, 2, 18));
        Assertions.assertEquals(0, solve(list4, 6));
    }

    // O(n * m) -  worst case O (n ^ 2)
    public int solve2(ArrayList<Integer> A, int B) {
        int minSum = Integer.MAX_VALUE;
        int n = A.size();
        int index = -1;
        for(int i=0; i<=n-B;i++){
            System.out.println("i"+ i);
            int count = 1;
            int sum = 0;
            int j = i;
            while(count <= B){
                sum += A.get(j);
                j++;
                count++;
                //System.out.println("i:"+i + " j:" +j + " count:"+count);
            }
            if(minSum > sum){
                minSum = sum;
                index = i;
            }
            //System.out.println("---- i:"+i + " sum:" +sum + " avg:"+minSum);
        }
        return index;
    }

    public int solve(ArrayList<Integer> A, int B) {
        //int sumX = 0;
        int sum = A.get(0);
        int n = A.size();
        int index = 0;

        for(int i=1; i<B;i++){
            sum += A.get(i);
        }
        int minSum = sum;

        for(int i=1; i<=n-B;i++){
            //sumX = sum;
            sum = sum - A.get(i-1) + A.get(i+(B-1));
            //System.out.println(String.format("Sum ( %d ) = %d - %d + %d = %d", i, sumX, A.get(i-1), A.get(i+B-1), sum));
            if(minSum > sum){
                minSum = sum;
                index = i;
            }
            //System.out.println(String.format("MinSum = %d Index = %d", minSum, index));
        }
        return index;
    }
}
