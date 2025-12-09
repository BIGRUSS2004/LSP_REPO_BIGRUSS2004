package org.howard.edu.lsp.finale.question1;

import java.util.HashMap;
import java.util.Map;

/**
 * PasswordGeneratorService is the Singleton service class that manages password generation.
 * 
 * DESIGN PATTERNS IMPLEMENTED:
 * 
 * 1. SINGLETON PATTERN:
 *    - Ensures only ONE instance of PasswordGeneratorService exists in the application
 *    - Provides global access point via getInstance() method
 *    - Thread-safe lazy initialization using double-checked locking
 *    - Prevents direct instantiation with private constructor
 * 
 * 2. STRATEGY PATTERN:
 *    - Holds a PasswordAlgorithm (the strategy interface)
 *    - Allows runtime selection of different password generation algorithms
 *    - Delegates password generation to the selected strategy (BasicPasswordAlgorithm,
 *      EnhancedPasswordAlgorithm, or LettersPasswordAlgorithm)
 *    - Algorithms can be swapped without modifying this service class
 * 
 * Benefits:
 * - Single point of control for password generation across the application
 * - Loose coupling: service doesn't depend on concrete algorithm implementations
 * - Flexibility: new algorithms can be added without changing client code
 * - Thread-safe: all instances share the same Singleton safely
 */
public class PasswordGeneratorService {
    // Singleton instance: static, volatile ensures visibility across threads
    // volatile keyword ensures all threads see the most recent value
    private static volatile PasswordGeneratorService instance;
    
    // Current strategy (algorithm) to use for password generation
    // This changes when the user calls setAlgorithm()
    private PasswordAlgorithm algorithm;

    // HashMap that stores all available password generation algorithms
    // Key: algorithm name ("basic", "enhanced", "letters")
    // Value: instance of the corresponding PasswordAlgorithm implementation
    private static final Map<String, PasswordAlgorithm> algorithms = new HashMap<>();

    // Static initializer block: runs once when the class is loaded
    // Pre-populates the algorithms HashMap with all available strategies
    static {
        // Register the "basic" strategy: digits-only password generator
        algorithms.put("basic", new BasicPasswordAlgorithm());
        
        // Register the "enhanced" strategy: secure alphanumeric password generator
        algorithms.put("enhanced", new EnhancedPasswordAlgorithm());
        
        // Register the "letters" strategy: letters-only password generator
        algorithms.put("letters", new LettersPasswordAlgorithm());
    }

    // Private constructor: prevents instantiation from outside the class
    // Ensures the Singleton pattern is enforced
    private PasswordGeneratorService() {}

    /**
     * getInstance() method: provides the global access point to the Singleton instance.
     * 
     * Implements double-checked locking for thread-safe lazy initialization:
     * - First check (if instance == null) avoids synchronization overhead if instance exists
     * - synchronized block ensures only one thread creates the instance
     * - Second check (if instance == null) ensures instance wasn't created by another thread
     * 
     * @return the single instance of PasswordGeneratorService
     */
    public static PasswordGeneratorService getInstance() {
        // First check: if instance already exists, return it immediately (fast path)
        if (instance == null) {
            // Synchronize: only one thread can execute this block at a time
            synchronized (PasswordGeneratorService.class) {
                // Second check: double-check in case another thread created instance
                if (instance == null) {
                    // Create the Singleton instance only once
                    instance = new PasswordGeneratorService();
                }
            }
        }
        // Return the instance (either pre-existing or just created)
        return instance;
    }

    /**
     * setAlgorithm(String name): selects the password generation strategy by name.
     * 
     * This method implements the Strategy pattern by allowing runtime selection
     * of which PasswordAlgorithm to use for subsequent password generation.
     * 
     * @param name the name of the algorithm to select ("basic", "enhanced", or "letters")
     * @throws IllegalStateException if the algorithm name is not recognized
     */
    public void setAlgorithm(String name) {
        // Check if name is null or not in the algorithms HashMap
        if (name == null || !algorithms.containsKey(name)) {
            // Throw exception if algorithm is not supported
            throw new IllegalStateException("Algorithm not supported");
        }
        // Retrieve the PasswordAlgorithm from the HashMap and set it as the current strategy
        this.algorithm = algorithms.get(name);
    }

    /**
     * generatePassword(int length): generates a password using the selected algorithm.
     * 
     * This method delegates password generation to the current strategy (PasswordAlgorithm).
     * The actual generation logic is determined by which concrete implementation is selected.
     * 
     * @param length the desired length of the generated password
     * @return a randomly generated password of the specified length
     * @throws IllegalArgumentException if length is not positive (length <= 0)
     * @throws IllegalStateException if no algorithm has been selected via setAlgorithm()
     */
    public String generatePassword(int length) {
        // Validate that the requested length is positive
        if (length <= 0) {
            // Throw exception for invalid length
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        
        // Check if a strategy (algorithm) has been selected
        if (this.algorithm == null) {
            // Throw exception if no algorithm is set
            throw new IllegalStateException("No algorithm selected.");
        }
        
        // Delegate password generation to the selected algorithm strategy
        // The algorithm's generate() method does the actual work
        return algorithm.generate(length);
    }
}
