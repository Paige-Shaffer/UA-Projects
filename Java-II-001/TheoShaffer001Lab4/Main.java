
/*
Name: Theo Shaffer
Date: 10.31.2024
Professor: Zareen Farooqi
Defines a generic array that provides the highest and lowest value of a number. 
There are static string and integer values to test functionality.
*/

import java.util.Arrays;

// Main class to test the ComparableArray
public class Main {
    public static void main(String[] args) {
        // Integer array - Set data
        Integer[] intArray = {2, 2, 3, 5, 0};
        //Integer[] intArray = {2};
        //Replace for single integer array test
        ComparableArray<Integer> intComparableArray = new ComparableArray<>(intArray);
        System.out.println("Highest Integer: " + intComparableArray.getHighest());
        System.out.println("Lowest Integer: " + intComparableArray.getLowest());

        // String array - Set data
        String[] strArray = {"One", "Two", "Three", "Four"};
        // String[] strArray = {"One"};
        // Replace for single string array test 
        ComparableArray<String> strComparableArray = new ComparableArray<>(strArray);
        System.out.println("Longest String: " + strComparableArray.getHighest());
        System.out.println("Shortest String: " + strComparableArray.getLowest());
    }
}

// Generic ComparableArray class to find highest and lowest values
class ComparableArray<T extends Comparable<T>> {
    private T[] array;

    public ComparableArray(T[] array) {
        this.array = array;
    }

    public T getHighest() {
        return Arrays.stream(array)
                     .max(Comparable::compareTo)
                     .orElse(null);
    }

    public T getLowest() {
        return Arrays.stream(array)
                     .min(Comparable::compareTo)
                     .orElse(null);
    }
}
