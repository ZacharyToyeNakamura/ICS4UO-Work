
public class Student extends Person{
    private String id;

    public Student(String name, char gender, String id) {
        super(name, gender);
        this.id = id;
    }

    public Student(String name, int age, String id) {
        super(name, age);
        this.id = id;
    }

    public void printNumber() {
        System.out.println(id);
    }



    @Override
    public String toString() {
//        return super.toString() + ", " + number;
        return name + ", " + id;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) {
            return false;
        }
        return this.id == ((Student)o).id;
    }

}
