/**
 *
 */
public class Toy extends Item{
    private String materialType; // What the toy is made out of.
    private String ageRange; // The age rating of the toy. (ex some legos are 7+)
    private String numPlayers; // The number of players/users that can use the toy at once.
    private String dimensions;  // This could be 4 doubles (L, H, W, weight) for calculations but is currently only used

    /**
     * Creates an item
     *
     * @param name        The name of the item
     * @param description A short description of the item
     * @param itemId      The item's id
     * @param price       The price of the item
     * @param buyPrice    The price the store buys the item for
     * @param restockAmt  The Amount that the store buys when re-stocking
     */
    public Toy(String name, String description, String itemId, String business, double price, double buyPrice,
               int restockAmt, String materialType, String ageRange, String numPlayers, String dimensions) {
        super(name, description, itemId, business, price, buyPrice, restockAmt);
        this.materialType = materialType;
        this.ageRange = ageRange;
        this.numPlayers = numPlayers;
        this.dimensions = dimensions;
    }
    // to display information to the user.

    /**
     * @return The material type of the toy
     */
    public String getMaterialType() {
        return materialType;
    }

    /**
     * @return The age rating of the toy
     */
    public String getAgeRange() {
        return ageRange;
    }

    /**
     * @return The number of players for the toy
     */
    public String getNumPlayers() {
        return numPlayers;
    }

    /**
     * @return The dimensions of the toy in the format "W x L x H units; w grams"
     */
    public String getDimensions() {
        return dimensions;
    }


    @Override
    public String toString() {
        String output = super.toString();
        // Always want the description last.
        String partialOutput = output.split("Description: ")[0];
        return  partialOutput +
                "Age Range: " + ageRange + "\n" +
                "Number of Players: " + numPlayers + "\n" +
                "Material Type(s): " + materialType + "\n" +
                "Product Dimensions: " + dimensions + "\n" +
                "Description: " + description;


    }
}
