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
| Jay | Product Model | Product, Snack, Beverage, Fruit | Product hierarchy and inheritance |
|Padma | PurchaseResult | PurchaseResult, PurchaseStatus |Purchase processing |
| Carl | Payment Management | Payment | Coin validation and balance management |
| Member 4 | Vending Machine Logic | VendingMachine, VendingMachineImpl | Machine operations |
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

- [x] Finalize the domain model diagram.
- [x] Finalize the UML class diagram.
- [x] Confirm all class names and responsibilities.
- [x] Confirm which classes are abstract.
- [x] Confirm which classes are interfaces.
- [x] Confirm inheritance relationships.
- [x] Confirm composition and associations.
- [x] Confirm which class owns each responsibility.
- [x] Agree on all fields and data types.
- [x] Agree on constructors and validation rules.
- [x] Agree on method names, parameters, and return types.
- [x] Ensure all six members understand the architecture.

**Rule:** The approved UML diagram is the reference for implementation. No member should independently change the design without communicating with the team.

## 3.2 Finalize Package Structure

All members must follow the same Java package structure.

```text
Workshop-Vending-Machine/
│
├── README.md
├── checklist.md
├── .gitignore
├── pom.xml                         
│
├── docs/
│   ├── domain-model.png
│   ├── uml-class-diagram.png
│   └── final-report.md
│
└── src/
    │
    ├── main/
    │   └── java/
    │       └── se/
    │           └── lexicon/
    │               └── vendingmachine/
    │                   │
    │                   ├── app/
    │                   │   └── Main.java
    │                   │
    │                   ├── product/
    │                   │   ├── Product.java
    │                   │   ├── Snack.java
    │                   │   ├── Beverage.java
    │                   │   └── Fruit.java
    │                   │
    │                   ├── payment/
    │                   │   ├── Payment.java
    │                   │   └── CoinDenomination.java
    │                   │
    │                   ├── purchase/
    │                   │   ├── Purchase.java
    │                   │   └── PurchaseStatus.java
    │                   │
    │                   ├── machine/
    │                   │   ├── VendingMachine.java
    │                   │   └── VendingMachineImpl.java
    │                   │
    │                   └── ui/
    │                       └── ConsoleUI.java
    │
    └── test/
        └── java/
            └── se/
                └── lexicon/
                    └── vendingmachine/
                        │
                        ├── product/
                        │   ├── SnackTest.java
                        │   ├── BeverageTest.java
                        │   └── FruitTest.java
                        │
                        ├── payment/
                        │   └── PaymentTest.java
                        │
                        ├── purchase/
                        │   └── PurchaseTest.java
                        │
                        └── machine/
                            └── VendingMachineImplTest.java