import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();

        application.Start();

        while(application.is_running) {
            int choice = application.Main_Menu();
            switch (choice) {
                case 1 -> {
                    application.Get_Order();
                }
                case 2 -> System.out.println("Inventory");
                case 3 -> {
                    application.viewSales();
                }
                case 4 -> application.Close();
                default -> throw new IllegalStateException("Unexpected Input: " + choice);
            }
        }


    }

}

class Application {
    private static final Scanner scn = new Scanner(System.in);
    private Inventory inventory;
    private Order order;
    private Sales sales;
    public boolean is_running = true;
    public void Start() {
        inventory = new Inventory();
        sales = new Sales();
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
        System.out.println("Type Here: ");
        return scn.nextInt();
    }
    public void View_Menu() {
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
        System.out.println(" W3-      Coke zero ");
        System.out.println(" W4-      Royal  ");
        System.out.println("Coffee: ");
        System.out.println(" C1-      Hot Coffee                  P  20");
        System.out.println(" C2-      Iced Coffee                 P  30");
    }

    public void Get_Order() {
        System.out.println("Enter customer name: ");
        scn.nextLine();
        String customerName = scn.nextLine();
        View_Menu();
        order = new Order(customerName);
        System.out.println("Add order: ");
        createNewOrder();
        while (!checkQuantity()) {
            System.out.println("Change order / Cancel order");
            System.out.println("Type change / cancel");
            String next = scn.nextLine();
            if (next.toLowerCase().contains("change")) {
                createNewOrder();
            }
            else return;
        }

        while (true) {
            System.out.println("Add more items: ");
            System.out.println("Type y / n");
            String add = scn.nextLine();
            if (add.toLowerCase().contains("n"))
                break;
            createNewOrder();
        }
        order.Print();
        sales.Save_Report(order);
    }

    private void createNewOrder() {
        while (true) {
            System.out.println("Enter item id and quantity: (type close to end)");
            String items = scn.nextLine();
            if (items.contains("close"))
                break;
        String itemName = items.substring(0, 2).toUpperCase();
        int quantity = Integer.parseInt(items.substring(3));

        order.Add_Item(new Item(Item_Names(itemName), quantity));
        }
    }
    public void Close() {
        System.out.println("Are you sure you want to exit? ");
        System.out.println("Type Y / N");
        System.out.print("Type here: ");
        scn.nextLine();
        String choice = scn.nextLine();
        if (choice.toUpperCase().contains("Y"))
            System.exit(0);
    }
    private String Item_Names(String id) {
        return switch (id) {
            case "R1" -> "Honey Glazed Chicken";
            case "R2" -> "Adobo";
            case "R3" -> "Sweet and Spicy";
            case "B1" -> "Burger";
            case "B2" -> "Hotdog with Bun";
            case "B3" -> "Footlong";
            case "B4" -> "Fries";
            case "B5" -> "Nachos";
            case "W1" -> "Bottled Water";
            case "W2" -> "Coke";
            case "W3" -> "Coke Zero";
            case "W4" -> "Royal";
            case "C1" -> "Hot Coffee";
            case "C2" -> "Iced Coffee";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    private boolean checkQuantity() {
        Map<String, Item> inventoryItems = inventory.getInventory_items();
        List<Item> orderItems = order.getItems();

        for (Item item: orderItems) {
            Item currentItem = inventoryItems.get(item.getItemName());
            if (currentItem.getQuantity() < item.getQuantity()) {
                System.out.println("Not enough " + currentItem.getItemName() + ", please create new order. ");
                System.out.println(currentItem.getQuantity() + " left");
                order.Remove_Item(item);
                return false;
            }
        }
        return true;
    }
    public void viewSales() {
        sales.View_Sales();
    }
}