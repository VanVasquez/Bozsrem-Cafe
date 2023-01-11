public class Main {
    public static void main(String[] args) {
        //Examples

        /*
        //view Inventory, not yet connected to order, but can be updated
        Inventory inventory = new Inventory();
        //View method will print out all items
        inventory.View();
        //Update function will change quantity of items,
        inventory.Update_Order(new Item("Adobo", 2));
        //After inputting qty of 2, it will decrease the original value of adobo to 8
        inventory.View();
         */


        //Ordering
        /*
        Order order = new Order("Pedro");
        //There are 2 methods for ordering
        //1
        Item item = new Item("Hot Coffee", 2);
        order.Add_Item(item);
        order.Print();
        //2
        order.Add_Item(new Item("Iced Coffee", 1));
        order.Print();
         */

        Order order = new Order("Manny");
        Item item1 = new Item(Item_Names(1), 2);
        Item item2 = new Item(Item_Names(2), 1);
        Item item3 = new Item(Item_Names(3), 1);
        Item item4 = new Item(Item_Names(4), 1);
        Item item5 = new Item(Item_Names(5), 1);
        Item item6 = new Item(Item_Names(6), 1);
        Item item7 = new Item(Item_Names(7), 1);
        Item item8 = new Item(Item_Names(8), 1);
        Item item9 = new Item(Item_Names(9), 1);
        Item item10 = new Item(Item_Names(10), 1);
        Item item11 = new Item(Item_Names(11), 1);
        Item item12 = new Item(Item_Names(12), 1);
        Item item13 = new Item(Item_Names(13), 1);
        Item item14 = new Item(Item_Names(14), 1);
        order.Add_Item(item1);
        order.Add_Item(item2);
        order.Add_Item(item3);
        order.Add_Item(item4);
        order.Add_Item(item5);
        order.Add_Item(item6);
        order.Add_Item(item7);
        order.Add_Item(item8);
        order.Add_Item(item9);
        order.Add_Item(item10);
        order.Add_Item(item11);
        order.Add_Item(item12);
        order.Add_Item(item13);
        order.Add_Item(item14);
        order.Print();
    }

    private static String Item_Names(int num) {
        return switch (num) {
            case 1 -> "Honey Glazed Chicken";
            case 2 -> "Adobo";
            case 3 -> "Sweet and Spicy";
            case 4 -> "Burger";
            case 5 -> "Hotdog with Bun";
            case 6 -> "Footlong";
            case 7 -> "Fries";
            case 8 -> "Nachos";
            case 9 -> "Bottled Water";
            case 10 -> "Coke";
            case 11 -> "Coke Zero";
            case 12 -> "Royal";
            case 13 -> "Hot Coffee";
            case 14 -> "Iced Coffee";
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }

}