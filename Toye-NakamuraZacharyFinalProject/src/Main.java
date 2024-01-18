import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
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
    }

    public static String printMainMenu() {
        // Note not every attribute can be changed, that would be fraud
        return  "1. Edit items.\n" +
                "2. Display information.\n" +
                "3. Sell items.\n" +
                "4. Add a item.\n" +
                "5. Remove a item.\n" +
                "6. Permanently sort the order of items.\n" +
                "7. Exit the program.\n";
    }

    /**
     * Formats a string that is a sub menu for editing items
     *
     * @return  A formatted string of the sub menu for editing item to display to the user
     */
    public static String printEditMenu() {
        return "1. Edit 1 item.\n" + // Then choose a property.
        "2. Edit all items.\n" +
        "3. Go Back\n";
    }

    /**
     * @return A formatted string for the display information menu to the user
     */
    public static String printDisInfoMenu() {
        return "1. Display information for 1 item.\n" +
        "2. Display information for 1 category of items\n" +
        "3. Display information for all items.\n" +
        "4. Go back\n";
    }

    /**
     * The menu inside printEditMenu(), it asks the user what they what to edit about the
     * item.
     * 
     * @param multi True if the user is editing multiple values, false if not.
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
     * Get a valid integer from the user in a given range, it repeatedly asks them if they don't enter a "valid number".
     *
     * @param msg The message prompting the user to enter a number.
     * @param errorMsg The error message the program displays upon invalid input, leave as "" for the default message.
     * @param low The minimum number the user's input can be to be valid.
     * @param high The max number the user's input can be to be valid.
     * @return A valid number between [low, high] inclusively, that the user inputted.
     */
    public static int getValidInt(String msg, String errorMsg, int low, int high) {
        String userInput = "";
        while(true) {
            try {
                System.out.print(msg);
                userInput = input.nextLine();
                int userChoice = Integer.parseInt(userInput);
                if(userChoice >= low && userChoice <= high) {
                    return userChoice;
                }
            } catch(Exception exception) {}
            if(errorMsg.equals("")) {
                System.out.println("Invalid Input: Please enter a number between " + low + " and " + high);
            } else {
                System.out.println(errorMsg);
            }
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

    /**
     * Gets a valid price from the user. (It must be a non-negative double that fits in a double)
     *
     * @param msg The message displayed to the user prompting them to enter a value
     * @return A non-negative double value that the user wants to set the price as.
     */
    public static double getValidPrice(String msg) {
        double userInput;
        while(true) {
            try {
                System.out.print(msg);
                userInput = Double.parseDouble(input.nextLine());
                if (userInput > 0) {
                    return userInput;
                }
                System.out.println("Invalid Input: Please enter a non-negative number value.");
            } catch(Exception exception) {
                System.out.println("Invalid Input: Please enter a number value (double)!");
            }
        }
    }

    /**
     * Gets a valid boolean from the user
     *
     * @param msg The message displayed to the user prompting them to enter a boolean
     * @return The user's input (when finally correct).
     */
    public static boolean getValidBool(String msg) {
        String userInput;
        while(true) {
            System.out.print(msg);
            userInput = input.nextLine().toLowerCase();
            if (userInput.equals("y") || userInput.equals("yes")) {
                return true;
            } else if (userInput.equals("n") || userInput.equals("no")) {
                return false;
            }
            System.out.println("Invalid Input: Please enter yes(y) or no(n).");

        }
    }

    /**
     * Gets the expiration date from the user.
     *
     * @return The expiration date in unix
     */
    public static long getValidExpirationDate() {
        int year = getValidInt("Enter the year the item expires in (Latest 2038): ",
                "Please enter a valid year before 2038!",1970, 2038); // hoping they don't try to sell food from before 1970
        int month = getValidInt("Enter the month the item expires in (Jan = 1, Dec = 12): ",
                "Please enter a valid month! (between 1 and 12)",1,12);
        // I'm not lazy uhhhh, just thinking smarter. | The number of days in a month
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
        int day = getValidInt("Enter the day the item expires on:",
                "Please enter a valid day! [1, " + daysInMonth + "]", 1, daysInMonth);
        // Why -1900? I'm not sure but it works. I could probably do think myself with a little multiplication but nah.
        return new Date(year - 1900, month, day, 0, 0, 0).getTime() / 1000L;
    }



    public static void main(String[] args) {


        startUp();
        // The user's choice
        int userChoice = -1;
        // Temp variable used to store user input
        String userInput = "";
        // The current menu
        // 0 is the main, 1 is edit items, 2 display info, 3 sell items, 4 is adding/deleting items
        // 5 which attribute is going to be edited (in edit items) but editing just 1 value.
        // 6 is the same as menu 4 but editing ALL items
        int curMenu = 0;
        while(userChoice != 7) {
            System.out.println(userChoice);
            switch (curMenu) {  
                case 0:
                    userChoice = getValidInt(printMainMenu() + "> ", "", 1, 7);
                    if(userChoice == 4) {
                        // Adding an item
                        System.out.println("Which department is the item part of? ");

                        userChoice = getValidInt("1. Food\n" +
                                                        "2. Clothing\n" +
                                                        "3. Toys\n" +
                                                        "4. None of the above\n> ", "",1, 4);
                        
                        System.out.print("Enter the name of the item: ");
                        String newItemName = input.nextLine();
                        System.out.print("Enter the description of the item: ");
                        String newItemDesc = input.nextLine();
                        System.out.print("Enter the ID of the item: ");
                        String newItemId = input.nextLine();
                        System.out.print("Enter the manufacturer/producer of the item: ");
                        String newItemBusiness = input.nextLine();
                        System.out.print("Enter the selling price of the item: ");
                        double newItemPrice = getValidPrice("Enter the selling price of the item: ");
                        System.out.print("Enter the price the store buys the item for: ");
                        double newItemBuyPrice = getValidPrice("Enter the price the store buys the item for: ");
                        // Store shouldn't need to stock more than 2^31-1 items, if they do uhhhhhh not my problem.
                        int newItemRestockAmt = getValidInt("Enter the amount of item the store restocks: ",
                                "Please enter a non-negative integer value!",0,2147483646);
                        if(userChoice == 4) {
                            store.addItem(new Item(newItemName,newItemDesc,newItemId,newItemBusiness,newItemPrice,
                                    newItemBuyPrice,newItemRestockAmt));
                        }
                        if(userChoice == 1) {
                            long expDate = getValidExpirationDate();
                            boolean isVeg = getValidBool("Is it vegetarian: ");
                            boolean isLiquid = getValidBool("Is the item liquid: ");
                            boolean perGram = getValidBool("Is the item sold per pound: ");
                            int amount;
                            if(isLiquid) {
                                amount = getValidInt("Enter the volume of the item received per purchase: ", "Please enter a non-negative integer!",0,Integer.MAX_VALUE);
                            } else if(perGram) {
                                amount = getValidInt("Enter the weight of the item received per purchase: ", "Please enter a non-negative integer!",0,Integer.MAX_VALUE);
                            } else {
                                amount = getValidInt("Enter number of units received per purchase: ", "Please enter a non-negative integer!",0,Integer.MAX_VALUE);
                            }


                        } else if(userChoice == 2) {

                        } else if(userChoice == 3) {

                        }


                    } if(userChoice == 5) {
                        // Removing an item
                        System.out.println("Removing an item");
                        int itemToRemove = getValidItem();
                        store.removeItem(store.getItem(itemToRemove).getName());
                        System.out.println("");

                    } else if(userChoice == 6) {
                        store.permSort();
                        System.out.println("The items have been put in ");
                    } else if(userChoice != 7) { // sub menus are 1 - 3
                        curMenu = userChoice;
                    }
                    break;

                case 1:
                    userChoice = getValidInt(printEditMenu() + "> " ,"", 1, 3);
                    if(userChoice == 3) { // go back
                        curMenu = 0; 
                    } else {
                        curMenu = userChoice + 4;
                    }
                    break;

                case 2:
                    userChoice = getValidInt(printDisInfoMenu()+ "> ","",1, 4);
                    if(userChoice == 4) { // go back
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
                            while(!userInput.equals("food") && !userInput.equals("clothing") &&
                                    !userInput.equals("toys") && !userInput.equals("none")) {
                                System.out.println("Valid categories are: Food, Clothing, Toys, None");
                                System.out.print("Enter which category you would like to see info for: ");
                                userInput = input.nextLine().toLowerCase();
                                if(!firstTime) {
                                    firstTime = true;
                                } else { // Display an error message if it's not the first time asking the user for input
                                    System.out.println("Invalid category. Please enter a valid category");
                                }
                            }
                            boolean atleastOne = false;
                            for(int i = 0; i < store.getInventorySize(); i++) {
                                // Only print the items that are part of the department
                                if(store.getItem(i).getDepartment().equals(userInput)) {
                                    System.out.println(store.getItem(i));
                                    atleastOne = true;
                                }
                            }
                            // Print a message if there are no items in the department
                            if(!atleastOne) {
                                System.out.println("There are no items in that category");
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
                    // Canceling the transaction would be a pain to do, its easier not to. (limited time)
                    System.out.println("Transaction Started.");
                    userInput = "";
                    ArrayList<String> receipt = new ArrayList<>();
                    receipt.add("Sales transaction: ");
                    double totTaxed = 0; // total price of items that are taxed
                    double totNotTaxed = 0; // Total price of items that aren't taxed
                    while(!userInput.equals("-1")) {
                        // Get user input
                        userChoice = getValidInt("1. Sell an item\n2. Finish transaction\n>","", 1, 2);
                        if(userChoice == 1) {
                            System.out.print("Enter the name or ID of the id being sold: ");
                            Item requestedItem = store.getItem(getValidItem());
                            int amountSold = -2;
                            try {
                                System.out.println("There are " + requestedItem.getStockLeft() + " units of " + 
                                                    requestedItem.getName() + " left"); 
                                System.out.print("Enter the quantity being purchased: "); // Enter the number of units being sold:
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
                                if(requestedItem.isTaxed()) {
                                    totTaxed += cost;
                                } else {
                                    totNotTaxed += cost;
                                }
                            }
                        }
                        if(userChoice == 2) {
                            System.out.println("Transaction Finished!");
                            for (int i = 0; i < receipt.size(); i++) {
                                System.out.println(receipt.get(i));
                            }
                            System.out.println("Sub total: $" + totTaxed + totNotTaxed);
                            System.out.println("Tax: $" + totTaxed * (1-store.TAX_PERCENT));
                            System.out.println("Total: $" + totTaxed * store.TAX_PERCENT + totNotTaxed);

                            curMenu = 0;
                        }

                    }
                case 4:
                    printEditItem(false);
                     
                    break;


                case 5:
                    break;

            
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