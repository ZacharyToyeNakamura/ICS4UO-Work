import java.util.ArrayList;
import java.util.Scanner;
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

    public final static Scanner input = new Scanner(System.in);
    public static Store store = new Store();

    public static void startUp() {
        System.out.println("Starting . . . ");
        System.out.println("\t\tWelcome to GSS software");
        System.out.println("To use this program, just enter the number beside the action you would like to preform.");
        System.out.println("Then follow any following prompts.");
        printMainMenu();
    }

    public static void printMainMenu() {
        System.out.println("1. Edit items."); // Note not every attribute can be changed, that would be fraud
        System.out.println("2. Display information.");
        System.out.println("3. Sell items.");
        System.out.println("4. Permantly sort the order of items.");
        System.out.println("5. Exit the program.");
    }

    /**
     * Prints a sub menu for editing items
     */
    public static void printEditMenu() {
        System.out.println("1. Edit 1 item."); // Then choose a property.
        System.out.println("2. Edit all items.");
        System.out.println("3. Go Back");
    }

    /**
     * Prints the display information menu
     */
    public static void printDisInfoMenu() {
        System.out.println("1. Display information for 1 item.");
        System.out.println("2. Display information for 1 catagory of items");
        System.out.println("3. Display information for all items.");
        System.out.println("4. Go back");
    }

    /**
     * The menu inside printEditMenu(), it asks asks the user what they what to edit about the 
     * item.
     * 
     * @param multi True if the user is editting multiple values, false if not.
     */
    public static void printEditItem(boolean multi) {
        System.out.print("1. Edit name of item");
        if(multi) {
            System.out.print("s");
        } 
        System.out.print("\n2. Edit ID of item");
        if(multi) {
            System.out.print("s");
        } 
        System.out.println("\n3. Edit the description.");
        System.out.println("4. Edit the manufacturer/producer.");
        System.out.println("5. Edit the sale price.");
        System.out.println("6. Edit the buy price.");
        System.out.println("7. Edit the restock amount.");
    }

    /**
     * Get a valid number from the user, it repeatidly asks them if they don't enter a 
     * 
     * @param low The minimum number the user's input can be to be valid
     * @param high The max number the user's input can be to be valid
     * @return A valid number between [low, high] inclusively, that the user inputted.
     */
    public static int getMenuInput(int low, int high) {
        String userInput = "";
        while(true) {
            try {
                System.out.print("> ");
                userInput = input.nextLine();
                int userChoice = Integer.parseInt(userInput);
                if(userChoice >= low && userChoice <= high) {
                    return userChoice;
                }
            } catch(Exception excpt) {}
            System.out.println("Invalid Input: Please enter a number between " + low + " and " + high);
        }
    }

    /**
     * Asks the user for the name or id of the item (used elsewhere). Repeatedly asks them and displays an error message
     * if the user doesn't enter a name or id that is in the current inventory.
     * 
     * @return The index of the name or id in the store's inventory that the user wants.  
     */
    public static int getValidItem() {
        String userInput = "";
        while(true) {
            // Display all the name and ids of the items.
            System.out.println("Items:\nName : Item's ID");
            for(int i = 0; i < store.getInventorySize(); i++) {
                System.out.println(store.getItem(i).shortInfo());
            }

            System.out.print("Enter the item's name or ID: ");
            userInput = input.nextLine();
            int idxOfItem = store.findItem(userInput);
            if(idxOfItem != -1) {
                return idxOfItem;
            }
            System.out.println("Invalid Input: Please enter the name or id of a item in the inventory");
        }
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");

        startUp();
        // The user's choice
        int userChoice = -1;
        // Temp variable used to store user input
        String userInput = "";
        // The current menu
        // 0 is the main, 1 is edit items, 2 display info, 3 sell items, 4 which attribute is going to be editted (in edit items) but editting just 1 value.
        // 5 is the same as menu 4 but editing ALL items
        int curMenu = 0;
        while(userChoice != 5) {
            switch (curMenu) {
                case 0:
                    printMainMenu();
                    curMenu = getMenuInput(1, 5);
                    break;

                case 1:
                    printEditMenu();
                    curMenu = getMenuInput(1, 3) + 3;
                    if(curMenu == 6) {
                        curMenu = 0;
                    }
                    break;

                case 2:
                    printDisInfoMenu();
                    userChoice = getMenuInput(1, 4);
                    if(userChoice == 4) {
                        curMenu = 0;
                        break;
                    }
                    switch (userChoice) {
                        case 1:
                            userChoice = getValidItem();
                            System.out.println(store.getItem(userChoice));
                            break;
                        
                        case 2:
                            userInput = "";
                            boolean firstTime = false;
                            while(userInput != "food" && userInput != "clothing" && userInput != "toys" && userInput != "none") { 
                                System.out.println("Valid catagories are: Food, Clothing, Toys, None");
                                System.out.print("Enter which catagory you would like to see info for: ");
                                userInput = input.nextLine().toLowerCase();
                                if(!firstTime) {
                                    firstTime = true;
                                } else { // Display an error message if it's not the first time asking the user for input
                                    System.out.println("Invalid catagory. Please enter a valid catagory");
                                }
                            }
                            boolean atleastOne = false;
                            for(int i = 0; i < store.getInventorySize(); i++) {
                                // Only print the items that are part of the deparment
                                if(store.getItem(i).getDeparment().equals(userInput)) {
                                    System.out.println(store.getItem(i));
                                    atleastOne = true;
                                }
                            }
                            // Print a message if there are no items in the deparment
                            if(!atleastOne) {
                                System.out.println("There are no items in that catagory");
                            }
                            break;

                        case 3:
                            for(int i = 0; i < store.getInventorySize(); i++) {
                                System.out.println(store.getItem(i));
                            }
                            break;

                        default:
                            System.out.println("Fatal Error: Should have never gotten here.\nError inside information menu");
                            break;
                    }
                    
                    break;

                case 3:
                    System.out.println("Transaction Started. Enter -1 to any prompt to cancel this transaction.");
                    userInput = "";
                    ArrayList<String> receipt = new ArrayList<>();
                    receipt.add("Sales transaction: ");
                    double customerTot = 0;
                    while(!userInput.equals("-1")) {
                        // Get user input
                        System.out.println("1. Sell an item");
                        System.out.println("2. Finish transaction");
                        System.out.println("3. Cancel transaction");
                        System.out.print("> ");
                        userChoice = getMenuInput(1, 3);
                        if(userChoice == 3) {
                            curMenu = 0;
                            break;
                        }
                        if(userChoice == 1) {
                            System.out.print("Enter the name or ID of the id being sold: ");
                            Item requestedItem = store.getItem(getValidItem());
                            int amountSold = -2;
                            try {
                                System.out.println("There are " + requestedItem.getStockLeft() + " units of " + 
                                                    requestedItem.getName() + " left"); 
                                System.out.print("Enter the quantity being purcahsed: "); // Enter the number of units being sold:
                                amountSold = Integer.parseInt(input.nextLine());
                                if(amountSold <= 0) {
                                    System.out.println("Please enter a positive non-zero integer amount.");
                                } else if(amountSold > requestedItem.getStockLeft()) {
                                    System.out.println("Please enter a number less than or equal to the amount of stock left!\n" +
                                    "We can't sell more product than we have in stock!");
                                }
                            } catch(Exception expct) {
                                System.out.println("Please enter a positive integer.");
                            }
                            double cost = store.sell(requestedItem.getName(), amountSold);
                            if(cost == -2) {
                                System.out.println("Error: Too many tried more items, than in stock");
                            } else {
                                receipt.add(requestedItem.getName() + " x " + amountSold + " $" + cost);
                                customerTot += cost;
                            }
                        }
                        if(userChoice == 2) {
                            System.out.println("Transaction Finished!");
                            for (int i = 0; i < receipt.size(); i++) {
                                System.out.println(receipt.get(i));
                            }
                        }

                    }
                    

            
                default:
                    System.out.println("Error, outside of preset menus. Returning to the main menu");
                    curMenu = 0;
                    break;
            }

        }


        
        // System.out.println(store.encrypt("...............................", 34));
        // Food fod = new Food("Apple", "A red fruit", "PO236270","Bob's farm", 1.99, 1.00, 100 , 1705076069, false, false, 1, false);
        // System.out.println(fod.getExpirationDate());
        // System.out.println(fod.isExpired());

        // for(int i = 0; i < 100000; i++) {
        //     store.saveInventory("src/saved.txt");
        //     store.loadData("src/saved.txt");
        //     try {
        //         Thread.sleep(10);
        //     } catch(InterruptedException iox) {
        //         System.out.println("sleep error: " + iox.getMessage());
        //     }
        // }
            
    }
}