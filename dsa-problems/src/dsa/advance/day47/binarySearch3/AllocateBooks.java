package dsa.advance.day47.binarySearch3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllocateBooks {
    @Test
    public void test1(){
        int[] books = {12,34,67,90}; int students = 2;
        Assertions.assertEquals(113, minimizeBookPages(books,students));
    }
    @Test
    public void test2(){
        int[] books = {97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24}; int students = 26;
        Assertions.assertEquals(97, minimizeBookPages(books,students));
    }
    @Test
    public void test3(){
        int[] books = {31, 14, 19, 75}; int students = 12;
        Assertions.assertEquals(-1, minimizeBookPages(books,students));
    }

    public int minimizeBookPages(int[] books, int students){
        // Each Student should have one book, if number of book < students
        if(books.length < students){
            return -1;
        }
        int maxElement = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < books.length; i++){
            maxElement = Integer.max(maxElement, books[i]);
            sum += books[i];
        }
        int low = maxElement;
        int high = sum;
        int minPages = -1;
        while (low <= high){
            int mid = (low + high)/2;
            int requiredStudents = requireStudentsForGivenPages(books, mid);
            if(requiredStudents > students){
                low = mid + 1;
            }
            else {
                minPages = mid;
                high = mid - 1;
            }
        }
        return minPages;
    }

    private int requireStudentsForGivenPages(int[] books, int pageLimit) {
        int countStudent = 1;
        int i = 1;
        int sumPages = books[0];
        while (i < books.length){
            if(sumPages + books[i] <= pageLimit){
                sumPages += books[i];
            }
            else {
                countStudent++;
                sumPages = books[i];
            }
            i++;
        }
        return countStudent;
    }
}
