// Lesson
// https://docs.google.com/document/d/1KdnM10CvAu777J4_V4j0A1d1TnEzU9SFlbHd02ZO20o/edit
// Exercises
// https://docs.google.com/document/d/1SePHu-UtBJsBwGaBYOxxQu7Gw58Ekd4FnUxYwrmbVDQ/edit?pli=1

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Shape trig =  new Triangle(3,4,5);
        Shape rect = new Rectangle(34,3527);
        Shape cir = new Circle(347374.4334543);

        ArrayList<Shape> myShapes = new ArrayList<>();
        myShapes.add(trig);
        myShapes.add(rect);
        myShapes.add(cir);
        for(Shape shp: myShapes) {
            System.out.println(shp);
            System.out.println("Shape's area is " + shp.area());
            System.out.println("Shape's perimeter is " + shp.perimeter() + "\n");
        }
    }
}