import java.util.*;

public class Lab06 {
    abstract static class Shape { abstract double area(); abstract double perimeter(); }
    static class Circle extends Shape { final double r; Circle(double r){this.r=r;} double area(){return Math.PI*r*r;} double perimeter(){return 2*Math.PI*r;} }
    static class Rectangle extends Shape { final double w,h; Rectangle(double w,double h){this.w=w;this.h=h;} double area(){return w*h;} double perimeter(){return 2*(w+h);} }
    static class Square extends Rectangle { Square(double side){super(side,side);} }
    interface Flyable { void fly(); }
    static class Bird implements Flyable { public void fly(){System.out.println("Bird flaps");} }
    static class Aeroplane implements Flyable { public void fly(){System.out.println("Plane uses engines");} }
    abstract static class Account { double balance; void deposit(double amount){if(amount<=0)throw new IllegalArgumentException();balance+=amount;} abstract double monthlyFee(); }
    static class SavingsAccount extends Account { double monthlyFee(){return 2.0;} }
    static class CurrentAccount extends Account { double monthlyFee(){return 10.0;} }
    record Learner(String name,int marks) implements Comparable<Learner> { public int compareTo(Learner o){return name.compareTo(o.name);} }
    interface HelloA { default void hello(){System.out.println("Hello A");} }
    interface HelloB { default void hello(){System.out.println("Hello B");} }
    static class Both implements HelloA,HelloB { public void hello(){HelloA.super.hello();HelloB.super.hello();} }
    public static void main(String[] args){Shape[] shapes={new Circle(2),new Rectangle(3,4),new Square(5)};for(Shape s:shapes)System.out.printf("area %.2f, perimeter %.2f%n",s.area(),s.perimeter());List<Flyable> f=List.of(new Bird(),new Aeroplane());f.forEach(Flyable::fly);Account ac=new SavingsAccount();ac.deposit(100);System.out.println("balance="+ac.balance+", fee="+ac.monthlyFee());List<Learner> ls=new ArrayList<>(List.of(new Learner("Zed",80),new Learner("Ada",95)));Collections.sort(ls);System.out.println("By name: "+ls);ls.sort(Comparator.comparingInt(Learner::marks));System.out.println("By marks: "+ls);new Both().hello();}
}