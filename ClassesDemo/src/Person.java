import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Age: " + age;
    }

    public void getOlder() {
        age++;
    }

    public boolean equals(Person p) {
        return name.equals(p.name) && p.age == age;
    }

}
