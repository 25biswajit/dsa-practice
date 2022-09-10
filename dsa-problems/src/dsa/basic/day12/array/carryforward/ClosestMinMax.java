package dsa.basic.day12.array.carryforward;

import dsa.utils.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ClosestMinMax {
    @Test
    public void test(){
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,1,3,4,6,4,6,3,1));
        Assertions.assertEquals(3, solveBruteForce(integers));
        Assertions.assertEquals(solveBruteForce(integers), solveLeftToRightIteration(integers));
        Assertions.assertEquals(solveBruteForce(integers), solveRightToLeftIteration(integers));
    }

    public int solveRightToLeftIteration(ArrayList<Integer> A) {
        int minValue = A.get(0);
        int maxValue = A.get(0);
        int n = A.size();
        int answer = n;
        for(int i=1; i<n; i++){
            maxValue = Integer.max(maxValue, A.get(i));
            minValue = Integer.min(minValue, A.get(i));
        }
        System.out.println(minValue +" "+ maxValue);
        if(minValue == maxValue){
            answer = 1;
        }else{
            int minIndex = -1;
            int maxIndex = -1;
            for(int i= n-1; i>=0; i--){
                int currentAns = -1;
                if(A.get(i) == maxValue){
                    maxIndex = i;
                    if(minIndex != -1){
                        currentAns = minIndex - maxIndex + 1;
                        answer = Integer.min(answer , currentAns);
                    }
                }
                else if(A.get(i) == minValue){
                    minIndex = i;
                    if(maxIndex != -1){
                        currentAns = maxIndex - minIndex + 1;
                        answer = Integer.min(answer , currentAns);
                    }
                }
                System.out.println(String.format("A[%d]=%d minIndex=%d maxIndex=%d ans=%d", i, A.get(i), minIndex, maxIndex, answer));
            }
        }
        return answer;
    }

    public int solveLeftToRightIteration(ArrayList<Integer> arr) {
        int minValue = arr.get(0);
        int maxValue = arr.get(0);
        int n = arr.size();
        int answer = n;
        for (int i = 1; i < n; i++) {
            maxValue = Integer.max(maxValue, arr.get(i));
            minValue = Integer.min(minValue, arr.get(i));
        }
        System.out.println(minValue + " " + maxValue);
        if (minValue == maxValue) {
            answer = 1;
        } else {
            int minIndex = -1;
            int maxIndex = -1;
            for(int i=0;i<n;i++){
                int a = arr.get(i);
                if(a == maxValue){
                    maxIndex = Integer.max(maxIndex, i);
                    if(minIndex != -1){
                        answer = Integer.min(answer, MathUtils.getDifference(minIndex,maxIndex)+1);
                    }
                }
                else if(a == minValue){
                    minIndex = Integer.max(minIndex, i);
                    if(maxIndex != -1){
                        answer = Integer.min(answer, MathUtils.getDifference(minIndex,maxIndex)+1);
                    }
                }
                System.out.println(String.format("A[%d]=%d minIndex=%d maxIndex=%d ans=%d", i, arr.get(i), minIndex, maxIndex, answer));
            }
        }
        return answer;
    }

    public int solveBruteForce(ArrayList<Integer> A) {
        int minValue = A.get(0);
        int maxValue = A.get(0);
        int n = A.size();
        int answer = n;
        for(int i=1; i<n; i++){
            maxValue = Integer.max(maxValue, A.get(i));
            minValue = Integer.min(minValue, A.get(i));
        }
        if(minValue == maxValue){
            answer = 1;
        }else{
            int minIndex = -1;
            int maxIndex = -1;
            for(int i= n-1; i>=0; i--){
                int currentAns = -1;
                if(A.get(i) == maxValue){
                    maxIndex = i;
                    if(minIndex != -1){
                        currentAns = minIndex - maxIndex + 1;
                        answer = Integer.min(answer , currentAns);
                    }
                }
                else if(A.get(i) == minValue){
                    minIndex = i;
                    if(maxIndex != -1){
                        currentAns = maxIndex - minIndex + 1;
                        answer = Integer.min(answer , currentAns);
                    }
                }
            }
        }
        return answer;
    }

}
