import java.util.ArrayList;
import java.util.List;
abstract class Reports {
    public abstract void Save_Report (Order order);
    public abstract void Save_Report(Item item);
}
class Sales extends Reports{
    private final List<Order> orders;
    Sales() {
        orders = new ArrayList<>();
    }
    public void Save_Report (Order order){
        System.out.println("\nSys: Order successfully saved\n");
        orders.add(order);
    }
    @Override
    public void Save_Report(Item item) {}
    public void View_Sales() {
        double earnings = 0;
        for(Order order : orders) {
            order.Print();
            earnings += order.getTotalCost();
        }
        System.out.println("Total Earnings: " + earnings);
    }
    public List<Order> getOrders() {
        return orders;
    }
}
class Stocks extends Reports {
    private final List<Item> stocks;
    Stocks() {
        stocks = new ArrayList<>();
    }
    @Override
    public void Save_Report(Order order) {}
    public void Save_Report (Item item){
        stocks.add(item);
    }
    public void View_Stocks() {
        for(Item item : stocks) {
            System.out.print("Qty: " + item.getQuantity());
            System.out.println(" Name: " + item.getItemName());
        }
    }
    public List<Item> getStocks() {
        return stocks;
    }
}