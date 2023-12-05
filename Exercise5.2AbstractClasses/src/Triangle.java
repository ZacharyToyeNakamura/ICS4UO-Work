public class Triangle extends Shape{

    private double a;
    private double b;
    private double c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        return Math.sqrt((a + b + c)/2 * ((a + b + c)/2 - a) * ((a + b + c)/2 - b) * ((a + b + c)/2 - c));
    }

    @Override
    public String toString() {
        return "Triangle; Side a: " + a + " Side b: " + b + " Side c: " + c;
    }

}
