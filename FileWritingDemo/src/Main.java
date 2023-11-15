import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BufferedWriter writer;
        Scanner reader = new Scanner(System.in);
        File filename = new File("src/test.txt");
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            System.out.print("Write to the file: ");
            writer.write(reader.nextLine());
            writer.write("Why are we here just to suffer\n");
            writer.write(197);
            writer.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        System.out.println("Program finished.");
    }
}