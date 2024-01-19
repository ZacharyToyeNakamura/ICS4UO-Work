import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Note: Unknown effects some with changing final static fields: To change the "encryption" scheme, save a plain text
 * file, then change final static fields, then load the plain text file and save it as an "encrypted" file.
 *
 * This class contains all the items in the store and can
 *
 */
public class Store {
    private final static int ENCODED_CHAR_LEN = 11; // The number of bits a encoded character represents.
    private final static int ENCODING_CHAR_LEN = ENCODED_CHAR_LEN - 1; // must be greater than 6 for the dummy bit
    private final static int DUMMY_BIT_POS = 6; // position of the dummy bit in each character's unicode binary (from the right, 1 based indexing)
    private final static char PADDING_CHAR = ' '; // The character that symbolizes an extra bit.
    private boolean encryption = true; // Will the program encrypt the file when it's saved.
    private final static String ORIGINAL = "abcdefghijklmnopqrstuvxwyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+,./;'[]<>?:\"\\=-1234567890`~ \n\r";
    

    public final static double TAX_PERCENT = 1.13;
    private ArrayList<Item> inventory;

    /**
     * Initializes the array list and creates a store object
     */
    public Store() { 
        inventory = new ArrayList<>();
    }

    /**
     * @return The number of items in the stores inventory
     */
    public int getInventorySize() {
        return inventory.size();
    }

    /**
     * @return A copy of the store's inventory
     */
    public ArrayList<Item> copyInventory() {
        return new ArrayList<>(inventory);
    }

    /**
     * Acts like .get() for an array list, returning the element at the idxth position.
     * 
     * @param idx The position of the element that is needed.
     * @return the item in position, or null if idx is out of range.
     */
    public Item getItem(int idx) {
        if(idx < 0 || idx >= inventory.size()) {
            return null;
        }
        return inventory.get(idx);
    }


    /**
     * Adds an item to the stores inventory.
     * @param i The item to be added to the inventory
     */
    public void addItem(Item i) {
        inventory.add(i);
    }

    /**
     * Removes an item from the inventory, (Note: This will remove it's profit)
     * This means that the store is no longer tracking anything related to the removed item.
     * 
     * @param nameOrId The name or id of the item that is to be removed.
     * @return True if the item was removed, false if there was no item to remove with the name or id
     */
    public boolean removeItem(String nameOrId) {
        int toRemove = findItem(nameOrId);
        if(toRemove == -1) {
            return false;
        }
        inventory.remove(toRemove);
        return true;
    }


    /**
     * Loads data from a file to the invetory of the store. The data must be in a very specific format.
     * The first line tell the program if the data is "encrypted" or not, "Yes" means it is and "No" means it's not.
     * Every line contains a item (or a class that inherits item)
     * 
     * @param filename The filepath that the data will be loaded from
     * @return True if the data was loaded successfully, false if not.
     */
    public boolean loadData(String filename) {
        Scanner input;
        String allInput[] = new String[2]; // There will only be 2 lines in the input
        int cnt = 0;
        try {
            input = new Scanner(new File(filename));
            while(input.hasNextLine()) {
                allInput[cnt] = input.nextLine();
                cnt++;
            }
            // for(int i = 0; i < 2; i++) System.out.println(i  + " "  + allInput[i]);
            int secret = Integer.parseInt(allInput[1].substring(0, 6));
            if(allInput[0].equals("Yes")) {
                
                allInput[1] = decrypt(allInput[1].substring(6, allInput[1].length()), secret);
            }
            // System.out.println("INPUT WAS");
            // System.out.println(allInput[0] + "\n" + allInput[1]);
            if(!allInput[1].equals("Why do we use it?\r\n" + //
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\r\n" + //
                    "\r\n" + //
                    "\r\n" + //
                    "Where does it come from?\r\n" + //
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.")) {
                System.out.println("FAILED " + secret);
            }
            // System.out.println(secret);

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
    public boolean saveInventory(String filename) { 
        BufferedWriter writer; 
        try {
            
            writer = new BufferedWriter(new FileWriter(filename));
            String output = "Why do we use it?\r\n" + //
                    "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\r\n" + //
                    "\r\n" + //
                    "\r\n" + //
                    "Where does it come from?\r\n" + //
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.";
            if(encryption) {
                int seed = (int)(System.currentTimeMillis() % 1000000);
                Random rand = new Random(seed);
                int randSeed = rand.nextInt((int)1e6);
                // randSeed = 778883;
                // randSeed = seed;
                output = encrypt(output,randSeed);
                writer.write("Yes\n");
                writer.write(String.format("%06d",randSeed));
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
     * Pads a string with 0 to the left. If adds numDigits-str.length() 0s to the left of the string
     * This method is used instead of String.format("%010d",Long.parseLong(. . . . )); because that would break when
     * the binary inside is greater than 2^63 (I think)
     *
     * @param str The string that is to be padded with 0s
     * @param numDigits The number of digits the string should have.
     * @return The string padded with 0s to the left.
     */
    public static String padLZeros(String str, int numDigits) {
        while(str.length() < numDigits) {
            str = "0" + str;
        }
        return str;
    }


    /**
     * Scrambles a string in a way so that it's harder to read. DO NOT USE security purposes,
     * it's probably easy to crack due to not using very large prime numbers and the usage of java's random library.
     * Not revealing the secret number (by hardcoding it or smt) would make it more secure against people who know
     * what they are doing, otherwise it can be a large random number.
     * It uses java's random library to generate shift amounts and shifts every character in the input over [Similar to a single rotor engima machine]
     * Then converts each character of the string to binary, making a long string of 0s and 1s.  [This is kinda related to b64 encoding]
     * Lastly it takes chunks of 10 bits adds a dummy bit at position 6 (to make sure it's a valid readable character) and
     * converts the chunk to unicode. Padding characters are added if there aren't enough bits for the last new character.
     * PADDING_CHAR is added to the end of the "encrypted" string for the number of dummy bits.
     * 
     * Continuations:
     * Make random shifts each time instead of the same shift
     * Add hamming codes (error correcting codes). if extra time
     * Maybe use SecureRandom instead of random (Or make something that doesn't require random)
     * Scramble more by implementing a cipher as well as the rest of a more complex enigma machine.
     * Use of vector space encryption? Seems hard
     *
     * @param str The string that is to be scrambled or "encrypted"
     * @param secret A random number that it used as the seed for java's random number generator.
     * @return An "encrypted" version of str which can be decoded using decrypt
     */
    public static String encrypt(String str, int secret) {
        
        StringBuilder scrambled = new StringBuilder(ORIGINAL);
        // System.out.println("secret " +secret);
        Random rand = new Random(secret);
        int offset = rand.nextInt(ORIGINAL.length() - 1) + 1;
        int shift = rand.nextInt(ORIGINAL.length() - 1) + 1; // Shift can't be max len or 0 because it would be useless
        offset = 0; shift = 1;
        // Swap characters randomly
       for(int i = 0; i < 1000; i++) {
           int a = rand.nextInt(ORIGINAL.length());
           int b = rand.nextInt(ORIGINAL.length());
           char temp = scrambled.charAt(a);
           scrambled.setCharAt(a, scrambled.charAt(b));
           scrambled.setCharAt(b, temp);
       }
        // Used for fast look-ups (can't use integer values because the symbols are scattered in their value)
        Dictionary<Character, Integer> dict = new Hashtable<>();
        for(int i = 0; i < ORIGINAL.length(); i++) {
            dict.put(ORIGINAL.charAt(i), i);
        }
        // System.out.println("Scram " + scrambled);

        String binaryInput = "";
        // System.out.println("Print non-bianry: ");
        for(int i = 0; i < str.length(); i++) {
            // Finds the position of the character in the original string. Let this integer value be x.
            // Shift x over a certain amount. Lastly, add the binary value of the character at the x to shifted input.
            // System.out.println(str.charAt(i) + " i: "+ i + " " + str);
            // System.out.println(dict.get(str.charAt(i)));
            binaryInput +=  padLZeros((Integer.toBinaryString(scrambled.charAt((
                    dict.get(str.charAt(i)) + offset + shift * i) % ORIGINAL.length()))), 8);
            // System.out.println("Char " + scrambled.charAt((dict.get(str.charAt(i)) + offset + shift * i) % ORIGINAL.length()));
            // System.out.print(scrambled.charAt((dict.get(str.charAt(i)) + offset + shift * i) % ORIGINAL.length()));
        }
        // System.out.println("end ");

        // System.out.println("Binary input is " + binaryInput);
        StringBuilder output = new StringBuilder("");
        for(int i = 0; i < (int)binaryInput.length() / ENCODING_CHAR_LEN; i++) { // +1 because of 0 based indexing
            String nextBinary = (binaryInput.substring(
                    i * ENCODING_CHAR_LEN, i * ENCODING_CHAR_LEN + ENCODING_CHAR_LEN-DUMMY_BIT_POS) 
                    + "1" + binaryInput.substring(i * ENCODING_CHAR_LEN + ENCODING_CHAR_LEN-DUMMY_BIT_POS, (i+1) * ENCODING_CHAR_LEN));
            output.append((char)Integer.parseInt(nextBinary, 2));
            // System.out.println("Adding char " + (char)Integer.parseInt(nextBinary, 2) + " Binary: " + nextBinary);
        }
        int paddingCnt = 0;
        if((int)binaryInput.length() % ENCODING_CHAR_LEN != 0) {

            while((int)binaryInput.length() % ENCODING_CHAR_LEN != 0) {
                binaryInput += "0";
                paddingCnt++;
            }
            String nextBinary = (binaryInput.substring(binaryInput.length() - ENCODING_CHAR_LEN,
                    binaryInput.length() - DUMMY_BIT_POS) + "1" +
                    binaryInput.substring(binaryInput.length() - DUMMY_BIT_POS, binaryInput.length()));
                    
            output.append((char)Integer.parseInt(nextBinary, 2));
            // System.out.println("Adding char " + (char)Integer.parseInt(nextBinary, 2) + " Binary: " + nextBinary);
            // Can't use padding characters, 1 for each extra bit

            for(int i = 0; i < paddingCnt; i++) {
                output.append(PADDING_CHAR);
            }

        }
        // System.out.println("There is " + paddingCnt + " padding chars");
        // System.out.println("Shift " + shift + " offset " + offset);

        return output.toString();
    }


    /**
     * Converts an "encrypted" string to plain text which can be read by humans.
     *
     * @param str The scrambled string
     * @param secret The random number used by Java's random function
     * @return
     */
    public static String decrypt(String str, int secret) {
        // System.out.println("Decrypting . . .");
        StringBuilder scrambled = new StringBuilder(ORIGINAL);
        // System.out.println("secret " +secret);
        Random rand = new Random(secret);
        int offset = rand.nextInt(ORIGINAL.length() - 1) + 1;
        int shift = rand.nextInt(ORIGINAL.length() - 1) + 1; // Shift can't be max len or 0 because it would be useless
        offset = 0; shift = 1;

        // Make the exact same swaps
       for(int i = 0; i < 1000; i++) {
           int a = rand.nextInt(ORIGINAL.length());
           int b = rand.nextInt(ORIGINAL.length());
           char temp = scrambled.charAt(a);
           scrambled.setCharAt(a, scrambled.charAt(b));
           scrambled.setCharAt(b, temp);
       }
        // Reverse the dictionary keys and values
        // So scrambled -> location in array (later -> original)
        Dictionary<Character, Integer> dict = new Hashtable<>();
        for(int i = 0; i < scrambled.length(); i++) {
            dict.put(scrambled.charAt(i), i);
        }
        // System.out.println("Scram " + scrambled);
        String binaryInput = "";
        // Convert the string into binary
        int extraBits = 0; // Number of padding characters at the end
        for(int i = 0; i < str.length(); i++) {
            String binaryChar = padLZeros(Integer.toBinaryString(str.charAt(i)),11);
            // System.out.println("Binary char " + binaryChar);
            if (str.charAt(i) == PADDING_CHAR) {
                extraBits++;
            } else {
                // Remove the dummy bit
                binaryInput += binaryChar.substring(0, ENCODED_CHAR_LEN - DUMMY_BIT_POS - 1) +
                        binaryChar.substring(ENCODED_CHAR_LEN - DUMMY_BIT_POS + 1 - 1, binaryChar.length());
            }
        }
        // remove the extra bits
        binaryInput = binaryInput.substring(0, binaryInput.length() - extraBits);

        // System.out.println("Binary input is " + binaryInput);
        // System.out.println("There are "+extraBits+"extra bits");
        // System.out.println("Shift " + shift + " offset " + offset);

        String output = "";
        // Convert groups of 8 bits to unicode characters then shift it by the scramble and shift amount.
        for(int i = 0; i < binaryInput.length() / 8; i++) {
            // These are non-printable character, if this message ever triggers there's probably an off by 1 error
            // Which make 1 character different. mostly likely not breaking everything too much unless it's a critical
            // character ex a separating comma.
             if(Integer.parseInt(binaryInput.substring(i*8, (i+1)*8),2) < 8) {
                 System.out.println("Error: Character with a unicode value less than 32 found (This may cause data to not load properly). The binary is: " + binaryInput.substring(i*8, (i+1)*8));
                 continue;
             }
             // Convert the binary to a character, find its position in the scrambled, and shift it.
            int charPlace = (dict.get((char)Integer.parseInt(binaryInput.substring(i*8, (i+1)*8),2))
                    - offset - shift * i) % ORIGINAL.length();
            // System.out.println(dict.get((char)Integer.parseInt(binaryInput.substring(i*8, (i+1)*8),2)) - offset - shift * i + " % "  + ORIGINAL.length());
            // System.out.println(binaryInput.substring(i*8, (i+1)*8));

            // ensure that it's not a negative number, if it is then wrap around to the other end.
            if(charPlace < 0) {
                charPlace += ORIGINAL.length();
            }

            output += ORIGINAL.charAt(charPlace);
        }
         return output;
    }


    /**
     * Finds the index of an item with the name or id of the parameter "nameOrId".
     * For binary search you first search on the name, then if it doesn't exist search on the id. <TODO>
     *
     * @param nameOrId The name or id of the item
     * @return The index in inventory the item is.
     */
    public int findItem(String nameOrId) {
        for (int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).getName().equals(nameOrId) || inventory.get(i).getItemId().equals(nameOrId)) return i;
        }
        return -1;
    }

    /**
     * Permanently changes the order of the items to alphabetically sorted.
     */
    public void permSort() {
        Collections.sort(inventory);
    }

    /**
     * Sorts the inventory alphabetically, but doesn't alter the current order of the inventory.
     * 
     * @return A new array list that is the sorted version of the current inventory.
     */
    public ArrayList<Item> tempSort() {
        ArrayList<Item> temp = new ArrayList<>(inventory);
        Collections.sort(temp);
        return temp;
    }

    /**
     * Restocks all items in the inventory to their restock amount.
     * Spends money according to amount of items restocked.
     */
    public void restock() {
        for(int i = 0; i < inventory.size(); i++) {
            inventory.get(i).restock();
        }
    }

    /**
     * Sells an amount of item, assumes that the amount is valid.
     * 
     * @param nameOrId The name or id of the item being sold
     * @param amount The amount of item being sold
     * @return The amount the customer would paid 
     */
    public double sell(String nameOrId, int amount) {
        double returnFlag = inventory.get(findItem(nameOrId)).sell(amount);
        if(returnFlag == -2) {
            System.out.println("Error: Not enough stock left");
            return -2;
        }
        return returnFlag;

    }

    /**
     * Compares 2 items based on net profit
     * 
     * @param i The first item to be compared
     * @param i2 The second item to be compared
     * @return True if i has a higher (or equal) profit compared to i2, otherwise false.
     */
    public static boolean compByProfit(Item i, Item i2) {
        if(i.getNetProfit() >= i2.getNetProfit()) {
            return true;
        }
        return false;
    }    

    

}
