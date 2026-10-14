import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lab10 {
    record Student(String name,int roll,double gpa) { }
    static void saveFiveLines(Scanner input,Path path)throws IOException{List<String> lines=new ArrayList<>();for(int i=1;i<=5;i++){System.out.print("Line "+i+": ");lines.add(input.nextLine());}Files.write(path,lines);System.out.println(Files.readAllLines(path));}
    static long[] countText(Path path)throws IOException{String text=Files.readString(path);long lines=text.isEmpty()?0:text.lines().count();long words=text.isBlank()?0:text.trim().split("\\s+").length;return new long[]{lines,words,text.length()};}
    static void reportBands(Path source,Path directory)throws IOException{Files.createDirectories(directory);Map<String,List<String>> bands=Files.readAllLines(source).stream().filter(s->!s.isBlank()).map(s->s.split(",")).collect(Collectors.groupingBy(p->grade(Double.parseDouble(p[2])),TreeMap::new,Collectors.mapping(p->p[0]+","+p[1]+","+p[2],Collectors.toList())));for(String band:List.of("A","B","C"))Files.write(directory.resolve("grade-"+band+".csv"),bands.getOrDefault(band,List.of()));}
    static String grade(double gpa){return gpa>=3.5?"A":gpa>=3.0?"B":"C";}
    public static void main(String[] args)throws IOException{
        try(Scanner input=new Scanner(System.in)){saveFiveLines(input,Path.of("notes.txt"));}
        Predicate<Integer> even=n->n%2==0;List<Integer> numbers=new ArrayList<>(List.of(1,2,3,4,5,6));numbers.removeIf(even);System.out.println("Odd values: "+numbers);
        List<Student> students=List.of(new Student("Ada",1,3.9),new Student("Ben",2,3.1),new Student("Cara",3,3.6));double average=students.stream().mapToDouble(Student::gpa).average().orElse(0);students.stream().filter(s->s.gpa()>average).map(Student::name).sorted().forEach(System.out::println);
        Path sample=Path.of("lab10-sample.txt");Files.writeString(sample,"Java streams\nmake data processing clear\n");long[] counts=countText(sample);System.out.println("lines="+counts[0]+", words="+counts[1]+", characters="+counts[2]);
        Path csv=Path.of("learners.csv");Files.write(csv,List.of("Ada,1,3.9","Ben,2,3.2","Cara,3,2.7"));reportBands(csv,Path.of("grade-reports"));System.out.println("Wrote grade reports under grade-reports/");
    }
}