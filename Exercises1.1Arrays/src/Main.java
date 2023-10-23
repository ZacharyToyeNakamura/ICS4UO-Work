import java.util.Scanner;

class Main {
    public static final Scanner input = new Scanner(System.in);

    public static void q1 () {
        System.out.print("Enter a integer: ");
        int n = Integer.parseInt(input.nextLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = 1;
        for(int i = 0; i < n; i++) {
            System.out.print("Enter a number: ");
            arr[i] = Integer.parseInt(input.nextLine());
        }
        int temp = arr[n-1];
        arr[n-1] = arr[0];
        arr[0] = temp;
        for(int i = 0; i < n; i++) if(arr[i] < 0) arr[i]*=-1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < arr[i]; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i = 0; i < n; i++) if(arr[i] % 2 == 0) System.out.print(arr[i] +" ");
        System.out.println("");
    }


    public static void q2() {
        int[] arr = {13, 60, 50, 46, 56, 83, 22, 71};
        for(int i = arr.length-1; i>=0; i--) System.out.print(arr[i] + " ");
        System.out.println("");
        int[] arr2 = new int[8];
        for(int i = arr.length - 1; i > -1; i--) arr2[arr.length-1-i] = arr[i];
        for(int i: arr2) System.out.print(i + " ");
        System.out.println("");
    }



    // q3
    public static double smallest(double[] arr) {
        double small = 0x3f3f3f3;
        for(double i: arr) if (i < small) small = i;
        return small;
    }

    public static void q3() {
        System.out.print("Enter a number: ");
        int n = Integer.parseInt(input.nextLine());
        double[] arr = new double[n];
        double avg = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter a double: ");
            arr[i] = Double.parseDouble(input.nextLine());
            avg += arr[i];
        }
        avg /= n;
        System.out.println("The average of the input numbers is "+avg);
        for (double i : arr) System.out.print(i + " ");
        System.out.println("\n"+smallest(arr));
        for (int i = 0; i < n; i++) {
            if (arr[i] == smallest(arr)) {
                System.out.println("The index of the smallest element is "+ i);
                break;
            }
        }
    }


    public static void q4() {
        System.out.print("Enter a string: ");
        String str = input.nextLine();
        char[] arrChar = new char[str.length()];
        String[] arrStr = new String[str.length()];

        for (int i = 0; i < str.length(); i++) {
            arrChar[i] = str.charAt(i);
            arrStr[i] = str.substring(i, i+1);
        }
        System.out.print("Enter a number: ");
        int n = Integer.parseInt(input.nextLine());
        String[] strArr = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter a string: ");
            strArr[i] = input.nextLine();
        }
        for(int i = 0; i < n; i++) {
            System.out.print(strArr[i]);
            if(i != n-1) System.out.print(" ");
        }
        System.out.println("");
    }


    public static void q5() {
        String[] names = {"Alan", "Ada", "Grace", "Linus"};
        String[] numbers = {"181256345", "181159830", "181245891", "189875304"};
        System.out.println("Class:\n" +
                "  CS101\n" +
                "Students:\n" +
                "  Alan, 181256345\n" +
                "  Ada, 181159830\n" +
                "  Grace, 181245891\n" +
                "  Linus, 189875304");
        System.out.print("Please enter a name: ");
        String name = input.nextLine();
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            if(name.equals(names[i])) {
                found = true;
                System.out.println(name+"'s student number is "+numbers[i]+".");
                break;
            }
        }
        if(!found) System.out.println("Student "+name+" doesn't exist.");
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");
        q1();
        q2();
        q3();
        q4();
        q5();
        System.out.println("Raymond :D");

    }
}