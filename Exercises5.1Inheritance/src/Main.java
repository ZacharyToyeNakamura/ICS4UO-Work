// https://docs.google.com/document/d/1N0luT_10Zx-wdJ8gve_6sHzqJ6cp6zRgBWrukH5w-os/edit
// Exercise number 1 is done in InheritanceDemo

public class Main {
    public static void main(String[] args) {
        // 2)
        Book b = new Book("How to 101", "Joe");
        HistoryBook hb = new HistoryBook("WWII in 1 word", "EEEEEE", "1900");
        System.out.println(b);
        System.out.println("hb = " + hb);
//        System.out.println(((HistoryBook) b).getTP());
    }
}
/*
3)
Study the following classes (all of which compile correctly) and then answer the questions that follow them.

class Vehicle {
	private int numWheels;

	public Vehicle (int nw) {
		numWheels = nw;
	}

	public int getNumWheels() {
		return numWheels;
	}
}

class Truck extends Vehicle {
	private int loadCapacity;

	public Truck (int nw, int lc) {
		super(nw);
		loadCapacity = lc;
	}

	public int getLoadCapacity() {
		return loadCapacity;
	}
}

class Car extends Vehicle {
	private int numSeats;

	public Car (int ns) {
		super(4);
		numSeats = ns;
	}

	public int getNumSeats() {
		return numSeats;
	}
}

Consider each of the following fragments to be in a main method that uses these classes. All the constructors are valid. The other statements fall into one of the following categories.
The statement will compile and execute correctly.
The statement will not compile but it can be repaired by a cast.
The statement will not compile and cannot be repaired by a cast.
The statement will compile but fails to execute correctly.
For each fragment, identify the category to which the second statement belongs.

Vehicle v1 = new Vehicle(4);
// Works

int nw = v1.getNumWheels();
// Works and nw = 4

Car c1 = new Car(6);
// Works and nw = 4, numSeats = 6

System.out.println(c1.getNumWheels());
// 4

Vehicle v2 = new Car(4);
// Works

int capacity = v2.getNumSeats();
// Doesn't work, needs a cast
// int capacity = ((Car) v2).getNumSeats();
// With this capacity now = 4

Truck t1 = new Truck(18, 14000);
// Works

int loadLimit = ((Vehicle)t1).getNumSeats();
// Doesn't work because Truck is not a car and never will be

Car c2 = new Car(5);
// Works nw = 4, ns = 5

int maxLoad = (((Truck)c2).getLoadCapacity());
// Doesn't work because Car isn't a truck and never will be

Vehicle v3 = new Vehicle(4);
// Works nw = 4

System.out.println(((Car)v3).getNumSeats());
// not sure

Vehicle v4 = new Truck(4, 1200);
// Works, nw = 4, load = 1200

System.out.println(((Truck)v4).getNumWheels());
// 4

Truck t2 = new Truck(10, 8000);
// works nw = 10, load = 8000

Car c3 = (Car)t2;
// Doesn't work
 */