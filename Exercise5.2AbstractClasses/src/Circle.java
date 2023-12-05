public class Circle extends Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double perimeter() {
        return Math.PI * 2 * radius;
    }


    @Override
    public String toString() {
        return "Circle; Radius: " + radius;
    }

}
