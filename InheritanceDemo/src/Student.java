
public class Student extends Person{
    private String number;

    public Student(String name, char gender, String number) {
        super(name, gender);
        this.number = number;
    }

    public void printNumber() {
        System.out.println(number);
    }

    @Override
    public String toString() {
//        return super.toString() + ", " + number;
        return name + ", " + number;
    }

}
