package dsa.advance.day43.sorting2;


import dsa.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Given an array of integers A, Count how many such pair present where i < j and A[i] > 2*A[j]
public class ReversePairs {

    @Test
    public void test1(){
        int[] array = {1, 3, 2, 3, 1};
        Assertions.assertEquals(2, countPairs(array));
    }
    @Test
    public void test2(){
        int[] array = {10, 3, 8,15, 6, 12, 2, 18, 7, 1};
        Assertions.assertEquals(17, countPairs(array));
    }
    @Test
    public void test3(){
        int[] array = {-1,-2,-3};
        Assertions.assertEquals(3, countPairs(array));
    }
    @Test
    public void test4(){
        int[] array = {2,2,-2};
        Assertions.assertEquals(2, countPairs(array));
    }
    @Test
    public void test5(){ // ??????
        int[] array = {769, -71, 599, -1438, -530, -512, 1680, 1907, -301, 492, -844, 1765, -188, 685, -1879, -699, -990, 1022, 459, 528, -930, 1051, 1412, -1598, 245, -480, 1979, 1212, 1177, 447, -509, 881, 1968, -1603, -429, 1165, 405, 426, -1610, 931, -835, -1156, 1273, -143, -940, 199, -1886, -1646, 390, -1349, -256, -256, -103, -135, 637, -605, 55, -1805, -17, -229, 1162, 288, -818, -922, 32, -1032, -1823, -1874, -1650, 146, 721, 1586, 1969, 1720, -993, -1137, -1233, -1629, -879, -277, 444, -1191, -1273, 127, 1785, 1407, -1460, 414, -1578, -1348, 72, -794, 632, 877, 338, 1921, -650, -1314, 1187, -40 };
        Assertions.assertEquals(2761, countPairs(array));
    }

    // TC: O(N log N), SC: O(N + log N) = O(N)
    Integer count = 0;
    public int countPairs(int[] array){
        count = 0;
        mergeSort(array, 0, array.length-1);
        ArrayUtils.printArray(array);
        return count;
    }

    public void mergeSort(int[] array, int start, int end){
        if(start == end) return;
        int mid = (start + end)/2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
    }

    public void merge(int[] array, int start, int mid, int end){
        int[] temp = new int[end - start + 1];
        int p1 = start, p2 = mid+1, p3 = 0;
        while (p1 <= mid && p2 <= end){
            if(array[p1] <= array[p2]){
                temp[p3] = array[p1];
                p1++;
                p3++;
            }else {
                temp[p3] = array[p2];
                int p4 = p1;
                while (p4 <= mid && array[p4] <= (2 * array[p2])){
                    p4++;
                }
                count += (mid - p4 + 1);
                p2++;
                p3++;
            }
        }
        while (p1 <= mid){
            temp[p3] = array[p1];
            p1++;
            p3++;
        }
        while (p2 <= end){
            temp[p3] = array[p2];
            p2++;
            p3++;
        }

        for(int j=0,i=start; i<=end; i++,j++){
            array[i] = temp[j];
        }
    }
}
