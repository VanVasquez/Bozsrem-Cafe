/*
    Documentations:
        class Item -> this class is responsible for all data about items, each Item has name, quantity, and price;
        Name is the name of items given to easily access 'getPrizes' method, and name is responsible for naming items.
        Quantity is current number of item have.
        Price is the amount of items, this can be done by calling 'getPrizes' method multiplied by the Quantity, means it cannot be edited by anyone.
 */

public class Item {
    private final String itemName;
    private int quantity = 1;
    private final double itemPrice;

    //"Polymorphism"
    Item(String itemName) {
        this.itemName = itemName;
        this.itemPrice = getPrizes(itemName) * quantity;
    }
     Item(String itemName, int quantity) {
         this.itemName = itemName;
         this.quantity = quantity;
         this.itemPrice = getPrizes(itemName) * quantity;
     }
    //This method returns the string itemName
     public String getItemName() {
         return itemName;
     }
    //This method returns the int quantity
     public int getQuantity() {
         return quantity;
     }
    //This method returns the double itemPrice
     public double getItemPrice() {
         return itemPrice;
     }
     //This method returns the value of items, it has parameters of String itemName to determine their prizes. if the inputted name is undefined, return -1
    private double getPrizes(String itemName) {
        return switch (itemName) {
            case "Honey Glazed Chicken", "Adobo", "Sweet and Spicy" -> 100.00;
            case "Burger", "Hotdog with Bun", "Fries" -> 25.00;
            case "Footlong", "Coke", "Coke Zero", "Royal" -> 40;
            case "Nachos", "Iced Coffee" -> 30;
            case "Hot Coffee" -> 20;
            case "Bottled Water" -> 15.00;
            default -> -1;
        };
    }
}