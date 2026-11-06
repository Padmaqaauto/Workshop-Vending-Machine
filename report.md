# REPORT

## Thursday

### Workload Distribution

We started our meeting by assigning responsibilities in the project to equally share the workload.

Padma had a general idea of how to divide the workload by assigning groups of two to each part.

Anas suggested that some tasks would require more work depending on how much effort was needed for each task.

The responsibilities were divided as follows:

- **Modeling and Class Diagram** — Anas, Tukue
- **Product** — Jay
- **Purchase Result, Purchase Status** - Padma
- **Vending Implementation Logic & Console Menu** — Tukue, Vesalina
- **JUnit** — Carl, Anas
- **Coding Review** — All
- **UI Testing** — Anas, Carl
- **Report** — Carl

### UML Design

Anas came up with a suggested overall UML for how we should build our code, and Tukue also brought his UML.

Both had approximately the same structure, but we decided to use Tukue's UML as the basis for our implementation.

A payment method was missing, which we discussed and then added to the UML.

### Development

Our meeting lasted from **10:00 to 12:00**, and we agreed to continue at **14:00**.

We had a quick recap meeting at **14:00**.

- Jay wrote `Product.java`, `Snack.java`, `Beverage.java`, and `Fruit.java`.
- Carl wrote `Payment.java`.
- Anas reviewed the code while waiting to test and found some fixes that were needed in our code.
- We agreed to have a team meeting at **10:00 on Friday**.
- Anas started writing the JUnit tests for the code.
- Padma wrote `PurchaseResult.java` and `PurchaseStatus.java`.

---

## Friday

### Development

- Vesalina started working on the `ConsoleUI` now that a lot of the required code had been written.
- Tukue made some changes to the `Payment` code to follow the UML better and reduce potential bug issues.

### Team Meeting — 10:00

We had our meeting at **10:00**, where we reviewed our code together, fixed issues, and discussed how to move forward.

### Additional Tasks

- Padma agreed to make the application into a **runnable JAR**.
- Anas agreed to update the `README.md` so it follows what we have done with our code.
- We discussed the **optional challenge**, where we talked about implementing a **restock function**. Jay agreed to try it out.
- Anas was done with the **JUnit testing**.
- Jay completed the **restocking function** and pushed it to the **main branch**.
- Jay also updated the code to fix potential bugs and ran JUnit tests for the **restocking functionality**.