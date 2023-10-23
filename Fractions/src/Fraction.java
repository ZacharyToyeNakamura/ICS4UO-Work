/**
 * A class that defines rational numbers, p/q, where p and q are
 * integers and q != 0. Operations that are possible include:
 * addition, subtraction, multiplication, division, reducing,
 * reciprocal, comparison, and shortcut addition (+=) and
 * multiplication (*=).
 */
public class Fraction {

    // The numerator of the fraction
    private int num;
    // The denominator of the fraction
    private int den;

    // Getter for numerator
    /**
     *
     * @return The numerator of the fraction object
     */
    public int getNum() {
        return num;
    }

    // Getter for denominator
    /**
     *
     * @return The denominator of the fraction object
     */
    public int getDen() {
        return den;
    }

    /**
     * Changes the numerator of the Fraction object to num
     * @param num An int that will become the numerator of the Fraction
     */
    // Setter for numerator
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Changes the denominator of the Fraction object to den
     * @param den An int that will become the denominator of the Fraction
     */
    // Setter for denominator
    public void setDen(int den) {
        this.den = den;
    }
    
    // mutator

    /**
     * Changes both the numerator and the denominator of the fraction
     *
     * @param num An int that becomes the numerator of the fraction
     * @param den An int that becomes the denominator of the fraction
     */
    public void seNumDen(int num, int den) {
        this.den = den;
        this.num = num;
    }


    // Constructor function
    /**
     * Creates a Fraction object with a set numerator and denominator
     * The fraction is numerator / denominator
     *
     * @param numerator An int, the numerator of the fraction
     * @param denominator An int, the denominator of the fraction
     */
    public Fraction (int numerator, int denominator) {
        num = numerator;
        den = denominator;
    }

    //3a

    /**
     * Creates a fraction that is equal to the double parameter by multiplying the double by 10 until it's a decimal
     * then reduce the fraction.
     *
     * @param number a double that will become a fraction
     */
    public Fraction (double number) {
        den = 1;
        // While the number is not an integer
        while((int)number != number) {
            number *= 10; // multiply it by 10
            den *= 10; // increase the denominator by a factor of 10
        }
        num = (int)number;
        this.reduce();
    }

    //3b

    /**
     * Creates a new fraction that is number/1
     *
     * @param number A integer value
     */
    public Fraction (int number) {
        den = 1;
        num = number;
    }

    // 3c

    /**
     * Creates a default fraction of 0/1
     *
     */
    public Fraction () {
        num = 0; // this should auto init to 0 but uh just to be sure
        den = 1;
    }

    // d

    /**
     * reduces the fraction and changes the signs if necessary, if the fraction is negative the numerator will be the
     * negative one.
     * If the fraction is 0 then the fraction will reduce to 0/1
     */
    public void reduce() {
        // only needs to go to floor(sqrt(min(num,den)))
        if(this.num < 0 && this.den < 0) { // -5/-7 -> 5/7
            this.num = Math.abs(this.num); // could use * -1
            this.den = Math.abs(this.den);
        }
        if(this.num >= 0 && this.den < 0) { // 4/-5 -> -4/5
            this.num *= -1;
            this.den *= -1;
        }
        if(this.num == 0) { // lowest terms of th edge case with the num = 0 is 0/1
            this.den = 1;
            return;
        }

        for(int i = Math.min(Math.abs(this.num), Math.abs(this.den)); i >= 1; i--) {
            if(this.num % i == 0 && this.den % i == 0) {
                this.num /= i;
                this.den /= i;
                return;
            }
        }
    }

    // Instance methods

    /**
     * Returns the decimal value of the fraction
     *
     * @return The value of the fraction when evaluated
     */
    public double toDecimal() {
        return (double)num / (double)den;
    }


    /**
     * Returns whether the fraction is bigger than the other fraction
     *
     * @param otherFraction Another fraction class that is compared against the fraction.
     * @return If this has a higher value than otherFraction
     */
    public boolean greaterThan(Fraction otherFraction) {
        this.reduce();
        otherFraction.reduce();
        return this.toDecimal() > otherFraction.toDecimal();
    }

    /**
     * Returns whether the 2 fractions are equal
     *
     * @param otherFraction A fraction class
     * @return True if the 2 fractions are equal and false if they are not.
     */
    public boolean equals(Fraction otherFraction) {
        this.reduce();
        otherFraction.reduce();
        return this.num * otherFraction.den == otherFraction.num * this.den;
    }

    /**
     * Multiplies this fraction by another using the formula a/b * c/d = (a*b)/(c*d)
     *
     * @param other Another fraction class, that is multiplied with this fraction
     * @return A fraction that is the product of the 2 fractions.
     */
    public Fraction multiply(Fraction other) {
        Fraction answer = new Fraction(this.num * other.num, this.den * other.den);
        answer.reduce();
        return answer;
    }

    /**
     * Adds 2 fractions together using the formula a/b + c/d = (a*d+b*c)/(b*d)
     *
     * @param other A Fraction class that is added to this fraction
     * @return The sum of the 2 fraction classes
     */
    public Fraction add(Fraction other) {
        Fraction answer = new Fraction(this.num * other.den + other.num * this.den, this.den * other.den);
        answer.reduce();
        return answer;
    }

    /**
     * returns a copy of the fraction passed to it
     *
     * @param other A fraction
     * @return A fraction with the same values but a different address
     */
    public Fraction copy(Fraction other) {
        return new Fraction(other.num, other.den);
    }

    // a

    /**
     * Multiplies this fraction by another using the multiply method and sets it equal to the product
     *
     * @param other another fraction
     */
    public void timesEquals(Fraction other) {
        // There is a better way to do this
        Fraction temp = this.multiply(other);
        temp.reduce();
        this.num = temp.num;
        this.den = temp.den;
    }


    // b

    /**
     * Adds a fraction to this fraction and sets it equal to the sum
     *
     * @param other Another fraction class that is added to this fraction
     */
    public void plusEquals(Fraction other) {
        // There is a better way probably
        Fraction temp = this.add(other);
        temp.reduce();
        this.num = temp.num;
        this.den = temp.den;
    }


    // c

    /**
     * Multiplies this fraction by an integer using a* b/c = (a*b)/c
     *
     * @param k An integer
     */
    public void integerMultiply(int k) {
        this.num *= k;
        this.reduce();
    }


    // e

    /**
     * Switches the numerator and the denominator
     *
     * @return A new fraction
     */
    public Fraction reciprocal () {
        return new Fraction(this.den, this.num);
    }


    // f

    /**
     * Divides 2 fractions using multiply and reciprocal
     *
     * @param other Another fraction which is the divisor
     * @return A new fraction that is the quotient
     */
    public Fraction divide(Fraction other) {
        Fraction temp = this.multiply(other.reciprocal());
        temp.reduce();
        return temp;
    }

    /**
     * subtracts 2 fractions by adding one multiplied by negative 1
     *
     * @param other Another fraction class
     * @return A new fraction that is the difference of this - other
     */
    public Fraction subtract (Fraction other) {
        Fraction temp = this.add(new Fraction(other.num*-1,other.den));
        temp.reduce();
        return temp;
    }



    @Override
    public String toString (){
        if(num == 0) return "0";
        if(den == 1) return String.valueOf(num);
        return num + " / " + den;
    }

    // class methods (but static)

    /**
     * Returns the product of 2 fractions
     *
     * @param f1 A fraction object
     * @param f2 A fraction object
     * @return The product of the 2 fractions
     */
    public static Fraction product(Fraction f1, Fraction f2) {
        Fraction answer = f1.multiply(f2);
        answer.reduce();
        return answer;
    }

    /**
     * Returns the sum of 2 fractions
     *
     * @param f1 A fraction object
     * @param f2 A fraction object
     * @return the sum of the 2 fractions
     */
    public static Fraction sum(Fraction f1, Fraction f2) {
        Fraction answer = f1.add(f2);
        answer.reduce();
        return answer;
    }

    /**
     * Returns the difference of 2 fractions
     *
     * @param f1 A fraction object
     * @param f2 A fraction object
     * @return A fraction that is the difference of the 2 fractions
     */
    public static Fraction difference(Fraction f1, Fraction f2) {
        Fraction answer = f1.subtract(f2);
        answer.reduce();
        return answer;
    }

    /**
     * Returns the quotient of 2 fractions
     *
     * @param f1 A fraction object
     * @param f2 A fraction object
     * @return A fraction that is the quotient of the 2 fractions
     */
    public static Fraction quotient(Fraction f1, Fraction f2) {
        Fraction answer = f1.divide(f2);
        answer.reduce();
        return answer;
    }


}
