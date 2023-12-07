package payroll;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
            FileReader input = new FileReader(filename);
            // TODO

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



}
