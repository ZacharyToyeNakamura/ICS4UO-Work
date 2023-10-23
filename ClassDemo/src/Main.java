import java.util.Scanner;

public class Main {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Person p = new Person("Alan", 0b10110);
        System.out.println(p.toString());
        p.getOlder();
        p.getOlder();
        System.out.println(p.toString());
        System.out.println(p.getName());

    }
}
