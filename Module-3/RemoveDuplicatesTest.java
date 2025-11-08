// Joel Atkinson, November 5, 2025, CSD420 Advanced Java Programming Assignment 3.2
/* The purpose of this assignment is to create a test program using a generic method that creates an ArrayList with 50
random integers with values between 1-20, prints the list of 50 integers, then creates another list with the duplicates
removed and prints that updated list */

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    // Generic method to remove duplicates
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> noDuplicates = new ArrayList<>();

        // Loop through each item in the original list
        for (E item : list) {
            // Only add if it's not already in the new list
            if (!noDuplicates.contains(item)) {
                noDuplicates.add(item);
            }
        }
        return noDuplicates;
    }

    public static void main(String[] args) {
        // Create original list with 50 random values (1 to 20)
        ArrayList<Integer> original = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            original.add(rand.nextInt(20) + 1); // 1 to 20 inclusive
        }

        // Print original list
        System.out.println("Original List (50 values):");
        System.out.println(original);
        System.out.println("Size: " + original.size());

        // Call removeDuplicates method
        ArrayList<Integer> cleanList = removeDuplicates(original);

        // Print result
        System.out.println("\nList with Duplicates Removed:");
        System.out.println(cleanList);
        System.out.println("Size: " + cleanList.size());
    }
}
