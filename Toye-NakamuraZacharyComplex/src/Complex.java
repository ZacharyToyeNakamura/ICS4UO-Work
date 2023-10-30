public class Complex {
    // Comments are mostly for me, so I don't forget what math is
    // a
    private double re; // The real part of the complex number
    private double im; // The imaginary part of the complex number

    // b
    public Complex(double real, double imaginary) {
        re = real;
        im = imaginary;
    }

    // c
    public Complex() {
        re = 0;
        im = 0;
    }

    // f
    public double getRe() {
        return re;
    }

    // d
    @Override
    public String toString() {
        // if both are 0 it returns 0
        if(im == 0) return re + "";
        if(re == 0) return im + "i";
        if(im > 0) return re + " + " + im + "i";
        return re + " - " + im*-1 + "i"; // if im is negative then print a - bi instead of a + -bi
    }

    // e
    public boolean equals(Complex c) {
        // Ignoring floating point equality errors
        return im == c.im && re == c.re;
    }

    // g
    public double abs() {
        // magnitude of a complex number is |z| = sqrt(a^2 + b^2)
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    // h
    public void scale(double factor) {
        im *= factor;
        re *= factor;
    }

    // i
    public Complex plus(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    // j
    public static Complex conjugate(Complex c) {
        // The conjugate of a complex number (a + bi) is (a - bi)
        return new Complex(c.re, c.im * -1);
    }

}
