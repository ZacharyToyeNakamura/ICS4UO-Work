import java.util.Scanner;


/**
 *
 * Name: Zachary Toye-Nakamura
 * Date: October 4th 2023
 * A short program where the user inputs student's marks and the program calculates and prints the averages,
 * the overall average, the student with the highest average and all the students above or equal to the average.
 *
 */

public class Main {

    public static final Scanner INPUT = new Scanner(System.in);


    /**
     * This method asks the user to input the numTests test marks for each student and
     * returns them in an array. This method assumes that numStudents and numTests ARE NON-ZERO
     * @param numStudents The number of students | double
     * @param numTests The number of tests | double
     * @return An array with all the test marks for all the students inputted by the user.
     * then ith row is student i and the jth column contains the mark for the jth test.
     */
    public static double[][] getMarks(int numStudents, int numTests) {
        double[][] marks = new double[numStudents][numTests];
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter all the marks for Student " + i + ": ");
            for (int j = 0; j < numTests; j++) {
                System.out.print("mark " + j + ": ");
                marks[i][j] = Double.parseDouble(INPUT.nextLine());
            }
        }
        return marks;
    }


    /**
     * This method calculates the averages for each student.
     * @param marks The marks array, with marks[i][j] containing the ith student's mark on the jth test | double[][]
     * @return An array where the ith element is the ith students average.
     */
    public static double[] calcAvgs(double[][] marks) {
        double total;
        double[] stuAvgs = new double[marks.length];
        for (int i = 0; i < marks.length; i++) {
            total = 0;
            for (double j: marks[i]) {
                total += j;
            }
            stuAvgs[i] = total/marks[i].length;
        }
        return stuAvgs;
    }


    /**
     * This method prints all the students marks in a formatted manner.
     * @param marks The marks array, with marks[i][j] containing the ith student's mark on the jth test | double[][]
     * @param avgs An array that contains the averages of all the students.
     */
    public static void printMarks(double[][] marks, double[] avgs) {
        System.out.println("The test marks:");
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Student " + i);
            System.out.print("marks: ");
            for (int j = 0; j < marks[i].length; j++) {
                System.out.printf("%.1f",marks[i][j]);
                // only print the "," if it isn't the last element because otherwise it ruins the formatting.
                if(j != marks[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.printf("%naverage: %.1f%n%n", avgs[i]);
        }
    }


    /**
     * This method calculates the average of a list of doubles.
     * @param avgs An array of doubles
     * @return The average of the averages as a double
     */
    public static double classAvg(double avgs[]) {
        double tot = 0;
        for (double i : avgs) { // no one-liners :(
            tot += i;
        }
        return tot / avgs.length;
    }


    /**
     * This method finds the highest element in an array of doubles, (finds the student with the highest avg).
     * @param avgs A list of doubles
     * @return The index with the highest double (prioritizing the closest index to 0)
     *
     */
    public static int stuHighAvg(double avgs[]) {
        double high = -1;
        int idx = -1;
        for (int i = 0; i < avgs.length; i++) {
            // Tracks the highest value, and it's index. When a new highest is found change both to the new idx and high
            if(avgs[i] > high) {
                high = avgs[i];
                idx = i;
            }
        }
        return idx;
    }


    /**
     * This method prints which students are above or equal to the average.
     * @param avgs The student's averages | An array of doubles
     */
    public static void printAboveAvg(double avgs[]) {
        double classAvg = classAvg(avgs);
        System.out.println("Students above class average:");
        for (int i = 0; i < avgs.length; i++) {
            if(avgs[i] >= classAvg) {
                System.out.print("Student " + i);
                // Only print a newline if it's not the last element due to formatting
                if(i != avgs.length - 1) {
                    System.out.println();
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("MARKS PROGRAM");
        System.out.print("Enter the number of students: ");
        int numStudents = Integer.parseInt(INPUT.nextLine());
        System.out.print("Enter the number of tests: ");
        int numTests = Integer.parseInt(INPUT.nextLine());
        System.out.println(); // formatting

        double[][] marks = getMarks(numStudents, numTests); // gets all the input from the user
        double[] stuAvgs = calcAvgs(marks); // calculates the averages

        // Ensures correct formatting
        System.out.println();
        printMarks(marks, stuAvgs);
        System.out.printf("Class average: %.1f %n%n", classAvg(stuAvgs));

        int highestStu = stuHighAvg(stuAvgs);
        System.out.printf("Highest average: Student %d, average %.1f%n%n", highestStu, stuAvgs[highestStu]);

        printAboveAvg(stuAvgs);
    }
}