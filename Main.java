import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.Run();
        while (app.is_running) {
            int main_menu = app.Main_Menu();
            switch (main_menu) {
                case 1 -> app.Order();
                case 2 -> System.out.println("Inventory");
                case 3 -> app.Sales_Report();
                case 4 -> app.Close();
            }
        }
    }
}
class Application extends Controls{
    private static final Scanner scn = new Scanner(System.in);
    private Order order;
    private Sales sales;
    private Inventory inventory;
    public boolean is_running;
    @Override
    public void Run() {
        inventory = new Inventory();
        sales = new Sales();
        System.out.println("sys: App is Running...\n");
        is_running = true;
    }
    @Override
    public void Close() {
        System.out.println("Are you sure you want to exit? ");
        System.out.println("Type Y / N");
        System.out.print("Type here: ");
        scn.nextLine();
        String choice = scn.nextLine();
        if (choice.toUpperCase().contains("Y")) {
            is_running = false;
            System.out.println("sys: application ended");
        }
    }
    public int Main_Menu() {
        System.out.println("===========================================");
        System.out.println(" Bozsrem Cafe");
        System.out.println(" Choose: ");
        System.out.println("    1. Create Order");
        System.out.println("    2. Check Inventory");
        System.out.println("    3. Check Reports");
        System.out.println("    4. Exit");
        System.out.println("===========================================");
        System.out.print("Type Here: ");
        return scn.nextInt();
    }
    public void Order() {
        String customer_name = getName();
        viewMenu();
        order = new Order(customer_name);
        scn.nextLine();
        createOrder();
        while (checkQuantity()) {
            System.out.println("Change order / Cancel order");
            System.out.println("Type cancel or change");
            System.out.print("Type here: ");
            String next = scn.nextLine();
            if (next.toLowerCase().contains("cancel"))
                return;
            else if (next.toLowerCase().contains("change")) {
                System.out.println("Add new order [yes / no]");
                next = scn.nextLine();
                if (next.toLowerCase().contains("no"))
                    break;
                createOrder();
            }
        }
        order.Print();
        sales.Save_Report(order);
    }
    public void Sales_Report(){
        sales.View_Sales();
    }
    private void createOrder() {
        while (true) {
            System.out.println("Enter item id and quantity: (type close to end)");
            String items = scn.nextLine();
            if (items.contains("close"))
                break;
            String itemName = "";
            try {
                itemName = items.substring(0, 2).toUpperCase();
                int quantity = Integer.parseInt(items.substring(3));
                order.Add_Item(new Item(itemNames(itemName), quantity));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Wrong input : " + itemName + "\n Expected: ((Item id) (item quantity))");
            }
        }
    }
    private void viewMenu() {
        System.out.println("~~~~~~~~~~~~~~Choose Menu~~~~~~~~~~~~~~~~~~");
        System.out.println("Rice Meals:                           P 100");
        System.out.println(" R1-      Honey Glazed Chicken             ");
        System.out.println(" R2-      Adobo ");
        System.out.println(" R3-      Sweet and Spicy Fillet");
        System.out.println("Extra meals: ");
        System.out.println(" B1-      Burger                      P  25");
        System.out.println(" B2-      Hotdog with Bun             P  25");
        System.out.println(" B3-      Footlong                    P  40");
        System.out.println(" B4-      Fries                       P  25");
        System.out.println(" B5-      Nachos                      P  30");
        System.out.println("Beverages: ");
        System.out.println(" W1-      Bottled Water               P  15");
        System.out.println(" W2-      Coke                        P  40");
        System.out.println(" W3-      Coke Zero ");
        System.out.println(" W4-      Royal  ");
        System.out.println("Coffee: ");
        System.out.println(" C1-      Hot Coffee                  P  20");
        System.out.println(" C2-      Iced Coffee                 P  30");
    }
    private boolean checkQuantity() {
        Map<String, Item> inventoryItems = inventory.getInventory_items();
        List<Item> orderItems = order.getItems();
        for (Item item: orderItems) {
            Item current_inventory_item = inventoryItems.get(item.getItemName());
            if (current_inventory_item.getQuantity() < item.getQuantity()) {
                System.out.println("sys: Not enough " + current_inventory_item.getItemName() + ", \n" +
                        current_inventory_item.getQuantity() + " " + current_inventory_item.getItemName() + " left.");
                order.Remove_Item(item);
                return true;
            }
        }
        return false;
    }
}
