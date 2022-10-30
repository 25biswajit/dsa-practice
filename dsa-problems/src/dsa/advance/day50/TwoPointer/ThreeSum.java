package dsa.advance.day50.TwoPointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ThreeSum {
    @Test
    public void test1(){
        int[] list = new int[]{-1,2,1,-4};int B = 1;
        Assertions.assertEquals(2, threeSumClosest_bruteforce(list,B));
        Assertions.assertEquals(2, threeSumClosest(list,B));
    }
    @Test
    public void test2(){
        int[] list = new int[]{-1,-3,2,5,4};int B = 2;
        Assertions.assertEquals(1, threeSumClosest_bruteforce(list,B));
        Assertions.assertEquals(1, threeSumClosest(list,B));
    }

    public int threeSumClosest_bruteforce(int[] list, int B) {
        int n = list.length;
        int min = Integer.MAX_VALUE;
        int closestSum = 0;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                for(int k=j+1; k < n; k++){
                    int sum = list[i] + list[j] + list[k];
                    System.out.println(String.format("sum => %d = %d + %d + %d", sum, list[i] , list[j] , list[k]));
                    if(sum == B){
                        return sum;
                    }
                    if(Math.abs(sum - B) < min){
                        min = Math.abs(sum - B);
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }

    public int threeSumClosest(int[] list, int B) {
        Arrays.sort(list);
        int n = list.length;
        int closestSum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int fixedElement = list[i];
            int minPointer = i+1;
            int maxPointer = n-1;
            while (minPointer < maxPointer){
                int currentSum = fixedElement + list[minPointer] + list[maxPointer];
                if(currentSum == B){
                    return currentSum;
                }
                if(Math.abs(currentSum - B) < min){
                    min = Math.abs(currentSum - B);
                    closestSum = currentSum;
                }
                if(currentSum > B){
                    maxPointer--;
                }
                else {
                    minPointer++;
                }
            }
        }
        return closestSum;
    }
}
