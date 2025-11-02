// Joel Atkinson October 30, 2024, CSD420 Advanced Java Programming, Assignment 2.2
/* The purpose of this assignment is to create two java programs, one stores an array of five random integers and an array
of five random double values. Then the data will be written into a file called JoelAtkinsonDataFile, if there is no file
one will be created and if there is a file the data in it will be appended. Then write a second program that reads the
file and displays the data */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("JoelAtkinsonDataFile.dat"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}