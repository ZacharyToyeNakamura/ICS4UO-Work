public class Main {
    // O(N^2) average and worst, O(N) best
    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for(int j = 0; j < arr.length-i-1; j++) {
                if(arr[j+1] < arr[j]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if(!swapped) {
                return;
            }

        }

    }

    // worst case, average case O(N^2) best case O(N)
    public static void insertionSort(int arr[]) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]) { // push the element up until it's at a place where arr[j-1] > arr[j] > arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1]= temp;
                }
                else { // rest of the array is sorted
                    break;
                }
            }
        }
    }

    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    public static final int MN = (int)5e4;
    public static final int high = (int)1e9;
    public static final int low = (int)-1e9;
    public static void main(String[] args) {
        int arr[] = new int[MN];
        for(int i = 0; i < MN; i++) arr[i] = (int)(Math.random()*(high-low)+low);
        long start = System.currentTimeMillis();
        bubbleSort(arr);
//        printArr(arr);
        System.out.println("Bubble Sort took: " + (System.currentTimeMillis()-start)/1000.0 + "s");

        for(int i = 0; i < MN; i++) arr[i] = (int)(Math.random()*(high-low)+low);
        start = System.currentTimeMillis();
        insertionSort(arr);
//        printArr(arr);
        System.out.println("Insertion Sort took: " + (System.currentTimeMillis()-start)/1000.0 + "s");


    }
}