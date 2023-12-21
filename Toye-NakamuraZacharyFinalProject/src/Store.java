import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store {
    ArrayList<Item> inventory;

    /**
     * Initializes the array list
     */
    public Store() {
        inventory = new ArrayList<>();
    }


    public boolean loadData(String filename) {
        Scanner input;
        try {
            input = new Scanner(new File(filename));
            while(input.hasNextLine()) {

            }

        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        return false;
    }

    /**
     * Current Idea, convert the string to binary, then do something similar to base64 encryption except with 5 or 7
     * bits at a time, include a shift mechanism
     *
     * @param str
     * @param secret
     * @return
     */
    public String encrypt(String str, int secret) {
        final String ALPHA = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String output = "=";
        Random rand = new Random(secret);
        int shift = rand.nextInt(52);




        return "";
    }


}
