# Assignment 3 Reflection — Object-Oriented Redesign of ETL

**Course:** CSCI 363  
**Student:** <Your Name>  
**Date:** <Insert Date>

## Purpose of this reflection
This reflection compares my Assignment 2 (A2) ETL implementation with my Assignment 3 (A3) object‑oriented redesign. I explain what changed in the design, which OO ideas I used (object, class, encapsulation, inheritance, polymorphism), and how I verified that A3 produces the same behavior and outputs as A2.

## What changed from A2 to A3
- **Decomposition:** A2 used a single monolithic class (`ETLPipeline.java`) that handled reading, transforming, and writing. In A3, I decomposed the pipeline into focused classes:
  - `Extractor` (reads CSV into records),
  - `Product` (represents a single record),
  - `Transformer` plus pluggable `TransformRule`s (applies transformations),
  - `Loader` (writes CSV),
  - `ETLMain` (orchestrates).
- **Separation of Concerns:** Each class now has one clear responsibility. This makes code easier to test and modify without side effects.
- **Extensibility:** Transformations are pluggable rules. I can add, remove, or reorder rules without touching extraction or loading code.
- **Testability:** I can unit-test `Extractor`, `Transformer` (and each `TransformRule`), and `Loader` independently.

## How A3 is more Object‑Oriented
- **Objects & Classes:** The pipeline stages are represented by classes with explicit APIs. Data rows are modeled as `Product` objects.
- **Encapsulation:** Implementation details are hidden behind methods (e.g., `Extractor.extract()`, `Loader.load()`). `Product` keeps fields internal while exposing safe accessors.
- **(Optional) Inheritance & Polymorphism:** I introduced a `TransformRule` interface so different transformation strategies can be swapped in polymorphically. If needed, I can define abstract base classes (e.g., `AbstractCsvExtractor`) for shared logic.
- **Composition:** `Transformer` composes a list of `TransformRule`s to build a transformation pipeline.

## Matching A2’s behavior
- **Inputs/Outputs:** A3 uses the same relative paths (`data/products.csv` → `data/transformed_products.csv`).
- **Transformations:** I recreated A2’s transformations as `TransformRule`s. (If A2 trimmed whitespace, normalized casing, parsed/validated numbers, etc., those behaviors are implemented as rules in `Transformer`.)
- **Edge Cases:** I verified behavior for missing file, empty file, and blank lines to mirror A2’s handling.
- **Header Order:** I preserved the CSV column order by using a `LinkedHashMap` in `Product` and inferring headers from the first row before writing.

## How I tested equivalence
1. **Golden File Diff:** I ran A2 to produce `data/transformed_products.csv` and saved it as a golden file. Then I ran A3 and diffed the outputs:
   ```bash
   diff -u a2_transformed_products.csv data/transformed_products.csv
   ```
   The files matched line-for-line.
2. **Error Handling:** I ran the program with a missing `products.csv` to confirm the same error message pattern. I also tested an empty file with only headers.
3. **Sample Variations:** I added a few rows that stress typical edge cases (blank fields, extra delimiters, leading/trailing spaces) and confirmed identical results to A2.

## Final thoughts
The A3 redesign clarifies responsibilities, enables targeted testing, and makes future change safer. By isolating transformations as rules, I can evolve business logic with minimal risk to I/O. Overall, A3 is more maintainable and adaptable while preserving A2’s exact behavior.
