import java.util.*;

public class Lab08 {
    static class Stack<T> { private final List<T> values=new ArrayList<>(); void push(T v){values.add(v);} T pop(){if(isEmpty())throw new EmptyStackException();return values.remove(values.size()-1);} T peek(){if(isEmpty())throw new EmptyStackException();return values.get(values.size()-1);} boolean isEmpty(){return values.isEmpty();} }
    static <T extends Comparable<T>> void bubbleSort(T[] a){for(int end=a.length-1;end>0;end--)for(int i=0;i<end;i++)if(a[i].compareTo(a[i+1])>0){T t=a[i];a[i]=a[i+1];a[i+1]=t;}}
    public static void main(String[] args){List<Integer> nums=new ArrayList<>(List.of(8,2,9,1,4,7,3,5,6,10));System.out.println("sum="+nums.stream().mapToInt(Integer::intValue).sum()+", max="+Collections.max(nums));List<Integer> reverse=new ArrayList<>(nums);Collections.reverse(reverse);System.out.println(reverse);Set<String> names=new LinkedHashSet<>(List.of("Ada","Ben","Ada","Cara"));System.out.println(names);
        Stack<String>s=new Stack<>();s.push("first");s.push("second");System.out.println("peek="+s.peek()+", pop="+s.pop());String paragraph="to be or not to be to learn java";Map<String,Integer> freq=new HashMap<>();for(String w:paragraph.split("\\s+"))freq.merge(w,1,Integer::sum);freq.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey())).forEach(System.out::println);
        Integer[] ia={4,1,3,2};String[] sa={"pear","apple","plum"};bubbleSort(ia);bubbleSort(sa);System.out.println(Arrays.toString(ia));System.out.println(Arrays.toString(sa)); // int[] cannot be T[]: generics require reference types
    }
}