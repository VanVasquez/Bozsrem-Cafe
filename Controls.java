import java.util.Scanner;

abstract class Controls {
    private static final Scanner scn = new Scanner(System.in);
    public abstract void Run();
    public abstract void Close();

    public String getName() {
        System.out.print("Enter Name: ");
        return scn.nextLine();
    }
    public String itemNames(String id) {
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
}
