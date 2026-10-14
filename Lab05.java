public class Lab05 {
    static class Printer { void print(int x){System.out.println("int "+x);} void print(double x){System.out.println("double "+x);} void print(String x){System.out.println("String "+x);} void print(int[] x){System.out.println(java.util.Arrays.toString(x));} }
    static class Animal { String kind="animal"; void sound(){System.out.println("sound");} }
    static class Cat extends Animal { String kind="cat"; @Override void sound(){System.out.println("meow");} }
    static class Cow extends Animal { @Override void sound(){System.out.println("moo");} }
    static class Duck extends Animal { @Override void sound(){System.out.println("quack");} }
    static class Employee { final String name; final double salary; Employee(String n,double s){name=n;salary=s;} double pay(){return salary;} }
    static class Intern extends Employee { Intern(String n,double s){super(n,s);} @Override double pay(){return salary*0.5;} }
    interface Payment { }
    record CardPayment(String last4,double amount) implements Payment { }
    record MobileMoneyPayment(String phone,double amount) implements Payment { }
    static class PaymentProcessor {
        void pay(Payment p){ if(p instanceof CardPayment c) System.out.println("Card ending "+c.last4()+": "+c.amount()); else if(p instanceof MobileMoneyPayment m) System.out.println("Mobile "+m.phone()+": "+m.amount()); else throw new IllegalArgumentException("unsupported payment"); }
        void payPolymorphic(Processable p){p.process();}
    }
    interface Processable { void process(); }
    record Card(String last4,double amount) implements Processable { public void process(){System.out.println("Card ending "+last4+": "+amount);} }
    record MobileMoney(String phone,double amount) implements Processable { public void process(){System.out.println("Mobile "+phone+": "+amount);} }
    public static void main(String[] args){Printer p=new Printer();p.print(3);p.print(3.5);p.print("hi");p.print(new int[]{1,2});Animal[] a={new Cat(),new Cow(),new Duck()};for(Animal x:a)x.sound();Employee[] staff={new Employee("A",1000),new Intern("B",500)};double total=0;for(Employee e:staff)total+=e.pay();System.out.println("Payroll="+total);Animal ref=new Cat();System.out.println("field="+ref.kind);ref.sound();PaymentProcessor pp=new PaymentProcessor();pp.pay(new CardPayment("1234",50));pp.payPolymorphic(new MobileMoney("0771",20));}
}