import java.io.File;
import java.io.IOException;
import java.util.*;

public class Store {
    private final int ENCODING_CHAR_LEN = 10;
    private final char PADDING_CHAR = ' ';
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
     * Current Idea, convert the string to binary, then do something similar to base64 encryption except with 7
     * bits instead of 6, include a shift mechanism
     *
     * @param str
     * @param secret
     * @return
     */
    public String encrypt(String str, int secret) {

        final String original = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()))_+,./;'[]<>?:\"\\=-1234567890`~";
        StringBuilder scrambled = new StringBuilder("abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()))_+,./;'[]<>?:\"\\=-1234567890`~");
        Random rand = new Random(secret);
        int offset = rand.nextInt(original.length() - 1) + 1;
        int shift = rand.nextInt(original.length() - 1) + 1; // Shift can't be max len or 0 because it would be useless

        // Swap characters randomly
        for(int i = 0; i < 1000; i++) {
            int a = rand.nextInt(original.length());
            int b = rand.nextInt(original.length());
            char temp = scrambled.charAt(a);
            scrambled.setCharAt(a, scrambled.charAt(b));
            scrambled.setCharAt(b, temp);
        }
        Dictionary<Character, Integer> dict = new Hashtable<>();
        for(int i = 0; i < original.length(); i++) {
            dict.put(original.charAt(i), i);
        }

        String binaryInput = "";
        for(int i = 0; i < str.length(); i++) {
            // Finds the position of the character in the original string. Let this integer value be x.
            // Shift x over a certain amount. Lastly, add the binary value of the character at the x to shifted input.
            binaryInput +=
                    ((String)(Integer.toBinaryString(scrambled.charAt((
                            dict.get(str.charAt(i)) + offset + shift * i) % original.length()))));
        }
        String output = "";
        for(int i = 0; i < (int)binaryInput.length() / ENCODING_CHAR_LEN; i++) {
            output += (char)Integer.parseInt(binaryInput.substring(i, i + 10), 2);
        }
        if((int)binaryInput.length() / ENCODING_CHAR_LEN != 10) {
            int paddingCnt = 0;
            while((int)binaryInput.length() / ENCODING_CHAR_LEN != 10) {
                binaryInput += "0";
                paddingCnt++;
            }
            output += (char)Integer.parseInt(binaryInput.substring(binaryInput.length() - 10,
                    binaryInput.length()), 2);
            // Can't use padding characters because any character can be part of the encrypted text
            output += String.format("%05d", paddingCnt);
        }








        return "";
    }


}
