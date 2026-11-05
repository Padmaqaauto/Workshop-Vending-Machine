# Lexicon Vending Machine — Project Completion Checklist

> **Project scope:** Core Java console application. The console menu is the only user interface. We will use basic Java concepts, classes, inheritance, encapsulation, polymorphism, List/ArrayList, loops, conditionals, switch statements, and Scanner.
>
> The application must also be packaged as a runnable JAR that can be launched from the command line.
>
> **Team size:** 6 Members [Anas, Carl, Jay, Padma, Tukue, Veselina ]
> **Target completion:** Monday  
> **GitHub Repository:** [Vending-Machine](https://github.com/Padmaqaauto/Workshop-Vending-Machine)

---

# 1. Team Allocation

| Member | Module | Responsibility |
|---|---|---|
| Member 1 | Product Model | Product, Snack, Beverage, Fruit |
| Member 2 | Product Catalog | ProductCatalog and List<Product> |
| Member 3 | Payment Management | Payment and coin validation |
| Member 4 | Vending Machine Logic | VendingMachine and VendingMachineImpl |
| Member 5 | Console Application | ConsoleUI, Main, and runnable JAR |
| Member 6 | JUnit Testing | All required JUnit test cases |

### Team Responsibilities

- [ ] Coordinate all six team members. - Padma
- [ ] Finalize the domain model and UML diagram.
- [x] Distribute and monitor tasks. - Padma
- [ ] Coordinate integration between modules.
- [ ] Organize coding reviews.
- [ ] Manage README - Padma
- [ ] Prepare Final Report 
- [ ] Verify that all work is committed and pushed to GitHub. 
- [ ] Ensure the final application and runnable JAR work correctly.
- [ ] Share the final GitHub repository with the instructor. - Padma

**Important:** Member 6 owns the JUnit test implementation. Members 1–5 should provide the expected behavior and class details to Member 6.

---

# 2. Project Planning and Design

- [x] All six members understand the workshop requirements.
- [x] Assign module responsibilities to all members.
- [ ] Agree on class names, fields, constructors, and method signatures.
- [ ] Agree on Java package structure.
- [ ] Identify relationships between all classes.
- [ ] Finalize the domain model diagram.
- [ ] Create the UML class diagram with fields and methods.
- [ ] Include inheritance relationships and multiplicities.
- [ ] Review the diagrams together.
- [ ] Ensure the diagrams match the planned implementation.
- [ ] Commit the approved diagrams to GitHub.

---

# 3. Member 1 — Product Model and Inheritance

**Classes:**
- `Product` (Abstract Class)
- `Snack`
- `Beverage`
- `Fruit`

## Product — Parent Class

**Fields:**

- [ ] `id`
- [ ] `name`
- [ ] `price`
- [ ] `quantity`

**Methods:**

- [ ] Constructor with validation.
- [ ] Getters for required fields.
- [ ] Abstract method `getDescription()`.
- [ ] Method to check product availability.
- [ ] Method to reduce stock by one.

## Subclasses

### Snack

- [ ] Add `weightGrams` field.
- [ ] Implement constructor.
- [ ] Override `getDescription()`.

### Beverage

- [ ] Add `volumeMl` field.
- [ ] Implement constructor.
- [ ] Override `getDescription()`.

### Fruit

- [ ] Add `origin` field.
- [ ] Implement constructor.
- [ ] Override `getDescription()`.

## Validation and Review

- [ ] Make all fields private.
- [ ] Validate constructor inputs.
- [ ] Ensure Product is abstract.
- [ ] Ensure subclasses inherit from Product.
- [ ] Ensure each subclass provides its own description.
- [ ] Share final class and method signatures with other members.

**OOP Concepts:**

- Abstraction
- Inheritance
- Encapsulation
- Method Overriding
- Polymorphism

**Relationships:**

- Snack, Beverage, and Fruit inherit from Product.
- ProductCatalog stores these objects through the common Product type.

---

# 4. Member 2 — Product Catalog and Inventory

**Class:** `ProductCatalog`

## Fields

- [ ] `List<Product> products`
- [ ] Initialize the list using `ArrayList<Product>`.

## Methods

- [ ] `addProduct(Product product)`
- [ ] `getProductById(int id)`
- [ ] `getAllProducts()`
- [ ] Prevent duplicate product IDs.
- [ ] Prevent null products from being added.
- [ ] Preserve insertion order when displaying products.
- [ ] Ensure the internal list cannot be modified unexpectedly by other classes.
- [ ] Share method signatures with Members 4 and 5.

## Implementation Concepts

- List
- ArrayList
- Generics
- For loops
- Encapsulation
- Object references

## Relationships

- One ProductCatalog contains multiple Product objects.
- ProductCatalog stores Snack, Beverage, and Fruit through the parent type Product.

**Important:** Use List/ArrayList and basic loops for product storage and ID searching. Do not introduce Maps, Streams, or databases.

---

# 5. Member 3 — Payment and Balance Management

**Class:** `Payment`

## Fields

- [ ] `balance` (int)

## Methods

- [ ] `insertCoin(int coin)`
- [ ] `isValidCoin(int coin)`
- [ ] `getBalance()`
- [ ] `returnChange()`
- [ ] `deductAmount(int amount)`

## Business Rules

- [ ] Accept only 1, 2, 5, 10, 20, and 50 kr.
- [ ] Reject invalid coins.
- [ ] Invalid coins must not change the balance.
- [ ] Valid coins must increase the balance.
- [ ] Return the remaining balance when requested.
- [ ] Reset balance to zero after returning change.
- [ ] Prevent invalid deductions.
- [ ] Ensure failed purchases do not deduct money.

## Implementation Concepts

- Encapsulation
- Conditional statements
- Switch statements
- Validation
- State management

## Relationships

- VendingMachineImpl has one Payment object.
- VendingMachineImpl uses Payment to manage the balance.

---

# 6. Member 4 — Vending Machine Business Logic

**Classes:**

- `VendingMachine` (Interface)
- `VendingMachineImpl`
- `PurchaseResult` (supporting class, if agreed)

## VendingMachine Interface

Define the required operations:

- [ ] Insert coin.
- [ ] Get current balance.
- [ ] Get product list.
- [ ] Purchase product by ID.
- [ ] Return change.

## VendingMachineImpl

### Fields

- [ ] `ProductCatalog productCatalog`
- [ ] `Payment payment`

### Methods

- [ ] Constructor to receive ProductCatalog and Payment.
- [ ] Implement all VendingMachine interface methods.
- [ ] Find a product using its ID.
- [ ] Handle unknown product IDs.
- [ ] Check product availability.
- [ ] Check whether the balance is sufficient.
- [ ] Calculate the missing amount when balance is insufficient.
- [ ] Deduct the product price after a successful purchase.
- [ ] Reduce product quantity by one after a successful purchase.
- [ ] Calculate and return automatic change when the balance exceeds the price.
- [ ] Ensure failed purchases do not modify balance or stock.
- [ ] Return a clear purchase result to the UI.
- [ ] Do not print console messages from this class.
- [ ] Do not use instanceof to identify product types.

## Implementation Concepts

- Interface
- Abstraction
- Encapsulation
- Composition
- Delegation
- Polymorphism
- Conditional statements

## Relationships

- VendingMachineImpl implements VendingMachine.
- VendingMachineImpl has one ProductCatalog.
- VendingMachineImpl has one Payment.
- ProductCatalog contains multiple Product objects.

---

# 7. Member 5 — Console Menu Application and Runnable JAR

**Classes:**

- `ConsoleUI`
- `Main`

## ConsoleUI

### Fields

- [ ] `VendingMachine vendingMachine`
- [ ] Scanner for user input.

### Responsibilities

- [ ] Display welcome message.
- [ ] Display all available products.
- [ ] Display product ID, name, price, description, and stock.
- [ ] Display current balance.
- [ ] Display the console menu.
- [ ] Accept coin input.
- [ ] Accept product selection.
- [ ] Provide an option to return change.
- [ ] Provide an option to exit.
- [ ] Use a loop to keep the application running.
- [ ] Use switch or if/else statements for menu selection.
- [ ] Handle invalid user input.
- [ ] Display clear error messages.
- [ ] Display successful purchase confirmation.
- [ ] Display automatic change returned.
- [ ] Display the balance after every action.
- [ ] Handle the zero-balance change return case.
- [ ] Keep business logic outside the UI.

## Main

### Responsibilities

- [ ] Create sample Snack objects.
- [ ] Create sample Beverage objects.
- [ ] Create sample Fruit objects.
- [ ] Create ProductCatalog.
- [ ] Add products to the catalog.
- [ ] Create Payment object.
- [ ] Create VendingMachineImpl.
- [ ] Create ConsoleUI.
- [ ] Start the console application.

## Runnable JAR Creation

- [ ] Configure the application to create an executable JAR.
- [ ] Ensure the JAR manifest specifies the correct Main class.
- [ ] Build the runnable JAR using IntelliJ IDEA or the project's build configuration.
- [ ] Verify that the JAR is generated successfully.
- [ ] Run the application from the command line using `java -jar`.
- [ ] Verify that the console menu starts correctly.
- [ ] Test coin insertion using the JAR.
- [ ] Test product selection and purchase using the JAR.
- [ ] Test insufficient balance and out-of-stock scenarios using the JAR.
- [ ] Test change return using the JAR.
- [ ] Document JAR build and execution instructions in README.md.

## Implementation Concepts

- Scanner
- Loops
- Switch
- If/else
- Object creation
- Dependency passing
- Separation of concerns

## Relationships

- Main creates and connects the application objects.
- ConsoleUI communicates with the VendingMachine interface.
- ConsoleUI does not directly manage stock or payment rules.

---

# 8. Member 6 — JUnit Testing

**Test Class:** `VendingMachineImplTest`

**Responsibility:** Member 6 will own and implement all JUnit test cases.

**Testing Rule:** Test VendingMachineImpl directly. Do not test ConsoleUI or console output.

## Mandatory Test Cases

### Test 1 — Insert Valid Coin

- [ ] Given balance = 0.
- [ ] When `insertCoin(10)` is called.
- [ ] Then balance should be 10.

### Test 2 — Reject Invalid Coin

- [ ] Given balance = 0.
- [ ] When `insertCoin(7)` is called.
- [ ] Then balance should remain 0.

### Test 3 — Successful Purchase

- [ ] Given product price = 20.
- [ ] Given quantity = 3.
- [ ] Insert 20 kr.
- [ ] Purchase the product.
- [ ] Verify purchase succeeds.
- [ ] Verify returned product is correct.
- [ ] Verify balance = 0.
- [ ] Verify quantity = 2.

### Test 4 — Insufficient Balance

- [ ] Given product price = 20.
- [ ] Given balance = 10.
- [ ] Attempt purchase.
- [ ] Verify purchase fails.
- [ ] Verify balance remains 10.
- [ ] Verify quantity remains unchanged.

### Test 5 — Out of Stock

- [ ] Given product quantity = 0.
- [ ] Given sufficient balance.
- [ ] Attempt purchase.
- [ ] Verify purchase fails.
- [ ] Verify balance remains unchanged.
- [ ] Verify quantity remains 0.

### Test 6 — Return Change

- [ ] Given balance = 50.
- [ ] Call `returnChange()`.
- [ ] Verify returned amount = 50.
- [ ] Verify balance = 0.

### Test 7 — Product List

- [ ] Add three products.
- [ ] Call the agreed product-list method.
- [ ] Verify list size = 3.

## Additional Test Cases

- [ ] Unknown product ID.
- [ ] Overpayment and automatic change.
- [ ] Return change when balance is zero.
- [ ] Duplicate product ID validation.
- [ ] Invalid product constructor values.
- [ ] Product stock cannot become negative.

## Test Completion

- [ ] Ensure all test cases pass.
- [ ] Ensure tests verify business logic and state changes.
- [ ] Fix issues in collaboration with the relevant module owner.
- [ ] Record final test results.
- [ ] Share test evidence with the team lead for the final report.

**Testing Concepts:**

- Unit Testing
- Arrange–Act–Assert
- Assertions
- Test Isolation
- Business Rule Verification

---

# 9. Integration and Manual Testing

**Responsibility:** All members, coordinated by the team lead.

- [ ] Integrate all completed modules.
- [ ] Check that all classes compile together.
- [ ] Verify all class and method signatures.
- [ ] Resolve integration issues.
- [ ] Verify Main creates and connects all required objects.
- [ ] Run the console application from the IDE.
- [ ] Verify all three product types display correctly.
- [ ] Verify valid coin insertion.
- [ ] Verify invalid coin rejection.
- [ ] Verify successful purchase with exact payment.
- [ ] Verify successful purchase with overpayment.
- [ ] Verify automatic change calculation.
- [ ] Verify insufficient balance handling.
- [ ] Verify out-of-stock handling.
- [ ] Verify unknown product ID handling.
- [ ] Verify manual change return.
- [ ] Verify zero-balance change return.
- [ ] Verify balance is displayed after every action.
- [ ] Verify the menu continues until the user exits.
- [ ] Verify the executable JAR launches from the command line.
- [ ] Verify all console menu operations work through the JAR.
- [ ] Confirm the JAR uses the correct application entry point.
- [ ] Fix defects and repeat relevant tests.
- [ ] Member 6 runs the complete JUnit test suite after integration.

---

# 10. Coding Review and GitHub Management

**Responsibility:** Every member reviews another member's code.

## GitHub Workflow

- [ ] Create a feature branch for each module.
- [ ] Pull the latest changes before starting integration work.
- [ ] Commit changes with meaningful commit messages.
- [ ] Push completed work to GitHub.
- [ ] Create a Pull Request for each completed module.
- [ ] Link Pull Requests to the corresponding task.
- [ ] Assign another member as reviewer.
- [ ] Resolve review comments.
- [ ] Merge approved changes into main.
- [ ] Keep the main branch in a working state.

## Code Review Checklist

- [ ] Check class and method naming.
- [ ] Check code readability and formatting.
- [ ] Check private fields and encapsulation.
- [ ] Check constructor validation.
- [ ] Check inheritance and method overriding.
- [ ] Check polymorphism.
- [ ] Ensure no instanceof checks are used.
- [ ] Ensure List/ArrayList and loops are used as agreed.
- [ ] Check that business logic is not inside ConsoleUI.
- [ ] Check that failed purchases do not change stock or balance.
- [ ] Check that there is no unnecessary duplicate code.
- [ ] Check that the application compiles.
- [ ] Check that relevant JUnit tests pass.
- [ ] Check that the JAR launches successfully.
- [ ] Approve and merge only after review.

## Suggested Peer Review Allocation

| Code Owner | Reviewer |
|---|---|
| Member 1 | Member 2 |
| Member 2 | Member 3 |
| Member 3 | Member 4 |
| Member 4 | Member 5 |
| Member 5 | Member 6 |
| Member 6 | Member 1 |

---

# 11. Documentation and Final Report Management

**Responsibility:** Team lead coordinates documentation. All members contribute.

## README.md

- [ ] Add project title and description.
- [ ] Add project objectives.
- [ ] Add workshop requirements summary.
- [ ] Add team member names.
- [ ] Add module allocation.
- [ ] Add domain model diagram.
- [ ] Add UML class diagram.
- [ ] Explain class responsibilities.
- [ ] Explain class relationships.
- [ ] Explain inheritance and abstraction.
- [ ] Explain polymorphism and encapsulation.
- [ ] Explain the use of List/ArrayList and loops.
- [ ] Explain how to run the application from Main in the IDE.
- [ ] Explain how to build the runnable JAR.
- [ ] Include the command to launch the JAR.
- [ ] Explain how to execute JUnit tests.

## Final Project Report

- [ ] Prepare the project introduction.
- [ ] Document the problem statement.
- [ ] Document project objectives and scope.
- [ ] Include the domain model diagram.
- [ ] Include the UML class diagram.
- [ ] Explain the architecture and class relationships.
- [ ] Document the implementation of each module.
- [ ] Explain the OOP concepts used.
- [ ] Explain the collection and loop concepts used.
- [ ] Document the seven mandatory JUnit test cases.
- [ ] Include final test execution results.
- [ ] Include manual testing results.
- [ ] Document code review activities.
- [ ] Document important design decisions.
- [ ] Document defects found and resolved.
- [ ] Include individual team contributions.
- [ ] Include runnable JAR creation and execution results.
- [ ] Document known limitations, if any.
- [ ] Review the report for accuracy and completeness.
- [ ] Obtain final approval from the team lead.

---

# 12. Final Submission Checklist — Monday

- [ ] All six members have completed their assigned modules.
- [ ] All classes are integrated.
- [ ] Product inheritance works correctly.
- [ ] Product descriptions use polymorphism.
- [ ] Product storage uses List/ArrayList.
- [ ] Loops and basic Java concepts are used for implementation.
- [ ] Payment and coin validation work correctly.
- [ ] Purchase functionality works correctly.
- [ ] Stock management works correctly.
- [ ] Console menu works correctly.
- [ ] All seven mandatory JUnit tests pass.
- [ ] Additional edge-case tests pass.
- [ ] Code reviews are complete.
- [ ] All approved changes are merged into main.
- [ ] Runnable JAR is successfully generated.
- [ ] JAR launches using `java -jar`.
- [ ] Console application functionality is verified through the JAR.
- [ ] JAR build and execution instructions are included in README.md.
- [ ] Domain model and UML diagrams are updated.
- [ ] README.md is complete.
- [ ] Final report is complete.
- [ ] All files are committed and pushed to GitHub.
- [ ] Team lead verifies the final repository.
- [ ] Share the GitHub repository link with the instructor.

---

# 13. Optional OOP Challenge

- [ ] Agree on one meaningful improvement.
- [ ] Implement the improvement using the agreed Java scope.
- [ ] Add relevant JUnit tests.
- [ ] Explain what was added.
- [ ] Explain why it improves the application.
- [ ] Identify the OOP concept demonstrated.
- [ ] Add the explanation to README.md or the final report.

---

# Team Completion Status

| Work Area | Assigned Member | Status |
|---|---|---|
| Product Model and Inheritance | Member 1 | Not Started |
| Product Catalog and Inventory | Member 2 | Not Started |
| Payment Management | Member 3 | Not Started |
| Vending Machine Logic | Member 4 | Not Started |
| Console Menu and Runnable JAR | Member 5 | Not Started |
| All JUnit Test Cases | Member 6 | Not Started |
| Integration and Manual Testing | All Members | Not Started |
| Coding Review | All Members | Not Started |
| README and Final Report | Team Lead + All Members | Not Started |
| GitHub Submission | Team Lead + All Members | Not Started |

---

# Definition of Done

A module is considered complete when:

1. Its agreed classes and methods are implemented.
2. The implementation follows the approved design.
3. The code compiles successfully.
4. Another team member has reviewed the code.
5. Relevant changes are committed and pushed to GitHub.

The complete project is ready for submission when:

- All modules are integrated.
- All mandatory JUnit tests pass.
- The console application works correctly.
- The runnable JAR launches successfully from the command line.
- Documentation and diagrams are finalized.
- The final report is reviewed.
- The GitHub repository is ready to share with the instructor.