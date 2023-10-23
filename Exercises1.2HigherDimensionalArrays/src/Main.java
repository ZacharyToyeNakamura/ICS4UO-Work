import java.util.Scanner;
import java.util.Arrays;



public class Main {
    public static Scanner input = new Scanner(System.in);


    public static void q1() {
        System.out.print("Enter the length of the array: ");
        int n = Integer.parseInt(input.nextLine());
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            System.out.print("Array "+(i+1)+": ");
            System.out.print("Enter the length of array "+(i+1)+": ");
            arr[i] = new int[Integer.parseInt(input.nextLine())];
            System.out.println("Enter "+arr[i].length + " integer(s): ");
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Integer.parseInt(input.nextLine());
            }
        }
        System.out.println("The array:\n");
        System.out.println(Arrays.deepToString(arr));
    }

    // q2
    public static int arraySum(int[] arr) {
        int tot = 0;
        for (int i: arr) tot+=i;
        return tot;
    }
    public static void printArray(int[][] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(Arrays.toString(arr[i]));
            if(i != arr.length-1) System.out.print(",\n");
        }
        System.out.println(" ]");
    }
    public static void printAll(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println("row " + i + ", col " + j + " = " + arr[i][j]);
            }
        }
    }

    public static void q2() {
        int[][] data = {{3, 2, 5},
                        {1, 4, 4, 8, 13},
                        {9, 1, 0, 2},
                        {0, 2, 6, 4, -1, -8} };
        int tot = 0;
        for (int i = 0; i < data.length; i++) {
            System.out.println("Sum of "+(i+1)+" row is "+arraySum(data[i]));
            tot+=arraySum(data[i]);
        }
        System.out.println("Sum of all elements in the array is " + tot);
        printArray(data);
        printAll(data);

    }


    // q3
    public static void swapRow(int[][] arr, int row1, int row2) {
        int[] temp = arr[row1];
        arr[row1] = arr[row2];
        arr[row2] = temp;
    }

    // must an element in the column
    public static void swapColumn(int[][] arr, int col1, int col2) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i][col2];
            arr[i][col2] = arr[i][col1];
            arr[i][col1] = temp;
        }
    }


    public static void multiplyRow(int[][] arr, int row, int constant) {
        for (int i = 0; i < arr[row].length; i++) {
            arr[row][i] *= constant;
        }
    }


    public static int[][] rotate(int arr[][]) {
        int[][] rotated = new int[arr[0].length][arr.length];
        for(int i = 0 ;i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                rotated[j][rotated[i].length-i-1] = arr[i][j];
            }
        }
        return rotated;
    }

    public static void q3() {
        int[][] arr = {
                {3,45,5,74},
                {5,72,1,28},
                {3,46,8,76},
        };
        System.out.println(Arrays.deepToString(arr));
        swapRow(arr, 1, 2);
        System.out.println("Swapped rows 1 and 2\n" + Arrays.deepToString(arr));
        swapColumn(arr, 2, 3);
        System.out.println("Swapped columns 2 and 3]\n" + Arrays.deepToString(arr));
        multiplyRow(arr, 2, 235);
        System.out.println("Multiplied row 2 by 235\n" + Arrays.deepToString(arr));
        printArray(arr);
        arr = rotate(arr);
        System.out.println("Rotated 90deg clockwises\n" + Arrays.deepToString(arr));
        printArray(arr);

    }

    public static void main(String[] args) {
        q1();
        q2();
        q3();


    }
}