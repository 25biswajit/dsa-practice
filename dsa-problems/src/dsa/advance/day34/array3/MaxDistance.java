package dsa.advance.day34.array3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
/*
Given an array, A of integers of size N. Find the maximum value of j - i such that A[i] <= A[j].
A = [3, 5, 4, 2],For A[0] = 3 and A[2] = 4, the ans is (2 - 0) = 2.
*/

public class MaxDistance {
    @Test
    public void test(){
        int[] array = {3, 5, 4, 2};
        Assertions.assertEquals(maximumGap_bruteforce(array),maximumGap_optimised(array));
    }

    public int maximumGap_bruteforce(final int[] array) {
        int n = array.length;
        int max = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(array[j] >= array[i]){
                    max = Math.max(max , j-i);
                }
            }
        }
        return max;

    }
/*
    Let us say we sort the array.
    We need to make sure that we also store the original index of the values when we are sorting the array.
    Now we are looking for all values of A[j] which are bigger than A[i].
    Since the array is sorted, all the elements to the right of A[i] will qualify for being A[j].
    Since we want to maximize index[j] - index[i], and index[i] is fixed, we are essentially looking at max index[j] for all j > i.
*/

    public int maximumGap_optimised(final int[] array) {
        List<Node> list = new ArrayList<>();
        for(int i=0; i < array.length; i++){
            list.add(new Node(array[i],i));
        }
        list.sort(Node.getComparatorValue());
        int maxDiff = 0;
        int minIthIndex = list.get(0).index;

        for(int j = 1 ; j < list.size(); j++){
            Node pairJ = list.get(j);
            maxDiff = Math.max(maxDiff, pairJ.index - minIthIndex);
            minIthIndex = Math.min(minIthIndex, pairJ.index);
        }
        return maxDiff;
    }
}

class Node{
    int value;
    int index;

    public Node(int value,int index){
        this.value = value;
        this.index = index;
    }

    public static Comparator<Node> getComparatorValue(){
        return (o1, o2) -> {
            if(o1.value < o2.value){
                return -1;
            }
            else if(o1.value == o2.value){
                return 0;
            }
            else {
                return 1;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return value == node.value && index == node.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, index);
    }
}
