package com.example.test;

import org.junit.jupiter.api.Test;

import com.example.src.StringPermutations;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringPermutationsTest {

    @Test
    public void testPermutationsOfThreeCharacterString() {
        String input = "abc";
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(6, result.size());
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    public void testPermutationsOfFourCharacterString() {
        String input = "abcd";
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(24, result.size());
        assertTrue(result.contains("abcd"));
        assertTrue(result.contains("abdc"));
        assertTrue(result.contains("acbd"));
        assertTrue(result.contains("acdb"));
        assertTrue(result.contains("adbc"));
        assertTrue(result.contains("adcb"));
        // Add more assertions as needed to validate other permutations
    }

    @Test
    public void testPermutationsOfEmptyString() {
        String input = "";
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(0, result.size());
    }

    @Test
    public void testPermutationsOfNullString() {
        String input = null;
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(0, result.size());
    }

    @Test
    public void testPermutationsOfSingleCharacterString() {
        String input = "a";
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(1, result.size());
        assertTrue(result.contains("a"));
    }

    @Test
    public void testPermutationsOfRepeatedCharacters() {
        String input = "aa";
        List<String> result = StringPermutations.generatePermutations(input);
        System.out.println(result);
        assertEquals(2, result.size()); //2 non unique permutations
        assertTrue(result.contains("aa"));
    }

    @Test
    public void testPermutationsOfLongerString() {
        String input = "abcde";
        List<String> result = StringPermutations.generatePermutations(input);
        assertEquals(120, result.size()); // 5! = 120
    }
}
