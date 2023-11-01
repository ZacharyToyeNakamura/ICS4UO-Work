import java.util.ArrayList;

public class Course {
    private String name; // The name of the course
    private String code; // The course code
    private ArrayList<Student> students; // A list of all the students in the course

    public Course(String name, String code, ArrayList<Student> students) {
        this.name = name;
        this.code = code;
        this.students = students;
    }

    /**
     * Deletes the first student with a nameOrId from the course
     *
     * @param nameOrId The student's name or id
     * @return 0 on success, and -1 if the student doesn't exist
     */
    public int demitStudent(String nameOrId) {
        for(Student stud: students) {
            if(nameOrId.equals(stud.getName()) || nameOrId.equals(stud.getNumber())) {
                students.remove(stud);
                // ends the method as it preformed what it needed to do.
                return 0;
            }
        }
        // No student was removed
        return -1;
    }

    // Adds a student to the course
    public void addStudent(Student stud) {
        students.add(stud);
    }




    @Override
    public String toString() {
        String output = "Course Name: " + name + "\n" +
                        "Course Code: " + code + "\n\n" +
                        "Students (Name, ID): \n";
        for (Student stud: students) {
            output += stud.getName() + ", " + stud.getNumber() + "\n";
        }
        output += "\nAssignment Marks:\n";
        for (Student stud: students) {
            // Add the name except the last initial, and all the marks
            output += stud.getName().substring(0, stud.getName().length() - 3) +
                    ": " + stud.getMarks().toString().substring(1, stud.getMarks().toString().length() - 2) +
                    "\n";
        }
        return output.substring(0, output.length() - 1);
    }

}
