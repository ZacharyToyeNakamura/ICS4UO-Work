import java.util.ArrayList;

/**
 * This class holds its name, course code, and students it has the functionality to add and demit students
 * add and edit marks on assignments and assignments themselves. Also, it can calculate the average of an assignment.
 * 
 * Why do certain methods have a int return value when they could be void? Error flags
 * 
 * Error flag meanings:
 * -1: Student name or number doesn't exist in the course
 * -2: Assignment number out of range
 * -3: Divsion by 0 (in average) 
 * 
 * 0 Means a successful operation
 * 
 * Why are there no constants? The mark min and max should ALWAYS be between 0 and 100.
 */
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
     * @return The name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * @return The course code
     */
    public String getCode() {
        return code;
    }


    /**
     * @return The number of students in the course
     */
    public int getNumStudents() {
        return students.size();
    }

    /**
     * Sets the course name
     *
     * @param name The new course name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the course code
     *
     * @param code The new course code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Gets the student at index idx
     *
     * @param idx An integer between [0, students.size() - 1]
     * @return The student at index idx
     *         A student with everything empty to symbolize idx is out of range.
     */
    public Student getStudent(int idx) {
        if(idx < 0 || idx >= students.size()) {
            return new Student("", "", new ArrayList<>());
        }
        return students.get(idx);
    }

    /**
     * Returns the name of the student at idx i
     *
     * @param idx An integer between [0, students.size()]
     * @return The name of the student at index idx
     */
    public String studName(int idx) {
        return getStudent(idx).getName();
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
     * Returns the student object with the name or number of the input
     *
     * @param nameOrId The name or number of the student object that is wanted
     * @return The student object with a name or number matching the parameter
     *         - A student with everything set to nothing if not found.
     */
    public Student findStudObj(String nameOrId) {
        if(findStudent(nameOrId) == -1) {
            return new Student("", "");
        }
        return students.get(findStudent(nameOrId));
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
     * Changes the mark of 1 assignment for 1 student to a new mark.
     *
     * @param nameOrId The student's name or number
     * @param newMark The new mark to update to
     * @param assignmentNum The assignment that is to be updated
     * @return  0 if the operation was successful <br>
     *          -1 if the nameOrId didn't exist in students <br>
     *          -2 if the assignmentNum was out of range
     *          (ex. there are 4 assignment and tries to change the 7th assignment.)
     */
    public int editMark(String nameOrId, int newMark, int assignmentNum) {
        int studentIdx = findStudent(nameOrId);
        if(studentIdx == -1) {
            System.out.println("Bad Error: editMark student out of range!");
            return -1;
        }
        if(findStudObj(nameOrId).setMark(assignmentNum, newMark) != 0) {
            System.out.println("Bad Error: editMark mark wasn't set!");
            return -2;
        }
        // findStudObj(nameOrId).setMark(assignmentNum, newMark);

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
     *          -2 if the assignmentNum was out of range
     */
    public int deleteAssignment(int assignmentNum) {
        for(Student stud: students) {
            if(stud.deleteIdx(assignmentNum) == -2) {
                return -2;
            }
        }
        return 0;
    }

    /**
     * returns the number of assignments the course has.
     * @return The number of assignments
     *         -1 for there are no students in the course
     */
    public int numAssignments() {
        if(students.size() == 0) {
            return -1;
        }
        return students.get(0).getNumMarks();
    }

    /**
     * Calculates the course average using the formula A = 1/n * Σ[i = 1, n, ai]
     *
     * @return The average of the course (the average of the student's averages).
     */
    public double calcCourseAvg() {
        double tot = 0, numStud = 0;
        for(Student stud: students) {
            // If the student has at least 1 mark
            if (stud.average() != -3) {
                tot += stud.average();
                numStud++;
            }
        }
        return tot / numStud;
    }


    /**
     * Gets the average mark of an assignment.
     *
     * @param assignmentNum The assignment of the number that the average is calculated for.
     * @return The average mark for the assignment
     *         -2 for assignmentNum out of range.
     */
    public double assignmentAvg(int assignmentNum) {
        double tot = 0, numMarks = 0;
        for(Student stud: students) {
            int mark = stud.getMark(assignmentNum);
            if(mark == -1) {
                continue;
            }
            if(mark == -2) {
                return -2;
            }
            tot += mark;
            numMarks++;
        }
        if (numMarks == 0) {
            return -3; // division by 0 error
        }
        return tot / numMarks;
    }


    /**
     * Redefines the toString methods, now it prints an overview of the course
     *
     * @return A string that is formatted to be printed
     */
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
            // Add the name, and all the marks
            output += stud.getName() + ": ";
            for(int i = 0; i < numAssignments(); i++) {
                output += stud.getMark(i);
                if(i != numAssignments()-1) output += ", ";
            }
            output += "\n";
        }
        return output.substring(0, output.length() - 1);
    }

}
