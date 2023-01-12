import java.util.ArrayList;
import java.util.List;

public class Reports {
    public void Save_Report (Order order){}
}

class Sales extends Reports{
    private List<Order> orders;
    Sales() {
        orders = new ArrayList<>();
    }
    public void Save_Report (Order order){
        System.out.println("\nSys: Order successfully saved\n");
        orders.add(order);
    }

    public void View_Sales() {
        for(Order order : orders) {
            order.Print();
        }
    }
}

class Stocks extends Reports {
    private List<Item> stocks;
    Stocks() {
        stocks = new ArrayList<>();
    }
    public void Save_Report (Item item){
        stocks.add(item);
    }

    public void View_Stocks() {
        for(Item item : stocks) {
            System.out.println("Name: " + item.getItemName());
            System.out.println("Qty: " + item.getQuantity());
        }
    }
}