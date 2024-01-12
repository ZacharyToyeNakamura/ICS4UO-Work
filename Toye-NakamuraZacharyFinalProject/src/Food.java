import java.time.Instant;

public class Food extends Item{
    private long expirationDate;
    private boolean isVegetarian;
    private boolean isLiquid;
    private double amount; // How much you get per purchase ex 100g or 1 apple
    private boolean perPound;
    /**
     * Creates an item
     *
     * @param name        The name of the item
     * @param description A short description of the item
     * @param itemId      The item's id
     * @param price       The price of the item
     * @param buyPrice    The price the store buys the item for
     * @param restockAmt  The Amount that the store buys when re-stocking
     * @param expirationDate The date in unix time when the product expires, -1 for N/A
     * @param isVegetarian If the food is vegetarian or not
     * @param isLiquid If the food item is a liquid or not
     * @param amount How much the buyer gets with 1 purchase (in mL if isLiquid is true, in lbs if perPound is true)
     *               otherwise it's per item
     * @param perPound If the amount is in pounds.
     */
    public Food(String name, String description, String itemId, double price, double buyPrice, int restockAmt,
                long expirationDate, boolean isVegetarian, boolean isLiquid, double amount, boolean perPound) {
        super(name, description, itemId, price, buyPrice, restockAmt);
        this.expirationDate = expirationDate;
        this.isVegetarian = isVegetarian;
        this.isLiquid = isLiquid;
        this.amount = amount;
        this.perPound = perPound;
    }


    /**
     * If the current unix time is past the unix time of the expiration date then the food has expired
     *
     * @return True if the food has expired, false if not.
     */
    public boolean isExpired () {
        // If the food never expires then it hasn't expired.
        if(expirationDate == -1) return false;
        return System.currentTimeMillis()/1000L - expirationDate > 0;
    }

    public String getExpirationDate() {
        String time = Instant.ofEpochMilli(expirationDate * 1000L).toString();
        return time.substring(0, 10) + " at " + time.substring(11, 19);
    }



}
