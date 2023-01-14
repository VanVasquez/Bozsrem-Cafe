/*
    Documentations:
        class Order -> this class is responsible for saving data about orders, the Order object will save customer's name,
        list of items and its total cost. the order class have constructor with 3 variables, customer name, items, and total cost.
        Customer name, is a string that will be used to save customers name. It is not really necessary to add this.
        items, is a List array that collects a list of (class)Item. it will save the data of items that the customer picked.
        total cost, is a double, it will be used to save the total amount of items saved in the data.
 */


import java.util.HashMap;
import java.util.Map;

public class Order{
    private final String customerName;
    private Map<String, Item> items;
    private double totalCost;
    //Constructor.
    Order(String customerName) {
        this.customerName = customerName;
        this.items = new HashMap<>();
        this.totalCost = 0;
    }
    //This method will add the object item to List of items, then adds the price of its item and save it to totalCost.
    public void Add_Item(Item item) {
        if (items.containsKey(item.getItemName())) {
            int newQuantity = items.get(item.getItemName()).getQuantity() + item.getQuantity();
            items.replace(item.getItemName(), new Item(item.getItemName(), newQuantity));
        } else {
            items.put(item.getItemName(), item);
        }
        totalCost += item.getItemPrice();
    }
    public void Remove_Item(Item item) {
        items.remove(item.getItemName(), item);
    }
    //This method will return the list of items when called.
    public Map<String, Item> getItems() {
        return items;
    }
    //This method will print all data about the object.
    public void Print() {
        System.out.println("===========================================");
        System.out.println("Name: " + customerName);
        items.forEach((name, item) -> {
            int i = 1;
            System.out.println("Item #" + i);
            System.out.print("\t\t" + item.getQuantity());
            System.out.print("\t" + String.format("%24s", item.getItemName()));
            System.out.println("\t" + item.getItemPrice());
        });
        System.out.println("Total: " + totalCost);
        System.out.println("===========================================");
    }
}