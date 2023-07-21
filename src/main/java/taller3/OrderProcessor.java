package taller3;

public class OrderProcessor {
    public static final int MAX_ITEMS = 100;
    public static final int DISCOUNT_THRESHOLD_1 = 5;
    public static final int DISCOUNT_THRESHOLD_2 = 10;
    public static final double DISCOUNT_AMOUNT_1 = 0.9; // 10% discount
    public static final double DISCOUNT_AMOUNT_2 = 0.8; // 20% discount
    public static final double DISCOUNT_AMOUNT_3 = 0.9; // $10 discount
    public static final double DISCOUNT_AMOUNT_4 = 0.75; // $25 discount
    public static final double DISCOUNT_THRESHOLD_AMOUNT_1 = 50;
    public static final double DISCOUNT_THRESHOLD_AMOUNT_2 = 100;

    public static double processOrder(Order order, Menu menu) {
        double totalCost = 0.0;

        for (String itemName : order.getItems().keySet()) {
            MenuItem menuItem = menu.getItem(itemName);
            int quantity = order.getItems().get(itemName);
            totalCost += menuItem.getPrice() * quantity;
        }

        int totalItems = order.getTotalItems();

        if (totalItems == 0) {
            return 0.0;
        }

        if (totalItems > MAX_ITEMS) {
            return -1.0; // Error: Order exceeds the maximum number of items
        }

        if (totalItems > DISCOUNT_THRESHOLD_2) {
            totalCost *= DISCOUNT_AMOUNT_2;
        } else if (totalItems > DISCOUNT_THRESHOLD_1) {
            totalCost *= DISCOUNT_AMOUNT_1;
        }

        if (totalCost > DISCOUNT_THRESHOLD_AMOUNT_2) {
            totalCost -= DISCOUNT_AMOUNT_4;
        } else if (totalCost > DISCOUNT_THRESHOLD_AMOUNT_1) {
            totalCost -= DISCOUNT_AMOUNT_3;
        }

        return totalCost;
    }
}
