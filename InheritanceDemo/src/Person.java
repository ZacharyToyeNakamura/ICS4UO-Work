public class Person {
    protected String name;
    protected int age;
    protected char gender;

    public Person(String name, char gender) {
        this.name = name;
        this.gender = gender;
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet() {
        System.out.println("hello");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(this.getClass() == o.getClass()) return ((Person) o).name == this.name && ((Person) o).gender == this.gender;
        return false; // ???
    }
}
