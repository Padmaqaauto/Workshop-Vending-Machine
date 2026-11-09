# PROJECT REPORT

## Thursday

### Workload Distribution

We started the meeting by discussing how to distribute project responsibilities fairly among team members.

Padma suggested dividing the workload into groups of two for different areas. 
Anas highlighted that some tasks would require more effort than others, so the workload should also be distributed based on complexity.

The responsibilities were divided as follows:

- **Modeling and Class Diagram** — Anas, Tukue
- **Product** — Jay
- **Purchase Result, Purchase Status** — Padma
- **Vending Implementation Logic & Console Menu** — Tukue, Vesalina
- **JUnit Testing** — Carl, Anas
- **Coding Review** — All team members
- **UI Testing** — Anas, Carl
- **Report** — Carl

### Project Setup and Coordination

- Padma established the GitHub repository, invited all team members as collaborators, and created the Maven project structure with an organised package and file layout.
- She prepared the coding standards and team checklist, covering implementation guidelines, required fields, coding practices, and checks before creating and merging pull requests.

### UML Design

Anas proposed an overall UML design for the application, and Tukue also presented his UML diagram.

Both designs had approximately the same structure, and the team decided to use Tukue's UML as the basis for implementation.

During the discussion, we identified that a payment method was missing and agreed to include it in the UML design.

### Development

Our meeting lasted from **10:00 to 12:00**, and we agreed to continue at **14:00**.

We had a quick recap meeting at **14:00**.

- Jay implemented `Product.java`, `Snack.java`, `Beverage.java`, and `Fruit.java`.
- Carl implemented `Payment.java`.
- Anas reviewed the existing code, identified issues, and suggested necessary fixes.
- Anas started writing the JUnit tests.
- Padma implemented `PurchaseResult.java` and `PurchaseStatus.java`.
- We agreed to have the next team meeting at **10:00 on Friday**.

### Code Review and Team Support

- All team members participated in reviewing pull requests, validating changes, and providing constructive feedback to identify necessary improvements or corrections.
- Team members supported one another in resolving technical doubts and encouraged discussions about implementation concepts, including synchronization, its purpose, and its impact on the application.

---

## Friday

### Development

- Vesalina started working on `ConsoleUI` as the required application classes became available.
- Tukue made changes to the `Payment` implementation to align it more closely with the UML design and reduce potential issues.

### Team Meeting — 10:00

We had our team meeting at **10:00**, where we reviewed the code together, discussed identified issues, made necessary corrections, and agreed on the next steps.

### Additional Tasks and Implementation

- Padma took responsibility for preparing the application as a **runnable JAR**.
- Anas agreed to update the `README.md` to reflect the actual implementation.
- We discussed the optional challenge of implementing a **stock replenishment feature**. Padma suggested this improvement to enhance inventory management, and Jay agreed to implement it.
- Anas completed the JUnit testing assigned to him.
- Jay implemented the restocking functionality and pushed it to the `main` branch.
- Jay also made additional bug fixes and ran JUnit tests for the restocking functionality.
- Anas updated the `README.md`, UML class diagram, and project checklist. He also tested the console menu and runnable JAR to verify that they worked correctly.

### Pull Requests, Code Quality and Technical Contributions

- **Padma:** Raised a pull request for console UI improvements to make the interface clearer and more user-friendly. She also continued reviewing and validating team contributions.

  PR: [#31 – Console UI Improvements](https://github.com/Padmaqaauto/Workshop-Vending-Machine/pull/31)

- **Tukue:** Raised PR #22 to improve payment transaction handling. He introduced synchronized payment and product operations to support thread safety, updated payment deduction to validate the available balance before deducting money, and improved purchase processing to coordinate payment and product dispensing.

  PR: [#22 – Payment Transaction Improvements](https://github.com/Padmaqaauto/Workshop-Vending-Machine/pull/22/changes)

- **Anas:** Reviewed multiple pull requests, identified missing validations and potential issues, and implemented the necessary fixes to improve code quality and ensure the application met the requirements.

  Relevant PRs: [#12](https://github.com/Padmaqaauto/Workshop-Vending-Machine/pull/12), [#15](https://github.com/Padmaqaauto/Workshop-Vending-Machine/pull/15), [#16](https://github.com/Padmaqaauto/Workshop-Vending-Machine/pull/16).

### Suggestions and Future Improvements

The team also discussed ideas to extend the application's functionality and usability:

- **Carl:** Suggested supporting multiple payment methods as a potential future improvement to the payment functionality.
- **Tukue:** Suggested replacing or supplementing the console interface with a graphical user interface (GUI), following the **Model–View–Controller (MVC)** architectural pattern.

---

## Project Outcomes

The project achieved the following outcomes through collaborative development:

- Established a shared GitHub repository with all team members collaborating.
- Created an organised Maven project structure and shared coding standards.
- Distributed development responsibilities and established a collaborative pull request workflow.
- Implemented the core product, payment, purchase result, and purchase status components.
- Completed the stock replenishment feature as an additional functionality.
- Improved payment transaction handling and thread-safety considerations.
- Improved console UI clarity and usability.
- Strengthened code quality through pull request reviews, validation, and JUnit testing.
- Updated project documentation, including the README, UML class diagram, and project checklist.
- Worked towards delivering a runnable JAR for command-line execution.
