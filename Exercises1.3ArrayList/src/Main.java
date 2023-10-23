import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner input = new Scanner(System.in);

    public static ArrayList<Integer> getIntList() {
        ArrayList<Integer> userInts = new ArrayList<>();
        System.out.println("Enter integers one at a time, enter a empty line to stop.");
        String userInput = "0x3f3f3f";
        while(!userInput.equals("")){
            userInput = input.nextLine();
            if(userInput.equals("")) break;
            userInts.add(Integer.parseInt(userInput));
        }
        return userInts;
    }


    public static int greatest(ArrayList<Integer> arr) {
        int high = -0x3f3f3f3f;
        for (int i: arr) if(i > high) high = i;
        return high;
    }


    public static int sum(ArrayList<Integer> arr) {
        int sum = 0;
        for(int i: arr) sum+=i;
        return sum;
    }


    public static double average(ArrayList<Integer> arr) {
        if(arr.size() == 0) return 0x3f3f3f3f;
        return (double)sum(arr) / (double)arr.size();
    }


    public static double median(ArrayList<Integer> arr ) {
        if(arr.size() == 0) return 0x3f3f3f3f;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i: arr) temp.add(i);
        Collections.sort(temp);
        if(arr.size() % 2 == 0) {
            return ((double)temp.get(arr.size() / 2) + (double)temp.get(arr.size() / 2 - 1)) / (double)2;
        }
        return temp.get(arr.size() / 2);
    }


    public static double variance(ArrayList<Integer> arr) {
        if(arr.size() == 0) return 0x3f3f3f3f;
        double ans = 0;
        double avg = average(arr);
        for(int i: arr) ans += Math.pow(avg-i,2);
        return ans/(arr.size()-1);
    }


    public static boolean moreThanOnce(ArrayList<Integer> arr, int key) {
        int seen = 0;
        for(int i: arr) if(i == key) seen++;
        if(seen >= 2) return true;
        return false;
    }

    // very bad solution
    public static ArrayList<Integer> combine (ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        ArrayList<Integer> newList = new ArrayList<>();
        boolean seen;
        for(int i: arr1) {
            seen = false;
            for(int j: newList) if(i == j) {
                seen = true;
                break;
            }
            if(!seen) newList.add(i);
        }
        for(int i: arr2) {
            seen = false;
            for(int j: newList) if(i == j) {
                seen = true;
                break;
            }
            if(!seen) newList.add(i);
        }
        return newList;
    }




    // q2
    public static ArrayList<String> getStringList () {
        ArrayList<String> arr = new ArrayList<>();
        String userInput = "-1";
        while(userInput != "") {
            System.out.print("Enter a string: ");
            userInput = input.nextLine();
            if(userInput == "") break;
            arr.add(userInput);
        }
        return arr;
    }


    public static int[] lengthsOfStrings(ArrayList<String> arr){
        int[] lenOfStrings = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) lenOfStrings[i] = arr.get(i).length();
        return lenOfStrings;
    }


    public static void removeLast(ArrayList<String> arr) {
        if(arr.size() == 0) return;
        arr.remove(arr.size() - 1);
    }


    public static void removeFirst(ArrayList<String> arr) {
        if(arr.size() == 0) return;
        arr.remove(0);
    }


    public static void printList(ArrayList<String> arr) {
        for(String i: arr) System.out.println(i);
    }


    public static void printReversed(ArrayList<String> arr) {
        for (int i = arr.size()-1; i >= 0; i--) System.out.println(arr.get(i));
    }

    public static void q2() {
        System.out.println("Enter strings below (enter a newline to quit): ");
        ArrayList<String> arr = getStringList();
        System.out.println("The list is: ");
        printList(arr);
        System.out.println("The length of each string is: ");
        for(int i: lengthsOfStrings(arr)) System.out.println(i);
        System.out.println("The list in reverse order is: ");
        printReversed(arr);
        removeFirst(arr);
        removeLast(arr);
        System.out.println("Removed first and last items: ");
        printList(arr);
        if(arr.size() == 0) return; // impossible to preform next actions

        System.out.println("Enter a string to remove from the list");
        String userInput = input.nextLine();
        if(arr.remove(userInput) == false) System.out.println("Error, couldn't remove "+userInput);
        else {
            System.out.println("New list is: ");
            printList(arr);
        }

        if(arr.size() == 0) return; // impossible to preform next actions

        System.out.println("Enter a string to remove from the list");
        int removeIdx = Integer.parseInt(input.nextLine());
        if(removeIdx >= arr.size()) System.out.println("Error, out of bounds exception!");
        else {
            arr.remove(removeIdx);
            System.out.println("New list is: ");
            printList(arr);
        }
    }


    public static void q1() {
        ArrayList<Integer> arr = getIntList();
        System.out.println("Greatest value: "+greatest(arr));
        System.out.println("Sum: "+sum(arr));
        System.out.println("Average: "+average(arr));
        System.out.println("Median: "+median(arr));
        System.out.print("Enter a integer to check if appears more than once: ");
        int dupeInt = Integer.parseInt(input.nextLine());
        System.out.println(dupeInt +" appears more than 1 time: "+moreThanOnce(arr, dupeInt));
        System.out.println("Enter a second list to combine with the first list");
        ArrayList<Integer> arr2 = getIntList();
        ArrayList<Integer> arr3 = combine(arr, arr2);
        System.out.println("Combined arrays:\n"+arr3);
    }


    public static void main(String[] args) {
        q1();
        q2();
    }
}