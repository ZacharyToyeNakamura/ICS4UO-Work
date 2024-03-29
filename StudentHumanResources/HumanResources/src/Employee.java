/**
 * Manages an employee's information, such as name, ID, yearly salary, and
 * number of sick days.
 *
 * @author Student
 */
public class Employee {
    private String name;
    private String id;
    private int salary;
    private int sickDays;

    /**
     * Constructs an Employee with name only.
     * 
     * @param name the name
     */
    public Employee(String name) {
        // your code here
        this.name = name;
    }
    /**
     * Constructs an Employee with name and employee ID only.
     * 
     * @param name the name
     * @param id the employee ID
     */
    public Employee(String name, String id) {
        // your code here
        this.name = name;
        this.id = id;
    }

    /**
     * Constructs an Employee with name, employee ID, salary, and sick days.
     * 
     * @param name the name
     * @param id the employee ID
     * @param salary the salary in dollars
     * @param sickDays the number of sick days allocated
     */
    public Employee(String name, String id, int salary, int sickDays) {
        // your code here
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.sickDays = sickDays;
    }

    /**
     * Gets the name of the employee.
     * 
     * @return employee name
     */
    public String getName() {
        return name; // your code here
    }

    /**
     * Sets the name of the employee.
     * 
     * @param name the name
     */
    public void setName(String name) {
        // your code here
        this.name = name;
    }

    /**
     * Gets the employee ID.
     * 
     * @return the employee ID
     */
    public String getId() {
        return id; // your code here
    }

    /**
     * Sets the employee ID.
     * 
     * @param id the employee ID
     */
    public void setId(String id) {
        // your code here
        this.id = id;
    }

    /**
     * Gets the salary of employee.
     * 
     * @return the salary
     */
    public int getSalary() {
        return salary; // your code here
    }

    /**
     * Sets employee's salary (yearly).
     * 
     * @param salary the salary of employee
     */
    public void setSalary(int salary) {
        // your code here
        this.salary = salary;
    }

    /**
     * Gets the number of sick days.
     * 
     * @return number of sick days remaining
     */
    public int getSickDays() {
        return sickDays; // your code here
    }

    /**
     * Sets the number of sick days.
     * 
     * @param sickDays number of sick days allocated
     */
    public void setSickDays(int sickDays) {
        // your code here
        this.sickDays = sickDays;
    }

    /**
     * The String representation of the Employee.
     * 
     * @return the employee's information formatted nicely
     */
    @Override
    public String toString() {
        return  "\nEmployee Information:"+
                "\n Name: " + name +
                "\n ID: " + id +
                "\n Salary: $" + salary +
                "\n Sick Days: " + sickDays; // your code here
    }

}
