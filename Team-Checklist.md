# Lexicon Vending Machine — Final Project Checklist

**Project:** Java 25 console vending-machine workshop  
**Repository:** https://github.com/Padmaqaauto/Workshop-Vending-Machine  
**Team:** Anas, Carl, Jay, Padma, Tukue, Veselina  
**Review date:** 6 November 2026  
**Evidence:** Current `main` source, merged pull requests, GitHub Actions run [42223182591](https://github.com/Padmaqaauto/Workshop-Vending-Machine/actions/runs/42223182591), and team confirmation that the JAR works locally.

**Status notation:** `[x]` = evidenced/confirmed; `[ ]` = still needs an explicit final check, decision, or submission action. This checklist records the **implemented application**, not an obsolete proposed API.

## 1. Architecture and OOP design

- [x] Agree on and implement the product hierarchy: abstract `Product` and `Snack`, `Beverage`, `Fruit` subclasses.
- [x] Encapsulate product identity, price, and stock; validate constructor arguments.
- [x] Use `describe()` for polymorphic product descriptions.
- [x] Implement `VendingMachine` interface and `VendingMachineImpl`.
- [x] Store products in `ProductCatalog` with `ArrayList<Product>`.
- [x] Use lookup by unique product ID and return an unmodifiable list snapshot.
- [x] Separate business rules from console input/output.
- [x] Use classes, objects, inheritance, interfaces, encapsulation, polymorphism, collections, loops, conditionals, and `Scanner`.
- [x] Match the README's final UML to actual packages, class responsibilities, method names, and return types (prepared in the revised README accompanying this checklist).
- [x] Team confirms and merges the revised README/UML documentation.

## 2. Product model and inventory — Jay / team

- [x] `Product`: `id`, `name`, `price`, `quantity`, validated constructor, getters.
- [x] `Product.describe()` is abstract and implemented by each concrete product.
- [x] `Snack`: weight in grams; `Beverage`: volume in milliliters; `Fruit`: origin.
- [x] `dispenseOne()` reduces stock by one only when available.
- [x] Product stock never becomes negative through dispensing.
- [x] `ProductCatalog.addProduct()` rejects null and duplicate IDs.
- [x] `ProductCatalog.getProductById()` returns `Optional<Product>`.
- [x] `ProductCatalog.getAllProducts()` returns an unmodifiable snapshot.
- [x] Optional enhancement: `Product.restock(int amount)` exists and rejects zero/negative amounts.
- [x] Restocking-related product tests are included in the green suite.
- [ ] If restocking is presented as an end-user feature, clarify that it is **API-only**, not an option in the current console menu.

## 3. Payment — Carl / team

- [x] Private integer balance maintained by `Payment`.
- [x] Accept coins of 1, 2, 5, 10, 20, and 50 kr only.
- [x] Invalid coins leave the balance unchanged.
- [x] Valid inserted coins increase the balance.
- [x] `getBalance()` reports current funds.
- [x] `deductAmount(int)` returns `boolean`, rejects nonpositive or unaffordable deductions, and preserves the balance on failure.
- [x] `returnChange()` returns remaining money and resets the balance to zero.
- [x] Payment behavior and failures are covered by unit tests.

## 4. Purchase outcome — Padma / team

- [x] `PurchaseStatus` has `SUCCESS`, `NOT_FOUND`, `INSUFFICIENT_BALANCE`, `OUT_OF_STOCK`.
- [x] `PurchaseResult` stores final status, product reference, missing amount, and change returned.
- [x] `getProduct()` returns `Optional<Product>`; the stored field itself is a `Product`, not an `Optional`.
- [x] Static factory methods exist for each purchase outcome.
- [x] Constructor rejects null status, invalid amounts, and contradictory status/product combinations.
- [x] Successful purchase cannot have a positive missing amount.
- [x] Insufficient-balance result requires a positive missing amount.
- [x] Purchase-result tests pass.

## 5. Vending-machine operations — Tukue / Veselina / team

- [x] Implementation receives `ProductCatalog` and `Payment` through its constructor.
- [x] Machine interface exposes coin insertion, balance, product listing, purchase by ID, and change return.
- [x] Product IDs are resolved through the catalog.
- [x] Unknown products return `NOT_FOUND`.
- [x] Sold-out products return `OUT_OF_STOCK`.
- [x] Insufficient funds return `INSUFFICIENT_BALANCE` with the missing amount.
- [x] Failed purchases do not change stock or balance.
- [x] Successful purchase deducts the price, dispenses one item, and returns automatic change.
- [x] Machine business logic contains no console input/output.
- [x] Product types are handled polymorphically, without `instanceof` selection.
- [x] Unit tests cover exact payment, overpayment, invalid input, unknown IDs, low balance, stock depletion, and change return.

## 6. Console application and executable JAR — Tukue / Veselina / team

- [x] `se.lexicon.Main` initializes sample products, catalog, payment, machine, and UI.
- [x] `se.lexicon.ui.ConsoleUI` contains the interactive menu.
- [x] Displays welcome message, products, descriptions, prices, stock, and balance.
- [x] Supports coin insertion, purchasing by product ID, manual change return, and exit.
- [x] Handles invalid menu and numeric input.
- [x] Displays purchase success and relevant failure messages.
- [x] Returns remaining balance when exiting or when input ends.
- [x] Only one application `Main` class remains; previous duplicate-class compilation error was resolved.
- [x] `pom.xml` declares the JAR manifest main class as `se.lexicon.Main`.
- [x] `mvn clean verify` generates the application JAR.
- [x] GitHub Actions uploads the JAR as `vending-machine-jar`.
- [x] A team member confirmed the executable application launches and works locally.
- [x] Before instructor demonstration, perform the complete live menu walkthrough from the packaged JAR once more.

## 7. JUnit testing — Anas / Carl / team

- [x] `PaymentTest` covers coin and balance behavior.
- [x] `ProductTest`, `SnackTest`, `BeverageTest`, and `FruitTest` cover product validation and behavior.
- [x] `ProductCatalogTest` covers lookup, duplicates, ordering, and immutability of returned lists.
- [x] `PurchaseResultTest` covers statuses and constructor invariants.
- [x] `VendingMachineImplTest` covers machine operations and failure safeguards.
- [x] `ConsoleUITest` covers menu output, valid and invalid inputs, purchase, refund, and input closure.
- [x] Seven original mandatory vending-machine scenarios are covered.
- [x] **Final verified CI result: 92 tests, 0 failures, 0 errors, 0 skipped.**
- [x] Maven `clean verify` passed on the latest reviewed `main` commit.
- [x] Previously failing purchase-result validation tests were resolved and merged.
- [ ] Save/share a final test-run screenshot or CI link with the team report if requested by the instructor.

## 8. GitHub integration and code review

- [x] Use feature branches and pull requests for implementation/testing work.
- [x] Merge JUnit contribution (PR #20) to `main`.
- [x] Merge console application contribution (PR #24) to `main`.
- [x] Resolve duplicate `Main` compilation failure.
- [x] Pass automated GitHub Actions compilation/tests after integration.
- [x] Upload executable JAR artifact in CI.
- [ ] Obtain any remaining team/instructor-required review sign-offs.
- [ ] Merge final documentation updates into `main`.
- [ ] Confirm the final repository has no outstanding submission-blocking PRs or issues.

## 9. Documentation and final report

- [x] Prepare revised README with project introduction, prerequisites, build/run commands, and feature summary.
- [x] Prepare corrected UML diagram and actual API/class responsibility table in revised README.
- [x] Document OOP concepts, purchase workflow, console behavior, and JUnit execution.
- [x] Document optional `restock()` method and distinguish it from proposed future enhancements.
- [x] Existing `report.md` records meetings, contributions, development, and team responsibilities.
- [x] Existing `improvement.md` documents possible future improvements.
- [x] Team reviews the revised README and checklist and merges them.
- [x] Team reviews `report.md` for final accuracy and completeness, including recent merges, the final 92-test result, and optional restocking.
- [x] Correct remaining historical differences in `CodingStandards.md` or label its proposed package structure as superseded by the implemented code.
- [ ] Obtain team lead approval for final documentation and report.

## 10. Final submission and handoff

- [x] Core product, payment, purchase, inventory, machine, and console features are implemented.
- [x] Automated tests pass on `main`.
- [x] Executable JAR is produced successfully.
- [x] Team member reports the JAR works interactively.
- [x] Final manual walkthrough: list products, valid/invalid coin, exact payment, overpayment, insufficient balance, out of stock, unknown ID, manual refund, exit.
- [ ] Merge documentation corrections and check `main` remains green.
- [ ] Team lead reviews final report and submission contents.
- [ ] Share the final repository link and any requested evidence/JAR with the instructor.

## 11. Current team completion overview

| Area | State | Evidence / next action |
|---|---|---|
| Product model and catalog | Complete | Implemented; JUnit passing |
| Payment and purchasing | Complete | Implemented; JUnit passing |
| Vending-machine logic | Complete | Implemented; JUnit passing |
| Console UI and Main | Complete | Merged PR #24; console tests passing |
| JUnit suite | Complete | 92/92 passing in GitHub Actions |
| Runnable JAR | Complete | CI artifact generated; locally reported working |
| README and UML | Draft ready | Review and merge the revised README |
| Project checklist | Draft ready | Review and merge this file |
| Report / standards consistency | Needs review | Finalize report; reconcile older planning docs |
| Final instructor submission | Pending | Team lead approval and submission |

## Definition of done

**Application build complete:** Implemented classes compile, automated tests pass, the runnable JAR is produced, and the interactive program works.

**Workshop submission complete:** Final diagrams and documentation match the code, manual acceptance results are confirmed, report is approved, and the repository is delivered to the instructor.
