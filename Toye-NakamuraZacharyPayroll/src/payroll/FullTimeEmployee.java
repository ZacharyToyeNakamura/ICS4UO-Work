package payroll;

/**
 * This class represents a full-time employee, it contains their yearly salary, the number of sick days they have left,
 * the number of sick days they get a year and the number of months in a year.
 * It can preform the methods found in employee as well as a few getter methods.
 * A part-time employee is paid their salary divide by the number of months every month.
 */
public class FullTimeEmployee extends Employee {
    private static final double YEARLY_SICK_DAYS = 20;
    private static final int MONTHS = 12;
    private double yearlySalary;
    private double sickDaysLeft;


    /**
     * Creates a new full time employee with a number, a first and last name, a job title, a yearly salary and
     * some amount of sick days left
     *
     * @param employeeNumber The employee's number
     * @param lastName The employee's last name
     * @param firstName The employee's first name
     * @param jobTitle The employee's job title
     * @param yearlySalary The employee's yearly salary
     * @param sickDaysLeft The number of sick days the employee has left
     */
    public FullTimeEmployee(String employeeNumber, String lastName, String firstName, String jobTitle,
                            double yearlySalary, double sickDaysLeft) {
        super(employeeNumber, lastName, firstName, jobTitle);
        this.yearlySalary = yearlySalary;
        this.sickDaysLeft = sickDaysLeft;
    }


    /**
     * @return The yearly salary of the employee
     */
    public double getYearlySalary() {
        return yearlySalary;
    }


    /**
     * @return A nicely formatting string of the employee's information. This method is used for displaying information
     * on the employee to the user.
     */
    @Override
    public String toString() {
        return super.toString() + ", full-time";
    }


    /**
     * Each full time employee gets a yearly salary, but they get paid monthly. Each paycheck is 1/12 of their salary.
     *
     * @return The amount the employee made in a month.
     */
    @Override
    public double pay() {
        return yearlySalary / MONTHS;
    }


    /**
     * Reduces the amount of sick days the employee has left to use (because they used some).
     *
     * @param amount The number of sick days the employee used.
     */
    @Override
    public void useSickDay(double amount) {
        sickDaysLeft -= amount;
    }


    /**
     * @return The number of sick days the employee has left.
     */
    @Override
    public double getSickDays() {
        return sickDaysLeft;
    }


    /**
     * Resets the number of sick days the employee has left. (This makes the number of sick days they have left equal
     * to the yearly amount of allocated sick days (YEARLY_SICK_DAYS).)
     */
    @Override
    public void resetSickDays() {
        sickDaysLeft = YEARLY_SICK_DAYS;
    }


    /**
     * Prints a pay stub to output for the user to see. This pay stub includes The employee's information, their
     * yearly salary, their current monthly pay, and the number of sick days they have left.
     */
    @Override
    public void printPayStub() {
        System.out.println("--------------- PAY STUB ---------------");
        System.out.println(this.toString());
        System.out.printf("Yearly Salary: $%.2f %n", yearlySalary);
        System.out.printf("Current Month pay: $%.2f %n", this.pay());
        System.out.println("Sick days left: " + sickDaysLeft);
        System.out.println("----------------------------------------\n");
    }

}
