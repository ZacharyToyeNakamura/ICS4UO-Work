import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final int MN = (int)1e7; // ~3s for 1e5
    // ~20s for 3e5
    // ~17s for 1e7 quicksort
    public static final int high = 1000000000;
    public static final int low = -1000000000;
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

        // optimize this?
        left.add(pivot);
        for (int i = 0; i < right.size(); i++) {
            left.add(right.get(i));
        }
        return left;
    }


    // O(NlogN) average O(N^2) worst
//    public static final int CUTOFF = 8;
    public static ArrayList<Integer> quickSort2(ArrayList<Integer> arr, int CUTOFF) {
        if(arr.size() <= CUTOFF) {
            // run insertion sort
            for(int i = 1; i < arr.size(); i++) {
                for(int j = i; j > 0; j--) {
                    if(arr.get(j) < arr.get(j-1)) { // push the element up until it's at a place where arr[j-1] > arr[j] > arr[j+1]
                        int temp = arr.get(j);
                        arr.set(j, arr.get(j-1));
                        arr.set(j-1, temp);
                    }
                    else { // rest of the array is sorted
                        break;
                    }
                }
            }
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
        ArrayList<Integer> right = quickSort2(greater, CUTOFF);
        ArrayList<Integer> left = quickSort2(lesser, CUTOFF);

        // optimize this?
        left.add(pivot);
        for (int i = 0; i < right.size(); i++) {
            left.add(right.get(i));
        }
        return left;
    }


    // Inplace quick sort
    public static void swap(int a, int b, int arr[]) {
        int temp = arr[a];
        arr[a] = b;
        arr[b] = temp;
    }

    /**
     * In theory in place sorting should be faster
     * @param arr
     * @param l Inclusive
     * @param r Inclusive
     */
    public static void inPlaceQuickSort(int[] arr, int l, int r) {
        if(r - l < 20) {
            // run insertion sort
            for(int i = l; i <= r; i++) {
                for(int j = i; j > l; j--) {
                    if(arr[j] < arr[j-1]) { // push the element up until it's at a place where arr[j-1] > arr[j] > arr[j+1]
                        int temp = arr[j];
                        arr[j] = arr[j-1];
                        arr[j-1] = temp;
                    }
                    else { // rest of the array is sorted
                        break;
                    }
                }
            }
            return;
        }

        // arr[r] is the pivot
        int pivot = arr[r]; // optimize later
        int small = l, equal = l, large = l; // the next element that is small/large is
        for (int i = l; i < r; i++) {
            if(arr[i] < pivot) {
                if(i != small) swap(small, i, arr);
                small++;
                equal++;
                large++;
            }
            else if(arr[i] == pivot) {
                if(i != equal) swap(equal, i, arr);
                equal++;
                large++;
            }
            else { // it's larger than pivot
                large++;
            }
        }
        swap(pivot, equal, arr); // swap the pivot with the next element after
        inPlaceQuickSort(arr, l, small-1);
        inPlaceQuickSort(arr, equal, r);
    }


    public static void mrChoQuickSort(int a[], int low, int high) {
        final int MOVING_LEFT = 0;
        final int MOVING_RIGHT = 1;

        if(low < high) {
            int left = low;
            int right = high;
            // choose first element
            int pivot = a[low];
            // init current direction
            int currentDirection = MOVING_LEFT;

            while(left < right) {
                if(currentDirection == MOVING_LEFT) {
                    while(a[right] >= pivot && left < right) {
                        right--;
                    }
                    a[left] = a[right]; // shouldn't this be a swap?
                    currentDirection = MOVING_RIGHT;
                }
                if(currentDirection == MOVING_RIGHT) {
                    while(a[left] <= pivot && left < right) {
                        left++;
                    }
                }
                a[right] = a[left];
                currentDirection = MOVING_LEFT;
            }
            a[left] = pivot; // or a[right] since they are equal?

            mrChoQuickSort(a, low, left - 1);
            mrChoQuickSort(a, right + 1, high);
        }
    }


    public static void quickSort3(int a[], int low, int high) {

        if(high - low < 8) {
            // run insertion sort
            for(int i = low; i <= high; i++) {
                for(int j = i; j > low; j--) {
                    if(a[j] < a[j-1]) { // push the element up until it's at a place where arr[j-1] > arr[j] > arr[j+1]
                        int temp = a[j];
                        a[j] = a[j-1];
                        a[j-1] = temp;
                    }
                    else { // rest of the array is sorted
                        break;
                    }
                }
            }
            return;
        }

        final int MOVING_LEFT = 0;
        final int MOVING_RIGHT = 1;

        if(low < high) {
            int left = low;
            int right = high;
            // choose first element
            int pivot = a[low];
            // init current direction
            int currentDirection = MOVING_LEFT;

            while(left < right) {
                if(currentDirection == MOVING_LEFT) {
                    while(a[right] >= pivot && left < right) {
                        right--;
                    }
                    a[left] = a[right]; // shouldn't this be a swap?
                    currentDirection = MOVING_RIGHT;
                }
                if(currentDirection == MOVING_RIGHT) {
                    while(a[left] <= pivot && left < right) {
                        left++;
                    }
                }
                a[right] = a[left];
                currentDirection = MOVING_LEFT;
            }
            a[left] = pivot; // or a[right] since they are equal?

            quickSort3(a, low, left - 1);
            quickSort3(a, right + 1, high);
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[MN];
        int[] arr5 = new int[MN];
        int[] arr6 = new int[MN];
        ArrayList<Integer> arr2 = new ArrayList<>();

        for (int i = 0; i < MN; i++) { // rand number generation
            arr[i] = (int)(Math.random()*(high - low + 1) + low);
            arr2.add(arr[i]);
            arr5[i] = arr[i];
            arr6[i] = arr[i];
        }
        ArrayList<Integer> arr3 = new ArrayList<>(arr2);
        ArrayList<Integer> arr4 = new ArrayList<>(arr2);

        System.out.println("Starting. . .");
        long startTime = System.currentTimeMillis();


        // inPlaceQuickSort(arr, 0, arr.length-1);
        // printArr(arr);
        // System.out.println("Time for inPlace quickSort: " + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");

        // selectionSort(arr);

        // startTime = System.currentTimeMillis();
        // arr2 = quickSort(arr2);
//        saveList(arr2);
        // System.out.println("Time for quickSort, original: \t\t" + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");

//        arr3.clear();
//        for (int i = 0; i < MN; i++) arr3.add((int)(Math.random()*(high - low + 1) + low));
//         startTime = System.currentTimeMillis();
//         arr3 = quickSort2(arr3, 20);
// //        saveList(arr3);
//         System.out.println("Time for quickSort2, cut off 20: \t" + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");

//         startTime = System.currentTimeMillis();
//         arr4 = quickSort2(arr4, 8);
// //        saveList(arr4);
//         System.out.println("Time for quickSort2, cut off 8: \t" + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");

        startTime = System.currentTimeMillis();
        mrChoQuickSort(arr5, 0, arr5.length - 1);
        System.out.println("Time for mrChoQuickSort: \t" + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");
        printArr(arr5);

        startTime = System.currentTimeMillis();
        quickSort3(arr6, 0, arr6.length - 1);
        System.out.println("Time for quickSort3: \t" + ((System.currentTimeMillis() - startTime)/1000.0) + " seconds");
        printArr(arr6);

        // 1e6
//        Time for inPlace quickSort: 170.603 seconds
//        Time for quickSort: 1.165 seconds
//        Time for quickSort2: 1.085 seconds
    }
}