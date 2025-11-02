// Joel Atkinson October 30, 2024, CSD420 Advanced Java Programming, Assignment 2.2
/* The purpose of this assignment is to create two java programs, one stores an array of five random integers and an array
of five random double values. Then the data will be written into a file called JoelAtkinsonDataFile, if there is no file
one will be created and if there is a file the data in it will be appended. Then write a second program that reads the
file and displays the data */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        // Generate random integers (0 to 99)
        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100);
        }

        // Generate random doubles (0.0 to 99.9)
        for (int i = 0; i < 5; i++) {
            doubleArray[i] = rand.nextDouble() * 100;
        }

        // Write to file, appending if it exists
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("JoelAtkinsonDataFile.dat", true))) {
            writer.write("Integers: ");
            for (int num : intArray) {
                writer.write(num + " ");
            }
            writer.newLine();

            writer.write("Doubles: ");
            for (double num : doubleArray) {
                writer.write(String.format("%.2f", num) + " "); // Format to 2 decimal places
            }
            writer.newLine();
            writer.write("------------------------");
            writer.newLine();
            System.out.println("Data written to JoelAtkinsonDataFile.dat");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}