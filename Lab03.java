import java.util.*;

public class Lab03 {
    static class Student {
        private final String name; private int marks;
        Student(String name, int marks) { if (name == null || name.isBlank()) throw new IllegalArgumentException("name required"); this.name=name; setMarks(marks); }
        void setMarks(int marks) { if (marks < 0 || marks > 100) throw new IllegalArgumentException("marks must be 0..100"); this.marks=marks; }
        int getMarks() { return marks; }
        String getGrade() { return marks >= 80 ? "A" : marks >= 60 ? "B" : marks >= 50 ? "C" : "F"; }
        public String toString() { return name + ": " + marks + " (" + getGrade() + ")"; }
    }
    static class BankAccount {
        private double balance; private final List<String> history = new ArrayList<>();
        BankAccount(double opening) { if (opening < 0) throw new IllegalArgumentException(); balance=opening; history.add("Opened: " + opening); }
        void deposit(double amount) { if (amount <= 0) throw new IllegalArgumentException(); balance+=amount; history.add("Deposit: " + amount); }
        List<String> getHistory() { return Collections.unmodifiableList(new ArrayList<>(history)); }
        double getBalance() {
            return balance;
        }
    }
    static final class ImmutablePerson {
        private final String name; private final int age;
        ImmutablePerson(String name, int age) { if (name == null || name.isBlank() || age < 0) throw new IllegalArgumentException(); this.name=name; this.age=age; }
        String name() { return name; } int age() { return age; }
    }
    static class KelvinTemperature {
        private final double kelvin;
        KelvinTemperature(double kelvin) { if (kelvin < 0) throw new IllegalArgumentException("below absolute zero"); this.kelvin=kelvin; }
        double getCelsius() { return kelvin-273.15; } double getFahrenheit() { return getCelsius()*9/5+32; }
    }
    public static void main(String[] args) {
        Student s = new Student("Rudo", 87); System.out.println(s);
        BankAccount b = new BankAccount(100); b.deposit(25); System.out.println(b.getHistory());
        ImmutablePerson p = new ImmutablePerson("Tariro", 20); System.out.println(p.name()+", "+p.age());
        System.out.printf("20 C = %.2f F%n", new KelvinTemperature(293.15).getFahrenheit());
        System.out.printf("Balance: $%.2f%n", b.getBalance());
    }
}