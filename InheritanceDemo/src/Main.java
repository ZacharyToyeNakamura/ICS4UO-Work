// Lesson
// https://docs.google.com/document/d/1Zd8zBuRgCqhmD2-n7TJFaYc37lRujhDcu1UFF4f1nas/edit
// Exercises
// https://docs.google.com/document/d/1N0luT_10Zx-wdJ8gve_6sHzqJ6cp6zRgBWrukH5w-os/edit
public class Main {
    public static void main(String[] args) {
        Person p2 = new Person("Alan", 'M');
        p2.greet();
        Student s2 = new Student("bob", 'f', "3453673");
        Person p22 = new Student("WTF", 'm', "43696934936496349649364578483"); // Declaring a class a one of it's subclasses

        Object o = null;
        Student s22 = (Student) o; // Class casting
        System.out.println(s22);

        System.out.println(p2);
        System.out.println(s2);
        System.out.println(p22);
        ((Student)p2).printNumber(); // Class cast so that I can use

        System.out.println("Hello world!");

        Person p = new Person("Alan", 42);
        Student q = new Student("Ada", 37, "10010101");
// print each person using toString
        Person r = p;
        System.out.println(r.equals(p));
        Student s = new Student("Ada", 37, "10010101");
        System.out.println(s.equals(q));
    }
}