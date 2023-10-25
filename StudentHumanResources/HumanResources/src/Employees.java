import java.util.ArrayList;

/**
 * Manages a list of employees (of type Employee). Employee objects are
 * stored in an ArrayList. Employees can be added, removed, and given raises.
 * Sick days can be used. Employees are referenced by their String
 * employee ID or name.
 * @author Student
 */
public class Employees {

    private ArrayList<Employee> employees;

    /**
     * Constructs an Employees instance and initializes the list.
     */
    public Employees() {
        // your code here
        employees = new ArrayList<>();
    }

    /**
     * Adds an employee to the list.
     * 
     * @param e an instance of class Employee
     */
    public void addEmployee(Employee e) {
        // your code here
        employees.add(e);
    }

    /**
     * Gets an Employee from the list by matching the employee
     * name or ID given.
     * 
     * @param nameOrID the employee name or ID
     * @return the Employee or null if not in the list
     */
    public Employee getEmployee(String nameOrID) {
        // your code here
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getId().equals(nameOrID) || employees.get(i).getName().equals(nameOrID)) {
                return employees.get(i);
            }
        }
        return null;
    }

    /**
     * Removes the first occurrence of an employee from the list, if they exist.
     * Prints a message with results.
     * 
     * @param nameOrID String that is the employee name or ID
     */
    public void removeEmployee(String nameOrID) {
        // your code here
        Employee e = getEmployee(nameOrID);
        if(e == null) {
            System.out.println(nameOrID + " is not an employee.");
            return;
        }
        employees.remove(e);
        System.out.println("Employee " + nameOrID + " has been removed.");
    }


    /**
     * Reduces by 1 the number of sick days of the given employee.
     * Prints a success message or an error message if the employee does not
     * exist or does not have enough sick days.
     * 
     * @param nameOrID the employee name or ID
     */
    public void useSickDay(String nameOrID) {
        // your code here
        Employee e = getEmployee(nameOrID);
        if(e == null) {
            System.out.println(nameOrID + " is not an employee.");
            return;
        }
        if(e.getSickDays() > 0) {
            e.setSickDays(e.getSickDays() - 1);
            System.out.println(e.getName() + " used a sick day.");
        } else {
            System.out.println(e.getName() + " does not have enough sick days.");
        }
    }


    /**
     * Gives a raise to the employee. Prints the new salary or an error
     * message if they do not exist.
     * 
     * @param nameOrID the name or ID of the employee
     * @param amount dollar amount to increase the salary
     */
    public void giveRaise(String nameOrID, int amount) {
        // your code here
        Employee e = getEmployee(nameOrID);
        if(e == null) {
            System.out.println(nameOrID + " is not an employee.");
            return;
        }
        e.setSalary(e.getSalary() + amount);
        System.out.println(nameOrID + " new salary: $"+e.getSalary());
    }


    /**
     * Returns the String representation of Employee, including all field values.
     * 
     * @return all employee information formatted nicely
     */
    @Override
    public String toString() {
        String output = "Employees:\n" +
                        "[\n";
        for (Employee e : employees) {
            output += e.toString();
            output = output.substring(0,output.length()-1) + ",\n";
        }
        output = output.substring(0,output.length()-2) + "]";
        return output; // your code here
    }

}

