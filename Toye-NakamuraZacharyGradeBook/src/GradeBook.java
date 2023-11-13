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
        "  2: Edit one mark on an assignment\n" +
        "  3: Edit all student's marks for an assignment\n"+
        "  4: Edit all student's marks for all assignments\n" +
        "  5: Go back",

        "Enter which option you would like to preform: \n" +
        "  0: Add an assignment\n" +
        "  1: Delete an assignment\n" +
        "  2: Go back",

        "Enter which option you would like to preform: \n" +
        "  0: Print the course average\n" +
        "  1: Print an assignment's average\n" +
        "  2: Print all assignment's averages\n" +
        "  3: Print a student's average\n" +
        "  4: Print all student's average\n" +
        "  5: Print all student's averages and id's\n" +
        "  6: Print all of the marks for an assignment\n" +
        "  7: Print all marks for all assignments\n" +
        "  8: Print all student's marks\n" +
        "  9: Print all of a student's marks\n" +
        " 10: Print an overview of the course\n" + // custom
        " 11: Go back"
    };


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
    public static void printAssignmentAvg(int idx) {
        double avg = introCS.assignmentAvg(idx);
        if(avg == -3) {
            System.out.println("There are no marks for assignment " + idx + "!");
        } else if(avg == -2) {
            System.out.println("Assignment " + idx + " doesn't exist");
        } else if (avg == -1) {
            System.out.println("Assignment " + idx + " has no marks.");
        } else {
            System.out.printf("Assignment %d's average is %.1f%%%n", idx, avg);
        }
    }

    /**
     * Prints the average of a single student
     *
     * @param nameOrNum The name or id of the student that will have their average printed
     */
    public static void printStudentAvg(String nameOrNum) {
        double avg = introCS.findStudObj(nameOrNum).average();
        // If there is an error, tell the user
        if(avg == -3) {
            System.out.println(nameOrNum + " has no marks!");
        } else {
            System.out.printf("The average of %s is %.1f%% %n", nameOrNum, avg);
        }
    }


    /**
     * Prints all the students mark including for which assignment number they are for.
     *
     * @param nameOrId The name or number of the student
     */
    public static void printStudMarks(String nameOrId) {
        Student stud = introCS.findStudObj(nameOrId);
        System.out.println("Marks for " + stud.getName() + ": ");
        for(int i = 0; i < introCS.numAssignments(); i++) {
            if (stud.getMark(i) != -1) {
                System.out.println("  Assignment " + i + ": " + stud.getMark(i));
            } else {
                System.out.println("  Assignment " + i + ": No Mark");
            }
        }
    }

    /**
     * Prints all the current student's and their numbers
     */
    public static void printStudents() {
        System.out.println("Current students are: ");
        for (int i = 0; i < introCS.getNumStudents(); i++) {
            System.out.println("  " + introCS.studName(i) + ", " + introCS.getStudent(i).getNumber());
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
     * Repeatedly asks the user for a student name or id until they enter a name or id of a student in the course
     * When the user enters a student name/id in that's in the course it returns the input.
     *
     * @param message The message that is seen by the user when asking for input.
     * @return The name/id of the student that the user entered.
     */
    public static String getValidStud(String message) {
        printStudents(); // Shows the user the options for what students are in the course
        String userInput;
        do {
            System.out.print(message);
            userInput = input.nextLine();
            if (introCS.findStudent(userInput) == -1) {
                System.out.println("There is no student with that name or number in this course!");
            }
        } while (introCS.findStudent(userInput) == -1);
        return userInput;
    }

    public static int getValidMark(String message) {
        String newMark;
        boolean first = true;
        do {
            // After the first loop print an invalid mark message, letting the user know that they must try again
            if (!first) {
                System.out.println("Mark invalid please enter a mark between [0, 100] or -1 for no mark");
            }
            System.out.print(message);
            newMark = input.nextLine();
            first = false;
        } while(!isValid(newMark, -1, 100));
        return Integer.parseInt(newMark);
    }

    public static int getValidAssignNum(String message) {
        String assignmentNum;
        boolean first = true;
        do {
            // After the first loop print an invalid assign num message, letting the user know that they must try again
            if (!first) {
                System.out.println("Invalid assignment number. Please enter a number " +
                        "between 0 and " + (introCS.numAssignments() - 1) + ".");
            }
            System.out.print(message);
            assignmentNum = input.nextLine();
            first = false;

        } while (!isValid(assignmentNum, 0, introCS.numAssignments() - 1));
        return Integer.parseInt(assignmentNum);
    }

    /**
     * The command and control center for the program,
     * operates the menu and calls appropriate methods based on input
     */
    public static void control() {
        boolean exit = false;
        int menuNum = 0; // Which menu the program is currently in (-1 for not in menu)

        // Temp Variables used to store input from time to time.
        String nameOrNum; // The user's input for a name or number
        int newMark; // The user's input for a new mark
        int assignmentNum; // The user's input for which assignment to view/change

        while (!exit) { // While the user doesn't decide to end the program
            System.out.println("\n" + menus[menuNum]);
            System.out.print("> ");
            String userInput = input.nextLine();

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
                        case "0": // Add a student with all marks set to no marks.
                            System.out.print("Enter student's name: ");
                            String studName = input.nextLine();
                            System.out.print("Enter student's number: ");
                            String studNum = input.nextLine();
                            ArrayList<Integer> tempMarks = new ArrayList<>();
                            for (int i = 0; i < introCS.numAssignments(); i++) {
                                tempMarks.add(-1);
                            }
                            introCS.addStudent(new Student(studName, studNum, tempMarks));

                            System.out.println("Successfully added new student: " + studName + ", " + studNum);
                            menuNum = 0; // Return to main menu
                            break;

                        case "1": // Edit a student's information
                            nameOrNum = getValidStud("Enter the student's name or number that you wish to change: ");

                            System.out.print("Enter the student's new name (-1 for don't change): ");
                            String newName = input.nextLine();
                            System.out.print("Enter the student's new number (-1 for don't change): ");
                            String newNum = input.nextLine();
                            // Should be impossible but as a safeguard make sure editStudent didn't throw an error flag
                            if(introCS.editStudent(nameOrNum, newName, newNum) == -1) {
                                System.out.println("Unknown Error: editStudent() student not found");
                            }

                            System.out.println("Successfully changed " + nameOrNum + "'s information");
                            menuNum = 0;
                            break;

                        case "2": // Remove a student
                            nameOrNum = getValidStud("Enter the name or number of the student you wish to " +
                                    "demit from the course: ");

                            // Should be impossible but as a safeguard make sure demitStudent didn't throw an error flag
                            if (introCS.demitStudent(nameOrNum) == -1) {
                                System.out.println("Unknown Error: demitStudent student not found");
                            }

                            System.out.println("Successfully removed " + nameOrNum + " from the course");
                            menuNum = 0; // return to main menu
                            break;

                        case "3": // Go back
                            System.out.println("Returning to main menu. . .");
                            menuNum = 0;
                            break;

                        default:
                            System.out.println("Invalid Input!");
                            break;
                    }
                    break;
                
                case 2:
                    switch(userInput) {
                        case "0": // Edit 1 mark for 1 student
                            nameOrNum = getValidStud("Enter the name or number of the student you wish " +
                                    "to change 1 marks for: ");
                            assignmentNum = getValidAssignNum("Enter which assignment's mark you would like to change" +
                                    ": ");
                            newMark = getValidMark("Enter the mark you wish to set the mark of assignment number " + assignmentNum
                                + " for " + nameOrNum + " to (-1 for no mark): ");

                            introCS.editMark(nameOrNum, newMark, assignmentNum);
                        

                            System.out.println("Successfully changed " + nameOrNum + "'s mark for assignment number " 
                                + assignmentNum + " to " + newMark);
                            menuNum = 0;
                            break; 

                        case "1": // Edit all of 1 student's marks (set all student's marks to x)
                            nameOrNum = getValidStud("Enter the name or number of the student you wish " +
                                    "to change all marks for: ");
                            newMark = getValidMark("Enter the mark you wish to set all of " + nameOrNum +
                                    "'s marks to (-1 for no mark): ");

                            introCS.findStudObj(nameOrNum).setAllMarks(newMark);

                            System.out.println("Successfully changed all of the marks of " + nameOrNum +
                                " to " + newMark + ".");
                            menuNum = 0;
                            break;

                        case "2":
                            assignmentNum = getValidAssignNum("Enter which assignment's mark you would like to change " +
                                    "(for one student): ");

                            nameOrNum = getValidStud("Enter the name or number of the student you wish " +
                                    "to the mark for assignment number " + assignmentNum + " : ");

                            newMark = getValidMark("Enter the mark you wish to set assignment number " +
                                    assignmentNum + "'s mark to for " + nameOrNum + " (-1 for no mark): ");

                            introCS.editMark(nameOrNum, assignmentNum, newMark);
                            System.out.println("Successfully changed assignment number " + assignmentNum + 
                                "'s mark for " + nameOrNum + " to " + newMark + ".");
                            menuNum = 0;
                            break;


                        case "3": // Edit 1 mark for every student  // Edit all student's marks for an assignment
                            assignmentNum = getValidAssignNum("Enter which assignment's mark you would like to change " +
                                    "(for all students): ");

                            newMark = getValidMark("Enter the mark you wish to set all of assignment number " +
                                    assignmentNum + "'s marks to (-1 for no mark): ");

                            for(int i = 0; i < introCS.getNumStudents(); i++) {
                                introCS.editMark(introCS.studName(i), assignmentNum, newMark);
                            }
                            System.out.println("Successfully changed all marks for assignment number " +
                            assignmentNum + " to " + newMark + " for all students.");
                            menuNum = 0;
                            break;

                        case "4": // Edit all of all student's marks (set all marks to x)
                            newMark = getValidMark("Enter the mark you wish to set all student's marks to " +
                                    "-1 for no mark: ");

                            for (int i = 0; i < introCS.getNumStudents(); i++) {
                                introCS.getStudent(i).setAllMarks(newMark);
                            }

                            System.out.println("Successfully set all marks for the course to " + newMark + ".");
                            menuNum = 0; // return to main menu
                            break;

                        case "5": // Go back
                            System.out.println("Returning to main menu. . .");
                            menuNum = 0;
                            break;

                        default:
                            System.out.println("Invalid input, please choose a valid option!");
                            break;
                    }
                    break;
                
                case 3:
                    switch(userInput) {
                        case "0": // Add assignment
                            introCS.addAssignment();
                            System.out.println("New assignment created. There are now " + introCS.numAssignments()
                                + " assignments for this course.");
                            menuNum = 0;
                            break;

                        case "1": // Delete assignment
                            assignmentNum = getValidAssignNum("Enter the assignment number you would like to delete: ");

                            introCS.deleteAssignment(assignmentNum);
                            System.out.println("Deleted assignment number " + assignmentNum + ". There are now " +
                                introCS.numAssignments() + " assignments in the course.");
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
                    break;
                
                case 4:
                    switch(userInput) {
                        case "0": // print the course average
                            printCourseAvg();
                            menuNum = 0;
                            break;

                        case "1": // print an assignment's average
                            assignmentNum = getValidAssignNum("Enter the assignment number you would like to get " +
                                    "the average for: ");
                            printAssignmentAvg(assignmentNum);
                            menuNum = 0;
                            break;

                        case "2": // Print all assignment's averages
                            for (int i = 0; i < introCS.numAssignments(); i++) {
                                printAssignmentAvg(i);
                            }
                            menuNum = 0; 
                            break;

                        case "3": // prints a student's average
                            nameOrNum = getValidStud("Enter the name or number of the student you wish " +
                                    "to see the average of: ");
                            printStudentAvg(nameOrNum);
                            menuNum = 0;
                            break;

                        case "4": // prints all student's averages
                            for(int i = 0; i < introCS.getNumStudents(); i++) {
                                printStudentAvg(introCS.studName(i));
                            }
                            menuNum = 0;
                            break;

                        case "5": // prints all student's averages and their ids
                        // print a list of all students and their average in a course
                            for(int i = 0; i < introCS.getNumStudents(); i++) {
                                Student stud = introCS.getStudent(i);
                                if (stud.average() == -3) { // Edge case of a student having no marks.
                                    System.out.printf("%s, %s, No marks %n",
                                            stud.getName(), stud.getNumber());
                                } else {
                                    System.out.printf("%s, %s, Average: %.1f%% %n",
                                            stud.getName(), stud.getNumber(), stud.average());
                                }
                            }
                            menuNum = 0;
                            break;

                        case "6": // Prints all the marks for 1 assignment
                            assignmentNum = getValidAssignNum("Enter the assignment's number for which you would like" +
                                    "to see the marks for: ");
                            System.out.println("Marks for assignment number " + assignmentNum);
                            for (int i = 0; i < introCS.getNumStudents(); i++) {
                                if (introCS.getStudent(i).getMark(assignmentNum) != -1) {
                                    System.out.printf("  %s: %d%% %n", introCS.studName(i), introCS.getStudent(i).getMark(assignmentNum));
                                } else {
                                    System.out.printf("  %s: No Mark %n", introCS.studName(i));
                                }
                            }
                            menuNum = 0;
                            break;
                        
                        case "7": // prints all marks for all assignments
                            for(int i = 0; i < introCS.numAssignments(); i++) {
                                System.out.println("Marks for assignment number " + i + ":");
                                for (int j = 0; j < introCS.getNumStudents(); j++) {
                                    if (introCS.getStudent(j).getMark(i) != -1) {
                                        System.out.printf("  %s: %d%% %n", introCS.studName(j), introCS.getStudent(j).getMark(i));
                                    } else {
                                        System.out.printf("  %s: No Mark %n", introCS.studName(j));
                                    }
                                }
                            }
                            menuNum = 0;
                            break;


                        case "8": // Prints all marks of a student's marks
                            for(int i = 0; i < introCS.getNumStudents(); i++) {
                                printStudMarks(introCS.studName(i));
                            }
                            menuNum = 0;
                            break;

                        case "9":
                            nameOrNum = getValidStud("Enter the name or number of the student you would like to " +
                                    "see the marks for: ");

                            printStudMarks(nameOrNum);
                            menuNum = 0;
                            break;

                        case "10": // Custom: prints an overview of the course
                            System.out.println(introCS);
                            menuNum = 0;
                            break;

                        case "11":
                            System.out.println("Returning to main menu. . .");
                            menuNum = 0;
                            break;

                        default:
                            System.out.println("Invalid input: please choose a valid option.");
                            break;
                    }
                    break;

                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        System.out.println("Thank you for using GradeBook program.");

    }

    public static void main(String[] args) {
        control();
    }
}