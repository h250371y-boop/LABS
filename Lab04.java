import java.util.*;

public class Lab04 {
    static class Person { private final String name; private final int age; Person(String n,int a){name=n;age=a;} void display(){System.out.println(name+", age "+age);} }
    static class Student extends Person { private final String course; Student(String n,int a,String c){super(n,a);course=c;} @Override void display(){super.display();System.out.println("Course: "+course);} }
    static class Teacher extends Person { private final String subject; Teacher(String n,int a,String s){super(n,a);subject=s;} @Override void display(){super.display();System.out.println("Subject: "+subject);} }
    static class Vehicle { Vehicle(){System.out.println("Vehicle constructor");} }
    static class Car extends Vehicle { Car(){System.out.println("Car constructor");} }
    static class ElectricCar extends Car { ElectricCar(){System.out.println("ElectricCar constructor");} }
    static class Animal { final String name; Animal(String name){this.name=name;} }
    // Animal a = new Animal(); would not compile: Animal has no no-argument constructor.
    static class Dog extends Animal { Dog(String name){super(name);} }
    static class Stack<T> { private final List<T> data=new ArrayList<>(); void push(T v){data.add(v);} T pop(){if(data.isEmpty())throw new NoSuchElementException();return data.remove(data.size()-1);} }
    public static void main(String[] args){new Student("Ada",19,"OOP").display();new Teacher("Ben",40,"Java").display();new ElectricCar();Dog d=new Dog("Rex");System.out.println("Dog: "+d.name);Stack<Integer>s=new Stack<>();s.push(1);System.out.println("pop="+s.pop());}
}