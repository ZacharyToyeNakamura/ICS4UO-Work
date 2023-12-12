package payroll;

/**
 * Any class that inherits this class will hold information on employee.
 * Each employee has a number, last name, first name, and job title.
 * They also will have methods to use sick days, get the number of sick days, reset the number of sick days,
 * pay the employee, and print a pay stub for the employee.
 */
public abstract class Employee {
    protected String employeeNumber;
    protected String lastName;
    protected String firstName;
    protected String jobTitle;

    /**
     * Creates a new employee with a number, last name, first name, and job title
     *
     * @param employeeNumber The employee's number
     * @param lastName The employee's last name
     * @param firstName The employee's first name
     * @param jobTitle The employee's job title
     */
    public Employee(String employeeNumber, String lastName, String firstName, String jobTitle) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
    }


    /**
     * @return The employee's number
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }


    /**
     * @return The employee's last name
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @return The employee's first name
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @return The employee's job title
     */
    public String getJobTitle() {
        return jobTitle;
    }


    /**
     * @return A nicely formatted string of all the employee's information, used to display info to the user.
     */
    @Override
    public String toString() {
        return "Employee: " + employeeNumber + ", " + firstName + " " + lastName + ", " + jobTitle;
    }


    // Returns the amount the employee is paid each month
    abstract double pay();
    // Uses amount sick days for the employee
    abstract void useSickDay(double amount);
    // Returns the number of sick days
    abstract double getSickDays();
    // Resets the number of sick days to the default
    abstract void resetSickDays();
    // Prints a pay stub for the employee with information on them and their pay.
    abstract void printPayStub();
}
