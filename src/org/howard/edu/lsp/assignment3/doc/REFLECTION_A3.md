# Reflection: Assignment 2 vs Assignment 3

**Design differences.**  
A2 implemented extraction, transformation, and loading in a single class (`ETLPipeline`) using arrays of strings. A3 decomposes the solution into cohesive classes and interfaces: `Extractor`/`CSVExtractor`, `TransformerRule` implementations (`UppercaseNameRule`, `ElectronicsDiscountRule`, `PremiumElectronicsUpgradeRule`, `PriceRangeRule`), a coordinating `ProductTransformer`, `Loader`/`CSVLoader`, and a small `ETLMain`. The domain is explicit via a `Product` object that encapsulates state.

**More object-oriented.**  
- *Encapsulation:* `Product` hides fields behind getters/setters; `CSVExtractor` and `CSVLoader` hide I/O concerns.  
- *Abstraction & Polymorphism:* `TransformerRule` is a common interface; the transform pipeline iterates a `List<TransformerRule>`, so each rule is swappable/extendable without changing orchestration.  
- *Separation of concerns:* Parsing, business rules, and output are independent units.  
- *Open/Closed Principle:* New rules can be added by creating another `TransformerRule` without editing existing ones.

**OO ideas used.**  
Object (`Product`), Class/Interface (`Extractor`, `Loader`, `TransformerRule`), Encapsulation (private fields), Polymorphism (rules list), and a light use of composition over inheritance (rules composed into the transformer).

**Behavioral equivalence.**  
A3 preserves A2’s required behavior:  
1) Uppercase `Name`.  
2) If original `Category == Electronics`, apply 10% discount then round HALF_UP to 2 decimals.  
3) If final price > 500 and original category was *Electronics*, set `Category = "Premium Electronics"`.  
4) Derive `PriceRange` from final price: `Low` (≤10), `Medium` (≤100), `High` (≤500), else `Premium`.  
It also preserves header detection, skipping invalid rows, relative paths, and summary output.

**Testing performed.**  
- Compared A3 output with A2 output for the same `data/products.csv` using a file diff.  
- Verified edge cases: missing file (FileNotFound error message), empty input (header-only output), and invalid price rows (skipped count increments).

**AI usage.**  
I used a generative AI assistant to sketch the OO decomposition (interfaces, rules pattern) and to draft Javadocs, then I reviewed and adjusted naming, rule order, and rounding semantics to ensure exact A2 parity.
