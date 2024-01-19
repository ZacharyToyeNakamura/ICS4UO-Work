public class Clothing extends Item {
    private String type;
    private String size;
    private String fabricType;
    private String color;


    /**
     * Creates an article of clothing
     * 
     * @param name The name of the item
     * @param description A short description of the item
     * @param itemId The item's id
     * @param price The price of the item
     * @param buyPrice The price the store buys the item for
     * @param restockAmt The Amount that the store buys when re-stocking
     * @param type What type of clothing it is, (ex. t-shirt, coat, overalls)
     * @param size The size of the clothing (ex. XL, S, M, L or 23M, 9F for shoes etc.)
     */
    public Clothing(String name, String description, String itemId, String business, double price, double buyPrice,
                    int restockAmt, String type, String size, String fabricType, String color) {
        super(name, description, itemId, business, price, buyPrice, restockAmt);
        this.type = type;
        this.size = size;
        this.fabricType = fabricType;
        this.color = color;
    }

    /**
     * @return The type of the clothing
     */
    public String getType() {
        return type;
    }

    /**
     * @return the size of the clothing item
     */
    public String getSize() {
        return size;
    }


    /**
     * @return The fabric type of the clothing item (ex. Cotton 100%)
     */
    public String getFabricType() {
        return fabricType;
    }

    /**
     * @return The color of the clothing item (Ex. Blue)
     */
    public String getColor() {
        return color;
    }

    /**
     * @return The deparment that the item is part of.
     */
    @Override
    public String getDepartment() {
        return "clothing";
    }

    /**
     * @return Returns a nicely formatted string with all the information of the food item
     *         to be printed to the user. It includes the units as part of the price of the food.
     */
    @Override
    public String toString() {
        return  "Name:  " + name + "\n" +
                "Id:    " + itemId + "\n" +
                "Price: " + price + "\n" +
                "Stock Left:  " + stockLeft + "\n" +
                "Size: " + size + "\n" +
                "Type: " + type + "\n" +
                "Fabric Type: " + fabricType + "\n" +
                "Description: " + description;
    }



    
    
    
}
