import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "src/test.txt";

//        scannerByLine(filename);
//        scannerByToken(filename);
//        scannerByTokenInt("src/numbers.txt", ",");
//        bufferedReader(filename);
        bufferedReaderByChar(filename);
    }

    /**
     * A demonstration of how to use scanner and a file and takes input separated by new line
     * @param filename
     */
    public static void scannerByLine(String filename) {
        Scanner reader;
        try {
            reader = new Scanner(new File(filename));
            while(reader.hasNext()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }

    /**
     * A demonstration on how to use scanner and a file to take input separated by spaces.
     * @param filename
     */
    public static void scannerByToken(String filename) {
        Scanner reader;
        try {
            reader = new Scanner(new File(filename));
            while(reader.hasNext()) {
                String token = reader.next();
                System.out.println(token);
            }
            reader.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }


    public static void scannerByTokenInt(String filename, String delim) {
        Scanner reader;
        try {
            reader = new Scanner(new File(filename));
            reader.useDelimiter(delim);
            while(reader.hasNextInt()) {
                int token = reader.nextInt();
                System.out.println(token);
            }
            reader.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }


    public static void bufferedReader(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = "";
            while(line != null) {
                line = reader.readLine();
                System.out.println(line);
            }
            reader.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }


    public static void bufferedReaderByChar(String filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            int ch = reader.read();
            while(ch != -1) {
                System.out.print((char)ch);
                ch = reader.read();
            }
            reader.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }

}