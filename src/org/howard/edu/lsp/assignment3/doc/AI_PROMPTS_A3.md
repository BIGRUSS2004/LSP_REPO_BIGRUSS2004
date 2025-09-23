# AI Prompts & Excerpts — Assignment 3

Below are several prompts I used with a generative AI assistant during my A3 redesign, plus short excerpts of the responses. I reviewed and adapted the suggestions and verified correctness.

---
**Prompt 1:**  
*“How can I refactor a single-file ETL pipeline into an object-oriented design while keeping exactly the same I/O behavior?”*

**Excerpt:**  
“Split into Extractor, Transformer (with pluggable rules), Loader, a data model (Product), and a small ETLMain. Keep relative paths and mirror the exact transformations from A2.”

---
**Prompt 2:**  
*“Show a Java interface for a transformation rule that mutates a Product in place.”*

**Excerpt:**  
“Create a `TransformRule` interface with `Product apply(Product p)`. Then implement rules like `TrimWhitespaceRule`… Chain them in a `Transformer`.”

---
**Prompt 3:**  
*“What’s a simple way to preserve CSV column order through a pipeline without hard-coding headers?”*

**Excerpt:**  
“Use a `LinkedHashMap<String,String>` in `Product` to preserve insertion order. Infer headers from the first record before writing.”

---
**Prompt 4:**  
*“Suggest tests to confirm A3 output matches A2 exactly.”*

**Excerpt:**  
“Golden file diff, missing/empty input cases, and a few edge rows (leading/trailing spaces, blank fields). Confirm error messages are consistent.”

---
**Prompt 5:**  
*“Where should I add Javadocs and what should they include?”*

**Excerpt:**  
“Add Javadocs to every public class and method. Document parameters, return values, error cases, and the responsibility of each class. Verify accuracy after editing.”
