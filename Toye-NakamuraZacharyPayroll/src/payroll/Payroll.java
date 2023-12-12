package payroll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The payroll class is used to hold a list of employees and preform methods on the list. It can list all the
 * employee's and their information, print all employee's pay stubs, print one employee's pay stub, load employees from
 * a file (given that the file is formatted properly) and save employee information to a file (formatting it). It can
 * also use sick days for employee's and reset sick days for part-time employees monthly and full-time employees
 * monthly.
 */
public class Payroll {
    private ArrayList<Employee> staffList;

    /**
     * Constructor for a payroll object. Initializes an empty list.
     */
    public Payroll() {
        staffList = new ArrayList<Employee>();
    }

    /**
     * Loads a list of staff from the given file path, the file must follow this format for it to load properly.
     * Full-time employees formatted like this:
     * EmployeeID,LastName,FirstName,JobTitle,Status,Salary,SickDaysLeft
     * And part-time employees formatted like this:
     * EmployeeID,LastName,FirstName,JobTitle,Status,HoursAssigned,HourlyWage,SickDaysTaken
     *
     * @param filename The path of the file that the staff list is to be read from
     * @return True if the staff list was successfully loaded, false if not.
     */
    public boolean loadStaffList(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            input.useDelimiter(",");
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
                staffList.add(emp);
            }
            input.close();

            return true;
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        return false;
    }


    /**
     * Saves the current staff and all their information to a csv file that can be loading into the program later
     * Formats the saved data in this way. Each employee is on a separate line and.
     * Full-time employees are formatted like this:
     * EmployeeID,LastName,FirstName,JobTitle,Status,Salary,SickDaysLeft
     * And part-time employees are formatted like this:
     * EmployeeID,LastName,FirstName,JobTitle,Status,HoursAssigned,HourlyWage,SickDaysTaken
     *
     * @param filename
     * @return True if the file was saved successfully and false if not.
     */
    public boolean saveStaffList(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for(Employee staff: staffList) {
                if (staff instanceof FullTimeEmployee) {
                    writer.write(staff.getEmployeeNumber() + "," + staff.getLastName() + "," +
                            staff.getFirstName() + "," + staff.getJobTitle() + ",full-time,"+
                            ((FullTimeEmployee) staff).getYearlySalary() + "," + staff.getSickDays());
                } else {
                    writer.write(staff.getEmployeeNumber() + "," + staff.getLastName() + "," +
                            staff.getFirstName() + "," + staff.getJobTitle() + ",part-time,"+
                            ((PartTimeEmployee) staff).getNumHoursAssigned()+ "," +
                            +((PartTimeEmployee) staff).getHourlyWage() +","+ staff.getSickDays());
                }
                writer.write("\n");
            }
            writer.close();

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
        System.out.println("All Employees:");
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
            System.out.println("Employee " + id + " not found!");
            return;
        }
        emp.printPayStub();
    }


    /**
     * Displays the pay stubs for all employees to the user.
     */
    public void printAllPayStubs() {
        System.out.println( "All pay stubs of all employees:\n" +
                            "All Employee Pay Stubs:");
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
            System.out.println("Employee " + id + " not found!");
            return;
        }
        emp.useSickDay(amount);
        if (emp instanceof PartTimeEmployee) System.out.println("New sick days taken: " + emp.getSickDays());
        else System.out.println("New number of sick days left: " + emp.getSickDays());
    }


    /**
     * Resets the number of sick days left for all full time employees, this resets to the number of sick days
     * full-time employees are allocated a year, currently 20 but can be changed, the variable is YEARLY_SICK_DAYS.
     */
    public void yearlySickDayReset() {
        for (Employee staff: staffList) {
            if(staff instanceof FullTimeEmployee) {
                staff.resetSickDays();
            }
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
