package com.example.src;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {

    // Recursive method to generate all permutations of a given string
    public static List<String> generatePermutations(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            // Return an empty list if the input string is null or empty
            return permutations;
        }
        permute(str.toCharArray(), 0, permutations);
        return permutations;
    }

    // Helper recursive function to perform permutations by swapping characters
    private static void permute(char[] chars, int currentIndex, List<String> permutations) {
        if (currentIndex == chars.length - 1) {
            // Add the current permutation to the list when we reach the last character
            permutations.add(new String(chars));
        } else {
            // Recursively swap and permute for each index
            for (int i = currentIndex; i < chars.length; i++) {
                swap(chars, currentIndex, i);  // Swap to create a new permutation
                permute(chars, currentIndex + 1, permutations);  // Recurse for the next character
                swap(chars, currentIndex, i);  // Swap back to revert the changes
            }
        }
    }
    public static List<String> generatePermutationsIterative(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result;  // Return empty list for null or empty input
        }

        char[] chars = str.toCharArray();
        int n = chars.length;
        int[] c = new int[n];  // Control array for Heap's Algorithm

        // Add the first permutation (initial order of chars)
        result.add(new String(chars));

        int i = 0;
        while (i < n) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(chars, 0, i);  // Swap first and i-th element
                } else {
                    swap(chars, c[i], i);  // Swap c[i] and i-th element
                }
                result.add(new String(chars));  // Add the current permutation
                c[i] += 1;
                i = 0;
            } else {
                c[i] = 0;
                i += 1;
            }
        }
        return result;
    }

    // Utility method to swap characters at two positions in the char array
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


}
