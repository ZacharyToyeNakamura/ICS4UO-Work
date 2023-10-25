import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("ahhhhh", 12);
        Person p2 = new Person("bob", 75);
        Person p3 = new Person("Namie", 45);
        Person p4 = new Person("Named", 23);

        People ppl = new People();

        p1.getOlder();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);

        ppl.addPerson(p1);
        ppl.addPerson(p2);
        ppl.addPerson(p3);
        ppl.addPerson(p4);
        ppl.addPerson("Alice", 18);
        System.out.println(ppl);
        ppl.happyNewYear();
        ppl.printPeople();
        ppl.removePerson(p1);
        ppl.printPeople();


    }
}