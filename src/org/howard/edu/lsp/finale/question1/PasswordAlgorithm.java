package org.howard.edu.lsp.finale.question1;

/**
 * PasswordAlgorithm is the Strategy interface that defines the contract for password generation.
 * 
 * Design Pattern: STRATEGY PATTERN
 * - This interface represents the "Strategy" in the Strategy design pattern
 * - Multiple concrete implementations can provide different password generation algorithms
 * - PasswordGeneratorService uses this interface to allow runtime algorithm selection
 * - Concrete implementations: BasicPasswordAlgorithm, EnhancedPasswordAlgorithm, LettersPasswordAlgorithm
 * 
 * Benefits:
 * - Encapsulates algorithms in separate classes (Open/Closed Principle)
 * - Allows algorithms to be swapped at runtime without modifying client code
 * - Promotes loose coupling between the service and specific algorithm implementations
 */
public interface PasswordAlgorithm {
    /**
     * Generate a password of the requested length using the concrete algorithm.
     * 
     * @param length the desired length of the password (must be > 0)
     * @return a generated password string of the specified length
     * @throws IllegalArgumentException if length <= 0 (optional, implementation-specific)
     */
    String generate(int length);
}
