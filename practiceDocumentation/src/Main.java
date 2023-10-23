public class Main {

    /**
     * Takes in the radius of a circle and calculates the area of the circle using the formula PI*r^2
     *
     * @param r a double. The radius of a circle
     * @return The area of a circle with radius r
     */
    public static double areaCircle(double r) {
        double area = Math.PI * r * r;
        return area;
    }

    /**
     * Takes in a number and multiplies it by itself.
     *
     * @param num A double. The number that will be squared
     * @return the number squared
     */
    public static double sqrNum(double num) {
        double result = num * num;
        return result;
    }

    /**
     * Takes in 2 integers and adds them together.
     *
     * @param num1 An integer
     * @param num2 An integer
     * @return the sum of the 2 integers
     */
    public static int add2Nums(int num1, int num2) {
        int total = num1 + num2;
        return total;
    }

    /**
     * Takes in 2 integers and returns the average of them as a double (unrounded).
     * It uses the equation (m1+m2)/2.0
     *
     * @param m1 An integer.
     * @param m2 An integer.
     * @return half the sum of the 2 integers as a double.
     */
    public static double avg2Marks(int m1, int m2) {
        int total = m1 + m2;
        double avg = (double) total/2;
        return avg;
    }

    /**
     * Takes in a number and returns if the number passes or not. A pass is defined as any number >= 50
     *
     * @param num a float
     * @return true if the number is equal to or higher than 50, otherwise false.
     */
    public static boolean isPass(float num) {
        if (num >= 50){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes in a age and returns what age group the person belongs in.
     *
     * @param age An integer
     * @return The age group that the person is part of (>65 for senior, >19 for adult and >0 for young)
     * If the age is negative "invalid age" is returned
     */
    public static String ageMessage(int age) {
        if (age > 65) {
            return "senior";
        }
        else if (age > 19) {
            return "adult";
        }
        else if (age > 0) {
            return "young";
        }
        else {
            return "invalid age";
        }
    }

    /**
     * Takes in the cost per unit and the amount and calculates the total cost of to buy all of the amount.
     *
     * @param costPerUnit a double, the cost of each unit
     * @param quantity a int, the number of units
     * @return The total cost for quantity units.
     */
    public static double totalCost(double costPerUnit, int  quantity) {
        double result = costPerUnit * quantity;
        return result;
    }

    /**
     * Takes in a double and returns the double * 1.13, useful for calculating prices after tax
     *
     * @param subTotal A double (usually a price)
     * @return the subTotal * 1.13
     */
    public static double taxTotal(double subTotal) {
        double result = subTotal * 1.13;
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}