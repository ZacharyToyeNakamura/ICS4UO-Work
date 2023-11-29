// https://docs.google.com/document/d/1ErBR662YX6WEF6oXP6kdsq6c4hwEu5Y53WkjyGsI4xc/edit
// 2023-11-24
public class Main {

//    What is wrong with these methods?
//    a)
    public static double bad(double a, double b) {
        // No base case?
        a = a / 2;
        b = b * 2;
        return bad(a, b);
    }

//    b)
    public static int badToo(int n) {
        // Never reaches the base case if n > 1
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 5;
        } else {
            return 2*badToo(n + 1) + 3;
        }
    } // This curly bracket didn't exist

    // 2
    public static double powerFunc(double x, int n) {
        if(n == 1) {
            return x;
        }
        if(n > 0) {
            return x * (powerFunc(x, n - 1));
        }
        n *= -1;
        return 1 / (x * powerFunc(x, n - 1));
    }

    // Mr cho's way
    public static double powerFunc2(double x, int n) {
        if(n == 1) {
            return x;
        }
        if(n > 0) {
            return x * (powerFunc2(x, n - 1));
        }
        return 1 / (x * powerFunc2(x, n + 1));
    }

    // 3
    public static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 4
    public static int gcd(int m, int n) {

        // if non-negative numbers
        if(m >= 0 && n >= 0) {
            if(n == 0) {
                return m;
            }
            return gcd(n, m % n);
        }

        // negative algo
        if(m == n) {
            return m;
        }
        if (m > n) {
            return gcd(n, m - n);
        }
        return gcd(n, m); // why not gcd(n - m, m); ??????
    }


    // 5
    public static int squareNum(int n) {
        if(n < 0) {
            n *= -1;
        }
        if(n == 1) {
            return 1;
        }
        return squareNum(n - 1) + 2 * n - 1;
    }

    // 6 (2 methods because overload)
    public static boolean prime(int n) {
        if(n < 0) {
            n *= -1;
        }
        if(n == 0) {
            return false;
        }
        return prime(n, n-1);
    }

    public static boolean prime(int n, int d) {
        if(d == 1) {
            return true;
        }
        if(n % d == 0) {
            return false;
        }
        return prime(n, d - 1);
    }





    public static void main(String[] args) {
        System.out.println("powerFun " + powerFunc(3, -3));
        System.out.println("fib " + fib(10));
        System.out.println("gcd " + gcd(120,120000));
        System.out.println("square num " + squareNum(-10));
        System.out.println("Prime " + prime(25000));
        System.out.println("Power function 3^-4: " + powerFunc2(3, -3));
    }
}