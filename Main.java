
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
                case 2 -> app.Inventory();
                case 3 -> app.Report();
                case 4 -> app.Close();
            }
        }
    }
}
class Application extends Controls{
    private static final Scanner scn = new Scanner(System.in);
    private Order order;
    private Sales sales;
    private Item item;
    private Stocks stocks;
    private Inventory inventory;
    public boolean is_running;
    @Override
    public void Run() {
        inventory = new Inventory();
        sales = new Sales();
        stocks = new Stocks();
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
        while (true) {
            createOrder();
            System.out.println("Add new order [yes / no]");
            String next = scn.nextLine();
            if (!next.toLowerCase().contains("yes")) break;
        }
        if (order.getItems().isEmpty())
            return;
        order.Print();
        sales.Save_Report(order);
    }
    public void Report() {
        System.out.println("===========================================");
        System.out.println(" Reports: ");
        System.out.println(" [1] View end of the day sales.");
        System.out.println(" [2] Inventory reports.");
        System.out.println(" [0] Back to main menu.");
        System.out.println("===========================================");
        System.out.print(" Type here: ");
        int choice = scn.nextInt();
        switch (choice) {
            case 0 -> {}
            case 1 -> Sales_Report();
            case 2 -> Stocks_Report();
            default -> System.out.println(choice + " is not in choices");
        }
    }
    public void Inventory() {
        int a = 0;
        while (true) {
            inventory.View();
            System.out.println("Select item name to update. (Type cancel to exit)");
            System.out.print("Type name of item: ");
            if (a == 0)
                scn.nextLine();
            a++;
            String itemName = scn.nextLine();
            itemName = itemName.trim();
            if (itemName.toLowerCase().contains("cancel"))
                return;
            System.out.print("Enter stocks to add: ");
            int itemQuantity = scn.nextInt();
            item = new Item(nameFormatter(itemName), itemQuantity);
            inventory.Update_Stock(item);
            stocks.Save_Report(item);
            System.out.println("Update another item: ");
            scn.nextLine();
            String next;
            do {
                System.out.print("type yes / no: ");
                next = scn.nextLine();
                if (next.toLowerCase().contains("no"))
                    return;
            } while (!next.toLowerCase().contains("yes"));
        }
    }
    private void Sales_Report() {
        if (sales.getOrders().isEmpty()) {
            System.out.println("No orders have been made");
            return;
        }
        sales.View_Sales();
    }
    private void Stocks_Report() {
        if (stocks.getStocks().isEmpty()) {
            System.out.println("No update made");
            return;
        }
        stocks.View_Stocks();
    }
    private void createOrder() {
        while (true) {
            System.out.println("Enter item id and quantity: ");
            String items = scn.nextLine();
            String itemName = " ";
            try {
                itemName = items.substring(0, 2).toUpperCase();
                int quantity = Integer.parseInt(items.substring(3));
                item = new Item(itemNames(itemName), quantity);
                if (checkQuantity(item)) {
                    order.Add_Item(item);
                    inventory.Update_Order(item);
                    return;
                }
                else {
                    System.out.println("Change order / Cancel order");
                    System.out.println("Type cancel or change");
                    System.out.print("Type here: ");
                    String next = scn.nextLine();
                    if (!next.toLowerCase().contains("change"))
                        break; 
                }
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
    private boolean checkQuantity(Item item) {
        Map<String, Item> inventoryItems = inventory.getInventory_items();
        Item current_inventory_item = inventoryItems.get(item.getItemName());
        if (current_inventory_item.getQuantity() < item.getQuantity()) {
            System.out.println("sys: Not enough " + current_inventory_item.getItemName() + ", " +
                    current_inventory_item.getQuantity() + " " + current_inventory_item.getItemName() + " left.");
            return false;
        }
        return true;
    }
}
