package com.example.anagramchecker;

import java.util.*;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class AnagramChecker {
    static HashMap<String, Set<String>> anagramMap = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the string: ");
            String inputStr1 = scanner.nextLine();

            System.out.print("Enter the next string: ");
            String inputStr2 = scanner.nextLine();


            if (areAnagrams(inputStr1, inputStr2)) {
                System.out.println("These strings are anagrams!");
                precomputeAnagrams(inputStr1);
                precomputeAnagrams(inputStr2);
            } else {
                System.out.println("These strings are not anagrams!");
            }

            while (true) {
                System.out.print("Do you want to check more anagrams? (yes/no): ");
                String choice = scanner.nextLine().toLowerCase();
                try {
                    if ("yes".equals(choice)) {
                        break;
                    } else if ("no".equals(choice)) {
                        System.out.print("Enter a text to find its anagrams: ");
                        String searchTextInput = scanner.nextLine();
                        List<String> anagrams = searchForAnagrams(searchTextInput);
                        System.out.println("Anagrams of '" + searchTextInput + "': " + anagrams);
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    } else {
                        throw new IllegalArgumentException("Invalid input. Please enter 'yes' or 'no'.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        } while (true);
    }

    public static void precomputeAnagrams(String inputStr) {
        String cleanedStr = sanitizeInput(inputStr);
        String sortedStr = sortString(cleanedStr);
        anagramMap.computeIfAbsent(sortedStr, k -> new HashSet<>()).add(inputStr);
    }

    public static boolean areAnagrams(String inputStr1, String inputStr2) {
        String cleanedStr1 = sanitizeInput(inputStr1);
        String cleanedStr2 = sanitizeInput(inputStr2);

        return sortString(cleanedStr1).equals(sortString(cleanedStr2));
    }

    public static String sortString(String input) {
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static String sanitizeInput(String input) {
        // Remove special characters and convert to lowercase
        String sanitizedInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        if (sanitizedInput.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string.");
        }
        return sanitizedInput;
    }

    public static List<String> searchForAnagrams(String searchTextInput) {
        String searchText = sortString(sanitizeInput(searchTextInput));
        Set<String> anagrams = anagramMap.getOrDefault(searchText, new HashSet<>());
        anagrams.remove(searchTextInput);
        return new ArrayList<>(anagrams);
    }
}
