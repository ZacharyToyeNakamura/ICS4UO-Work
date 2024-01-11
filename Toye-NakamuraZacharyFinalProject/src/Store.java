import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Store {
    private final int ENCODED_CHAR_LEN = 11;
    private final int ENCODING_CHAR_LEN = ENCODED_CHAR_LEN - 1; // must be greater than 6 for the dummy bit
    private final int DUMMY_BIT_POS = 6; // position of the dummy bit in each character's unicode binary
    private final char PADDING_CHAR = ' ';
    private boolean encryption = true; // Will the program encrypt the file when it's saved.

    ArrayList<Item> inventory;

    /**
     * Initializes the array list
     */
    public Store() {
        inventory = new ArrayList<>();
    }


    /**
     *
     * @param filename
     * @return
     */
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
     * Saves the current inventory to a file in memory called [filename]. It first encrypts the string, so it's harder
     * to manipulate than plain text in the file.
     *
     * @param filename
     * @return
     */
    public boolean saveData(String filename) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            String output = "WOWOWOWOW";

            if(encryption) {
                int seed = (int)(System.currentTimeMillis() % 1000000);
                Random rand = new Random(seed);
                output = encrypt(output,rand.nextInt());
                writer.write("Yes\n");
                writer.write(String.format("%06d",seed));
            } else {
                writer.write("No\n");
            }

            writer.write(output);
            writer.close();

        } catch (IOException iox) {
            System.out.println("Fatal Error: Error saving data\n" +iox.getMessage());
            return false;
        }

        return true;
    }


    /**
     * Scrambles a string in a way so that it's harder to read. DO NOT USE security purposes,
     * it's probably easy to crack due to not using very large prime numbers and the usage of java's random library.
     * Current Idea, convert the string to binary, then do something similar to base64 encryption except with 7
     * bits instead of 6, include a shift mechanism.
     * Would add hamming codes (error correcting codes) if I had time.
     * maybe use SecureRandom instead of random
     *
     * @param str the string that is to be encrypted
     * @param secret A random number that it used as the seed for java's random number generator.
     * @return An "encrypted" version of str which can be decoded using decrypt
     */
    public String encrypt(String str, int secret) {

        final String original = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()))_+,./;'[]<>?:\"\\=-1234567890`~ ";
        StringBuilder scrambled = new StringBuilder("abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()))_+,./;'[]<>?:\"\\=-1234567890`~ ");
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
        System.out.println("Binary input is " + binaryInput);
        StringBuilder output = new StringBuilder("");
        for(int i = 0; i < (int)binaryInput.length() / ENCODING_CHAR_LEN; i++) {
            String nextBinary = (binaryInput.substring(
                    i * ENCODING_CHAR_LEN, i * ENCODING_CHAR_LEN + ENCODING_CHAR_LEN-DUMMY_BIT_POS)
                    + "1" + binaryInput.substring(i * ENCODING_CHAR_LEN + ENCODING_CHAR_LEN-DUMMY_BIT_POS, (i+1) * ENCODING_CHAR_LEN));
            output.append((char)Integer.parseInt(nextBinary, 2));
            System.out.println("Adding char " + (char)Integer.parseInt(nextBinary, 2) + " Binary: " + nextBinary);
        }
        if((int)binaryInput.length() % ENCODING_CHAR_LEN != 0) {
            int paddingCnt = 0;
            while((int)binaryInput.length() % ENCODING_CHAR_LEN != 0) {
                binaryInput += "0";
                paddingCnt++;
            }
            String nextBinary = (binaryInput.substring(binaryInput.length() - ENCODING_CHAR_LEN,
                    binaryInput.length() - DUMMY_BIT_POS) + "1" +
                    binaryInput.substring(binaryInput.length() - DUMMY_BIT_POS, binaryInput.length()));
            output.append((char)Integer.parseInt(nextBinary, 2));
            System.out.println("Adding char " + (char)Integer.parseInt(nextBinary, 2) + " Binary: " + nextBinary);
            // Can't use padding characters, 1 for each extra bit
            for(int i = 0; i < paddingCnt; i++) {
                output.append(PADDING_CHAR);
            }
        }
        return output.toString();
    }


}
