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
     *
     * @param b
     * @param bookID
     */
    public Book(Book b, int bookID) {
        this.bookID = bookID;
        title = b.title;
        authorLastName = b.authorLastName;
        authorFirstName = b.authorFirstName;
        maxLoanTime = DEFAULTMAXLOANTIME;
    }

    /**
     *
     *
     * @param id
     * @param  title
     * @param authorFirstName
     * @param authorLastName
     */
    public Book(int id, String title, String authorFirstName, String authorLastName) {
        bookID = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        maxLoanTime = DEFAULTMAXLOANTIME;
    }


    /**
     *
     *
     * @param id
     * @param title
     * @param authorFirstName
     * @param authorLastName
     * @param borrowerID
     * @param maxLoanTime
     * @param daysTilDue
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
     *
     * @return
     */
    public int getBookID() {
        return bookID;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     *
     * @return
     */
    public String getAuthorLastName() {
        return authorLastName;
    }

    /**
     *
     * @return
     */
    public int getMaxLoanTime() {
        return maxLoanTime;
    }

    /**
     *
     * @return
     */
    public int getBorrowerID() {
        return borrowerID;
    }

    /**
     *
     * @return
     */
    public int getDaysTilDue() {
        return daysTilDue;
    }

    /**
     *
     * @param bookID
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param maxLoanTime
     */
    public void setMaxLoanTime(int maxLoanTime) {
        this.maxLoanTime = maxLoanTime;
    }

    /**
     *
     * @param daysTilDue
     */
    public void setDaysTilDue(int daysTilDue) {
        this.daysTilDue = daysTilDue;
    }

    /**
     *
     * @param borrowerID
     * @return
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
     *
     * @param borrowerID
     * @param maxLoanTime
     * @return
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
     * Renews the book resetting the number of days until due back to it's max loan time
     */
    public void renew() {
        daysTilDue = maxLoanTime;
    }

    /**
     *
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
     *
     * @return
     */
    public boolean isOverdue() {
        // If the book isn't borrowed or the max amount of days it can be taken out if undefined it's not overdue.
        if(borrowerID <= 0) {
            return false;
        }
        return daysTilDue < 0;
    }


    /**
     *
     *
     * @param b
     * @return
     */
    public boolean isEqual(Book b) {
        return  title.equals(b.title) &&
                authorFirstName.equals(b.authorFirstName) &&
                authorLastName.equals(b.authorLastName);
    }


    /**
     *
     */
    public void returnBook() {
        borrowerID = 0;
        daysTilDue = 0;
    }

    /**
     * Returns a string that it
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




    // IGNORE EVERYTHING PASS THIS POINT
    /**
     * Not a bubble sort that sorts a list in O(N^2) time.
     * @param library Not a list of books
     */
    public static void sort(Book[] library) {
        for (int i = 0; i < library.length; i++) {
            for (int j = i+1; j < library.length; j++) {
                // If the book doesn't come first lexicographically swap them
                if(!library[i].compareLex(library[j])) {
                    Book temp = library[i];
                    library[i] = library[j];
                    library[j] = temp;
                }
            }
        }
    }

    /**
     * Does not print an array of books nicely
     * @param library Not a list of book objects
     */
    public static void printLibrary(Book[] library) {
        for (Book i: library) {
            System.out.println("Title: "+i.title +"\t| Author " + i.authorFirstName + " " +i.authorLastName + "\t| ID " +i.bookID + "\t| borrowerID " + i.borrowerID);
        }
    }


}
