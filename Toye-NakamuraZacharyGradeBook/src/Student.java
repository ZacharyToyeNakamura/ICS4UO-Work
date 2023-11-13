import java.util.ArrayList;

/**
 * A student has a name, a number, and a list of all of the student's marks.
 * This class can preform many operations including but no limited to changing it's name, calculating the average of the arraylist,
 * returning the size of the array list, and settings certain indexes in the array list!
 */
public class Student {
    private String name; // The students name
    private String number; // The student's number

    // A list of all the student's marks
    // The ith index contains the ith's assignment's mark. between [0-100]
    // -1 means no mark.
    private ArrayList<Integer> marks;

    /**
     * @return The student's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The student's number
     */
    public String getNumber() {
        return number;
    }


    /**
     * @return the number of marks the student has
     */
    public int getNumMarks() {
        return marks.size();
    }


    /**
     * Changes the name of the student
     * 
     * @param name Any string, the new name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the student's number
     * 
     * @param number Any string, the new number for the student
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Sets the idx th assignment mark to mark.
     *
     * @param idx A integer number | The assignment that will have it's mark changed
     * @param mark An integer number between [-1, 100] (with -1 meaning no mark)
     * @return 0 if it successfully set the mark, -2 if not
     */
    public int setMark(int idx, int mark) {
        if(idx < 0 || idx >= marks.size()) {
            return -2;
        }
        marks.set(idx, mark);
        return 0;
    }


    /** MAYBE DELETE
     * Returns the student's mark for the idx th assignment, and -1 if out of bounds
     *
     * @param idx An integer in [0 - marks.size())
     * @return The student's mark for the idx th assignment
     *         -2 for out of range of the marks array
     */
    public int getMark(int idx) {
        // Safeguard for out of bounds on the array.
        if(idx >= marks.size() || idx < 0) {
            return -2;
        }
        return marks.get(idx);
    }

    /**
     * Adds a mark to the list of marks
     *
     * @param mark An integer between [-1, 100]
     */
    public void addMark(int mark) {
        marks.add(mark);
    }

    /**
     * Sets all the student's mark to the parameter mark
     *
     * @param mark The new mark that will overwrite all the student's old marks.
     */
    public void setAllMarks(int mark) {
        for (int i = 0; i < marks.size(); i++) {
            marks.set(i, mark);
        }
    }

    /**
     * Removes an element at the idx th index.
     *
     * @param idx An integer, in the range [0,marks.size()]
     * @return  0 if the operation was successful <br>
     *          -1 if the index was out of range
     */
    public int deleteIdx(int idx) {
        if(idx >= marks.size() || idx < 0) {
            return -2;
        }
        marks.remove(idx);
        return 0;
    }

    /**
     * Calculates the average of the student (all assignments are weighted the same)
     * using sum(marks)/number of marks
     *
     * @return The average of the student
     *         -3 for a division by 0 error (they had no marks).
     */
    public double average() {
        int tot = 0, marked = 0;
        for (int mark: marks) {
            if(mark != -1) {
                tot += mark;
                marked ++;
            }
        }
        if (marked != 0) {
            return tot / (double)marked;
        }
        return -3;
    }

    /**
     * Creates a student with a name a number and all of their marks
     * @param name The student's name
     * @param number The student's number
     * @param marks The number of marks the student has. (should be equal to numAssignments
     * for the course the student is in if they are in a course.)
     */
    public Student(String name, String number, ArrayList<Integer> marks) {
        this.name = name;
        this.number = number;
        this.marks = marks;
    }

    /**
     * Contrustor for student, execpt it doesn't require any marks.
     * Since the student itself doesn't know how many assignments there are
     * it is assumed that any call of contrusting a student this way will be followed up with 
     * filling it's marks with -1 equal to the number of assignments
     * 
     * @param name the name of the student
     * @param number The number of the student
     */
    public Student(String name, String number) {
        this.name = name;
        this.number = number;
        this.marks = new ArrayList<>();
    }

}
