import java.util.HashMap;
import java.util.Map;
public class Inventory {
    private final Map<String, Item> inventory_items;
    Inventory() {
        inventory_items = new HashMap<>();
        Add(new Item("Honey Glazed Chicken", 10));
        Add(new Item("Adobo", 10));
        Add(new Item("Sweet and Spicy Fillet", 10));
        Add(new Item("Burger", 10));
        Add(new Item("Hotdog with Bun", 10));
        Add(new Item("Footlong", 10));
        Add(new Item("Fries", 10));
        Add(new Item("Nachos", 10));
        Add(new Item("Bottled Water", 10));
        Add(new Item("Coke", 10));
        Add(new Item("Coke Zero", 10));
        Add(new Item("Royal", 10));
        Add(new Item("Hot Coffee", 10));
        Add(new Item("Iced Coffee", 10));
    }
    public void View() {
        System.out.println("INVENTORY");
        System.out.println("Qty:\t\tName: ");
        inventory_items.forEach((name, item) ->
                System.out.println("\t" + String.format("%04d",item.getQuantity()) + "\t\t\t" +item.getItemName()));
    }
    public void Update_Order(Item item) {
        int quantity = inventory_items.get(item.getItemName()).getQuantity() - item.getQuantity();
        Item newItem = new Item(item.getItemName(), quantity);
        inventory_items.replace(item.getItemName(), newItem);
    }
    public void Update_Stock(Item item) {
        int quantity = inventory_items.get(item.getItemName()).getQuantity() + item.getQuantity();
        Item newItem = new Item(item.getItemName(),quantity);
        inventory_items.replace(item.getItemName(), newItem);
        View();
    }
    public Map<String, Item> getInventory_items() {
        return inventory_items;
    }
    private void Add(Item item) {
        inventory_items.put(item.getItemName(), item);
    }
}