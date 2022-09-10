package dsa.advance.day32.array1;
/*

Problem Description
There are A beggars sitting in a row outside a temple. Each beggar initially has an empty pot. When the devotees come to the temple,
they donate some amount of coins to these beggars. Each devotee gives a fixed amount of coin(according to their faith and ability)
to some K beggars sitting next to each other.

Given the amount P donated by each devotee to the beggars ranging from L to R index, where 1 <= L <= R <= A,
find out the final amount of money in each beggar's pot at the end of the day, provided they don't fill their pots by any other means.
For ith devotee B[i][0] = L, B[i][1] = R, B[i][2] = P, Given by the 2D array B

Example : 5 beggar
B [ [1,3,10 Rs] , [2,4,20 Rs] , [3,5, 25 Rs]
Beggars     : 1  2  3  4  5
initial     : 0  0  0  0  0
[1,3,10 Rs] : 10 10 10 0  0
[2,4,20 Rs] : 10 30 30 20 0
[3,5,25 Rs] : 10 30 55 45 25 <= This is the final Answer

*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BeggarsOutsideTemple {
    @Test
    public void test(){
          int numberOfBeggars = 5;
          ArrayList<ArrayList<Integer>> donations = new ArrayList<>();
          donations.add(new ArrayList<>(Arrays.asList(1,3,10)));
          donations.add(new ArrayList<>(Arrays.asList(2,4,20)));
          donations.add(new ArrayList<>(Arrays.asList(3,5,25)));

          Integer[] expected = {10,30,55,45,25};
          Integer[] resultBruteForce = solveBruteForce(numberOfBeggars,donations).toArray(new Integer[0]);
          Integer[] resultOptimised = solve(numberOfBeggars,donations).toArray(new Integer[0]);

          Assertions.assertArrayEquals(expected,resultBruteForce);
          Assertions.assertArrayEquals(expected,resultOptimised);
    }

    public ArrayList<Integer> solveBruteForce(int numberOfBeggars, ArrayList<ArrayList<Integer>> donations) {
        int[] result = new int[numberOfBeggars];
        for(ArrayList<Integer> donation  : donations){
            int leftIndex = donation.get(0)-1;
            int rightIndex = donation.get(1)-1;
            int money = donation.get(2);
            for(int i = leftIndex; i<=rightIndex; i++){
                result[i] += money;
            }
        }
        return Arrays.stream(result).boxed().collect(Collectors.toCollection(ArrayList::new));
    }


    public ArrayList<Integer> solve(int numberOfBeggars, ArrayList<ArrayList<Integer>> donations) {
        int[] tempPrefixSumArray = new int[numberOfBeggars + 1];
        for(ArrayList<Integer> donation  : donations){
            int leftIndex = donation.get(0)-1;
            int rightIndex = donation.get(1)-1;
            int money = donation.get(2);
            tempPrefixSumArray[leftIndex] += money;
            tempPrefixSumArray[rightIndex + 1] += -money;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i=1;i<tempPrefixSumArray.length;i++){
            result.add(tempPrefixSumArray[i-1]);
            tempPrefixSumArray[i] = tempPrefixSumArray[i-1] + tempPrefixSumArray[i];
        }
        return result;
    }
}
