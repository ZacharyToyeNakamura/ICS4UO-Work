public class Main {
    public static void main(String[] args) {
        Fraction f = new Fraction(777, 777);
        System.out.println(f.toString());
        Fraction g = new Fraction(50, -100);
        g.reduce();
        System.out.println(g.toString());
        System.out.println("f = " + f.toDecimal());
        System.out.println("g = " + g.toDecimal());
        System.out.println(f.greaterThan(g));
        System.out.println(f.equals(g));
        System.out.println(f.multiply(g));
        System.out.println(f.add(g));
        System.out.println(Fraction.product(f, g));
        System.out.println(f);
        f.integerMultiply(10000);
        System.out.println("f = " + f);
        f.plusEquals(g);
        System.out.println("f += g: " + f);
        f.timesEquals(g);
        System.out.println("f *= g: " + f);

        Fraction a = new Fraction(365.75);
        System.out.println("a = "+a);
        Fraction b = new Fraction(235);
        System.out.println("b = " + b);
        Fraction c = new Fraction();
        System.out.println("c = " + c);

        System.out.println("a / b = " + Fraction.quotient(a, b));
        System.out.println("a * b = " + Fraction.difference(a, b));
        System.out.println("b + a = " + Fraction.sum(b, a));
        System.out.println("Reciprocal of b: " + b.reciprocal());






    }
}