package dsa.basic.day10.array.prefixsum1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefixSumArray {
    @Test
    public void test(){
        m();

        List<Integer> list = Arrays.asList(5,12,3,1,2,4);
        Integer result = solve1(list, 5);
        Assertions.assertEquals(26, result);

        //list = Arrays.asList(16, -5, -8, -9, -4, 8);
        list = Arrays.asList(-969, -948, 350, 150, -59, 724, 966, 430, 107, -809, -993, 337, 457, -713, 753, -617, -55, -91, -791, 758, -779, -412, -578, -54, 506, 30, -587, 168, -100, -409, -238, 655, 410, -641, 624, -463, 548, -517, 595, -959, 602, -650, -709, -164, 374, 20, -404, -979, 348, 199, 668, -516, -719, -266, -947, 999, -582, 938, -100, 788, -873, -533, 728, -107, -352, -517, 807, -579, -690, -383, -187, 514, -691, 616, -65, 451, -400, 249, -481, 556, -202, -697, -776, 8, 844, -391, -11, -298, 195, -515, 93, -657, -477, 587);
        result = solve2(list, 81);
        Assertions.assertEquals(-5453, result);

        list = Arrays.asList(-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961, -509, -5, 942, -173, 436, -609, -396, 902, -847, -708, -618, 421, -284, 718, 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322, -667, 673, -336, 141, 711, -747, -132, 547, 644, -338, -243, -963, -141, -277, 741, 529, -222, -684, 35);
        result = solve2(list, 48);
        Assertions.assertEquals(6253, result);
    }

    // Prefix Sum Approach - SC: O(1), TC: O(N)
    private Integer solve1(List<Integer> A, int B){
        int n = A.size();
        List<Integer> prefixSumFront = getPrefixSum(A);

        int max = Integer.MIN_VALUE;
        for(int i=0;i< n;i++){
            if(B - i >= 0){
                int sum = frontSum(i, prefixSumFront) + backSum(B-i, prefixSumFront);
                System.out.println("i="+ i +" : "+
                        frontSum(i, prefixSumFront)+ " + " + backSum(B-i, prefixSumFront) +
                        " = "+ sum +" > "+ max +" = "+ (sum>max) );
                if(sum > max){
                    max = sum;
                }
            }else {
                break;
            }
        }
        return max;
    }

    private List<Integer> getPrefixSum(List<Integer> list) {
        for(int i=1; i< list.size() ; i++){
            int sum = list.get(i-1) + list.get(i);
            list.set(i, sum);
        }
        System.out.println(list);
        return list;
    }

    private int backSum(int k, List<Integer> prefixSum) {
        int n = prefixSum.size();
        if(k == 0){
            return 0;
        }else {
            return prefixSum.get(n-1) - frontSum(n-k, prefixSum);
        }
    }

    private int frontSum(int i, List<Integer> prefixSum) {
        if(i - 1 < 0){
            return 0;
        }else{
            return prefixSum.get(i-1);
        }
    }

    // Suffix Sum Approach - SC: O(N), TC: O(N)
    private Integer solve2(List<Integer> list, int limit) {
        System.out.println(list);
        int max = Integer.MIN_VALUE;
        int suffixSum = 0;
        List<Integer> suffixSumList = getSuffixSum(list);
        int prefixSum = 0;
        for(int i=0;i<list.size();i++){
            if(limit - i == 0){ // i == limit, no Suffix sum
                max = Integer.max(max, prefixSum);
            }else if(limit - i>0 && limit - i <= limit){ // i == 0 , no prefix sum
                suffixSum = suffixSumList.get(list.size() - (limit - i));
                max = Integer.max(max, prefixSum + suffixSum);
                prefixSum = prefixSum + list.get(i);
            }else{
                break;
            }
        }
        return max;
    }

    private List<Integer> getSuffixSum(List<Integer> list) {
        int n = list.size();
        List<Integer> suffixSumList = new ArrayList<>(list);
        suffixSumList.set(n-1, list.get(n-1));
        for (int i= n-2; i>=0; i--){
            int sum = suffixSumList.get(i+1) + list.get(i);
            suffixSumList.set(i, sum);
        }
        return suffixSumList;
    }

    private void m(){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1,2)));
        matrix.add(new ArrayList<>(Arrays.asList(5,9)));

        for(ArrayList<Integer> range : matrix){
            Integer s = range.get(0);
            Integer e = range.get(1);
            System.out.println(s + " " + e);
        }
    }
}


