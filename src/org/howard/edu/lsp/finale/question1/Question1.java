package org.howard.edu.lsp.finale.question1;

/**
 * Question1 demonstrates the Strategy and Singleton design patterns in action.
 * 
 * This class serves as the driver/main class that:
 * 1. Obtains the Singleton instance of PasswordGeneratorService
 * 2. Selects different password generation strategies (algorithms)
 * 3. Generates sample passwords using each strategy
 * 4. Displays the results to show how different strategies produce different outputs
 * 
 * OUTPUT DEMONSTRATION:
 * This class demonstrates all three available password generation strategies:
 * - BasicPasswordAlgorithm: generates numeric passwords (0-9 only)
 * - LettersPasswordAlgorithm: generates alphabetic passwords (A-Z, a-z only)
 * - EnhancedPasswordAlgorithm: generates secure alphanumeric passwords (A-Z, a-z, 0-9)
 */
public class Question1 {
    /**
     * main(String[] args): Entry point for the application.
     * 
     * This method demonstrates:
     * 1. How to use the Singleton pattern to get the service instance
     * 2. How to use the Strategy pattern to select different algorithms
     * 3. How to generate passwords with different character sets
     * 
     * @param args command-line arguments (not used in this demo)
     */
    public static void main(String[] args) {
        // Print header message for the demonstration
        System.out.println("=== Question 1: Password Generator Demo ===\n");
        
        // Step 1: Get the Singleton instance of PasswordGeneratorService
        // This is the global access point to the password generation service
        // Only ONE instance exists in the entire application
        PasswordGeneratorService svc = PasswordGeneratorService.getInstance();
        
        // ===== STRATEGY 1: BASIC PASSWORD ALGORITHM (Digits Only) =====
        // Print section header for the first strategy
        System.out.println("--- Basic (Digits Only) ---");
        
        // Set the service to use the "basic" algorithm (numeric passwords)
        // This demonstrates the Strategy pattern: switching algorithms at runtime
        svc.setAlgorithm("basic");
        
        // Generate and print 3 sample passwords using the basic strategy
        // Each password is 10 characters long and contains only digits 0-9
        for (int i = 0; i < 3; i++) {
            // Call generatePassword(10) which delegates to BasicPasswordAlgorithm
            // The algorithm randomly selects digits and builds a 10-character password
            System.out.println("Password " + (i + 1) + ": " + svc.generatePassword(10));
        }
        
        // Print blank line for readability
        System.out.println();
        
        // ===== STRATEGY 2: LETTERS PASSWORD ALGORITHM (Letters Only) =====
        // Print section header for the second strategy
        System.out.println("--- Letters Only ---");
        
        // Set the service to use the "letters" algorithm (alphabetic passwords)
        // This demonstrates switching to a different strategy
        svc.setAlgorithm("letters");
        
        // Generate and print 3 sample passwords using the letters strategy
        // Each password is 10 characters long and contains only letters A-Z, a-z
        for (int i = 0; i < 3; i++) {
            // Call generatePassword(10) which delegates to LettersPasswordAlgorithm
            // The algorithm randomly selects letters and builds a 10-character password
            System.out.println("Password " + (i + 1) + ": " + svc.generatePassword(10));
        }
        
        // Print blank line for readability
        System.out.println();
        
        // ===== STRATEGY 3: ENHANCED PASSWORD ALGORITHM (Alphanumeric, Secure) =====
        // Print section header for the third strategy
        System.out.println("--- Enhanced (Alphanumeric) ---");
        
        // Set the service to use the "enhanced" algorithm (secure alphanumeric passwords)
        // This demonstrates switching to the most secure strategy using SecureRandom
        svc.setAlgorithm("enhanced");
        
        // Generate and print 3 sample passwords using the enhanced strategy
        // Each password is 16 characters long (longer for increased security)
        // Contains uppercase, lowercase, and digits, generated with SecureRandom
        for (int i = 0; i < 3; i++) {
            // Call generatePassword(16) which delegates to EnhancedPasswordAlgorithm
            // The algorithm uses SecureRandom to randomly select from 62 characters
            // (26 uppercase + 26 lowercase + 10 digits) and builds a 16-character password
            System.out.println("Password " + (i + 1) + ": " + svc.generatePassword(16));
        }
        
        // Print blank line for readability
        System.out.println();
        
        // Print completion message
        System.out.println("Demo complete!");
        
        // NOTE ON DESIGN PATTERNS:
        // This class demonstrates practical usage of two design patterns:
        // 
        // SINGLETON PATTERN:
        // - PasswordGeneratorService.getInstance() always returns the same instance
        // - Ensures centralized control of password generation across the application
        // - Thread-safe implementation using double-checked locking
        //
        // STRATEGY PATTERN:
        // - PasswordGeneratorService holds a PasswordAlgorithm (the strategy interface)
        // - svc.setAlgorithm(name) switches the strategy at runtime
        // - Each algorithm (BasicPasswordAlgorithm, EnhancedPasswordAlgorithm,
        //   LettersPasswordAlgorithm) implements PasswordAlgorithm interface
        // - Client code (this class) doesn't need to know concrete algorithm details
    }
}
