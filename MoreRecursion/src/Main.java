// https://docs.google.com/document/d/1B2DUfT5JkoqsAgQd2dibe2WuiDV3iwMlxwG7cc79EmQ/edit

public class Main {
    public static final int INF = (int)0x3f3f3f3f;

    // 1
    public static String reverse(String s) {
        if(s.length() <= 1) {
            return s;
        }
        return reverse(s.substring(1)) + s.substring(0,1);
    }

    // 2a) Let m = 1, print m+1 stars until m > n
    public static void printTriangle(int n) {
        if(n < 1) {
            return;
        }
        if (n == 1) {
            System.out.println("*");
            return;
        }
        printTriangle(n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    //3
    public static void printPattern(int n) {
        if(n < 1) {
            return;
        }
        if (n == 1) {
            System.out.println("*");
            return;
        }
        for(int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();

        printPattern(n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    // 4
    public static int numDigits(int n) {
        if(n == 0) return 0;
        return numDigits(n / 10) + 1;
    }

    //5
    public static int digitSum(int n) {
        if(n == 0) return 0;
        return digitSum(n / 10) + (n % 10);
    }


    // 6 Hailstone Sequence || The Collatz conjecture!!!!
    public static int collatz(long n) {
        System.out.print(n + ", ");
        if(n == 0) {
            System.out.println("ERROR!!!!");
            return 0;
        }
        if(n == 1) {
            System.out.println();
            return 0; // base case says stop
        }
        if(n % 2 == 1) {
            return collatz(n * 3 + 1);
        }
        return collatz(n / 2);
    }


    // 7. Ackermann's function
    // For m = . . .
    // 1. Add
    // 2. Mutliply
    // 3. Exponential   m↑n
    // 4. Tetration     m↑↑n
    // 5. Pentation     m↑↑↑n
    // 6. Sextation ?   m↑↑↑↑n
    // 7.
    public static int ackermann(int m, int n) {
        if(m == 0) {
            return n + 1;
        }
        if(n == 0) {
            return ackermann(m-1, 1);
        }
        return ackermann(m-1, ackermann(m, n-1));
    }



    // Attempt to optimize the function?
    public static int[][] rem = new int[100][100];
    public static int ackermann2(int m, int n) {
        if(rem[m][n] != INF) return rem[m][n];
        if(m == 0) {
            return rem[m][n] = n + 1;
        }
        if(n == 0) {
            return rem[m][n] = ackermann(m-1, 1);
        }
        return rem[m][n] = ackermann(m-1, ackermann(m, n-1));
    }




    public static void main(String[] args) {
        System.out.println("Reversed Hello World!: " + reverse("Hello World!"));
        printTriangle(6);
        printPattern(6);
        System.out.println( "Num digits in 2655437: " + numDigits(2655437));
        System.out.println("Digit sum of 346: " + digitSum(346));
        collatz(901);
        System.out.println("Ackerman(4, 0): " + ackermann(4, 0));

        for(int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                rem[i][j] = INF;
            }
        }
        System.out.println(ackermann2(3, 2));
    }
}