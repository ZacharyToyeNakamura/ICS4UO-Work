package payroll;

/**
 * This class represents a part-time employee, it has the number of hours a part-time employee works be day
 * The number of hours they are assigned to work in a month, their hourly wage and the number of sick days they
 * have taken.
 * It can preform the methods found in employee as well as a few getter methods.
 * A part-time employee is paid their hourly wage times the number of hours they worked. The number of hours they worked
 * is the number of hours they were assigned minus (sickDaysTaken * HOURS_PER_DAY). They are paid monthly.
 */
public class PartTimeEmployee extends Employee {
    private static final double HOURS_PER_DAY = 7;
    private double numHoursAssigned;
    private double hourlyWage;
    private double sickDaysTaken;

    /**
     * Creates a new full time employee with a number, a first and last name, a job title, an hourly wage,
     *
     * @param employeeNumber The employee's number
     * @param lastName The employee's last name
     * @param firstName The employee's first name
     * @param jobTitle The employee's job title
     * @param hourlyWage The employee's hourly wage (The amount of money they make in 1 hour)
     * @param sickDaysTaken The number of sick days the employee has taken (They don't get paid for these hours)
     */
    public PartTimeEmployee(String employeeNumber, String lastName, String firstName, String jobTitle,
                            double numHoursAssigned, double hourlyWage, double sickDaysTaken) {
        super(employeeNumber, lastName, firstName, jobTitle);
        this.numHoursAssigned = numHoursAssigned;
        this.hourlyWage = hourlyWage;
        this.sickDaysTaken = sickDaysTaken;
    }


    /**
     * @return The number of hours the employee is assigned
     */
    public double getNumHoursAssigned() {
        return numHoursAssigned;
    }


    /**
     * @return The hourly wage of the part-time employee
     */
    public double getHourlyWage() {
        return hourlyWage;
    }


    /**
     * @return A nicely formatting string of the employee's information. This method is used for displaying information
     * on the employee to the user.
     */
    @Override
    public String toString() {
        return super.toString() + ", part-time";
    }


    /**
     * Part-time employees are paid their hourly wage for each hour they work. They work the number of hours assigned
     * minus (sick days they have taken times the number of hours they would have worked that day).
     *
     * @return The amount the employee made in a month.
     */
    public double pay() {
        return (numHoursAssigned - (sickDaysTaken * HOURS_PER_DAY)) * hourlyWage;
    }


    /**
     * Increases the number of sick days the employee has taken by the parameter amount
     *
     * @param amount The number of sick days the employee uses
     */
    public void useSickDay(double amount) {
        sickDaysTaken += amount;
    }


    /**
     * @return The number of sick days the employee has taken.
     */
    public double getSickDays() {
        return sickDaysTaken;
    }


    /**
     * Resets the number of sick days the employee has taken to 0.
     */
    public void resetSickDays() {
        sickDaysTaken = 0;
    }


    public void printPayStub() {
        System.out.println("--------------- PAY STUB ---------------\n");
        this.toString();
        System.out.printf("Hourly Wage: $%.2f %n", hourlyWage);
        System.out.println("Sick days taken: " + sickDaysTaken); // This should be below month pay but don't want to
        System.out.printf("Current Month pay: $%.2f %n", this.pay()); // lose marks for anything.
        System.out.println("----------------------------------------\n");
    }



}
