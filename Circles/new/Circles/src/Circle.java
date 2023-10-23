public class Circle {
    private double x;
    private double y;
    private double r;


    // Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }


    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    // mutator
    public void mutate(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }


    // Constructors
    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = Math.abs(r);
    }

    public Circle() {
        this.x = 0;
        this.y = 0;
        this.r = 1;
    }

    public Circle(Circle c) {
        x = c.x;
        y = c.y;
        r = c.r;
    }


    public double area() {
        return Math.PI * Math.pow(r, 2); // pi*r^2 = area of a circle
    }


    public double circumference() {
        return 2 * Math.PI * r; // 2pir is the circumference of a circle
    }


    public double diameter() {
        return 2 * r; // 2r is the equation for the diameter of a circle
    }

    public Circle smaller(Circle c) {
        // since area is based on radius and a constant comparing just the radii is fine.
        if(this.r <= c.r) {
            return this;
        }
        return c;
    }

    public double distance(Circle c) {
        // Distance between the center of the 2 circles using pythagorean theorem
        return Math.sqrt(Math.pow(this.x-c.x, 2) + Math.pow(this.y-c.y, 2));
    }


    // Returns true if c fully contains this without touching
    public boolean isInside(Circle c) {
        // Takes a little though, but I'm not sure if it's right
        // Visualization: https://www.desmos.com/calculator/86vz98kljx
        return (this.r*2 + this.distance(c)) < c.r*2;
    }


    public boolean equals(Circle c) {
        return this.x == c.x && this.y == c.y && this.r == c.r;
    }


    public static void printEquation(Circle c) {
        System.out.print("(x ");
        if(c.x < 0) { // flip the sign if x is negative
            System.out.print("+ ");
        }
        else {
            System.out.print("- ");
        }
        // then take the abs of x because the sign has already been corrected
        System.out.print(Math.abs(c.x) + ")² + (y ");
        // same thing with y
        if(c.y < 0) {
            System.out.print("+ ");
        }
        else {
            System.out.print("- ");
        }
        System.out.println(Math.abs(c.y) + ")² = " + Math.pow(c.r, 2));
    }


    @Override
    public String toString(){
        return "Circle: centre at ("+x+", "+y+") and radius "+r;
    }


}
