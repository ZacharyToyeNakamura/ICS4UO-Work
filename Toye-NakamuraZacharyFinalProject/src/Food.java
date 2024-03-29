import java.time.Instant;

/**
 * This class stores most of the information that would be food product would require. It has all the methods of item on top of some unique
 * methdods. It has an expiration date, the amount of food the buyer gets when purchasing, if it's charged per pound,
 * if it's liquid and if it's vegetarian. Other descriptors like vegan/organic are in the description of the item.
 */
public class Food extends Item{
    private long expirationDate; // The date in unix when the product expires.
    private boolean isVegetarian; // If the product is vegetarian or not.
    private boolean isLiquid; // If the product is a liquid or not.
    private double amount; // How much you get per purchase ex 100g or 1 apple.
    private boolean perGram; // If the product is purchased per pound.
    /**
     * Creates a food item, with all of its parameters.
     *
     * @param name        The name of the item.
     * @param description A short description of the item.
     * @param itemId      The item's id.
     * @param price       The price of the item.
     * @param buyPrice    The price the store buys the item for.
     * @param restockAmt  The Amount that the store buys when re-stocking.
     * @param expirationDate The date in unix time when the product expires, -1 for N/A.
     * @param isVegetarian If the food is vegetarian or not.
     * @param isLiquid If the food item is a liquid or not.
     * @param amount How much the buyer gets with 1 purchase (in mL if isLiquid is true, in lbs if perGram is true)
     *               otherwise it's per item.
     * @param perGram If the amount is in pounds.
     */
    public Food(String name, String description, String itemId, String business, double price, double buyPrice,
                int restockAmt, long expirationDate, boolean isVegetarian, boolean isLiquid, double amount,
                boolean perGram, boolean isTaxed) {
        super(name, description, itemId, business, price, buyPrice, restockAmt);
        this.expirationDate = expirationDate;
        this.isVegetarian = isVegetarian;
        this.isLiquid = isLiquid;
        this.amount = amount;
        this.perGram = perGram;
        this.isTaxed = isTaxed; // Most food products aren't taxed except candy
    }

    /**
     * @return the amount of the item the customer gets in 1 purchase
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return If the food is sold per gram.
     */
    public boolean isPerGram() {
        return perGram;
    }

    /**
     * Sets if the food is vegetarian or not.
     *
     * @param vegetarian True if the food is vegetarian. otherwise false
     */
    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    /**
     * Sets if the food is a liquid or not
     *
     * @param liquid True if the food is a liquid otherwise false.
     */
    public void setLiquid(boolean liquid) {
        isLiquid = liquid;
    }

    /**
     * Sets the amount the customer gets per purchase
     *
     * @param amount The new amount the customer gets per purchase
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets whether the food is sold per gram or not
     *
     * @param perGram If the food is sold per gram or not.
     */
    public void setPerGram(boolean perGram) {
        this.perGram = perGram;
    }

    /**
     * @return Whether the food item is vegetarian or not.
     */
    public boolean isVegetarian() {
        return isVegetarian;
    }


    /**
     * @return Whether the food item is a liquid or not.
     */
    public boolean isLiquid() {
        return isLiquid;
    }


    /**
     * If the current unix time is past the unix time of the expiration date then the food has expired
     *
     * @return True if the food has expired, false if not.
     */
    public boolean isExpired () {
        // If the food never expires then it hasn't expired.
        if(expirationDate == -1) return false;
        return System.currentTimeMillis() / 1000L - expirationDate > 0;
    }

    /**
     * Converts the unix time to a date and time format (human readable time)
     * @return The date the food expires in YYY-MM-DD at HH-MM-SS format
     */
    public String getExpirationDate() {
        String time = Instant.ofEpochMilli(expirationDate * 1000L).toString();
        return time.substring(0, 10) + " at " + time.substring(11, 19);
    }

    /**
     * @return The deparment that the item is part of.
     */
    @Override
    public String getDepartment() {
        return "food";
    }

    /**
     * @return The expiration date in unix time (number of seconds since Jan 1st, 1970)
     */
    public long getUnixExpDate() {
        return expirationDate;
    }


    /**
     * @return Returns a nicely formatted string with all the information of the food item
     *         to be printed to the user. It includes the units as part of the price of the food.
     */
    @Override
    public String toString() {
        // Always want the description last.
        String units = " for " + amount;
        if(perGram) units = " /" + amount + "g"; // prioritize per pound, but really both shouldn't be true at the same time.
        else if(isLiquid) units = " /" + amount + "ml";
        return  "Name:  " + name + "\n" +
                "Id:    " + itemId + "\n" +
                "Price: $" + price + units + "\n" +
                "Stock Left: " + stockLeft + "\n" +
                "Producer: " + business + "\n" +
                "Vegetarian: " + isVegetarian + "\n" +
                "Liquid: " + isLiquid + "\n" + 
                "Description: " + description;
    }



}
