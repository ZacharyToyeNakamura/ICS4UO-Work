public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        System.out.println(ANSI_BLUE + "This text is colored" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "This text is colored" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "This text is colored" + ANSI_RESET);
        System.out.println(ANSI_RED + "This text is colored" + ANSI_RESET);
    }
}
