import java.util.ArrayList;
import java.util.List;

public class Order{
    private final String customerName;
    private final List<Item> items;
    private double totalCost;

    Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.totalCost = 0;
    }

    public void Add_Item(Item item) {
        items.add(item);
        totalCost += item.getItemPrice();
    }

    public void Remove_Item(Item item) {
        items.remove(item);
    }
    public void Print() {
        System.out.println("Name: " + customerName);
        for (int i = 0; i < items.size(); i++) {
            Item current_item = items.get(i);
            System.out.println("Item #" + (i+1));
            System.out.print("\t\t" + current_item.getQuantity());
            System.out.print("\t" + String.format("%24s", current_item.getItemName()));
            System.out.println("\t" + current_item.getItemPrice());
        }
        System.out.println("Total: " + totalCost);
    }
}