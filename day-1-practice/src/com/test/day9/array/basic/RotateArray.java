package com.test.day9.array.basic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class RotateArray {
    @Test
    public void test(){
        int arr[] = {1,2,3,4};
        int k = 2;
        doRotate(arr,k );
        printArray(arr);

        int arr1[] = {1,2,3,4,5,6,7,8};
        int k1 = 34;
        doRotate(arr1,k1 % arr1.length); /* VeryVery Important Concept*/
        printArray(arr1);

        List<Integer> list = Arrays.asList(1,2,3,4);
        method(list);
        System.out.println(list);
    }

    private void method(List<Integer> list) {
        for(int i=0; i<list.size(); i++){
            list.set(i , list.get(i)*2);
            //list.add(0);
        }
//        int i=0;
//        for(int x : list){
//            list.set(i, x*2);
//            i++;
//        }
    }

    private void doRotate(int[] arr, int k){
        int n = arr.length-1;
        reverse(arr, 0, n);
        reverse(arr, 0, k-1);
        reverse(arr, k, n);
    }

    private void reverse(int[] arr, int p1, int p2){
        while(p2 >= p1){
            swap(arr, p1, p2);
            p1++;
            p2--;
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void printArray(int[] arr){
        for(int i=0;i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
        System.out.print("\n");
    }
}
