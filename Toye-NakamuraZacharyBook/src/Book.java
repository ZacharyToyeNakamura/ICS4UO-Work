public class Book {
    private int bookID;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int maxLoanTime;
    private int borrowerID;
    private int daysTilDue;
    private static final int DEFAULTMAXLOANTIME = 21;

    /**
     * Create a book with default attributes, everything is set to 0 or an empty string
     * A bookID of -1 is NULL or undefined.
     * A maxLoanTime of -1 is infinite or undefined.
     */
    public Book() {
        bookID = -1;
        title = "";
        authorFirstName = "";
        authorLastName = "";
        maxLoanTime = DEFAULTMAXLOANTIME;
        borrowerID = 0;
        daysTilDue = 0;
    }

    /**
     * Creates a book with the same parameters as another book except it's not borrowed and the ID is different.
     * It sets the maxLoanTime to the default of 21 days.
     *
     * @param b A book object that the title and author's name will be copied from
     * @param bookID An integer number greater than 0.
     */
    public Book(Book b, int bookID) {
        this.bookID = bookID;
        title = b.title;
        authorLastName = b.authorLastName;
        authorFirstName = b.authorFirstName;
        maxLoanTime = b.maxLoanTime;
    }

    /**
     * Creates a book that isn't borrowed with all required parameters. It sets the maxLoanTime to the default of 21 days.
     *
     * @param id An integer number greater than 0, used to identify the book
     * @param title A string, the title of the book.
     * @param authorFirstName A string, the book's author's first name
     * @param authorLastName A strig, the book's author's last name
     */
    public Book(int id, String title, String authorFirstName, String authorLastName) {
        bookID = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        maxLoanTime = DEFAULTMAXLOANTIME;
    }


    /**
     * Creates a book with all attributes manually inputted.
     *
     * @param id An integer number greater than 0, used to identify the book
     * @param title A string, the title of the book.
     * @param authorFirstName A string, the book's author's first name
     * @param authorLastName A strig, the book's author's last name
     * @param borrowerID An integer number greater than 0, used to identify the borrower
     * @param maxLoanTime An integer number > 0, The maximum time that the book can be borrowed for
     * @param daysTilDue An integer number > 0, The amount of days until the book is due
     */
    public Book(int id, String title, String authorFirstName, String authorLastName,
                int borrowerID, int maxLoanTime, int daysTilDue) {
        bookID = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.borrowerID = borrowerID;
        this.maxLoanTime = maxLoanTime;
        this.daysTilDue = daysTilDue;
    }

    /**
     * Returns the book's identification number.
     *
     * @return the book's identification number.
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book | String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author's first name.
     *
     * @return the author's first name | String
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     * Returns the author's last name.
     *
     * @return the author's last name | String
     */
    public String getAuthorLastName() {
        return authorLastName;
    }

    /**
     * Returns the maximum time that the book can be borrowed for, a number less than or equal to 0 means undefined.
     *
     * @return the maximum time that the book can be borrowed for | Integer
     */
    public int getMaxLoanTime() {
        return maxLoanTime;
    }

    /**
     * Returns the borrow's identification number, a number less than or equal to 0 means unborrowed.
     *
     * @return the borrow's identification number | Integer.
     */
    public int getBorrowerID() {
        return borrowerID;
    }

    /**
     * Returns the number of days until the book is due.
     *
     * @return the number of days until the book is due.
     */
    public int getDaysTilDue() {
        return daysTilDue;
    }

    /**
     * Sets the book's identification number.
     *
     * @param bookID An integer | The book's new identification number.
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /**
     * Sets the book's title
     *
     * @param title A String | The book's new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the maximum time that the book can be borrowed for
     *
     * @param maxLoanTime An integer | The book's new maximum loan time.
     */
    public void setMaxLoanTime(int maxLoanTime) {
        this.maxLoanTime = maxLoanTime;
    }

    /**
     * Sets the number of days until the book is due.
     *
     * @param daysTilDue An integer | The new number of days until the book is due.
     */
    public void setDaysTilDue(int daysTilDue) {
        this.daysTilDue = daysTilDue;
    }

    /**
     * Sets the book as borrowed and stores the borrower's ID and how many days are left until the book is due.
     *
     * @param borrowerID A non-zero integer | The borrower's ID (like a library card's ID)
     */
    public int borrow(int borrowerID) {
        // If the book is already taken out, don't let another person take it out.
        if(this.borrowerID > 0) return -1;
        this.borrowerID = borrowerID;
        maxLoanTime = DEFAULTMAXLOANTIME;
        daysTilDue = maxLoanTime;
        return 0;
    }

    /**
     * Sets the book as borrowed, and it stores the borrow's ID, how many days are left until the book is due and it changes the max loan time.
     *
     * @param borrowerID A non-zero integer | The borrower's ID (like a library card's ID)
     * @param maxLoanTime An integer | the maximum amount of time the book can be borrowed for
     */
    public int borrow(int borrowerID, int maxLoanTime) {
        // If the book is already taken out, don't let another person take it out.
        if(this.borrowerID > 0) return -1;
        this.borrowerID = borrowerID;
        this.maxLoanTime = maxLoanTime;
        daysTilDue = maxLoanTime;
        return 0;
    }

    /**
     * Renews the book, setting the number of to the maximum time it can be taken out for.
     */
    public void renew() {
        daysTilDue = maxLoanTime;
    }

    /**
     * Simulates days passing for the book, bringing it closer to being due.
     * This method could be used with a negative value to re-new the book extending it's due date.
     *
     * @param days An integer | The number of days that pass for the book
     */
    public void passDays(int days) {
        // A borrower's ID should be a positive non-zero integer.
        // If the book isn't borrowed do nothing.
        if(borrowerID <= 0) {
            return;
        }
        daysTilDue -= days;
    }


    /**
     * Checks whether a book is overdue or not.
     * A book is only overdue if it has a negative number of daysTilDue.
     *
     * @return True if the book is due, otherwise false.
     */
    public boolean isOverdue() {
        // If the book isn't borrowed or the max amount of days it can be taken out if undefined it's not overdue.
        if(borrowerID <= 0) {
            return false;
        }
        return daysTilDue < 0;
    }


    /**
     * Checks if 2 books are equal, for this purpose 2 books are equal if they have the same
     * title and author names. This is because if someone where to borrow a book they don't care about
     * anything else and the contents of the book (the words/ story) would be the same.
     *
     * @param b A book object that is compared against this book.
     * @return True if the 2 books are equal and false if they aren't.
     */
    public boolean isEqual(Book b) {
        return  title.equals(b.title) &&
                authorFirstName.equals(b.authorFirstName) &&
                authorLastName.equals(b.authorLastName);
    }


    /**
     * Returns the borrowerID to 0 indicating that it isn't borrowed.
     */
    public void returnBook() {
        borrowerID = 0;
        daysTilDue = 0;
    }

    /**
     * Returns a string that is formatted nicely (used for printing to output).
     * If the book isn't borrowed (borrower ID <= 0) then it only prints information pertaining to the book.
     * If the book is borrower (borrower ID > 0) then it also prints the borrower's ID and how many days they have left until it's due
     *
     * @return
     */
    @Override
    public String toString() {
        if(borrowerID <= 0){
            return "Book\n---------------\n" +
                    "Title: " + title + "\n" +
                    "Author: " + authorFirstName + " " + authorLastName + "\n" +
                    "Book ID: " + bookID + "\n" +
                    "Maximum loan time: " + maxLoanTime + " days\n";
        }
        else {
            return "Book\n---------------\n" +
                    "Title: " + title + "\n" +
                    "Author: " + authorFirstName + " " + authorLastName + "\n" +
                    "Book ID: " + bookID + "\n" +
                    "Maximum loan time: " + maxLoanTime + " days\n" +
                    "Borrower ID: " + borrowerID + "\n" +
                    "Days Until Due: " + daysTilDue + " days\n";
        }

    }


    /**
     * Compares this book's author's last name against b's author's last name lexicographically,
     * comparing all letter as lowercase.
     * If the last names are the same then isn't first lexicographically.
     *
     * @param b Any book object | Compared lexicographically against this book.
     * @return If this book comes first lexicographically it returns true. Otherwise it returns false.
     */
    public boolean compareLex(Book b) {
        for (int i = 0; i < Math.min(authorLastName.length(), b.authorFirstName.length()); i++) {
            if(authorLastName.toLowerCase().charAt(i) < b.authorLastName.toLowerCase().charAt(i)) {
                return true;
            }
            if(authorLastName.toLowerCase().charAt(i) > b.authorLastName.toLowerCase().charAt(i)) {
                return false;
            }
        }
        return authorLastName.length() < b.authorLastName.length();
    }




//    // IGNORE
//    /**
//     * Not a bubble sort that sorts a list in O(N^2) time.
//     * @param library Not a list of books
//     */
//    public static void sort(Book[] library) {
//        for (int i = 0; i < library.length; i++) {
//            for (int j = i+1; j < library.length; j++) {
//                // If the book doesn't come first lexicographically swap them
//                if(!library[i].compareLex(library[j])) {
//                    Book temp = library[i];
//                    library[i] = library[j];
//                    library[j] = temp;
//                }
//            }
//        }
//    }
//
//    /**
//     * Does not print an array of books nicely
//     * @param library Not a list of book objects
//     */
//    public static void printLibrary(Book[] library) {
//        for (Book i: library) {
//            System.out.println("Title: "+i.title +"\t| Author " + i.authorFirstName + " " +i.authorLastName + "\t| ID " +i.bookID + "\t| borrowerID " + i.borrowerID);
//        }
//    }

}
