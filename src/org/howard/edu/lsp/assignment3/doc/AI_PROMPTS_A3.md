# AI Prompts (Excerpts)

**Prompt:**  
"Redesign my A2 CSV ETL (Java) into an OO version that keeps the same input/output and transformations. I want small classes, one public type per file, and a rules pipeline."

**AI (excerpt):**  
"Create `Extractor`, `Loader`, and a `TransformerRule` interface, then implement rules: UppercaseName, ElectronicsDiscount, PremiumUpgrade, PriceRange..."

---

**Prompt:**  
"Ensure rounding is HALF_UP to 2 decimals *after* the 10% discount and before category upgrade logic."

**AI (excerpt):**  
"After discount, call `setScale(2, HALF_UP)`, then check `> 500` for the premium category."

---

**Prompt:**  
"Provide VS Code + PowerShell commands that won’t break with backslashes in git adds."

**AI (excerpt):**  
"Use separate commands (no line continuation). Example: `git add src\org\howard\edu\lsp\assignment3`, `git commit -m 'feat(...)'` ..."
