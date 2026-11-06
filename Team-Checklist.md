# Lexicon Vending Machine — Final Project Checklist

**Project:** Java 25 console vending-machine workshop  
**Repository:** https://github.com/Padmaqaauto/Workshop-Vending-Machine  
**Team:** Anas, Carl, Jay, Padma, Tukue, Veselina  
**Status reviewed:** 6 November 2026  
**Evidence:** merged PRs #20, #24, #25, #26, #27; GitHub Actions main run [42223182591](https://github.com/Padmaqaauto/Workshop-Vending-Machine/actions/runs/42223182591); confirmation of a working local executable JAR.

`[x]` = confirmed by repository, automated tests or team feedback. `[ ]` = requires final confirmation or external action. Planned improvements are not submission requirements.

## 1. Application architecture and OOP

- [x] Abstract `Product` with `Snack`, `Beverage` and `Fruit` subclasses.
- [x] Encapsulated, validated product fields and polymorphic `describe()` implementations.
- [x] `ProductCatalog` uses `ArrayList<Product>`, ID-based lookup, and an unmodifiable returned list.
- [x] `VendingMachine` interface and `VendingMachineImpl` separate domain behavior from console I/O.
- [x] `Payment` manages coin denominations, balance, deductions and refunds.
- [x] `PurchaseResult` and `PurchaseStatus` represent business outcomes.
- [x] `Main` initializes application dependencies; `ConsoleUI` handles interaction.
- [x] Core Java OOP, interfaces, inheritance, encapsulation, polymorphism, collections, loops, conditionals and `Scanner` are demonstrated.

## 2. Products and inventory — Jay / team

- [x] `Product` validates ID, name, price and initial quantity.
- [x] `Snack` holds weight; `Beverage` holds volume; `Fruit` holds origin.
- [x] `dispenseOne()` reduces stock only when available.
- [x] Product catalog rejects null and duplicate product IDs.
- [x] Product lookup and catalog list are tested.
- [x] Optional improvement: `Product.restock(int)` validates positive amounts and is covered by tests.
- [x] Restocking is documented as an **API-level improvement**; it is not a console menu option.

## 3. Payment and purchase results — Carl, Padma / team

- [x] Accepted coins: 1, 2, 5, 10, 20 and 50 kr.
- [x] Invalid coins do not change the balance.
- [x] Valid coins accumulate correctly.
- [x] `deductAmount(int)` returns a boolean and leaves funds unchanged on failure.
- [x] `returnChange()` returns the balance and resets it to zero.
- [x] Purchase status values: `SUCCESS`, `NOT_FOUND`, `INSUFFICIENT_BALANCE`, `OUT_OF_STOCK`.
- [x] `PurchaseResult` returns the product via `Optional<Product>` and validates status/amount combinations.
- [x] The two earlier purchase-result validation regressions were fixed and retested.

## 4. Vending machine and console — Tukue, Veselina / team

- [x] Product lookup, successful purchase, insufficient balance, out of stock and unknown ID handled.
- [x] Failed purchases preserve stock and funds.
- [x] Successful purchase deducts payment, reduces stock and returns any change.
- [x] Console lists products, descriptions, prices, quantities and available balance.
- [x] Console accepts coins and product IDs, handles invalid input, allows refunds and exits cleanly.
- [x] Duplicate `Main` class removed; the entry point is `se.lexicon.Main`.
- [x] Maven JAR manifest references the correct entry point.
- [x] Executable JAR successfully generated and manually launched.
- [x] Console application was manually tested and reported working.

## 5. Automated verification — Anas, Carl / team

- [x] Tests cover products, subclasses, catalog, payment, purchase outcomes, machine logic and console behavior.
- [x] Mandatory vending-machine business scenarios are included.
- [x] **Latest verified GitHub Actions result: 92 tests, 0 failures, 0 errors, 0 skipped.**
- [x] Maven compilation, testing and JAR packaging succeed in CI.
- [x] GitHub Actions successfully uploads the JAR artifact.
- [x] JUnit contribution merged through PR #20.
- [ ] Attach a final screenshot or CI evidence to the report **only if required** by the instructor.

## 6. Integration and GitHub

- [x] Business logic, product catalog, payment and test contributions merged to `main`.
- [x] Console implementation merged to `main` (PRs #24 and #26).
- [x] Restocking contribution merged to `main` (PR #25).
- [x] Final documentation PR #27 merged into `main` (commit `f7cd4e5`).
- [x] Local `main` synchronized with `origin/main` after PR #27; clean working tree reported.
- [x] Latest checked GitHub Actions run for `main` passed.
- [x] Team lead confirms there are no submission-blocking unresolved PRs or issues.

## 7. Documentation

- [x] README updated with accurate build/run instructions, architecture and class responsibilities.
- [x] README Mermaid UML corrected to reflect the implemented class/package structure.
- [x] OOP principles, purchasing flow, tests, JAR and optional restocking documented.
- [x] `Team-Checklist.md` prepared and merged in PR #27.
- [x] `report.md` exists and records team work and project development.
- [x] `improvement.md` documents proposed future features.
- [ ] Team lead confirms that the final report is accurate, complete and approved.

## 8. Final submission

- [x] Required implementation and core functionality completed.
- [x] Automated test suite passing on `main`.
- [x] Executable JAR built and run locally.
- [x] Documentation changes merged into `main`.
- [ ] Team lead gives final submission approval.
- [ ] Submit repository link and any required report/JAR/test evidence to the instructor.

## Team completion overview

| Area | Status | Evidence or action |
|---|---|---|
| Product model and catalog | Complete | Implemented and tested |
| Payment and purchase outcomes | Complete | Implemented and tested |
| Vending machine business logic | Complete | Implemented and tested |
| Console UI and Main | Complete | Merged, CI passing, manual run confirmed |
| Optional product restocking | Complete (API only) | Implemented and tested |
| JUnit suite | Complete | 92/92 passing in latest verified CI |
| Runnable JAR | Complete | Packaged, uploaded as CI artifact, manually run |
| README / final UML | Complete | PR #27 merged |
| Project checklist | Updated; merge this revision if adopted | This final status correction |
| Report and coding standards | Final review | Report approval and historical guide cleanup pending |
| Instructor submission | Pending | Team lead to confirm and submit |

## Definition of done

**Development complete:** The code compiles; required product, payment, vending and console functionality works; automated tests pass; and the executable JAR runs.

**Submission complete:** The team lead accepts the report and final documentation, resolves any submission blockers and delivers the required repository link and artifacts to the instructor.
