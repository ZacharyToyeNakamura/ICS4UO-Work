package payroll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Payroll {
    private ArrayList<Employee> staffList;

    /**
     * Constructor for a payroll object. Initializes an empty list.
     */
    public Payroll() {
        staffList = new ArrayList<Employee>();
    }

    /**
     * Loads a list of staff from the given file path
     *
     * @param filename The path of the file that the staff list is to be read from
     * @return True if the staff list was successfully loaded, false if not.
     */
    public boolean loadStaffList(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            input.useDelimiter(",");
            // TODO
            while(input.hasNext()) {
                String id = input.next(); // The employee's number/id
                String last = input.next(); // The employee's last name
                String first = input.next(); // The employee's first name
                String title = input.next(); // The employee's title
                String status = input.next(); // The employee's status (full or part)-time
                Employee emp;

                if(status.equals("full-time")) { // If the employee is full-time
                    double yearlySalary = Double.parseDouble(input.next());
                    double sickDaysLeft = Double.parseDouble(input.nextLine().substring(1));
                    emp = new FullTimeEmployee(id, last, first, title, yearlySalary, sickDaysLeft);
                } else { // Otherwise they are part-time
                    // Parse for the rest of the employee's information. hours assigned, hourly wage, and sick days taken
                    double hoursAssigned = Double.parseDouble(input.next());
                    double hourlyWage = Double.parseDouble(input.next());
                    double sickDaysUsed = Double.parseDouble(input.nextLine().substring(1));
                    emp = new PartTimeEmployee(id, last, first, title, hoursAssigned, hourlyWage, sickDaysUsed);
                }
                System.out.println("Here");
                staffList.add(emp);
            }

            return true;
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        return false;
    }


    /**
     *
     *
     * @param filename
     * @return
     */
    public boolean saveStaffList(String filename) {
        try {
            FileWriter input = new FileWriter(filename);
            // TODO

            return true;
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        return false;
    }


    /**
     * Displays all the staff's information to the user.
     */
    public void listAllEmployees() {
        for (Employee staff: staffList) {
            System.out.println(staff);
        }
    }


    /**
     * Returns the first employee object in the staff list with an employee number that matches "id"
     *
     * @param id The employee number that the employee has.
     * @return The employee object that has the employee number equal to "id"
     */
    public Employee getEmployee(String id) {
        for (Employee staff: staffList) {
            if(staff.getEmployeeNumber().equals(id)) {
                return staff;
            }
        }
        // If no staff was found with the id, return nothing
        return null;
    }


    /**
     * Displays the information of 1 employee to the user.
     *
     * @param id The number of the employee that the user wishes to see.
     */
    public void printEmployeePayStub(String id) {
        Employee emp = getEmployee(id);
        if(emp == null) {
            System.out.println("No such employee with id: " + id +" exists in the staff list");
            return;
        }
        emp.printPayStub();
    }


    /**
     * Displays the pay stubs for all employees to the user.
     */
    public void printAllPayStubs() {
        for (Employee staff: staffList) {
            staff.printPayStub();
        }
    }


    /**
     * Uses a certain amount of sick days for an employee.
     *
     * @param id The number of the employee that the user wishes to use sick days for.
     * @param amount The number of sick days that are used.
     */
    public void enterSickDay(String id, double amount) {
        Employee emp = getEmployee(id);
        if(emp == null) {
            System.out.println("No such employee with id: " + id +" exists in the staff list");
            return;
        }
        emp.useSickDay(amount);
    }


    /**
     * Resets the number of sick days for every employee (Can't have the start of a year without the start of a
     * new month).
     */
    public void yearlySickDayReset() {
        for (Employee staff: staffList) {
            staff.resetSickDays();
        }
    }


    /**
     * Resets the number of sick days for every part-time employee, full-time employees don't get resets because
     * they have a certain amount of sick days a year.
     */
    public void monthlySickDayReset() {
        for (Employee staff: staffList) {
            if(staff instanceof PartTimeEmployee) {
                staff.resetSickDays();
            }
        }
    }


}
