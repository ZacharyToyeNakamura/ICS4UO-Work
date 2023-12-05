import org.w3c.dom.css.Rect;

public class Rectangle extends Shape {

    private double length;
    private double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }

    public double perimeter() {
        return length * 2 + width * 2;
    }

    @Override
    public String toString() {
        return "Rectangle; Width: " + width + " Length: " + length;
    }
}
