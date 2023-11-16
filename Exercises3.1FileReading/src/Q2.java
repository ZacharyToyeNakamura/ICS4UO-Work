import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Second question | Finished
public class Q2 {
    public static void main(String args[]) {
        String filename = "src/names.txt";
        ArrayList<String> names = new ArrayList<>();
        int total = 0;
        try {
            Scanner reader = new Scanner(new File(filename));
            reader.useDelimiter(",");
            while(reader.hasNext()) {
                String name = reader.next();
                names.add(name.substring(1,name.length()-1)); // remove the quotations
            }
            Collections.sort(names);
            for(int i = 1; i <= names.size(); i++) {
                int score = 0;
                for(int j = 0; j < names.get(i-1).length(); j++) {
                    score += (int)names.get(i-1).charAt(j)-'A'+1;
                }
                total += score * i;
            }
            System.out.println("The total score is " + total);

        } catch (IOException iox ){
            System.out.println(iox.getMessage());
        }

    }
}
