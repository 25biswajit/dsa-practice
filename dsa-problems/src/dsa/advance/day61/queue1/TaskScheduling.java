package dsa.advance.day61.queue1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
A CPU has N tasks to be performed. It is to be noted that the tasks have to be completed in a specific order to avoid deadlock.
In every clock cycle, the CPU can either perform a task or move it to the back of the queue.
You are given the current state of the scheduler queue in array A and the required order of the tasks in array B.
Determine the minimum number of clock cycles to complete all the tasks.
A = [2, 3, 1, 5, 4]
B = [1, 3, 5, 4, 2]
According to the order array B task 1 has to be performed first, so the CPU will move tasks 2 and 3 to the end of the queue (in 2 clock cycles).
Total clock cycles till now = 2
Now CPU will perform task 1.
Total clock cycles till now = 3
Now, queue becomes [5, 4, 2, 3]
Now, CPU has to perform task 3. So it moves tasks 5, 4, and 2 to the back one-by-one.
Total clock cycles till now = 6
Now all the tasks in the queue are as in the required order so the CPU will perform them one-by-one.
Total clock cycles = 10
*/

public class TaskScheduling {
    @Test
    public void test1(){
        int[] tasks = {2, 3, 1, 5, 4};
        int[] taskOrder = {1, 3, 5, 4, 2};
        Assertions.assertEquals(10, taskSchedulingOperationCount(tasks,taskOrder));
    }
    @Test
    public void test2(){
        int[] tasks = {27, 123, 58, 130, 43, 52, 92, 150, 106, 122, 57, 22, 9, 12, 3, 154, 160, 132, 70, 31, 39, 62, 51, 126, 121, 82, 124, 68, 148, 142, 94, 32, 100, 108, 134, 118, 120, 99, 77, 74, 40, 37, 85, 71, 76, 90, 50, 107, 80, 10, 24, 48, 60, 111, 95, 36, 138, 109, 64, 29, 28, 105, 53, 46, 34, 158, 89, 73, 49, 30, 161, 20, 13, 44, 4, 67, 83, 75, 157, 110, 88, 61, 112, 116, 155, 147, 66, 81, 140, 103, 114, 159, 101, 91, 152, 79, 135, 119, 25, 55, 65, 26, 127, 84, 41, 54, 16, 72, 144, 86, 164, 97, 139, 21, 69, 63, 117, 87, 7, 17, 23, 35, 141, 15, 133, 137, 129, 33, 47, 125, 56, 163, 42, 143, 45, 102, 38, 96, 14, 2, 145, 115, 18, 136, 149, 93, 113, 78, 151, 6, 11, 8, 1, 146, 153, 156, 98, 5, 131, 128, 19, 104, 59, 162};
        int[] taskOrder = {76, 5, 88, 93, 67, 41, 32, 113, 111, 101, 81, 89, 53, 48, 80, 137, 120, 164, 22, 71, 106, 54, 3, 18, 62, 112, 108, 141, 78, 58, 9, 82, 27, 125, 135, 2, 34, 150, 122, 79, 12, 155, 147, 121, 90, 11, 63, 161, 114, 26, 99, 65, 129, 68, 115, 29, 8, 143, 39, 133, 52, 51, 84, 131, 119, 10, 149, 6, 70, 110, 162, 59, 69, 60, 109, 87, 44, 140, 4, 103, 57, 46, 75, 128, 15, 64, 77, 124, 130, 105, 1, 7, 98, 43, 159, 158, 66, 95, 116, 14, 107, 37, 42, 72, 118, 47, 123, 142, 96, 45, 38, 33, 17, 100, 35, 19, 102, 13, 157, 132, 94, 20, 139, 92, 21, 134, 156, 56, 117, 154, 97, 163, 146, 152, 104, 86, 49, 25, 30, 31, 16, 74, 91, 144, 145, 136, 83, 40, 126, 61, 24, 36, 50, 23, 73, 153, 55, 85, 151, 148, 160, 127, 28, 138};
        Assertions.assertEquals(6821, taskSchedulingOperationCount(tasks,taskOrder));
    }

    public int taskSchedulingOperationCount(int[] tasks, int[] taskOrder) {
        Queue<Integer> taskQueue = new LinkedList<>(Arrays.stream(tasks).boxed().collect(Collectors.toList()));
        Queue<Integer> taskSequenceQueue = new LinkedList<>(Arrays.stream(taskOrder).boxed().collect(Collectors.toList()));
        int operation = 0;
        while (!taskSequenceQueue.isEmpty()){
            Integer seqTask = taskSequenceQueue.poll();
            while (!taskQueue.isEmpty() && !Objects.equals(taskQueue.peek(), seqTask)){
                taskQueue.add(taskQueue.poll());
                operation++;
            }
            taskQueue.poll();
            operation++;
        }
        return operation;
    }
}
