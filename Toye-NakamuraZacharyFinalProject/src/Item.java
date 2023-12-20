public class Item implements Comparable<Item> {
    private String name;
    private String description;
    private String itemId;

    @Override
    public int compareTo(Item i) {
        return (this.name.compareTo(i.name));
    }

}
