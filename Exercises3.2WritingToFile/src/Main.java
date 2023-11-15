import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        BufferedWriter writer;
        File originalFile = new File("src/sisters.txt");
        File copyFile = new File("src/sistersCopy.txt");
        File upperFile = new File("src/SISTERS_COPY.txt");
        File cleanFile = new File("src/SISTERSCLEAN.txt");
        File statsFile = new File("src/stats.txt");
        try {
            // Part 1
            reader = new BufferedReader(new FileReader(originalFile));
            writer = new BufferedWriter(new FileWriter(copyFile));
            while(reader.ready()) {
                writer.write(reader.read());
            }
            reader.close();
            writer.close();

            // Part 2
            reader = new BufferedReader(new FileReader(copyFile));
            writer = new BufferedWriter(new FileWriter(upperFile));
            while (reader.ready()) {
                int temp = reader.read();
                if((int)'a' <= temp && temp <= (int)'z') {
                    writer.write(temp-'a'+'A');
                }
                else {
                    writer.write(temp);
                }
            }
            reader.close();
            writer.close();

            // Part 3
            reader = new BufferedReader(new FileReader(upperFile));
            writer = new BufferedWriter(new FileWriter(cleanFile));
            while (reader.ready()) {
                int temp = reader.read();
                if( ((int)'a' <= temp && temp <= (int)'z') || // not needed
                    ((int)'A' <= temp && temp <= (int)'Z') ||
                    ((int)' ' == temp)) {
                    writer.write(temp);
                }
            }
            reader.close();
            writer.close();

            // Part 4
            reader = new BufferedReader(new FileReader(cleanFile));
            writer = new BufferedWriter(new FileWriter(statsFile));
            int numVowels = 0;
            int numChars = 0;
            while (reader.ready()) {
                int temp = reader.read();
                if(temp == 'A' || temp == 'E' || temp == 'O' || temp == 'I' || temp == 'U') {
                    numVowels++;
                }
                if((int)'A' <= temp && temp <= (int)'Z') {
                    numChars++;
                }
            }
            writer.write("Number of vowels in SISTERSCLEAN.txt: " + numVowels);
            writer.newLine();
            writer.write("Number of characters in SISTERSCLEAN.txt " + numChars);
            writer.newLine();
            writer.write("Percentage of characters that are vowels in SISTERSCLEAN.txt (ignoring spaces): " +
                    (double)numVowels/numChars*100 + "%");
            writer.newLine();
            reader.close();
            writer.close();

        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
        System.out.println("Finished execution");
    }
}