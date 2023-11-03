import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Zachary Toye-Nakamura
 * @Date 2023-11-01
 * <br>
 * This program lets the user interact with a single course's students, assignments and marks.
 * The user may change marks, and add/delete assignments or students.
 * They may also see the averages for students or assignments and other information on the course and it's students.
 */
public class GradeBook {

    public static Scanner input = new Scanner(System.in);
    // Hard-coded course
    public static Course introCS = new Course(
            "Introduction to Computer Science",
            "CSC100",
            new ArrayList<>(Arrays.asList(
                    new Student("Alan T",
                    "110101011",
                            new ArrayList<>(Arrays.asList(83, 71, 76, 91, 85))),
                    new Student("Donald K",
                            "314159265",
                            new ArrayList<>(Arrays.asList(84, 90, 88, 99, 80))),
                    new Student("Albert E",
                            "299792458",
                            new ArrayList<>(Arrays.asList(93, 65, 95, 40, 19))),
                    new Student("Marie C",
                            "002661600",
                            new ArrayList<>(Arrays.asList(76, 52, 96, 92, 66))),
                    new Student("Ada L",
                            "018151210",
                            new ArrayList<>(Arrays.asList(91, 98, 89, 99, 99)))
            ))
        );

    public void printCourseAvg() {
        System.out.printf("The course average is: %.1f%% %n", introCS.calcCourseAvg());
    }

    /**
     * Prints the average of a single assignment
     *
     * @param idx The index of the assignment of which the index will be printed
     */
    public void printAssignmentAvg(int idx) {
        double avg = introCS.assignmentAvg(idx);
        if(avg == -2) {
            System.out.println("Assignment " + idx + "doesn't exist");
        }
        System.out.printf("Assignment %d's average is %.1f%%%n", idx, avg);
    }

    /**
     * Prints the average of a single student
     *
     * @param stud The name or id of the student that will have their average printed
     */
    public void printStudentAvg(String stud) {
        double avg = introCS.getStudent(introCS.findStudent(stud)).average();
        // If there is an error, tell the user
        if(avg == -3) {
            System.out.println("The average of " + stud + " is unknown");
        }
        System.out.printf("The average of %s is %.1f%% %n", stud, avg);
    }

    /**
     * Prints all the students, their numbers and their average.
     */
    public void printAllStud() {
        // Can almost use printStudentAvg in a for loop, but I want to print ids
        for(int i = 0; i < introCS.getStudents().size(); i++) {
            Student stud = introCS.getStudent(i);
            System.out.printf("%s, %s, Average: %.1f",stud.getName(), stud.getNumber(), stud.average());
        }
    }

    /**
     *
     * @param idx
     */
    public void printAssignMarks(int idx) {
        for(int i = 0; i < introCS.getStudents().size(); i++) {
            Student stud = introCS.getStudent(i);

        }
    }







    public static void main(String[] args) {
        System.out.println(introCS);
    }
}