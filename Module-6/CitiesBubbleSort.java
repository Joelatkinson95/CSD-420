// Joel Atkinson November 21, 2025 CSD420 Assignment 6.2
/* The purpose of this assignment is to create a program with two methods that use bubble sort to sort data using
the Comparable interface and the Comparator interface
 */

import java.util.Comparator;



public class CitiesBubbleSort {

    // Method 1: Uses Comparable interface to sort by alphabetical order
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        if (list == null || list.length <= 1) return;

        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Method 2: Uses Comparator interface for custom sorting where the cities will be sorted by population size
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        if (list == null || list.length <= 1 || comparator == null) return;

        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Setting up the print to make it look better on output
    private static <E> void print(String label, E[] arr) {
        System.out.print(label + ": ");
        for (E item : arr) {
            System.out.print(item + "   ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        // adding the cities and their populations
        City[] cities = {
                new City("New York",     8_336_000L),
                new City("Los Angeles",  3_980_000L),
                new City("Chicago",      2_746_000L),
                new City("Phoenix",      1_644_000L)
        };

        System.out.println("Original order");
        print("        ", cities);

        // Test Comparable version (alphabetical by name)
        bubbleSort(cities);
        System.out.println("Sorted alphabetically (Using Comparable interface)");
        print("        ", cities);

        // Reset the array
        cities = new City[] {
                new City("New York",     8_336_000L),
                new City("Los Angeles",  3_980_000L),
                new City("Chicago",      2_746_000L),
                new City("Phoenix",      1_644_000L)
        };

        // Test Comparator version (by population High to Low)
        bubbleSort(cities, Comparator.comparingLong(City::population).reversed());
        System.out.println("Sorted by Population High to Low (Using Comparator interface)");
        print("        ", cities);
    }
}

// Creating city class
class City implements Comparable<City> {
    private final String name;
    private final long population;

    public City(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public long population() {
        return population;
    }

    // Natural order = alphabetical by city name
    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + String.format("%,d", population) + ")";
    }
}
