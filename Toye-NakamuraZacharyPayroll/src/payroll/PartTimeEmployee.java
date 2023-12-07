package payroll;

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
                            double hourlyWage, double sickDaysTaken) {
        super(employeeNumber, lastName, firstName, jobTitle);
        this.hourlyWage = hourlyWage;
        this.sickDaysTaken = sickDaysTaken;
    }
}
