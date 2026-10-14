# Object-Oriented Programming in Java — Labs 1–11

Worked examples for the eleven labs in the **Object-Oriented Programming in Java — University Lab Manual**. The project uses Java 17 language features, including records, switch expressions, pattern matching, and text blocks where appropriate.

## Requirements

- JDK 17 or later
- A terminal or Java IDE such as IntelliJ IDEA, Eclipse, or VS Code with the Java extension

Check that Java is installed:

```shell
java -version
javac -version
```

## Project layout

Each lab is a standalone source file. Supporting classes are nested inside the lab class so each lab can be compiled and run independently.

| File | Topics and exercises |
| --- | --- |
| `Lab01.java` | Classes and objects, fields and methods, arrays of objects, reference aliasing, composition, and `null` |
| `Lab02.java` | Constructors, constructor overloading and chaining, `this`, static members, initialization order, copy constructors, and factory methods |
| `Lab03.java` | Encapsulation, validation, access control, defensive copies, immutable objects, and temperature conversion |
| `Lab04.java` | Inheritance, `super`, constructor order, multilevel inheritance, and composition with a stack |
| `Lab05.java` | Method overloading, overriding, runtime polymorphism, field hiding, payroll, and payment processing |
| `Lab06.java` | Abstract classes, interfaces, shapes, comparable objects, comparators, and default-method conflicts |
| `Lab07.java` | Input validation, exception handling, checked custom exceptions, and `finally` |
| `Lab08.java` | Lists, sets, maps, generic stacks, word frequencies, and generic sorting |
| `Lab09.java` | Enums, records, `equals` and `hashCode`, iterators, and a coin-change example |
| `Lab10.java` | Text files, lambdas, predicates, streams, class averages, and CSV grade reports |
| `Lab11.java` | Singleton, Factory, Strategy, Observer, and a console Library Management System |
| `Lab11-class-diagram.md` | Mermaid class diagram for the library mini-project |
| `Lab11-report.md` | Short design report for the library mini-project |

## Compile and run a lab

Open a terminal in this folder. Replace `NN` with the two-digit lab number:

```shell
javac Lab01.java
java Lab01
```

For example, to run Lab 6:

```shell
javac Lab06.java
java Lab06
```

Compile and run one lab at a time. Lab files are independent and some define classes with the same names, such as `Student` or `Book`.

## Interactive labs and generated files

- **Lab 7** asks for two integers. Enter both values in the terminal to see integer division and its error handling.
- **Lab 10** asks for five lines of text and saves them in `notes.txt`. It also writes a sample text file, a sample CSV file, and grade-band CSV reports under `grade-reports/`.
- **Lab 11** launches a menu for adding books or DVDs, registering members, borrowing and returning items, listing overdue items, searching by title, and displaying the catalog. Choose `0` to save and exit. The program stores catalog, member, and loan data in `library-items.csv`, `library-members.csv`, and `library-loans.csv` in the current working directory.

Generated files are written relative to the directory from which `java` is run.

## Lab 11 class diagram and report

The library mini-project models `LibraryItem` as an abstract base class with `Book` and `Dvd` subclasses. `Borrowable` defines the borrowing contract, while `Member` and `Loan` model who has borrowed each item and when it is due. The diagram is in [Lab11-class-diagram.md](Lab11-class-diagram.md); design notes are in [Lab11-report.md](Lab11-report.md).

## Learning goals

Across the labs, the examples demonstrate how to:

- Model entities with classes, objects, fields, and methods.
- Protect valid state with encapsulation and input validation.
- Apply inheritance, interfaces, abstraction, and polymorphism appropriately.
- Handle exceptions and use generics and collections safely.
- Read and write files, and process data with lambdas and streams.
- Apply common design patterns in a small, complete application.

Use the examples to study and explain the design choices, then adapt them to your course requirements and lab record.
