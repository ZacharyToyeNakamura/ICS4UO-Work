public class Item implements Comparable<Item> {
    private String name;
    private String description;
    private String itemId;
    private double price;
    private int stockLeft;
    private double buyPrice;
    private boolean isTaxed;
    private final int INF = (int)0x3f3f3f3f;

    @Override
    public int compareTo(Item i) {
        return (this.name.compareTo(i.name));
    }

    /**
     * Creates an item
     *
     * @param name The name of the item
     * @param description A short description of the item
     * @param itemId The item's id
     * @param price The price of the item
     * @param buyPrice The price the store buys the item for
     * @param stockLeft The
     */
    public Item(String name, String description, String itemId, double price, double buyPrice, int stockLeft) {
        this.name = name;
        this.description = description;
        this.itemId = itemId;
        this.price = price;
        this.buyPrice = buyPrice;
        this.stockLeft = stockLeft;
        isTaxed = true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public int getStockLeft() {
        return stockLeft;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public boolean isTaxed() {
        return isTaxed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * The store sold some amount of this item.
     * Return codes:
     * -INF if there isn't enough stock left
     * Otherwise the amount of profit made. (Tax is not profit)
     *
     * @param amount the amount of the item is bought
     * @return The net profit made by the store.
     */
    public double sell(int amount) {
        if(amount > stockLeft) return -INF;
        stockLeft -= amount; // NOTE: CHANGE 0.13 to a constant for tax later
        return amount * (price - price * 1/(1 +0.13) * 0.13 - buyPrice);
    }
}
