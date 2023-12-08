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


    public String getEmployeeNumber() {
        return employeeNumber;
    }


    public String getLastName() {
        return lastName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getJobTitle() {
        return jobTitle;
    }


    @Override
    public String toString() {
        return "Employee: " + employeeNumber + ", " + firstName + " " + lastName + ", " + jobTitle;
    }


    abstract double pay();
    abstract void useSickDay(double amount);
    abstract double getSickDays();
    abstract void resetSickDays();
    abstract void printPayStub();

}
