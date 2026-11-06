# Future Improvements

This document describes possible extensions to the vending machine. These are design proposals; they are not part of the current console implementation.

## 1. Support Additional Payment Methods

Extend payment beyond Swedish coins to support:

- **Card payments**
- **Swish payments**

Introduce a common `PaymentMethod` interface (for example, `authorize(amount)` and `refund(amount)`) with separate implementations for cash, card, and Swish. The vending machine can depend on this abstraction instead of containing payment-provider-specific logic.

For electronic payments, model the outcome explicitly—such as approved, declined, or pending—before dispensing a product. Keep provider credentials and sensitive payment details outside the vending machine domain model, and use an established payment provider for actual transactions.

**Benefits and OOP concepts:** This uses abstraction, polymorphism, and dependency inversion. New payment options can be added without rewriting product or inventory logic.

## 2. Add Product Restocking

Add an inventory operation for authorized staff to restock a product by ID. The operation should:

- Report when the product ID is unknown.
- Reject a zero or negative restock amount.
- Increase quantity without allowing integer overflow.
- Return a clear result containing the updated stock level.

Keep stock changes encapsulated in the product or catalog API rather than allowing callers to modify quantity directly. A future version could also record restocking events, timestamps, and staff IDs for an audit history.

**Benefits and OOP concepts:** This strengthens encapsulation and gives inventory changes a clear domain operation.

## 3. Build a GUI Using MVC

The console interface could be replaced or supplemented by a graphical application using the **Model–View–Controller (MVC)** pattern:

- **Model:** Products, `ProductCatalog`, `Payment`, and vending-machine business logic. The model should not depend on GUI classes.
- **View:** Screens and controls for the product list, balance, payment choices, purchase results, and restocking tools.
- **Controller:** Handles user actions, invokes the machine or application services, and provides results to the view.

Keep the existing vending-machine logic reusable by both console and GUI clients. The GUI should call application interfaces rather than directly changing product stock or payment balances.

**Benefits and OOP concepts:** MVC separates responsibilities, reduces coupling, and supports multiple user interfaces over the same domain model.

## Suggested Implementation Order

1. Add and test restocking operations.
2. Define the payment-method abstraction and model payment outcomes.
3. Integrate card and Swish through appropriate payment providers.
4. Add a GUI with MVC while keeping business rules independent of the view.
