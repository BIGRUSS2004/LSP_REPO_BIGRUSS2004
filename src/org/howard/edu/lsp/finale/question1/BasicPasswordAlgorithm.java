package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * BasicPasswordAlgorithm is a concrete strategy that generates passwords containing only digits.
 * 
 * Strategy Details:
 * - Character Set: digits 0-9 only
 * - Randomization: uses java.util.Random (standard randomization)
 * - Use Case: simple numeric passwords (e.g., PIN codes, numeric-only requirements)
 * 
 * Implementation of PasswordAlgorithm interface:
 * This class is one of multiple implementations of the Strategy pattern,
 * allowing PasswordGeneratorService to select this algorithm at runtime.
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {
    // Define the character set: digits 0-9
    // This is a static final constant, so it's shared across all instances and immutable
    private static final String DIGITS = "0123456789";
    
    // Instance variable: Random number generator used to select random digits
    // Each instance has its own Random object for independence
    private final Random random = new Random();

    /**
     * Override the generate() method from PasswordAlgorithm interface.
     * Generates a password using only digits.
     * 
     * @param length the desired length of the password
     * @return a randomly generated string of digits
     */
    @Override
    public String generate(int length) {
        // StringBuilder is more efficient than concatenating strings in a loop
        // It accumulates characters and converts to String at the end
        StringBuilder sb = new StringBuilder();
        
        // Loop 'length' times to build a password of the requested length
        for (int i = 0; i < length; i++) {
            // random.nextInt(DIGITS.length()) returns a random integer from 0 to 9
            // This random index is used to select a character from DIGITS
            int index = random.nextInt(DIGITS.length());
            
            // Append the randomly selected digit to the StringBuilder
            sb.append(DIGITS.charAt(index));
        }
        
        // Convert the StringBuilder to a String and return it
        return sb.toString();
    }
}
