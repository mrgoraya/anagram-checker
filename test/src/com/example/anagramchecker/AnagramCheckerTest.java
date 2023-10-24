package com.example.anagramchecker;

import com.example.anagramchecker.AnagramChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AnagramCheckerTest {
    private String[] testStrings;

    @BeforeEach
    public void setUp() {
        testStrings = new String[]{"listen", "silent", "hello", "world"};
    }

    @Test
    public void testAreAnagrams_PositiveCase() {
        assertTrue(AnagramChecker.areAnagrams("listen", "silent"));
    }

    @Test
    public void testAreAnagrams_NegativeCase() {
        assertFalse(AnagramChecker.areAnagrams("hello", "world"));
    }

    @Test
    public void testAreAnagrams_MixedCaseAndSpaces() {
        assertTrue(AnagramChecker.areAnagrams("Eleven plus two", "Twelve plus one"));
    }

    @Test
    public void testAreAnagrams_WithSpecialCharacters() {
        assertTrue(AnagramChecker.areAnagrams("Madam Curie", "Radium came"));
    }

    @Test
    public void testAreAnagrams_NoAnagrams() {
        assertFalse(AnagramChecker.areAnagrams("apple", "banana"));
    }

    @Test
    public void testFindAnagrams_NoAnagrams() {
        List<String> anagrams = AnagramChecker.findAnagrams("apple");
        assertTrue(anagrams.isEmpty());
    }

    @Test
    public void testPrecomputeAnagrams() {
        AnagramChecker.precomputeAnagrams(testStrings);

        // Check if 'listen' and 'silent' are correctly associated as anagrams
        List<String> anagrams = AnagramChecker.anagramMap.get("eilnst");
        assertTrue(anagrams.contains("listen"));
        assertTrue(anagrams.contains("silent"));
    }

    @Test
    public void testCompareTexts() {
        // Use a custom PrintStream to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        AnagramChecker.compareTexts(testStrings);

        // Check if the program correctly prints anagram comparison results
        String output = outputStream.toString();
        assertTrue(output.contains("Are 'listen' and 'silent' anagrams? true"));
        assertTrue(output.contains("Are 'listen' and 'hello' anagrams? false"));
        assertTrue(output.contains("Are 'listen' and 'world' anagrams? false"));
        assertTrue(output.contains("Are 'hello' and 'world' anagrams? false"));
    }

    @Test
    public void testFindAnagrams() {
        // Test finding anagrams
        String[] texts = { "listen", "silent", "hello", "world", "abcd", "bcda" };
        AnagramChecker.precomputeAnagrams(texts);

        List<String> anagrams = AnagramChecker.findAnagrams("listen");
        assertEquals(1, anagrams.size());
        assertTrue(anagrams.contains("silent"));

        anagrams = AnagramChecker.findAnagrams("hello");
        assertEquals(0, anagrams.size());
    }
}
