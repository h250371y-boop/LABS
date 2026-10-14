import java.util.*;

public class Lab07 {
    static class InvalidAgeException extends Exception { InvalidAgeException(String message){super(message);} }
    static class InsufficientFundsException extends Exception { InsufficientFundsException(){super("insufficient funds");} }
    static void validateAge(int age) throws InvalidAgeException { if(age<0||age>120)throw new InvalidAgeException("age must be 0..120"); }
    static void withdraw(double balance,double amount) throws InsufficientFundsException { if(amount>balance)throw new InsufficientFundsException(); }
    static int tryFinallyReturn(){try{return 7;}finally{System.out.println("finally runs before method returns");}}
    public static void main(String[] args){try(Scanner in=new Scanner(System.in)){System.out.print("Two integers: ");try{int a=in.nextInt(),b=in.nextInt();System.out.println("quotient="+(a/b));}catch(InputMismatchException e){System.out.println("Enter integers only");}catch(ArithmeticException e){System.out.println("Cannot divide by zero");}}
        int[] values={4,8};try{System.out.println(values[3]);}catch(ArrayIndexOutOfBoundsException e){System.out.println("Index outside array");}
        try{validateAge(140);}catch(InvalidAgeException e){System.out.println(e.getMessage());}try{withdraw(20,50);}catch(InsufficientFundsException e){System.out.println(e.getMessage());}System.out.println("return="+tryFinallyReturn());}
}