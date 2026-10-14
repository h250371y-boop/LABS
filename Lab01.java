import java.time.Year;

public class Lab01 {
    static class Car {
        String brand; int year; double mileage;
        Car(String brand, int year, double mileage) { this.brand = brand; this.year = year; this.mileage = mileage; }
        void display() { System.out.printf("%s (%d), %.1f km%n", brand, year, mileage); }
        boolean isAntique() { return Year.now().getValue() - year > 25; }
    }
    static class Rectangle {
        double width, height;
        Rectangle(double width, double height) { this.width = width; this.height = height; }
        double area() { return width * height; }
        double perimeter() { return 2 * (width + height); }
    }
    static class Address { String city; Address(String city) { this.city = city; } }
    static class Person { String name; Address address; Person(String name, Address address) { this.name=name; this.address=address; } }

    public static void main(String[] args) {
        Car[] cars = { new Car("Toyota", 1998, 180000), new Car("Honda", 2018, 42000), new Car("Mazda", 2022, 12000) };
        for (Car car : cars) { car.display(); System.out.println("Antique? " + car.isAntique()); }
        Rectangle[] rs = { new Rectangle(2,3), new Rectangle(5,4), new Rectangle(1,9), new Rectangle(6,2), new Rectangle(3,3) };
        Rectangle largest = rs[0]; for (Rectangle r : rs) if (r.area() > largest.area()) largest = r;
        System.out.printf("Largest rectangle area=%.1f perimeter=%.1f%n", largest.area(), largest.perimeter());
        Rectangle r1 = new Rectangle(4, 2); Rectangle r2 = r1; r2.width = 10;
        System.out.println("Aliased r1.width=" + r1.width); // both references name the same object
        Person p = new Person("Tariro", new Address("Harare")); System.out.println(p.name + " lives in " + p.address.city);
        Person noAddress = new Person("Kuda", null);
        try { System.out.println(noAddress.address.city); } catch (NullPointerException e) { System.out.println("No address: dereferencing null throws NullPointerException"); }
    }
}