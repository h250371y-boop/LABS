import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class Lab11 {
    // Singleton: eagerly created, safely published once.
    static final class AppConfig { private static final AppConfig INSTANCE=new AppConfig(); private AppConfig(){} static AppConfig instance(){return INSTANCE;} String institution(){return "Example University";} }
    // Factory: caller works through an interface rather than selecting concrete classes itself.
    interface Notification { void send(String message); }
    static class Email implements Notification { public void send(String m){System.out.println("Email: "+m);} }
    static class Sms implements Notification { public void send(String m){System.out.println("SMS: "+m);} }
    static class NotificationFactory { static Notification create(String type){return switch(type.toLowerCase()){case "email"->new Email();case "sms"->new Sms();default->throw new IllegalArgumentException("Unknown type");};} }
    // Strategy and Observer examples.
    interface Discount { double apply(double price); }
    static class Checkout { private Discount discount; Checkout(Discount d){discount=d;} void setDiscount(Discount d){discount=d;} double total(double p){return discount.apply(p);} }
    interface GradeListener { void published(String course,int mark); }
    static class GradeBook { private final List<GradeListener> listeners=new ArrayList<>(); void subscribe(GradeListener l){listeners.add(l);} void publish(String c,int m){listeners.forEach(l->l.published(c,m));} }

    static class ItemUnavailableException extends Exception { ItemUnavailableException(String m){super(m);} }
    static class MemberLimitExceededException extends Exception { MemberLimitExceededException(){super("Member already has the maximum of 3 active loans");} }
    interface Borrowable { void borrow(Member member)throws ItemUnavailableException,MemberLimitExceededException; void giveBack(); }
    static final class Loan { final LibraryItem item; final Member member; final LocalDate borrowedOn,dueDate; Loan(LibraryItem i,Member m,LocalDate start){item=i;member=m;borrowedOn=start;dueDate=start.plusDays(i.loanPeriodDays());} public String toString(){return item.title()+" -> "+member.name+" (due "+dueDate+")";} }
    static abstract class LibraryItem implements Borrowable {
        private final String id,title; private Loan activeLoan;
        LibraryItem(String id,String title){if(id.isBlank()||title.isBlank())throw new IllegalArgumentException("id/title required");this.id=id;this.title=title;}
        abstract int loanPeriodDays(); String id(){return id;} String title(){return title;} boolean available(){return activeLoan==null;} Member borrower(){return activeLoan==null?null:activeLoan.member;} LocalDate dueDate(){return activeLoan==null?null:activeLoan.dueDate;}
        public void borrow(Member m)throws ItemUnavailableException,MemberLimitExceededException{if(!available())throw new ItemUnavailableException(title+" is already on loan");if(m.loans.size()>=3)throw new MemberLimitExceededException();activeLoan=new Loan(this,m,LocalDate.now());m.loans.add(activeLoan);}
        void restoreLoan(Member m,LocalDate borrowedOn)throws ItemUnavailableException,MemberLimitExceededException{borrow(m);activeLoan=new Loan(this,m,borrowedOn);m.loans.set(m.loans.size()-1,activeLoan);}
        public void giveBack(){if(activeLoan!=null)activeLoan.member.loans.remove(activeLoan);activeLoan=null;}
        public String toString(){return getClass().getSimpleName()+"["+id+", "+title+", "+(available()?"available":"due "+dueDate())+"]";}
    }
    static final class Book extends LibraryItem { Book(String i,String t){super(i,t);} int loanPeriodDays(){return 21;} }
    static final class Dvd extends LibraryItem { Dvd(String i,String t){super(i,t);} int loanPeriodDays(){return 7;} }
    static final class Member { final String id,name; final List<Loan> loans=new ArrayList<>(); Member(String i,String n){if(i.isBlank()||n.isBlank())throw new IllegalArgumentException();id=i;name=n;} public String toString(){return "Member["+id+", "+name+", active loans="+loans.size()+"]";} }
    static final class Library {
        private final Map<String,LibraryItem> items=new LinkedHashMap<>(); private final Map<String,Member> members=new LinkedHashMap<>(); private final Path itemFile=Path.of("library-items.csv"),memberFile=Path.of("library-members.csv"),loanFile=Path.of("library-loans.csv");
        void addItem(LibraryItem i){if(items.putIfAbsent(i.id(),i)!=null)throw new IllegalArgumentException("Duplicate item id");}
        void register(Member m){if(members.putIfAbsent(m.id,m)!=null)throw new IllegalArgumentException("Duplicate member id");}
        void borrow(String memberId,String itemId)throws ItemUnavailableException,MemberLimitExceededException{Member m=Optional.ofNullable(members.get(memberId)).orElseThrow(()->new IllegalArgumentException("Unknown member"));LibraryItem i=Optional.ofNullable(items.get(itemId)).orElseThrow(()->new IllegalArgumentException("Unknown item"));i.borrow(m);}
        void giveBack(String itemId){LibraryItem i=Optional.ofNullable(items.get(itemId)).orElseThrow(()->new IllegalArgumentException("Unknown item"));i.giveBack();}
        List<LibraryItem> search(String text){String q=text.toLowerCase();return items.values().stream().filter(i->i.title().toLowerCase().contains(q)).sorted(Comparator.comparing(LibraryItem::title)).toList();}
        List<LibraryItem> overdue(){return items.values().stream().filter(i->!i.available()&&i.dueDate().isBefore(LocalDate.now())).toList();}
        void list(){items.values().forEach(System.out::println);members.values().forEach(System.out::println);}
        void save()throws IOException{Files.write(itemFile,items.values().stream().map(i->(i instanceof Dvd?"DVD":"BOOK")+","+i.id()+","+i.title()).toList());Files.write(memberFile,members.values().stream().map(m->m.id+","+m.name).toList());Files.write(loanFile,items.values().stream().filter(i->!i.available()).map(i->i.id()+","+i.borrower().id+","+i.borrower().loans.stream().filter(l->l.item==i).findFirst().orElseThrow().borrowedOn).toList());}
        void load()throws IOException{if(Files.exists(itemFile))for(String l:Files.readAllLines(itemFile)){String[] p=l.split(",",3);addItem(p[0].equals("DVD")?new Dvd(p[1],p[2]):new Book(p[1],p[2]));}if(Files.exists(memberFile))for(String l:Files.readAllLines(memberFile)){String[] p=l.split(",",2);register(new Member(p[0],p[1]));}if(Files.exists(loanFile))for(String l:Files.readAllLines(loanFile)){String[] p=l.split(",");LibraryItem i=items.get(p[0]);Member m=members.get(p[1]);if(i!=null&&m!=null){try{i.restoreLoan(m,LocalDate.parse(p[2]));}catch(Exception e){throw new IOException("Could not restore loan "+l,e);}}}}
    }
    static void menu()throws IOException{Library lib=new Library();lib.load();try(Scanner in=new Scanner(System.in)){while(true){System.out.println("\n1 add item  2 register member  3 borrow  4 return  5 overdue  6 search  7 list  0 save and exit");String choice=in.nextLine().trim();try{switch(choice){case "1"->{System.out.print("Book or DVD: ");String kind=in.nextLine().trim();System.out.print("ID: ");String id=in.nextLine().trim();System.out.print("Title: ");String title=in.nextLine().trim();lib.addItem(kind.equalsIgnoreCase("DVD")?new Dvd(id,title):new Book(id,title));}case "2"->{System.out.print("Member ID: ");String id=in.nextLine().trim();System.out.print("Name: ");lib.register(new Member(id,in.nextLine().trim()));}case "3"->{System.out.print("Member ID and item ID: ");String[] p=in.nextLine().trim().split("\\s+");lib.borrow(p[0],p[1]);}case "4"->{System.out.print("Item ID: ");lib.giveBack(in.nextLine().trim());}case "5"->lib.overdue().forEach(System.out::println);case "6"->{System.out.print("Title contains: ");lib.search(in.nextLine()).forEach(System.out::println);}case "7"->lib.list();case "0"->{lib.save();return;}default->System.out.println("Unknown menu choice");}}catch(Exception e){System.out.println("Operation failed: "+e.getMessage());}}}}
    public static void main(String[] args)throws Exception{System.out.println("Same config instance? "+(AppConfig.instance()==AppConfig.instance())+" — "+AppConfig.instance().institution());NotificationFactory.create("email").send("Lab 11");Checkout c=new Checkout(p->p);System.out.println("Regular: "+c.total(200));c.setDiscount(p->p*.9);System.out.println("Student: "+c.total(200));GradeBook gb=new GradeBook();gb.subscribe((course,mark)->System.out.println("Portal: "+course+" = "+mark));gb.subscribe((course,mark)->{if(mark<50)System.out.println("Advisor: failed "+course);});gb.publish("OOP",72);gb.publish("Databases",45);menu();}
}