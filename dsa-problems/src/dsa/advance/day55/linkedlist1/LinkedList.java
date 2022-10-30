package dsa.advance.day55.linkedlist1;

import org.junit.jupiter.api.Test;

/*
Design and implement a Linked List data structure.
A node in a linked list should have the following attributes - an integer value and a pointer to the next node.
It should support the following operations:
insert_node(position, value) - To insert the input value at the given position in the linked list.
delete_node(position) - Delete the value at the given position from the linked list.
print_ll() - Print the entire linked list, such that each element is followed by a single space.
*/

public class LinkedList {

    @Test
    public void test(){
        LinkedList linkedList = new LinkedList();
        linkedList.insert_node(1, 23);
        linkedList.insert_node(2, 24);
        linkedList.print_ll();//23 24
        linkedList.delete_node(1);
        linkedList.print_ll();//24
        linkedList.insert_node(2, 25);
        linkedList.insert_node(3, 26);
        linkedList.insert_node(4, 27);
        linkedList.print_ll();//24 25 26 27
        linkedList.delete_node(3);
        linkedList.print_ll();//24 25 27
    }

    static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value=value;
            this.next = null;
        }
    }

    private static Node head=null;

    public static void insert_node(int position, int value) {
        Node newNode = new Node(value);

        if(position == 1){
            newNode.next = head;
            head = newNode;
        }else {
            Node temp = head;
            for(int i = 1; temp!= null; i++){
                if(position - 1 == i){
                    newNode.next = temp.next;
                    temp.next = newNode;
                }else {
                    temp = temp.next;
                }
            }
        }
    }

    public static void delete_node(int position) {
        int length = 0;
        Node tempPrev = null;
        Node temp = head;
        while (temp!=null){
            length++;
            if(length == position){
                if(tempPrev == null){
                    Node deleted = temp;
                    head = head.next;
                    deleted.next = null;
                }else if(tempPrev != null){
                    Node deleted = temp;
                    tempPrev.next = temp.next;
                    temp = temp.next;
                    deleted.next = null;
                }
            }
            else {
                tempPrev = temp;
                temp = temp.next;
            }
        }
    }

    public static void print_ll() {
        Node temp = head;
        String result = "";
        while (temp != null){
            result += " " + temp.value;
            temp = temp.next;
        }
        System.out.println(result.substring(1));
    }
}

