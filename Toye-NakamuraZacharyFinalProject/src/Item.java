/**
 * This class contains most the information for an item in a store, it only has the information that every single
 * item would have (ex. no expiration date because not everything expires). It has methods to return and change most
 * of its fields. It can also do things returning the department it's from, tracking profit made off the item,
 * selling the item, restocking the item, printing its information and comparisons to other item.
 */
public class Item implements Comparable<Item> {
    protected String name; // The name of the item
    protected String description; // The item's description. A short blurb about the item and what it is.
    protected String itemId; // The item's id code
    protected String business; // The business/manufacturer/producer that made/produced the item
    protected double price; // The price that the item is sold to consumers before tax
    protected double buyPrice; // The price of the item that the store buys it for (there is never tax for this)
    protected int stockLeft; // The amount of items the store has left
    protected int restockAmt; // The amount of items the store restocks to when they restock
    protected double netProfit; // The net profit made off the item so far.

    protected boolean isTaxed; // If the item is taxed or not.
    public final static int INF = (int)0x3f3f3f3f; // A return value for invalid
    

    /**
     * Creates an item with all it's attributes
     *
     * @param name The name of the item
     * @param description A short description of the item
     * @param itemId The item's id
     * @param business The business that made the item
     * @param price The price of the item
     * @param buyPrice The price the store buys the item for
     * @param restockAmt The Amount that the store buys when re-stocking
     */
    public Item(String name, String description, String itemId, String business, double price, double buyPrice,
                int restockAmt) {
        this.name = name;
        this.description = description;
        this.itemId = itemId;
        this.business = business;
        this.price = price;
        this.buyPrice = buyPrice;
        this.restockAmt = restockAmt;
        this.stockLeft = restockAmt;
        netProfit -= buyPrice * restockAmt;
        isTaxed = true;
    }

    /**
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The items ID
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return The amount of stock left.
     */
    public int getStockLeft() {
        return stockLeft;
    }

    /**
     * @return The buy price of the item. (the price the store buys the item for)
     */
    public double getBuyPrice() {
        return buyPrice;
    }

    /**
     * @return The restock amount for the item
     */
    public int getRestockAmt() {
        return restockAmt;
    }

    /**
     * @return If the item is taxed or not.
     */
    public boolean isTaxed() {
        return isTaxed;
    }

    /**
     * Sets the name of the item
     *
     * @param name the new name for the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description for the item
     *
     * @param description The description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  Sets the price of the item
     *
     * @param price The new price for the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the amount of stock left (ex. Accidentally lost some stock/ stock got damaged and was thrown out).
     * @param stockLeft The new amount of stock left
     */
    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }

    /**
     * Sets the restock amount (used when changing season maybe? ex during winter set restock of t-shirts to 0).
     *
     * @param restockAmt The new restock amount.
     */
    public void setRestockAmt(int restockAmt) {
        this.restockAmt = restockAmt;
    }

    /**
     * Sets the new buy price
     *
     * @param buyPrice The new buy price
     */
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Sets the id of the item
     * 
     * @param id The item's new id
     */
    public void setId(String id) {
        itemId = id;
    }

    /**
     * Sets the producer/manufacturer of the item
     * 
     * @param business The new producer/manufacturer of the item.
     */
    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * @return The net profit on the item
     */
    public double getNetProfit() {
        return netProfit;
    }

    /**
     * @return The department that the item is part of.
     */
    public String getDepartment() {
        return "None";
    }

    /**
     * The store sold some amount of this item.
     * Return codes:
     * -2 if there isn't enough stock left
     * Otherwise the amount of profit made. (Tax is not profit)
     *
     * @param amount the amount of the item is bought
     * @return The net profit made by the store.
     */
    public double sell(int amount) {
        if(amount > stockLeft) return -2;
        stockLeft -= amount;
        netProfit -= amount * (price - buyPrice);
        return amount * price;
    }

    /**
     * Restocks the inventory of the item and returns the cost to restock.
     * If there is more stock than restockAmt then it does nothing.
     * 
     * @return The amount it cost to restock
     */
    public void restock() {
        if(stockLeft >= restockAmt) return;
        netProfit -= (restockAmt - stockLeft) * buyPrice;
        stockLeft = restockAmt;
    }

    /**
     * @return Just the name and id of the item in a slightly formatted string.
     */
    public String shortInfo() {
        return name + ": " + itemId;
    }



    /**
     * Compares 2 items lexicographically, if they have the same name, then it compares them by item id.
     *
     * @param i the object to be compared.
     * @return 0< if this object should come before i, and >0 if i should come before this object.
     */
    @Override
    public int compareTo(Item i) {
        if (this.name.compareTo(i.name) != 0) {
            return this.name.compareTo(i.name);
        }
        return this.itemId.compareTo(i.itemId);
    }

    /**
     * Formats a string nicely for output to the user. The description of the item should always
     * be at the bottom of the information. It only displays the information a customer and/or cashier 
     * would need to see. 
     *
     * @return A string nicely formatted with all the information of the item.
     */
    @Override
    public String toString() {
        return  "Name:  " + name + "\n" +
                "Id:    " + itemId + "\n" +
                "Price: $" + price + "\n" +
                "Stock Left: " + stockLeft + "\n" +
                "Manufacturer: " + business + "\n" +
                "Description: " + description;
    }

}
