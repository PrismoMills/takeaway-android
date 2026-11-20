# Takeaway

**An Android food ordering app built with Java, Kotlin, XML layouts and SQLite.**

Takeaway lets users browse regional dishes, adjust quantities from the menu or cart, and sign in to place an order. Each checkout is saved as one order with its items, quantities, total and notes. Account settings keep delivery details and food preferences on the device.

| Home | Menu | Order history |
| --- | --- | --- |
| <img src="docs/images/home.png" width="235" alt="Takeaway home and featured dishes"> | <img src="docs/images/menu.png" width="235" alt="Menu with quantity controls"> | <img src="docs/images/order.png" width="235" alt="One order with two dishes, notes and a total"> |

## What you can do

- Browse dishes from Home or Menu using the same catalog and prices.
- Add dishes and adjust their quantities from the menu or cart.
- Register a local account, sign in and sign out.
- Place an order and review its items, quantities, line totals, total and notes.
- Save delivery details and food preferences separately for each account.

## How ordering works

Menu quantity changes carry over to the cart. At checkout, the application assigns an order ID and saves all line items in one SQLite transaction. Order history groups items under that ID and calculates the displayed total from their line totals.

Older records without an order ID remain separate entries. Account-specific delivery details and preferences are stored locally in SharedPreferences.

## Run locally

Requirements are Android Studio, JDK 8, Android SDK Platform 30 and Build Tools 30.0.3. The project uses Gradle Wrapper 6.5.

1. Open the project root in Android Studio and configure the required JDK and SDK.
2. Build the debug APK:

   ```powershell
   .\gradlew.bat assembleDebug
   ```

   On macOS or Linux, run `sh ./gradlew assembleDebug`.

3. Run the `app` module on an emulator or Android device. Create a local test account from the Account tab.
4. Select two dishes in Menu, adjust their quantities and place the order from Cart. Open **Account → My orders** to inspect the saved order.

The ordering flow was verified on an Android 9 emulator (API 28).

## Project layout

```text
app/src/main/java/com/example/order/activity/  App screens and account entry points
app/src/main/java/com/example/order/ui/        Home, menu, cart and account tabs
app/src/main/java/com/example/order/dao/       Local accounts and order persistence
app/src/main/java/com/example/order/data/      Shared dish catalog
app/src/main/res/                             Layouts, icons and dish images
docs/images/                                  Interface screenshots
docs/project-delivery.md                       Sprint schedule and acceptance notes
```

## Project delivery

Takeaway began as a team course project. I set up the Android app structure and developed and tested registration, sign-in, menu browsing, cart and checkout. I used Jira to break down the work, track progress and coordinate acceptance checks.

The delivery notes cover the Sprint 1 schedule and acceptance criteria, with a tested example from selecting dishes to viewing the saved order. [Project delivery notes in Chinese](docs/project-delivery.md)

## Current scope

The application demonstrates a local ordering workflow and stores orders in SQLite on the device. Payment, refund, review and payment-settings entries display an availability message. Connecting orders to a restaurant service, processing payments and arranging delivery remain outside the implemented workflow.
