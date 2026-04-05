package ch.zhaw.iwi.devops.fizzbuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromeCheckerTest {

    @Test
    void testSimplePalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertTrue(checker.isPalindrome("anna"));
    }

    @Test
    void testNotPalindrome() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertFalse(checker.isPalindrome("hello"));
    }

    @Test
    void testCaseInsensitive() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertTrue(checker.isPalindrome("Otto"));
    }

    @Test
    void testWithSpaces() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertTrue(checker.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    void testWithSpecialCharacters() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertTrue(checker.isPalindrome("Madam, I'm Adam"));
    }

    @Test
    void testNull() {
        PalindromeChecker checker = new PalindromeChecker();
        Assertions.assertFalse(checker.isPalindrome(null));
    }
}