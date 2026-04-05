package ch.zhaw.iwi.devops.fizzbuzz;

public class PalindromeChecker {

    public boolean isPalindrome(String input) {
        if (input == null) return false;

        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }
}
