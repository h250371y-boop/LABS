public class Lab02 {
    static class Circle {
        private static int count; private final double radius;
        Circle() { this(1.0); }
        Circle(double radius) { if (radius <= 0) throw new IllegalArgumentException("radius must be positive"); this.radius=radius; count++; }
        double area() { return Math.PI * radius * radius; }
        static int getCount() { return count; }
    }
    static class Employee {
        private static int nextId; final int id; String name; double salary;
        Employee(String name, double salary) { this.id=++nextId; this.name=name; this.salary=salary; }
        Employee(String name) { this(name, 0); }
        Employee() { this("Unassigned", 0); }
        Employee(Employee other) { this(other.name, other.salary); }
        public String toString() { return id + ": " + name + " ($" + salary + ")"; }
    }
    static class Temperature {
        private final double celsius;
        private Temperature(double c) { if (c < -273.15) throw new IllegalArgumentException("below absolute zero"); celsius=c; }
        static Temperature fromCelsius(double c) { return new Temperature(c); }
        static Temperature fromFahrenheit(double f) { return new Temperature((f-32)*5/9); }
        double celsius() { return celsius; } double fahrenheit() { return celsius*9/5+32; }
    }
    static class InitOrder {
        static { System.out.println("static block 1"); } static { System.out.println("static block 2"); }
        { System.out.println("instance block 1"); } { System.out.println("instance block 2"); }
        InitOrder() { System.out.println("constructor"); }
    }
    public static void main(String[] args) {
        System.out.println("Circle area=" + new Circle().area()); new Circle(2); System.out.println("Circles=" + Circle.getCount());
        Employee e = new Employee("Ada", 50000); System.out.println(e); System.out.println(new Employee(e));
        System.out.println("100 F = " + Temperature.fromFahrenheit(100).celsius() + " C");
        new InitOrder(); new InitOrder();
    }
}