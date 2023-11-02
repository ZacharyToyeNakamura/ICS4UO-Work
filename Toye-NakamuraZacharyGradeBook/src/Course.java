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
     * Finds the index of a student by name or number
     * 
     * @return The index of the student in the students ArrayList, or -1 if they aren't in the list
     */
    public int findStudent(String nameOrId) {
        for(int i = 0; i < students.size(); i++) {
            if(nameOrId.equals(students.get(i).getName()) || nameOrId.equals(students.get(i).getNumber())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Deletes the first student with a nameOrId from the course
     *
     * @param nameOrId The student's name or id
     * @return 0 on success, and -1 if the student doesn't exist
     */
    public int demitStudent(String nameOrId) {
        int studentIdx = findStudent(nameOrId);
        if(studentIdx == -1) { // nameOrId is not in students
            return -1; // No student was removed
        }
        students.remove(studentIdx);
        return 0; // Successfully removed the student
    }

    /**
     * Adds a student to the course
     * @param stud A student object
     */
    public void addStudent(Student stud) {
        students.add(stud);
    }


    /**
     * Changes a student's name / id to a new name / id
     *
     * @param nameOrId The student's current name or id
     * @param newName The student's new name (-1 for don't change)
     * @param newId The student's new number (-1 for don't change)
     * @return 0 if the change was successful, and -1 if the name/id wasn't in the course.
     */
    public int editStudent(String nameOrId, String newName, String newId) {
        int studentIdx = findStudent(nameOrId);
        if(studentIdx == -1) { // nameOrId is not in students
            return -1; // No student was removed
        }
        if (!newName.equals("-1")) {
            students.get(studentIdx).setName(newName);
        }
        if (!newId.equals("-1")) {
            students.get(studentIdx).setNumber(newId);
        }
        return 0;
    }

    /**
     * Changes a student's mark to a different mark.
     * It changes all the student's marks if changeAll is true or just the mark of assignment number
     * if false.
     * 
     * @param nameOrId The student's name or number
     * @param newMark The new mark
     * @param assignmentNum The assignment that is to be changed
     * @param changeAll True to change all the student's marks or false to just change assignmentNum th
     *                  assignment's mark
     * @return  0 if the operation was successful <br>
     *          -1 if the student doesn't exist <br>
     *          -2 if the assignmentNum is not a possible assignment number
     *          (ex. there are 4 assignment and tries to change the 7th assignment.)
     */
    public int editStudentMark(String nameOrId, int newMark, int assignmentNum, boolean changeAll) {
        int studentIdx = findStudent(nameOrId);
        if(studentIdx == -1) {
            return -1;
        }
        // Sets all the marks to newMark
        if(changeAll) {
            if(newMark < 0) {
                newMark = 0; // Don't change the mark to a negative number, instead change to 0.
            }
            for (int i = 0; i < students.get(studentIdx).getMarks().size(); i++) {
                students.get(studentIdx).setMark(i, newMark);
            }
            return 0;
        }
        return students.get(studentIdx).setMark(assignmentNum, newMark);
    }


    /**
     *
     *
     * @param nameOrId The student's name or number
     * @param newMark The new mark to update to
     * @param assignmentNum The assignment that is to be updated
     * @param changeAll If true changes all the marks for that assignmen
     * @return  0 if the operation was successful <br>
     *          -1 if the nameOrId didn't exist in students <br>
     *          -2 if the assignmentNum was out of range
     *          (ex. there are 4 assignment and tries to change the 7th assignment.)
     */
    public int editAssignmentMark(String nameOrId, int newMark, int assignmentNum, boolean changeAll) {
        int studentIdx = findStudent(nameOrId);
        if(studentIdx == -1) {
            return -1;
        }
        if(students.get(studentIdx).setMark(assignmentNum, newMark) != 0) {
            return -2;
        }
        if(changeAll) {
            for(Student stud: students) {
                stud.setMark(assignmentNum, newMark);
            }
        }
        return 0;
    }

    /**
     * Adds an assignment to every student that is initialized to -1 (aka no mark)
     */
    public void addAssignment() {
        for(Student stud: students) {
            stud.addMark(-1);
        }
    }

    /**
     * Deletes an assignment (and all the marks for it) from every student
     *
     * @param assignmentNum The number of the assignment that is to be deleted
     * @return  0 if the operation was successful <br>
     *          -1 if the assignmentNum was out of range
     */
    public int deleteAssignment(int assignmentNum) {
        for(Student stud: students) {
            if(stud.deleteIdx(assignmentNum) == -1) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Prints the course average using the formula A = 1/n * Σ[i = 1, n, ai]
     */
    public void printCourseAvg() {
        double tot = 0;
        for(Student stud: students) {
            tot += stud.average();
        }
        System.out.println("The course average is " + tot / (double)(students.size()-1));
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
            output += stud.getName() +
                    ": " + stud.getMarks().toString().substring(1, stud.getMarks().toString().length() - 2) +
                    "\n";
        }
        return output.substring(0, output.length() - 1);
    }

}
