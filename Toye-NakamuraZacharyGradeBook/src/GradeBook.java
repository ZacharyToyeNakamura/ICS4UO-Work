import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GradeBook {
    public static Scanner input = new Scanner(System.in);
    public static Course introCS = new Course(
            "Introduction to Computer Science",
            "CSC100",
            new ArrayList<>(Arrays.asList(
                    new Student("Alan T",
                    "110101011",
                            new ArrayList<>(Arrays.asList(83, 71, 76, 91, 85))),
                    new Student("Donald K",
                            "314159265",
                            new ArrayList<>(Arrays.asList(84, 90, 88, 99, 80))),
                    new Student("Albert E",
                            "299792458",
                            new ArrayList<>(Arrays.asList(93, 65, 95, 40, 19))),
                    new Student("Marie C",
                            "002661600",
                            new ArrayList<>(Arrays.asList(76, 52, 96, 92, 66))),
                    new Student("Ada L",
                            "018151210",
                            new ArrayList<>(Arrays.asList(91, 98, 89, 99, 99)))
            ))
        );

    public static void main(String[] args) {
        System.out.println(introCS);
    }
}