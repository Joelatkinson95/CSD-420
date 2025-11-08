//Joel Atkinson, November 7, 2025, CSD420 Advanced Java Programming, Assignment 4.2
/* The purpose of this assignment is to write a test program that traverses through LinkedLists full of 50,000 and
500,000 Integers and counts how long it takes (in milliseconds) when using iterator vs using get(index) */

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {


        //Test 50,000
        System.out.println("LinkedListTest: 50,000");
        compareTraversalMethods(50000);

        // Test 500,000
        System.out.println("\nLinkedListTest: 500,000");
        compareTraversalMethods(500000);

    }


    //Test the time to traverse the list of integers using an iterator vs using get(index) method
    private static void compareTraversalMethods(int size) {
        LinkedList<Integer> list = createLinkedList(size);

        long iteratorTme = measureIteratorTimeInMilliseconds(list);
        System.out.println("Iterator time in milliseconds: " + iteratorTme);

        long getTime = measureGetTimeInMilliseconds(list);
        System.out.println("Get time in milliseconds: " + getTime);
    }

    private static LinkedList<Integer> createLinkedList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.addLast(i);
        }

        return list;
    }

    private static long measureIteratorTimeInMilliseconds(LinkedList<Integer> list) {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (Integer integer : list) {
            sum += integer;
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long measureGetTimeInMilliseconds(LinkedList<Integer> list) {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}

/* How It Works:
In LinkedList when traversing through the list using iterator the system references the next/previous integer
so that it can continually count through the integers in the list one to the next to the next. For example 1 to 2 to 3, etc
which is quite fast. On the other hand, with the get(index) method, it must start at the head (or tail) and count through
the i steps on every call. For example 1, 1-2, 1-2-3, counting through each number every time the count increases which is
significantly slower than using the iterator method.

Results:
50,000 Integer List:
Using the iterator method for the 50,000 integer list took 4 milliseconds.
Using the get(index) method for the 50,000 integer list took 1115 milliseconds
This means that the iterator method was 278.75 times faster than the get(index) method when traversing through a 50,000
integer list.

500,000 Integer List:
Using the iterator method for the 500,000 integer list took 6 milliseconds
Using the get(index) method for the 500,000 integer list took 154553 milliseconds
This means that the iterator method was 25,758.83 times faster than the get(index) method when traversing through a 500,000
integer list
 */