package com.example.anagramchecker;

import com.example.anagramchecker.AnagramChecker;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AnagramCheckerTest {

    @Before
    public void setUp() {

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
    public void testAreAnagrams_Sentence() {
        assertTrue(AnagramChecker.areAnagrams("Astronomer", "Moon starer"));
        assertTrue(AnagramChecker.areAnagrams("The Morse Code", "Here come dots."));
        assertTrue(AnagramChecker.areAnagrams("School master!", "The classroom"));
    }

    @Test
    public void testAreNotAnagrams_Sentence() {
        assertFalse(AnagramChecker.areAnagrams("Hello world", "Goodbye world"));
        assertFalse(AnagramChecker.areAnagrams("Anagram test", "Not an anagram"));
    }

    @Test
    public void testSortString() {
        assertEquals("aelrst", AnagramChecker.sortString("laster"));
    }

    @Test
    public void testSanitizeInput_ValidInput() {
        assertEquals("hello", AnagramChecker.sanitizeInput("Hello!"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSanitizeInput_InvalidInput() {
        AnagramChecker.sanitizeInput("123");
    }

    @Test
    public void testFindAnagrams() {
        List<String> anagrams = AnagramChecker.searchForAnagrams("apple");
        assertTrue(anagrams.isEmpty());
    }

    @Test
    public void testFindAnagrams_NoAnagrams() {
        List<String> anagrams = AnagramChecker.searchForAnagrams("apple");
        assertTrue(anagrams.isEmpty());
    }

    @Test
    public void testPrecomputeAnagrams() {
        AnagramChecker.precomputeAnagrams("listen");
        AnagramChecker.precomputeAnagrams("silent");

        Set<String> anagrams = AnagramChecker.anagramMap.get("eilnst");
        assertTrue(anagrams.contains("listen"));
        assertTrue(anagrams.contains("silent"));
    }
}
