import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static final int MN = (int)1e7; // ~3s for 1e5
    // ~20s for 3e5
    public static final int high = 10000000;
    public static final int low = -10000000;
    public static int smallest (int[] array) {
        int small = (int)0x3f3f3f3f;
        for (int i = 0; i < array.length; i++) {
            if(small > array[i]) {
                small = array[i];
            }
        }
        return small;
    }

    public static int indexOfTheSmallest(int[] array) {
        int small = (int)0x3f3f3f3f;
        int idx = -1;
        for (int i = 0; i < array.length; i++) {
            if(small > array[i]) {
                small = array[i];
                idx = i;
            }
        }
        return idx;
    }


    public static int indexOfTheSmallestStartingFrom(int[] array, int start) {
        int small = (int)0x3f3f3f3f;
        int idx = -1;
        for (int i = start; i < array.length; i++) {
            if(small > array[i]) {
                small = array[i];
                idx = i;
            }
        }
        return idx;
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void printArr(int[] arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"));
            writer.write("[");
            for (int i = 0; i < arr.length; i++) {
                writer.write(Integer.toString(arr[i]));
                if (i != arr.length - 1) writer.write(", ");
            }
            writer.write("]");
            writer.newLine();
            writer.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }

    public static void saveList(ArrayList<Integer> arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/savedList.txt"));
            writer.write(arr.toString());
            writer.close();
        } catch (IOException iox) {
            System.out.println(iox.getMessage());
        }
    }

    // O(N^2) average
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int lowest = indexOfTheSmallestStartingFrom(array, i);
            if(lowest != i) {
                swap(array, i, lowest);
            }
            // Debug
//            printArr(array);
        }

    }

    // O(NlogN) average O(N^2) worst
    public static ArrayList<Integer> quickSort(ArrayList<Integer> arr) {
        if(arr.size() <= 1) {
            return arr;
        }
        int pivot = arr.get(0); // Random pivot for now, optimize later
        arr.remove(0);
        ArrayList<Integer> greater = new ArrayList<>();
        ArrayList<Integer> lesser = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > pivot) {
                greater.add(arr.get(i));
            } else {
                lesser.add(arr.get(i));
            }
        }
        ArrayList<Integer> right = quickSort(greater);
        ArrayList<Integer> left = quickSort(lesser);
//        System.out.println("Looking At: ");
//        System.out.println("Lesser " + left);
//        System.out.println("Pivot " + pivot);
//        System.out.println("Greater " + right);

        // optimize this?
        left.add(pivot);
        for (int i = 0; i < right.size(); i++) {
            left.add(right.get(i));
        }
        return left;
    }


    public static void main(String[] args) {
        int[] arr = new int[MN];
        ArrayList<Integer> arr2 = new ArrayList<>();

        for (int i = 0; i < MN; i++) { // rand number generation
            arr[i] = (int)(Math.random()*(high - low + 1) + low);
            arr2.add((int)(Math.random()*(high - low + 1) + low));
        }

        System.out.println("Starting. . .");
        final long startTime = System.currentTimeMillis();


//        selectionSort(arr);

        arr2 = quickSort(arr2);
        saveList(arr2);



        System.out.println("Time: " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");
    }
}