/*
    Documentations:
        class Order -> this class is responsible for saving data about orders, the Order object will save customer's name,
        list of items and its total cost. the order class have constructor with 3 variables, customer name, items, and total cost.
        Customer name, is a string that will be used to save customers name. It is not really necessary to add this.
        items, is a List array that collects a list of (class)Item. it will save the data of items that the customer picked.
        total cost, is a double, it will be used to save the total amount of items saved in the data.
 */
import java.util.ArrayList;
import java.util.List;
public class Order{
    private final String customerName;
    private final List<Item> items;
    private double totalCost;
    //Constructor.
    Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.totalCost = 0;
    }
    //This method will add the object item to List of items, then adds the price of its item and save it to totalCost.
    public void Add_Item(Item item) {
        items.add(item);
        totalCost += item.getItemPrice();
    }
    //This method will return the list of items when called.
    public List<Item> getItems() {
        return items;
    }
    //This method will remove the item, and also subtracts the price of item to total cost
    public void Remove_Item(Item item) {
        items.remove(item);
        totalCost -= item.getItemPrice();
    }
    //This method will print all data about the object.
    public void Print() {
        System.out.println("===========================================");
        System.out.println("Name: " + customerName);
        for (int i = 0; i < items.size(); i++) {
            Item current_item = items.get(i);
            System.out.println("Item #" + (i+1));
            System.out.print("\t\t" + current_item.getQuantity());
            System.out.print("\t" + String.format("%24s", current_item.getItemName()));
            System.out.println("\t" + current_item.getItemPrice());
        }
        System.out.println("Total: " + totalCost);
        System.out.println("===========================================");
    }
}