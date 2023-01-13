/*
    Description:
        class Controls -> this is an abstract class.
 */
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
            case "R3" -> "Sweet and Spicy Fillet";
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
    public String nameFormatter(String name) {
        switch (name) {
            case "honey glazed chicken" -> {
                return  "Honey Glazed Chicken";
            }
            case "adobo" -> {
                return "Adobo";
            }
            case "sweet and spicy fillet" ->
            {
                return "Sweet and Spicy Fillet";
            }
            case "burger" -> {
                return "Burger";
            }
            case "hotdog with bun" -> {
                return "Hotdog with Bun";
            }
            case "footlong" -> {
                return "Footlong";
            }
            case "fries" -> {
                return "Fries";
            }
            case "nachos" -> {
                return "Nachos";
            }
            case "bottled water" -> {
                return "Bottled Water";
            }
            case "coke" -> {
                return "Coke";
            }
            case "coke zero" -> {
                return "Coke Zero";
            }
            case "royal" -> {
                return "Royal";
            }
            case "hot coffee" -> {
                return "Hot Coffee";
            }
            case "iced coffee" -> {
                return "Iced Coffee";
            }
            default -> throw new IllegalStateException( name + " is not in inventory");
        }
    }
}
