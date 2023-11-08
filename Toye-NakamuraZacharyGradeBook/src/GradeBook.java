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

    public static String[] menus = {
        "Enter which option you would like to preform: \n" +
        "  0: Edit/Demit/Add students\n" +
        "  1: Edit/Delete/Add marks\n" +
        "  2: Delete or Add assignments\n" +
        "  3: Get information about the course\n" +
        "  4: Exit program",

        "Enter which option you would like to preform: \n" +
        "  0: Add a student to the course\n" +
        "  1: Change a student's name or id\n" +
        "  2: Demit a student from the course\n" +
        "  3: Go back",

        "Enter which option you would like to preform: \n" +
        "  0: Edit one mark for a student\n" +
        "  1: Edit all marks for a student\n" +
        "  2: Edit one student's mark for an assignment\n" +
        "  3: Edit all student's marks for an assignment\n"+
        "  4: Go back",

        "Enter which option you would like to preform: \n",
        "  0: Add an assignment\n" +
        "  1: Delete an assignment\n"+
        "  2: Go back",

        "Enter which option you would like to preform: \n" +
        "  0: Print the course average\n" +
        "  1: Print an assignment's average\n" +
        "  2: Print all assignment's averages\n" +
        "  3: Print a student's average\n" +
        "  4: Print all student's average\n" +
        "  5: Print a all student's averages and id's\n" +
        "  6: Print all of the marks for an assignment\n" +
        "  7: Print all of a student's marks\n" +
        "  8: Print an overview of the course\n" + // custom
        "  9: Go back"
    };
    public static int[][] bounds = {{0,4},{0,3},{0,4},{0,2},{0,9}};

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

    public static void printCourseAvg() {
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
     * Prints the marks for a certain assignment, including the student's name and their mark for the assignment.
     *
     * @param idx The assignment number for which the marks will be printed
     */
    public void printAssignmentMarks(int idx) {
        System.out.println("Marks for assignment "+idx+": ");
        for(int i = 0; i < introCS.getStudents().size(); i++) {
            Student stud = introCS.getStudent(i);
            System.out.printf("%s: %d%% %n", stud.getName(), stud.getMark(idx));
        }
    }

    /**
     * Prints all the students mark including for which assignment number they are for.
     *
     * @param nameOrId The name or number of the student
     */
    public void printStudMarks(String nameOrId) {
        Student stud = introCS.getStudent(introCS.findStudent(nameOrId));
        System.out.println("Marks for " + stud.getName() + ": ");
        for(int i = 0; i < stud.getMarks().size(); i++) {
            System.out.println("Assignment " + i + ": " + stud.getMark(i));
        }
    }

    /**
     * Prints all the current student's and their numbers
     */
    public static void printStudents() {
        System.out.println("Current students are: ");
        for (Student stud: introCS.getStudents()) {
            System.out.println("  " + stud.getName() + ", " + stud.getNumber());
        }
    }

    /**
     * Checks if the user's input is an integer between 2 given bounds.
     *
     * @param userInput Any string that is checked
     * @param lower The lower bound for valid input usually 0
     * @param upper The upper bound for valid input
     * @return True if the input it "valid" and false if the input isn't valid
     */
    public static boolean isValid(String userInput, int lower, int upper) {
        try {
            int num = Integer.parseInt(userInput);
            return num >= lower && num <= upper; // Return if it's within the bounds or not
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if the input is a student's name or id in the course.
     * Informs the user that there is no student with the name or number in the course
     * If it's invalid
     *
     * @param userInput Any string, it's checked to see if it's a valid student or id
     * @return True if the input is a student's name or id in the course.
     */
    public static boolean isValidStud(String userInput) {
        if (introCS.findStudent(userInput) == -1) {
            System.out.println("There is no student with that name or number in this course!");
            return false;
        }
        return true;
    }

    /**
     * Controls everything. . . ...___...
     */
    public static void control() {
        boolean exit = false;
        int menuNum = 0; // Which menu the program is currently in (-1 for not in menu)

        while (!exit) { // While the user doesn't decide to end the program
            System.out.println(menus[menuNum]);
            System.out.print("> ");
            String userInput = input.next();

            switch (menuNum) {
                case 0: // If in the first menu
                    switch (userInput) {
                        case "0": case "1": case "2": case "3": // switch menus
                            menuNum = Integer.parseInt(userInput) + 1; // Change to a sub menu
                            break;
                        case "4":
                            exit = true; // Exit program
                            break;
                        default:
                            System.out.println("Invalid Input!");
                            break;
                    }
                    break;
                case 1: // If in the second menu
                    switch (userInput) {
                        case "0": // Add a student
                            System.out.print("Enter student's name: ");
                            String studName = input.next();
                            System.out.print("Enter student's number: ");
                            String studNum = input.next();
                            introCS.addStudent(new Student(studName, studNum));
                            menuNum = 0; // Return to main menu
                            break;

                        case "1": // Edit a student's information
                            printStudents();
                            System.out.print("Enter the student's name or number that you wish to change: ");
                            String nameOrNum = input.next();
                            if(!isValidStud(nameOrNum)) {
                                break;
                            }
                            System.out.print("Enter the student's new name (-1 for don't change): ");
                            String newName = input.next();
                            System.out.print("Enter the student's new number (-1 for don't change): ");
                            String newNum = input.next();
                            // Should be impossible but as a safeguard make sure editStudent didn't throw an error flag
                            if(introCS.editStudent(nameOrNum, newName, newNum) == -1) {
                                System.out.println("Unknown Error: editStudent() student not found");
                            }
                            menuNum = 0;
                            break;

                        case "2": // Remove a student
                            printStudents();
                            System.out.print("Enter the name or number of the student you wish to demit from the course: ");
                            String nameOrId = input.next();
                            if(!isValidStud(nameOrId)) {
                                break; 
                            }
                            // Should be impossible but as a safeguard make sure demitStudent didn't throw an error flag
                            if (introCS.demitStudent(nameOrId) == -1) {
                                System.out.println("Unknown Error: demitStudent student not found");
                            }
                            menuNum = 0; // return to main menu
                            break;

                        case "3": // Go back
                            System.out.println("Returning to main menu. . .");
                            menuNum = 0;

                        default:
                            System.out.println("Invalid Input!");
                            break;
                    }
                    break;
                
                case 2:
                    String newMark;
                    String nameOrId;
                    String assignmentNum;
                    switch(userInput) {
                        case "0": // Edit all of 1 student's marks (set all student's marks to x)
                            printStudents();
                            System.out.print("Enter the name or number of the student you wish " +
                                    "to change all marks for: ");
                            nameOrId = input.next();
                            if(!isValidStud(nameOrId)) {
                                break;
                            }
                            // Possible make into a method
                            do {
                                System.out.print("Enter the mark you wish to set all of the " + nameOrId +
                                        "'s marks to [-1, 100]: ");
                                newMark = input.next();
                                if (!isValid(newMark, -1, 100)) {
                                    System.out.println("Mark invalid please enter a mark between " +
                                            "[0, 100] or -1 for no mark");
                                }
                            } while(!isValid(newMark, -1, 100));
                            introCS.getStudent(introCS.findStudent(nameOrId)).setAllMarks(Integer.parseInt(newMark));
                            menuNum = 0;
                            break;

                        case "1": // Edit all of all student's marks (set all marks to x)
                            printStudents();
                            // Possible make into a method
                            do {
                                System.out.print("Enter the mark you wish to set all student's marks to " +
                                        "[-1, 100]: ");
                                newMark = input.next();
                                if (!isValid(newMark, -1, 100)) {
                                    System.out.println("Mark invalid please enter a mark between " +
                                            "[0, 100] or -1 for no mark");
                                }
                            } while(!isValid(newMark, -1, 100));

                            for (int i = 0; i < introCS.getStudents().size(); i++) {
                                introCS.getStudent(i).setAllMarks(Integer.parseInt(newMark));
                            }
                            menuNum = 0; // return to main menu
                            break;

                        case "2": // Edit 1 mark for 1 student
                            printStudents();
                            System.out.print("Enter the name or number of the student you wish " +
                                    "to change all marks for: ");
                            nameOrId = input.next();
                            if(!isValidStud(nameOrId)) {
                                break;
                            }

                            do {
                                System.out.print("Enter which assignment's mark you would like to change: ");
                                assignmentNum = input.next();
                                if (!isValid(assignmentNum, 0, introCS.getStudent(nameOrId).getMarks().size())) {
                                    System.out.println("Invalid assignment number. Please enter a number between " +
                                            "0 and " + introCS.getStudent(nameOrId).getMarks().size() - 1);
                                }
                            } while(!isValid(assignmentNum, 0, introCS.getStudents().size() - 1));
                            // Possible make into a method
                            do {
                                System.out.print("Enter the mark you wish to set all of the " + nameOrId +
                                        "'s marks to [-1, 100]: ");
                                newMark = input.next();
                                if (!isValid(newMark, -1, 100)) {
                                    System.out.println("Mark invalid please enter a mark between " +
                                            "[0, 100] or -1 for no mark");
                                }
                            } while(!isValid(newMark, -1, 100));
                            introCS.getStudent(introCS.findStudent(nameOrId)).setAllMarks(Integer.parseInt(newMark));
                            menuNum = 0;
                            break; 

                        case "3": // Edit 1 mark for every student
                            do {
                                System.out.print("Enter which assignment's mark you would like to change (for all students): ");
                                assignmentNum = input.next();
                                if (!isValid(assignmentNum, 0, introCS.getStudent(nameOrId).getMarks().size())) {
                                    System.out.println("Invalid assignment number. Please enter a number between " +
                                            "0 and " + introCS.getStudent(nameOrId).getMarks().size() - 1);
                                }
                            } while(!isValid(assignmentNum, 0, introCS.getStudents().size() - 1));
                            // Possible make into a method
                            do {
                                System.out.print("Enter the mark you wish to set all of assignment number " + 
                                assignmentNum + "'s marks to [-1, 100]: ");
                                newMark = input.next();
                                if (!isValid(newMark, -1, 100)) {
                                    System.out.println("Mark invalid please enter a mark between " +
                                            "[0, 100] or -1 for no mark");
                                }
                            } while(!isValid(newMark, -1, 100));
                            for(Student stud: introCS.getStudent()) {
                                stud.setMark(assignmentNum, newMark);
                            }
                            menuNum = 0;
                            break; 

                        case "4": // Go back
                            System.out.println("Returning to main menu. . .");
                            break;
                        default:
                            System.out.println("Invalid input, please choose a valid option!");
                            break;
                    }
                
                case 3:
                    switch(userInput) {
                        case "0": // Add assignment
                            introCS.addAssignment();
                            System.out.println("New assignment created. There are now " + introCS.getStudents().size()
                                + " assignments for this course.");
                            menuNum = 0;
                            break;

                        case "1": // Delete assignment
                            do {
                                System.out.print("Enter the assignment number you would like to delete: ");
                                assignmentNum = input.next();
                                if (!isValid(assignmentNum, 0, introCS.numAssignments()-1)) {
                                    System.out.println("Invalid assignment number. Please enter a number " +
                                        "between 0 and " + (introCS.numAssignments() - 1) + ".");
                                }
                            } while(!isValid(assignmentNum, 0, introCS.numAssignments() - 1)); // CHANGE THIS
                            introCS.deleteAssignment(assignmentNum);
                            System.out.println("Deleted assignment number " + assignmentNum);
                            menuNum = 0; // return to the main menu
                            break;

                        case "2": // go back
                            System.out.println("Returning to main menu. . .");
                            menuNum = 0; // return to main menu
                            break;

                        default: // Invalid input
                            System.out.println("Invalid input, please choose a valid option!");
                            break;
                    }
                
                case 4:
                    switch(userInput) {
                        case "0":
                            printCourseAvg();
                    }

                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        System.out.println("Thank you for using GradeBook program.");

    }



    public static void main(String[] args) {
//        System.out.println(introCS);
        control();
    }
}