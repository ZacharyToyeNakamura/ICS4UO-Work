import java.util.ArrayList;

public class People {
    private ArrayList<Person> people;

    public People() {
        people = new ArrayList<>();
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public void addPerson(String name, int age) {
        people.add(new Person(name, age));
    }

    public void removePerson (Person p) {
//        for (int i = 0; i < people.size(); i++) {
//            if(p.equals(people.get(i))) {
//                people.remove(i);
//                return;
//            }
//        }
        int idx = -1;
        for (int i = 0; i < people.size(); i++) {
            if(people.get(i).equals(p)) {
                idx = i;
                break;
            }
        }
        if (idx >= 0) {
            people.remove(idx);
        }
    }

    public void happyNewYear() {
        for (Person p : people) {
            p.getOlder();
        }
    }

    public void printPeople() {
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    @Override
    public String toString() {
        return people.toString();
    }


}
