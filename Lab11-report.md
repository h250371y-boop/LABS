# Lab 11 — Design report

## Responsibilities and design choices

The library separates catalog and member management (`Library`), item identity and availability (`LibraryItem`), borrower details (`Member`), and the dated borrowing relationship (`Loan`). The abstract base class provides shared item state; `Book` and `Dvd` supply their different loan periods. `Borrowable` expresses the operations that an item supports. The catalog uses maps keyed by IDs for direct lookup and streams for title search and overdue filtering.

Private item fields protect availability and active-loan state. Borrowing checks both availability and the three-loan member limit before changing state. The custom checked exceptions make those expected domain failures visible to callers. CSV files persist items, members and active loans. The menu catches operation errors and reports them without ending the session.

## Patterns demonstrated

`AppConfig` is a Singleton for one shared configuration instance. `NotificationFactory` chooses a concrete notification while returning the `Notification` interface. `Checkout` accepts a `Discount` Strategy so pricing behavior can change at runtime. `GradeBook` is an Observer subject that notifies its listeners when a mark is published.

## Limitations and possible improvements

The teaching CSV format assumes IDs, names and titles contain no commas; a production system should use a properly escaped CSV library or a database. Persistence errors are surfaced as `IOException`; stronger production handling would make multi-file saving atomic. A larger system could add explicit overdue fines, return dates, audit history, and tests for invalid operations and persistence recovery.
