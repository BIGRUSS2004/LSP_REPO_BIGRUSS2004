package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * LettersPasswordAlgorithm is a concrete strategy that generates passwords containing only letters.
 * 
 * Strategy Details:
 * - Character Set: uppercase letters (A-Z) and lowercase letters (a-z) only
 * - Randomization: uses java.util.Random (standard randomization)
 * - Use Case: alpha-only passwords (e.g., pronounceable passwords, letter-based codes)
 * 
 * Implementation of PasswordAlgorithm interface:
 * This class is one of multiple implementations of the Strategy pattern,
 * allowing PasswordGeneratorService to select this algorithm at runtime.
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {
    // Define the character set: uppercase and lowercase letters (52 possible characters)
    // This is a static final constant, so it's shared across all instances and immutable
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    // Instance variable: Random number generator used to select random letters
    // Each instance has its own Random object for independence
    private final Random random = new Random();

    /**
     * Override the generate() method from PasswordAlgorithm interface.
     * Generates a password using only alphabetic characters.
     * 
     * @param length the desired length of the password
     * @return a randomly generated string of letters
     */
    @Override
    public String generate(int length) {
        // StringBuilder is more efficient than concatenating strings in a loop
        // It accumulates characters and converts to String at the end
        StringBuilder sb = new StringBuilder();
        
        // Loop 'length' times to build a password of the requested length
        for (int i = 0; i < length; i++) {
            // random.nextInt(LETTERS.length()) returns a random integer from 0 to 51
            // This random index is used to select a character from LETTERS
            int index = random.nextInt(LETTERS.length());
            
            // Append the randomly selected letter to the StringBuilder
            sb.append(LETTERS.charAt(index));
        }
        
        // Convert the StringBuilder to a String and return it
        return sb.toString();
    }
}
