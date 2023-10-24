package com.example.anagramchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AnagramChecker {
    static HashMap<String, List<String>> anagramMap = new HashMap<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("How many texts do you want to compare? ");
            int textCount = Integer.parseInt(reader.readLine());

            if (textCount < 0) {
                System.err.println("Please enter a non-negative integer for the number of texts.");
            } else {
                String[] texts = new String[textCount];

                for (int i = 0; i < textCount; i++) {
                    System.out.print("Enter text " + (char) ('A' + i) + ": ");
                    texts[i] = reader.readLine();
                }

                // texts => ["abc","cba"] input
                precomputeAnagrams(texts);

                compareTexts(texts);

                System.out.print("Enter a text to find its anagrams: ");
                String searchText = reader.readLine();
                List<String> anagrams = findAnagrams(searchText);
                System.out.println("Anagrams of '" + searchText + "': " + anagrams);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid integer.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading user input.");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("An error occurred while closing the input stream.");
            }
        }
    }

    static void precomputeAnagrams(String[] texts) {
        for (String text : texts) {
            String cleanText = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
            char[] textChars = cleanText.toCharArray();
            Arrays.sort(textChars);
            String sortedText = new String(textChars);

            anagramMap.computeIfAbsent(sortedText, k -> new ArrayList<>()).add(text);
        }
    }

    static void compareTexts(String[] texts) {
        int textCount = texts.length;
        Set<String> checkedAnagrams = new HashSet<>(); // To keep track of texts that are confirmed as anagrams

        for (int i = 0; i < textCount - 1; i++) {
            if (checkedAnagrams.contains(texts[i])) {
                continue; // Skip texts that are already confirmed as anagrams
            }

            for (int j = i + 1; j < textCount; j++) {
                String text1 = texts[i];
                String text2 = texts[j];

                boolean result = areAnagrams(text1, text2);
                System.out.println("Are '" + text1 + "' and '" + text2 + "' anagrams? " + result);

                if (result) {
                    checkedAnagrams.add(text1);
                    checkedAnagrams.add(text2);
                }
            }
        }
    }


    public static boolean areAnagrams(String str1, String str2) {
        String cleanStr1 = str1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanStr2 = str2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        char[] chars1 = cleanStr1.toCharArray();
        char[] chars2 = cleanStr2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    public static List<String> findAnagrams(String searchText) {
        searchText = searchText.replaceAll("[^a-zA-Z]", "").toLowerCase();
        char[] searchChars = searchText.toCharArray();
        Arrays.sort(searchChars);
        String sortedText = new String(searchChars);

        List<String> anagrams = anagramMap.getOrDefault(sortedText, new ArrayList<>());
        anagrams.remove(searchText);
        return anagrams;
    }
}
