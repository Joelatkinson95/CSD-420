//Joel Atkinson, November 14, 2025, CSD420 Advanced Java Programming Assignment
/* The purpose of this assignment was to create a .txt file with words in it and then write a program that reads
the .txt file and displays all the non-duplicate words in ascending order then again in descending order and also add a
method that tests the code to ensure that it is running correctly. For this assignment I wrote a .txt file with cities
I have visited and cities that I would like to visit (some cities are on both lists for ones I would like to visit again
which gives the duplicates to be taken out). I also added the test method that checks for the file as test #1 and then
tests to make sure the file is readable and contains strings for test 2 */


import java.io.*;
import java.util.*;

public class WordSorter {

    public static void main(String[] args) {
        String filename = "Module-5/collection_of_words.txt";
        Set<String> uniqueWords = new TreeSet<>();

        // Read file line by line and split into words
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                //Skip header lines by ignoring lines with "cities" so only the listed words are sorted
                if (line.toLowerCase().contains("cities")) {
                    continue;
                }

                // Split on any whitespace and only keep letters and underscores (for double word cities)
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String cleanWord = word.replaceAll("[^a-zA-Z_]", "").trim();
                    if (!cleanWord.isEmpty()) {
                        uniqueWords.add(cleanWord);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: '" + filename + "' not found in project root!");
            return;
        }

        // Ascending Order
        System.out.println("Non-duplicate words in ascending order:");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        // Descending Order
        System.out.println("\nNon-duplicate words in descending order:");
        List<String> descList = new ArrayList<>(uniqueWords);
        descList.sort(Collections.reverseOrder());
        for (String word : descList) {
            System.out.println(word);
        }

        // Run Tests
        runTests();
    }

    private static void runTests() {
        System.out.println("\n\nRunning Automated Tests");


        File file = new File("Module-5/collection_of_words.txt");

        // Test 1: Was the file found?
        if (file.exists()) {
            System.out.println("Test 1: PASS - File Found");
        } else {
            System.out.println("Test 1: FAIL - File not found");
        }

        // Test 2: Can the words be read and processed
        Set<String> testSet = new TreeSet<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty() || line.toLowerCase().contains("cities")) continue;
                String[] words = line.split("\\s+");
                for (String w : words) {
                    String clean = w.replaceAll("[^a-zA-Z_]", "");
                    if (!clean.isEmpty()) testSet.add(clean);
                }
            }

            if (!testSet.isEmpty()) {
                System.out.println("Test 2: PASS - Words successfully read and processed ("
                        + testSet.size() + " unique words).");
            } else {
                System.out.println("Test 2: FAIL - No valid words were processed.");
            }

        } catch (Exception e) {
            System.out.println("Test 2: FAIL - Could not read file");
        }

        System.out.println("All tests completed.");
    }
}

