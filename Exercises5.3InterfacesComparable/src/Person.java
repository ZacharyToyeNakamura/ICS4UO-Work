public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String first, String last, int age) {
        firstName = first;
        lastName = last;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person: " + firstName + " " + lastName + ", " + age;
    }

    @Override
    public int compareTo(Person p) {
        if (lastName.compareTo(p.lastName) == 0) {
            return firstName.compareTo(p.firstName);
        }
        return lastName.compareTo(p.lastName);
    }

}
