import static java.util.Arrays.sort;

public class Main {
    // linear search O(N)
    public static int linearSearch(int[] array, int searched) {
        // code here
        for (int i = 0; i < array.length; i++) {
            if(array[i] == searched) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Takes in a sorted array
     *
     * @param array
     * @param searched
     * @return
     */
    public static int linearSearch2(int[] array, int searched) {
        // code here
        for (int i = 0; i < array.length; i++) {
            if(array[i] == searched) {
                return i;
            }
            if(array[i] > searched) {
                return -1;
            }
        }
        return -1;
    }

    /**
     * Requires a sorted array
     * I like low = mid+1, high = mid and while(low < high) but whatever.
     *
     * @param array
     * @param searched
     * @return
     */
    public static int binarySearch(int[] array, int searched) {
        int low = 0;
        int high = array.length-1;
        int mid = (array.length-1)/2;
        while(low <= high) {
            if(array[mid] == searched) {
                return mid;
            }
            if(searched > array[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = low + ((high - low) / 2); // prevent overflow
        }
        return -1;
    }



    // 1e9 out of heap space
    public static final int MN = (int)1e8;

    public static void main(String[] args) {
//        System.out.println("Hello world!");
        int[] values = {3, -3, 7, 12, 9, 10, 14};
        System.out.println(linearSearch(values, 5));    // -1
        System.out.println(linearSearch(values, 9));    // 4
        sort(values);
        int[] arr = new int[MN];
        int l = -(int)1e9, h = (int)1e9;
        for(int i = 0; i < MN; i++) {
            arr[i] = (int)(Math.random()*(h - l) + l);
        }
        sort(arr);

        for (int i = 0; i < 10000; i++) {
            int search = (int)(Math.random()*(h - l) + l);
//            System.out.println("Looking for " + search);
            System.out.println("Index of " + search + " is: " + binarySearch(arr, search));
//            System.out.println("Linear search says " + linearSearch2(arr, search));
        }


    }
}