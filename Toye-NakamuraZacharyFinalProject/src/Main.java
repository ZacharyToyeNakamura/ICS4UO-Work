/**
 * @author Zachary Toye-Nakamura
 * @date 2024-01-17
 *
 * Simulates a store's inventory. It can save, and load data in plain text and non-plain text. It stores items which it
 * can sell, restock, check information on, sort alphabetically, and do some specialized thing (based on what the item
 * is). It has a user-friendly menu that is used to interact with the program. It should also handle any and all
 * incorrect user input and print an error message.
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Store store = new Store();
        // store.saveInventory("src/saved.txt");
        store.saveInventory("src/saved.txt");
        // System.out.println(store.encrypt("...............................", 34));
        // Food fod = new Food("Apple", "A red fruit", "PO236270", 1.99, 1.00, 100 , 1705076069, false, false, 1, false);
        // System.out.println(fod.getExpirationDate());
        // System.out.println(fod.isExpired());
        // store.loadData("src/saved.txt");
        for(int i = 0; i < 100000; i++) {
            store.saveInventory("src/saved.txt");
            store.loadData("src/saved.txt");
            try {
                Thread.sleep(10);
            } catch(InterruptedException iox) {
                System.out.println("sleep error: " + iox.getMessage());
            }
        }

    }
}