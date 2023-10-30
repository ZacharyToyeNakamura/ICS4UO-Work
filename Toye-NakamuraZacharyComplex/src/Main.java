/**
 *
 * @author Zachary Toye-Nakamura
 * @date 2023-10-30
 * A simple complex numbers class with a few methods including addition, magnitude, and multiplication by a scalar.
 *
 */
public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(0.2, 100.8);
        Complex c2 = new Complex();
        Complex c3 = new Complex(0.0, 45);
        Complex c4 = new Complex(0.1, 5.4);
        Complex c5 = new Complex(-26.297, 0);

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);
        System.out.println("c4 = " + c4);
        System.out.println("c5 = " + c5 + "\n");

        System.out.println("c1.equals(c3): " + c1.equals(c3));
        c4 = c4.plus(c3);
        System.out.println("c4.plus(c3): " + c4);
        c4.scale(2);
        System.out.println("c4 scaled by a factor of 2: " + c4);
        System.out.println("c1.equals(c4): " + c1.equals(c4));

        c5.scale(3.4);
        System.out.println("c5 scaled by a factor of 3.4: " + c5);
        c4.scale(374.7654);
        System.out.println("c4 scaled by a factor of 374.7654: " + c4);

        System.out.println("\nMagnitude of c1: " + c1.abs());
        System.out.println("Magnitude of c2: " + c2.abs());
        System.out.println("Magnitude of c3: " + c3.abs());
        System.out.println("Magnitude of c4: " + c4.abs());
        System.out.println("Magnitude of c5: " + c5.abs());
        System.out.println("\nc1's conjugate is " + Complex.conjugate(c1));
        System.out.println("c2's conjugate is " + Complex.conjugate(c2));
        System.out.println("c3's conjugate is " + Complex.conjugate(c3));
        System.out.println("c4's conjugate is " + Complex.conjugate(c4));
        System.out.println("c5's conjugate is " + Complex.conjugate(c5));

    }
}
// I failed the circles assignment because I don't know basic geometry