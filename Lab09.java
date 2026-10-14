import java.util.*;

public class Lab09 {
    enum Level { LOW, MEDIUM, HIGH }
    record Student(String name,int age) { }
    static final class Book { final String title,isbn; Book(String t,String i){title=t;isbn=i;} public boolean equals(Object o){return o instanceof Book b&&isbn.equals(b.isbn);} public int hashCode(){return Objects.hash(isbn);} public String toString(){return title+" ("+isbn+")";} }
    static class SimpleLinkedList<T> implements Iterable<T> {
        private static class Node<E>{E value;Node<E> next;Node(E v){value=v;}}
        private Node<T> head,tail; void add(T value){Node<T> n=new Node<>(value);if(tail==null)head=n;else tail.next=n;tail=n;}
        private class It implements Iterator<T>{Node<T> current=head;public boolean hasNext(){return current!=null;}public T next(){if(!hasNext())throw new NoSuchElementException();T v=current.value;current=current.next;return v;}}
        public Iterator<T> iterator(){return new It();}
    }
    enum Coin { PENNY(1),NICKEL(5),DIME(10),QUARTER(25); final int cents;Coin(int c){cents=c;} }
    static Map<Coin,Integer> fewestCoins(int cents){if(cents<0)throw new IllegalArgumentException();Map<Coin,Integer> result=new LinkedHashMap<>();for(Coin c:new Coin[]{Coin.QUARTER,Coin.DIME,Coin.NICKEL,Coin.PENNY}){result.put(c,cents/c.cents);cents%=c.cents;}return result;}
    public static void main(String[] args){for(Level l:Level.values())System.out.println(l+": "+switch(l){case LOW->"proceed carefully";case MEDIUM->"stay alert";case HIGH->"take immediate action";});Student s1=new Student("Ada",20),s2=new Student("Ada",20);System.out.println(s1+" equal="+s1.equals(s2)+" age="+s1.age());Set<Book> books=new HashSet<>();books.add(new Book("OOP","123"));books.add(new Book("Different title","123"));System.out.println("Unique ISBN books="+books.size());SimpleLinkedList<String> list=new SimpleLinkedList<>();list.add("A");list.add("B");for(String x:list)System.out.print(x+" ");System.out.println();System.out.println(fewestCoins(87));}
}