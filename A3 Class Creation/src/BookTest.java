public class BookTest {
    public static void main(String[] args) {
        Book b1 = new Book(3683673, "Book 1", "Suzanne", "Berne");
        Book b2 = new Book(8452340, "Book 2", "Neal", "Shusterman");
        Book b3 = new Book(b1, 9346328);
        Book b4 = new Book(9756564, "Quantum mechanics for babies", "John", "Doe", 93765981, 21, 21);
        b3.borrow(1786943, 24);
        Book b5 = new Book();
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);


        System.out.println("Does b1 come before b2 lexicographically (author's lastname)? " + b1.compareLex(b2));
        System.out.println("Does b2 come before b4 lexicographically (author's lastname)? " + b2.compareLex(b4));
        System.out.println("b1 = b2: " + b1.isEqual(b2));
        System.out.println("b1 = b3: " + b1.isEqual(b3));

        System.out.println("\nSimulate the passing of 23 days for book 3");
        b3.passDays(23);
        System.out.println("Is b3 overdue? " + b3.isOverdue());
        System.out.println("Days until b3 is overdue: " + b3.getDaysTilDue());
        System.out.println("Simulate the passing of 2 days for book 3");
        b3.passDays(2);
        System.out.println("Is b3 overdue? " + b3.isOverdue());
        System.out.println("Days until b3 is overdue: " + b3.getDaysTilDue());

        System.out.println("\n8648843 Borrows b1");
         if (b1.borrow(8648843) == -1) {
             System.out.println("b1 has already been taken out!");
         }
        System.out.println(b1);
        System.out.println("Simulate the passing of 4 days for book 1");
        b1.passDays(4);
        System.out.println(b1);
        System.out.println("\nBorrowerID 7240194 attempts borrows b1");
        if (b1.borrow(7240194) == -1) { // A return code of -1 means that it failed to take out the book
            System.out.println("b1 has already been taken out!");
        }
        System.out.println("Simulate borrower ID 8648843 returning book 1");
        b1.returnBook();
        System.out.println(b1);

        System.out.println("\n1234567 borrows b2, but the max loan time is set to 7 days");
        b2.borrow(1234567, 7);
        System.out.println(b2);
        System.out.println("\nSimulate the passing of 16 days for book 2");
        b2.passDays(16);
        System.out.println(b2);
        System.out.println("Is b2 overdue? " + b2.isOverdue());
        System.out.println("Days until b2 is due " + b2.getDaysTilDue());
        System.out.println("1234567 renews b2");
        b2.renew();
        System.out.println(b2);
        System.out.println("\nSimulate the passing of 7 days for book 2");
        b2.passDays(7);
        System.out.println(b2);
        System.out.println("\nIs b2 overdue? " + b2.isOverdue());
        System.out.println("1234567 returns b2");
        b2.returnBook();




        // IGNORE
        Book[] test = {b1,
                b2,
                b3,
                new Book( 2342, "newbook", "named", "lastname"),
                new Book( 2342, "newbook", "1", "zzz"),
                new Book( 3456, "newbook1", "2", "a"),
                new Book( 890, "newbook2", "3", "aa"),
                new Book( 134, "newbook3", "4", "ab"),
                new Book( 956, "newbook4", "5", "Baaaa"),
                new Book( 2345, "newbook5", "6", "cccc"),
                new Book( 3456, "newbook6", "7", "wret")};
        Book.printLibrary(test);
        Book.sort(test);
        System.out.println();
        Book.printLibrary(test);

        // How do I write a ab tree?
    }
}