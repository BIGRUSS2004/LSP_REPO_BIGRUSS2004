package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * EnhancedPasswordAlgorithm is a concrete strategy that generates secure passwords.
 * 
 * Strategy Details:
 * - Character Set: uppercase letters (A-Z), lowercase letters (a-z), and digits (0-9)
 * - Randomization: uses java.security.SecureRandom (cryptographically strong randomization)
 * - Use Case: high-security passwords where cryptographic quality is needed (e.g., security keys, tokens)
 * 
 * Key Difference from BasicPasswordAlgorithm:
 * SecureRandom is preferred over java.util.Random when security matters
 * because it uses a cryptographically secure pseudo-random number generator (CSPRNG).
 * 
 * Implementation of PasswordAlgorithm interface:
 * This class is one of multiple implementations of the Strategy pattern.
 */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {
    // Define the character set: uppercase + lowercase + digits (62 possible characters)
    // This static final constant is shared across all instances and immutable
    private static final String ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    // Instance variable: SecureRandom number generator for cryptographically secure randomization
    // Each instance has its own SecureRandom object
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Override the generate() method from PasswordAlgorithm interface.
     * Generates a secure password using alphanumeric characters.
     * 
     * @param length the desired length of the password
     * @return a randomly generated string of alphanumeric characters
     */
    @Override
    public String generate(int length) {
        // StringBuilder is more efficient than string concatenation in loops
        // It accumulates characters for better performance
        StringBuilder sb = new StringBuilder();
        
        // Loop 'length' times to build a password of the requested length
        for (int i = 0; i < length; i++) {
            // secureRandom.nextInt(ALLOWED.length()) returns a cryptographically random integer from 0 to 61
            // This index selects a character from the ALLOWED character set
            int index = secureRandom.nextInt(ALLOWED.length());
            
            // Append the randomly selected alphanumeric character to the StringBuilder
            sb.append(ALLOWED.charAt(index));
        }
        
        // Convert the StringBuilder to a String and return it
        return sb.toString();
    }
}
