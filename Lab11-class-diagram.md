# Lab 11 — Class diagram

```mermaid
classDiagram
    class Library {
      -Map~String, LibraryItem~ items
      -Map~String, Member~ members
      +borrow(memberId, itemId)
      +giveBack(itemId)
      +search(text) List~LibraryItem~
      +overdue() List~LibraryItem~
      +save()
      +load()
    }
    class LibraryItem {
      <<abstract>>
      -String id
      -String title
      -Loan activeLoan
      +loanPeriodDays()* int
      +borrow(Member)
      +giveBack()
    }
    class Book
    class Dvd
    class Member {
      +String id
      +String name
      +List~Loan~ loans
    }
    class Loan {
      +LibraryItem item
      +Member member
      +LocalDate borrowedOn
      +LocalDate dueDate
    }
    class Borrowable {
      <<interface>>
      +borrow(Member)
      +giveBack()
    }
    Borrowable <|.. LibraryItem
    LibraryItem <|-- Book
    LibraryItem <|-- Dvd
    Library o-- LibraryItem
    Library o-- Member
    LibraryItem --> Loan
    Member o-- Loan
    Loan --> LibraryItem
    Loan --> Member
```

`Book` and `Dvd` specialize `LibraryItem`; both inherit the borrowing contract while choosing different loan periods. `Library` coordinates collections, operations and persistence. A `Loan` connects a member and an item and records the start and due dates.
