# Lexicon Vending Machine — Team Development Guide

## 1. Project Overview

We are developing a Java-based Vending Machine application as part of the Lexicon OOP workshop.

The application will manage three categories of products:

- Snacks
- Beverages
- Fruits

The vending machine will accept predefined Swedish coin denominations, maintain a balance, manage product stock, process purchases, and return change.

The application will use a **console menu as its only user interface**.

### Technical Scope

We will use:

- Core Java
- Object-Oriented Programming (OOP)
- Classes and Objects
- Abstract Classes and Interfaces
- Inheritance
- Encapsulation
- Polymorphism
- Composition
- List and ArrayList
- For loops and while loops
- If/else and switch statements
- Scanner for console input
- JUnit for unit testing
- Git and GitHub for collaboration

We will also package the completed application as a runnable JAR that can be launched from the command line.

We will avoid unnecessary frameworks, graphical interfaces, databases, Maps, Streams, and other technologies outside the agreed workshop scope.

---

# 2. Team Allocation

The project is divided into six modules to enable parallel development.

| Member | Module | Assigned Classes | Responsibility |
|---|---|---|---|
| Member 1 | Product Model | Product, Snack, Beverage, Fruit | Product hierarchy and inheritance |
| Member 2 | Product Catalog | ProductCatalog | Product collection and inventory lookup |
| Member 3 | Payment Management | Payment | Coin validation and balance management |
| Member 4 | Vending Machine Logic | VendingMachine, VendingMachineImpl, PurchaseResult | Purchase processing and machine operations |
| Member 5 | Console Application | ConsoleUI, Main | Console menu, application initialization, runnable JAR |
| Member 6 | JUnit Testing | VendingMachineImplTest | All mandatory and additional business-logic tests |

### Team Lead Responsibilities

The team lead will coordinate the overall development process.

Responsibilities include:

- Finalizing the domain model and UML diagram.
- Ensuring all members follow the approved architecture.
- Confirming shared class and method contracts.
- Monitoring individual module progress.
- Coordinating integration activities.
- Managing Pull Request reviews.
- Resolving development dependencies and conflicts.
- Managing the final README and project report.
- Verifying the final application and runnable JAR.
- Preparing the repository for instructor submission.

**Important:** Member 6 is responsible for implementing all JUnit test cases. Other members should share their class contracts and expected behaviour with Member 6.

---

# 3. Parallel Development — Before Coding Starts

Before starting individual development, all members must agree on the following.

## 3.1 Finalize the Architecture

- [ ] Finalize the domain model diagram.
- [ ] Finalize the UML class diagram.
- [ ] Confirm all class names and responsibilities.
- [ ] Confirm which classes are abstract.
- [ ] Confirm which classes are interfaces.
- [ ] Confirm inheritance relationships.
- [ ] Confirm composition and associations.
- [ ] Confirm which class owns each responsibility.
- [ ] Agree on all fields and data types.
- [ ] Agree on constructors and validation rules.
- [ ] Agree on method names, parameters, and return types.
- [ ] Ensure all six members understand the architecture.

**Rule:** The approved UML diagram is the reference for implementation. No member should independently change the design without communicating with the team.

## 3.2 Agree on Class and Method Contracts

Parallel development requires all modules to communicate using agreed method signatures.

The following is the proposed contract:

| Class | Method | Responsibility |
|---|---|---|
| Product | getDescription() | Return product-specific description |
| Product | reduceQuantity() | Reduce product stock |
| Product | isInStock() | Check stock availability |
| ProductCatalog | addProduct(Product) | Add a product |
| ProductCatalog | getProductById(int) | Find product by ID |
| ProductCatalog | getAllProducts() | Return all products |
| Payment | insertCoin(int) | Validate and insert a coin |
| Payment | getBalance() | Return current balance |
| Payment | returnChange() | Return and reset balance |
| VendingMachine | insertCoin(int) | Accept coin through machine |
| VendingMachine | getBalance() | Return current machine balance |
| VendingMachine | getProducts() | Return available products |
| VendingMachine | purchaseProduct(int) | Process product purchase |
| VendingMachine | returnChange() | Return remaining balance |

Before implementation:

- [ ] Confirm exact method names.
- [ ] Confirm parameter types.
- [ ] Confirm return types.
- [ ] Confirm constructor parameters.
- [ ] Confirm exception and validation behaviour.
- [ ] Confirm how purchase success and failure are communicated.
- [ ] Share the finalized contracts with all members.
- [ ] Ensure dependent members use the same method signatures.

The final method contracts must be agreed upon by the team before coding starts.

## 3.3 Finalize Package Structure

All members must follow the same Java package structure.

```text
src/
├── main/
│   └── java/
│       └── se/lexicon/vendingmachine/
│           │
│           ├── model/
│           │   ├── Product.java
│           │   ├── Snack.java
│           │   ├── Beverage.java
│           │   └── Fruit.java
│           │
│           ├── inventory/
│           │   └── ProductCatalog.java
│           │
│           ├── payment/
│           │   └── Payment.java
│           │
│           ├── service/
│           │   ├── VendingMachine.java
│           │   ├── VendingMachineImpl.java
│           │   └── PurchaseResult.java
│           │
│           └── ui/
│               ├── ConsoleUI.java
│               └── Main.java
│
└── test/
    └── java/
        └── se/lexicon/vendingmachine/
            └── VendingMachineImplTest.java